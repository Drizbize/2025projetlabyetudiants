**Richard Danilchenko S3B**

## 02/09/2025 - Beginning
- Project cloning from gitlab
- Reading the general concept of the game and code
- Simplified diagram of the project
- Created Salle class
- Loading Salles to Etage method
- Complited method "estAdjacente" in Salle to check if salle is reachable from the current salle
- Created the list of etages in Labyrinthe class
- Edited class "Dessin" to draw labyrinth
## 03/09/2025 - Unit tests day
- Unit test of Salle et Etage
- Override method "add" for Etage to be sure that we add only valid salles
## 04/09/2025 - Sprite, Heros
- New abstract APersonage impliments IPersonnage
- New Hero class extends APersonnage, he has a **public** attribute "salleChoisi" to set the salle by user's input
- Added new ASprite and HeroSprite
- Keyboard handler to HeroSprite
- Added movements by keyboard
## 05/09/2025 - Monsters, bug fixes, exceptions
- fixed going upstairs and downstairs
- added new monster class
- random movements for monster
- added new IOException "ExceptionInvalidFile" for invalid loading etage files
- smooth movement for player and monters
- lighting around plyaer. Player can not see all the labyrinthe and monsters. He is limited with 7-8 blocks lighting
## 08/09/2025 - Bug fix
- bug fix lighting monsters
## 09/09/2025 - Labyrinthe graph
- new labyrinthe graph
- algorithm the shortest path
- drawing the shortest path
## 10/09/2025 - Dragon
- new dragon, he follows the hero with 50% chance to go foward him
## 11/09/2025
- all entites has speed attribute
- drawing objects within distance salle by player using graph shortest path
- changed dragon player follow to standart follow but his speed is 2 (much slower then player)
- player can remember rooms when he saw them. But not other monsters
- comments for almost all implimented functions
## 15/09/2025
- Diagram of "personnages" package
- Finishing the project