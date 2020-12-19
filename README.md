# AntiAntiXray
For the annoying server owners that add an anti xray plugin.

## Notice
For every plugin dev reading this, you basically cant fix this unless you want everyone on the server to gain riches off fake blocks that suddently become real ;)

## Usage
To scan nearby blocks in selected radius, press `G` ingame.

To remove a block for testing, creating a ghost block, press `V` ingame.

## Installing
1. Download the newest version from build/libs (or click [here](https://github.com/AriliusClient/AntiAntiXray/raw/master/build/libs/AntiAntiXray-1.0.jar))
2. Put it into your mods folder in your fabric installation
3. Enjoy

## Commands
The client has several commands. Prefix is `@aax`. Commands:

### SetRadius
Sets scanning radius (Default: 5)
#### Parameters
1. New radius (int)

#### Example
`@aax setradius 10` - Sets radius to 10

### SetDelay
Sets delay between block packets (Default: 0)

! RECOMMENDED TO CHANGE !

#### Parameters
1. New delay (int)

#### Example
`@aax setdelay 10` - Sets delay to 10 (Recommended delay)

### Cancel
Cancels every refreshing job currently active
### Parameters
None
#### Example
`@aax cancel` - Cancels every job