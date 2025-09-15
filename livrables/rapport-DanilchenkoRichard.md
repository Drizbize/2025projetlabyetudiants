**Richard Danilchenko S3B**

## About the project
This project was quiet intresting. There was no such difficultes and it was a good experience.
I can say that it was a good idea of the game with labyrinth and I can note this project to my portfolio.
The reason why I can't say that this project was perfect, it's because of his optimization.
If we add some more elements to the game it starts to freezing. The solution is to rewrite "Etage" class and Labyrinth.
Also it could be better to move Sprite to personnages. So that personnages has directly the sprite and everything is together. 

## Difficultes
1. "(ISalle)Collection.toArray()" blocks program forever, so it wasn't understandable why game was just freezed with no reason. With debug mode the problem found.
2. ILabyrinthe is a Collection of ISalles. But I don't understand what it made for. Because in Labyrinthe class we can simply use arraylist of etages and etages themselves. So I don't think that it was necessary to use.
3. It was a bit hard to accept that IEtage is a collection of ISalle. It could be just a simple 2D array of salles. Because when we load an etage we know a fix size of etage. It could be more optimized and super fast for machine to get precised position by his coordinates. For example method "sallesAccessibles" with "estAdjacente". I wanted to change all the implimated code but I didn't do it.
4. Interfaces used everywhere, but somewhere needs a functionality that is hidden and we can not use it. We have to methods to interface.
5. Image transparency was a difficult task to do. There was some research but I found only "setGlobalAlpha()" that was a good choice.