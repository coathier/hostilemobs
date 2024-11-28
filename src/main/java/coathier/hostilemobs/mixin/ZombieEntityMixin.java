package coathier.hostilemobs.mixin;

import coathier.hostilemobs.Hostilemobs;
import coathier.hostilemobs.entity.MoveToBlockGoal;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends HostileEntity implements SkinOverlayOwner {
	public ZombieEntityMixin(EntityType<? extends ZombieEntity> type, World world) {
		super(type, world);
	}

	@Inject(at = @At("HEAD"), method = "initGoals()V")
	public void injectGoal(CallbackInfo ci) {
    this.goalSelector.add(4, new MoveToBlockGoal(this, 1.0, Hostilemobs.config.detectionDistance, 10));
	}
}
