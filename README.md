# Snake Game Group Project

    Names: Aleqzander Baker and Dalton Ford
    Java Version: Java 25

## Patterns used in this project:
### Observer Pattern: 
Since snake is a game, it has a UI component along with the logic. To avoid tightly coupling these 
two ideas, the observer pattern will be implemented so the JFrame code observes the game 
logic and displays it.


### Factory Pattern: 
Since the game mainly consists of different rectangles that follow similar behavior, 
the factory pattern can be used to decouple the creation of these game rectangles and their use.
The game can call upon a rectangle factory to create rectangles like apples and snake segments.


### Singleton Pattern: 
The singleton pattern is utilized with an event bus, where different events
in the game are posted to the single instance event bus, that then updates the observers of the game.


### Model View Controller (MVC) Pattern: 
Since this project uses JPanel and JFrame, it uses the MVC Pattern.
The view is the panel that is created by the GamePanel class and GameWindow class, which handle the 
painting logic. The controller is the input handling from the user, since
it takes the input and uses KeyListener and ActionListener to determine how to change the UI. 
The model is the state of the snake game, 
including the positions and states of the snake and apple. They hold data on the state of the game, and
do not have input handling or UI logic.