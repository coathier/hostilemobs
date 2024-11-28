package coathier.hostilemobs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name="scalingmobs")
public class HostileMobsConfig implements ConfigData {
  public int activeNthDay = 1;

  // Basically every try is 10 seconds appart, every try it tries to find a new path to the block, if it's within the same area in N tries it will blow up.
  public int triesBeforeBlowingUp = 5;
  public int detectionDistance = 50;
}

