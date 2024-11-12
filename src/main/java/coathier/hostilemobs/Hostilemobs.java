package coathier.hostilemobs;

import coathier.hostilemobs.entity.Titan;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.util.Identifier;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class Hostilemobs implements ModInitializer {
    public static final String MOD_ID = "hostilemobs";

    public static final EntityType<Titan> TITAN = FabricEntityTypeBuilder.createMob()
      .entityFactory(Titan::new)
      .dimensions(new EntityDimensions(0.75f, 0.75f, true))
      .trackRangeChunks(8)
      .defaultAttributes(Titan::createTitanAttributes)
      .spawnGroup(SpawnGroup.MONSTER)
      .build();

	@Override
	public void onInitialize() {
    Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, "titan"), TITAN);
	}
}
