package coathier.hostilemobs.entity;

import coathier.hostilemobs.Hostilemobs;
import coathier.hostilemobs.Util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class MoveToBlockGoal extends MoveToTargetPosGoal {
    @Override
    public double getDesiredDistanceToTarget() {
        return 5.0;
    }

    @Override
    public boolean canStart() {
        long daysPassed = Util.daysPassed(this.mob.getWorld().getTimeOfDay());
        if (daysPassed == 0 && Hostilemobs.config.activeNthDay != 1) return false;
        return  daysPassed % Hostilemobs.config.activeNthDay == 0 &&
            super.canStart();
    }

    public MoveToBlockGoal(PathAwareEntity mob, double speed, int range, int maxYDifference) {
        super(mob, speed, range, maxYDifference);
    }

    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.isOf(Blocks.CHEST)) {
            return ChestBlockEntity.getPlayersLookingInChestCount(world, pos) > 0;
        } else if (blockState.isOf(Blocks.FURNACE)) {
            return (boolean)blockState.get(FurnaceBlock.LIT);
        } else {
            return Util.isRotatingBlock(world, blockState, pos);
        }
    }
}
