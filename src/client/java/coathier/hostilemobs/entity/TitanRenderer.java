package coathier.hostilemobs.entity;

import coathier.hostilemobs.HostilemobsClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TitanRenderer extends MobEntityRenderer<Titan, TitanModel> {
 
    public TitanRenderer(EntityRendererFactory.Context context) {
        super(context, new TitanModel(context.getPart(HostilemobsClient.MODEL_TITAN_LAYER)), 0.5f);
    }
 
    @Override
    public Identifier getTexture(Titan entity) {
        return new Identifier("hostilemobs", "textures/entity/titan/cube.png");
    }
}
