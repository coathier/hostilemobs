package coathier.hostilemobs;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.simpleRelays.AbstractShaftBlock;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class Util {
  public static int daysPassed(long time) {
    final long DAY_TICKS = 24000;
    final long TICKS_PASSED = time - time % DAY_TICKS;
    return (int)(TICKS_PASSED / DAY_TICKS);
  }

  public static boolean isRotatingBlock(WorldView world, BlockState blockState, BlockPos pos) {
    if (FabricLoader.getInstance().isModLoaded("create")) {
      if (blockState.isOf(AllBlocks.COGWHEEL.get())
          || blockState.isOf(AllBlocks.SHAFT.get())
          || blockState.isOf(AllBlocks.LARGE_COGWHEEL.get())
          || blockState.isOf(AllBlocks.CRUSHING_WHEEL.get())) {
        if (blockState.getBlock() instanceof AbstractShaftBlock shaft) {
            return shaft.getBlockEntity(world, pos).getSpeed() > 1;
        }
      }
    }
    return false;
  }
}
