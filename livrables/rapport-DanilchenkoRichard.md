**Richard Danilchenko S3B**

## 02/09/2025 - Beginning
- Project cloning from gitlab
- Reading the general concept of the game and code
- Simplified diagrams of the project
- Created Salle class
- Loading Salles to Etage with method "charger" 
- Completed method "estAdjacente" in class Salle to check if a salle is reachable from the current salle
- Created the list of etages in Labyrinthe class
- Edited class Dessin to draw labyrinth thanks to the completion of the method "dessinSalle"
## 03/09/2025 - Unit tests day
- Unit test of classes Salle et Etage. In salle class we checks if method estAdjacent is correct.
- Override method "add" for Etage to be sure that we add only valid salles
## 04/09/2025 - Sprite, Heros
- New abstract APersonage impliments IPersonnage
- New Hero class extends APersonnage, he has a **public** attribute "salleChoisie" to set the salle by user's input
- Added new ASprite that implements ISpite and HeroSprite that extends ASprite
- Keyboard handler to HeroSprite. With this util it checks which will be the next salle by the user input.
- Added movements by keyboard
## 05/09/2025 - Monsters, bug fixes, exceptions
- Fixed going upstairs and downstairs
- Added new monster class
- Random movements for monster
- Added new IOException "ExceptionInvalidFile" for invalid loading etage files
- Smooth movement for player and monters
- Lighting around player. Player can not see all the labyrinthe and monsters. He is limited with 7-8 blocks lighting
## 08/09/2025 - Bug fix
- Bug fix lighting monsters
## 09/09/2025 - Labyrinthe graph
- New labyrinthe graph extends original labyrinthe
- Implimented the shortest algorithm path
- Drawing the shortest path
## 10/09/2025 - Dragon
- New dragon, he follows the hero
## 11/09/2025
- All entites has speed attribute
- Drawing objects within distance salle by player using graph shortest path
- Changed dragon player follow to standart follow but his speed is 2 (much slower then player)
- Player can remember rooms when he saw them. But not other monsters
- Comments for almost all implimented functions
## 15/09/2025
- Diagram of "personnages" package
- Finishing the project
## 16/09/2025
- Drawing walls

## Difficulties and Solutions
1. "(ISalle)Collection.toArray()" blocks program forever, so it wasn't understandable why the game was frozen for no reason. With debug mode the problem found and rewrited to new ArrayList<>(Collection..);
2. ILabyrinthe is a Collection of ISalles. But I don't understand what it made for. Because in Labyrinthe class we can simply use arraylist of etages and etages themselves. So I don't think that it was necessary to use.
3. It was a bit hard to accept that IEtage is a collection of ISalle. It could be just a simple 2D array of salles. Because when we load an etage we know a fix size of etage. It could be more optimized and super fast for machine to get precised position by his coordinates. For example method "sallesAccessibles" with "estAdjacente". I wanted to change all the implemented code but I didn't do it.
4. Interfaces used everywhere, but somewhere needs a functionality that is hidden and we can not use it. We have to methods to interface.
5. Image transparency was a difficult task to do. There was some research but I found only "setGlobalAlpha()" that was a good choice.
6. Walls was not so easy. Because there was a lot of ways to implement it. For example while drawing walls, we check around him an empty space. But as we have a list of Salles in etage so it's complicated to check for each salle's position with for loop. So it was better to make a 2D array of booleans in Etage class. The position true means there is a wall, else no. I initialize array when we load an etage.
Also as I implemented this idea, some walls that are transparent lays on top of others.