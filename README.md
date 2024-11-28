# Hostile Mobs
This server side mod increases the hostility of mobs by making them detect "sounds" through walls such as open chests and lit furnaces.

Creeper will also blow up walls in order to get to and destroy these blocks, there's a long timer before they blow up and they make sounds in the mean while.

If the [Create](https://github.com/Fabricators-of-Create/Create) mod is loaded mobs will also detects spinning components and they will flock toward them.

Here's a list of the configuration options that can be found in the `hostilemobs.toml` file within the configuration directory.
| Name                 | Value  | Description                                                                                                                                                |
| -------------------- | ------ | ---------------------------------------------------------------------------------------------------------------------------------------------------------- |
| activeNthDay         | Number | A number 3 means that this behavior will only be active every 3rd day                                                                                      |
| triesBeforeBlowingUp | Number | Relevant for creepers, a try triggers everytime it tries finding a new path to a block, if it stays in the same area for N amount of times it will explode |
| detectionDistance    | Number | At what range can mobs "hear" blocks through walls                                                                                                         |
