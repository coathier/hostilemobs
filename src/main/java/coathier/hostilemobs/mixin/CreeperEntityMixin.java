package coathier.hostilemobs.mixin;

import coathier.hostilemobs.Hostilemobs;
import coathier.hostilemobs.entity.BlowUpBlockGoal;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity implements SkinOverlayOwner {
	public CreeperEntityMixin(EntityType<? extends CreeperEntity> type, World world) {
		super(type, world);
	}

	@Inject(at = @At("HEAD"), method = "initGoals()V")
	public void injectGoal(CallbackInfo ci) {
    this.goalSelector.add(5, new BlowUpBlockGoal(this, 1.0, Hostilemobs.config.detectionDistance, 10));
	}
}
