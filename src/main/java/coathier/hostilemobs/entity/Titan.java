package coathier.hostilemobs.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.attribute.EntityAttributes;

public class Titan extends HostileEntity {

  public Titan(EntityType<? extends HostileEntity> type, World world) {
    super(type, world);
  }

  public static DefaultAttributeContainer.Builder createTitanAttributes() {
    return MobEntity.createMobAttributes()
      .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
      .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3F)
      .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
  }

  @Override
  protected void initGoals() {
    this.goalSelector.add(1, new SwimGoal(this));
    this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0, false));
    this.goalSelector.add(3, new BlowUpBlockGoal(this, 1.0, 50, 10));
    this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
  }
}
