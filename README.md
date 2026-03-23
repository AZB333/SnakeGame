# Snake Game Group Project

    Names: Aleqzander Baker and Dalton Ford
    Java Version: Java 25

## Patterns used in this project:
Observer Pattern: Since snake is a game, it has a UI component along 
with the logic. To avoid tightly coupling these two ideas, the observer pattern 
will be implemented so the JFrame code observes the game logic and displays it.


Factory Pattern: Since the game mainly consists of different rectangles that follow similar behavior, 
the factory pattern can be used to decouple the creation of these game rectangles and their use.
The game can call upon a rectangle factory to create rectangles like apples and snake segments.


Singleton Pattern: The singleton pattern is utilized with an event bus, where different events
in the game are posted to the single instance event bus, that then updates the observers of the game.