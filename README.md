# WOMP Voogasalad
Duke CompSci 308 Game Authoring Engine Project
------

###WOMP: an authoring environment and game engine that allows users create and play their own Tower Defense games.

Sections: Authoring Environment, Game Engine, Game Player, Game Data

Date Started: March 24

Date Finished: May 1

Estimated Hours: 400 hours

Members:

* Andy Wang (ownzandy), Adam Tache (adamtache), Brian Lin (briguy52), David Yang (dy46), Cody Li (cl305), Paul Cruz (paulcruz74), Virginia Cheng (vcheng19), Alexander Tseng (xanderyst), Austin Hua (austinhua), Patrick Grady (hmpfa)

####Roles:

Authoring Environment:
* Brian (Briguy52) - Sub-team lead for first half, opening screen, general game settings and tabs, various screens, unit integration and front-end path and unit spawn integration, front-end waypoint-based pathing creation

* Cody (cl305) - Store, waves, level front-end

* Virginia (vcheng19) - Affector and Element Tabs

* Andy (ownzandy) - Sub-team lead for second half, Back-end integration of affectors, units, towers, and projectiles

* Adam (adamtache) - Designed back-end waypoint-based pathing system for search problems, map handling, helped integrate into path tab

* Paul (paulcruz74) - Back-end stores, waves, levels

* Patrick (hmpfa) - Store front-end, general back-end

* Xander (xanderyst) - MapEditor (Terrain Placements), Integration of Map with Paths

Game Engine:

* Andy (ownzandy) - Towers and projectiles, general affectors, special homing and cursor shooting affectors, non-AI path following, general units, range, levels, scores, and waves integration

* Adam (adamtache) - Sub-team lead, AI extension for autonomous path-following units guided by tower obstacles, paths and branches, general unit movement, tower placement validation with search on visibility graphs, simulated obstacle placement and path following, functions, general affectors, encapsulation handling and detection

* Paul (paulcruz74) - Back-end store and upgrades, collision handling and detection

Game Player:

* David (dy46) - Co-subteam lead, General configurations, tower displayer, HUD, all front-end

* Andy (ownzandy) - Co-subteam lead, Playing of the game

Game Data:

* Austin (austinhua) - Sub-team lead

Utility:

* Paul (paulcruz74) - Subteam lead, Box Cloud storage and Twilio

* Andy (ownzandy) - Setting up API/JARs

* Brian (briguy52) - Integration in authoring environment

### Other Information

* Starting Class: Main.java

* There are many resource files required by the project. These resource files can be found in a package within each of the respective parts of the project. The authoring environment has its resource files within src/auth_environment/properties. The game player has its resource files within src/game_player/resources. The data, such as images and music, needed to run the game is found in the game_images file. 

* The program is relatively self explanatory. Once the program is run, the main screen will pop up. If the user wants to create a new game, they will click build a game, and they will configure all of the settings of their game through the authoring environment. They will have to create the game's enemies, towers, paths, affectors, levels, and waves. Once all of these elements are created successfully, the user can save their game to an XML file to be played in the game player. They can press Play a Game to load in the XML file and the player will appear. The game will being immediately, and there are preferences on the side that they can configure if they desire.

* AI Extension: To construct an AI enemy, add an AIPathFollowAffector to the enemy while authoring, and add the enemy to a spawn point in the level. It is not necessary to configure individual branches or the goal for AI enemies.
