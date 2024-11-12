package coathier.hostilemobs;

import coathier.hostilemobs.entity.TitanModel;
import coathier.hostilemobs.entity.TitanRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class HostilemobsClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_TITAN_LAYER = new EntityModelLayer(new Identifier("hostilemobs", "titan"), "main");

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(Hostilemobs.TITAN, TitanRenderer::new);

		EntityModelLayerRegistry.registerModelLayer(MODEL_TITAN_LAYER, TitanModel::getTexturedModelData);
	}
}
