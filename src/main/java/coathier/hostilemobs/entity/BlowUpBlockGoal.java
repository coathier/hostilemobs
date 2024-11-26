package coathier.hostilemobs.entity;

import me.shedaniel.autoconfig.AutoConfig;

import coathier.hostilemobs.HostileMobsConfig;
import coathier.hostilemobs.Util;
import net.minecraft.block.*;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class BlowUpBlockGoal extends MoveToTargetPosGoal {
    private static final int BLOW_UP_TIME = 60;
    protected int timer;

    // private static final int SAME_POS_MAX_RESETS = 5;

    @Nullable
    protected Vec3d lastResetPosition;
    protected int resetsInSamePos;

    private static final HostileMobsConfig config = AutoConfig.getConfigHolder(HostileMobsConfig.class).getConfig();

    public BlowUpBlockGoal(PathAwareEntity mob, double speed, int range, int maxYDifference) {
        super(mob, speed, range, maxYDifference);
    }

    @Override
    public boolean canStart() {
       long daysPassed = Util.daysPassed(this.mob.getWorld().getTimeOfDay());
        return  daysPassed % config.activeNthDay == 0 &&
            super.canStart();
    }

    @Override
    public boolean shouldResetPath() {
        boolean trying = this.tryingTime % 200 == 0;
        if (trying) {
            if (lastResetPosition != null && lastResetPosition.distanceTo(this.mob.getPos()) < 5.0D) {
                resetsInSamePos++;
                // Change the pitch of the sound as it it closer to exploding.
                float intensity = 1.0F / (float)config.triesBeforeBlowingUp * (float)resetsInSamePos;
                this.mob.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, intensity + 0.5F, intensity);
            } else {
                resetsInSamePos = 0;
                lastResetPosition = this.mob.getPos();
            }
            if (resetsInSamePos >= config.triesBeforeBlowingUp) {
                this.explode();
            }
        }

        return trying;
    }

    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.isOf(Blocks.FURNACE)) {
            return blockState.get(FurnaceBlock.LIT);
        }
        return false;
    }

    public void tick() {

        if (this.hasReached()) {
            if (this.timer >= BLOW_UP_TIME) {
                this.explode();
            } else {
                ++this.timer;
            }
        }

        super.tick();
    }

    private void explode() {
        if (!this.mob.getWorld().isClient) {
            this.mob.getWorld().createExplosion(this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ(), 3.0f, World.ExplosionSourceType.MOB);
            // Killing the entity is probably not the right way to do it, if compared to creeper example. This could mean that it drops items etc.
            this.mob.discard();
        }
    }

    public void start() {
        this.timer = 0;
        this.resetsInSamePos = 0;
        this.lastResetPosition = null;
        super.start();
    }
}
