**Richard Danilchenko S3B**

## 02/09/2025 - Beginning
- Project cloning from gitlab
- Reading the general concept of the game and code
- Exercices 1-7 were done (Part 2)
## 03/09/2025 - Unit tests day
- Exercices 8-10 were done
- Released some corrections in the code
## 04/09/2025 - Sprite, Heros
- Exercises 11-16
- Added hero and movements
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

## Difficultes
1. "(ISalle)Collection.toArray()" blocks program forever, so it wasn't understandable why game was just freezed with no reason. With debug mode the problem found.
2. ILabyrinthe is a Collection of ISalles. But I don't understand what it made for. Because in Labyrinthe class we can simply use arraylist of etages and etages themselves. So I don't think that it was necessary to use.
3. It was a bit hard to accept that IEtage is a collection of ISalle. It could be just a simple 2D array of salles. Because when we load an etage we know a fix size of etage. It could be more optimized and super fast for machine to get precised position by his coordinates. For example method "sallesAccessibles" with "estAdjacente". I wanted to change all the implimated code but I didn't do it.
4. Interfaces used everywhere, but somewhere needs a functionality that is hidden and we can not use it. We have to methods to interface.
5. Image transparency was a difficult task to do. There was some research but I found only "setGlobalAlpha()" that was a good choice.