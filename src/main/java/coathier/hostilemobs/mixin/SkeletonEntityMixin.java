package coathier.hostilemobs.mixin;

import coathier.hostilemobs.entity.MoveToBlockGoal;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSkeletonEntity.class)
public abstract class SkeletonEntityMixin extends HostileEntity implements SkinOverlayOwner {
	public SkeletonEntityMixin(EntityType<? extends AbstractSkeletonEntity> type, World world) {
		super(type, world);
	}

	@Inject(at = @At("HEAD"), method = "initGoals()V")
	public void injectGoal(CallbackInfo ci) {
    this.goalSelector.add(3, new MoveToBlockGoal(this, 1.0, 50, 10));
	}
}
