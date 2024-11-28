package coathier.hostilemobs;

import net.fabricmc.api.ModInitializer;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import me.shedaniel.autoconfig.AutoConfig;

public class Hostilemobs implements ModInitializer {
    public static final String MOD_ID = "hostilemobs";

    public static HostileMobsConfig config;

		@Override
		public void onInitialize() {
				AutoConfig.register(HostileMobsConfig.class, Toml4jConfigSerializer::new);

				Hostilemobs.config = AutoConfig.getConfigHolder(HostileMobsConfig.class).getConfig();
		}
}
