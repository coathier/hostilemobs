package coathier.hostilemobs.entity;

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
    private static final int BLOW_UP_TIME = 40;
    protected int timer;

    private static final int SAME_POS_MAX_RESETS = 3;
    @Nullable
    protected Vec3d lastResetPosition;
    protected int resetsInSamePos;

    public BlowUpBlockGoal(PathAwareEntity mob, double speed, int range, int maxYDifference) {
        super(mob, speed, range, maxYDifference);
    }

    public int daysPassed(long time) {
        final long DAY_TICKS = 24000;
        final long TICKS_PASSED = time - time % DAY_TICKS;
        return (int)(TICKS_PASSED / DAY_TICKS);
    }

    @Override
    public boolean canStart() {
        // This goal is active on nights every 7 days.
        return //this.mob.getWorld().isNight() &&
            //daysPassed(this.mob.getWorld().getTime()) % 7 == 0 &&
            super.canStart();
    }

    @Override
    public boolean shouldResetPath() {
        boolean trying = this.tryingTime % 200 == 0;
        if (trying) {
            if (lastResetPosition != null && lastResetPosition.distanceTo(this.mob.getPos()) < 5.0D) {
                resetsInSamePos++;
                this.mob.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 1.0F);
            } else {
                resetsInSamePos = 0;
                lastResetPosition = this.mob.getPos();
            }
            if (resetsInSamePos >= SAME_POS_MAX_RESETS) {
                this.explode();
            }
        }

        return trying;
    }

    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isOf(Blocks.DIAMOND_BLOCK);
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
            this.mob.kill();
        }
    }

    public void start() {
        this.timer = 0;
        super.start();
    }
}