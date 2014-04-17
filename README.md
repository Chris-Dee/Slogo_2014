<<<<<<< HEAD
Design Goals:
<br>In terms of modules, the back-end of our program would consist of a text parser for the commands input, a module to run the commands, an interface between the front-end and back-end,  and either an abstract class hierarchy with certain methods implemented and others not, to be able to extensibly implement all instructions that can be input.
<br>On the front-end, we would have a text input for the commands, and a module to represent the turtle, which would need to consist of a physical turtle (potentially) and the trail that the turtle leaves behind as it moves.  We could also potentially have a run button, so that multiple lines could be input and then run. This would allow for looping and more complex designs. We may also have a help button that serves as a tutorial and shows the user some sample instructions. 
<br>Our initial design calls for flexibility in adding, removing and editing existing commands, and makes it easy to change the recognized command set. We also thought it would be useful to have flexibility in which source to read input from (i.e. make it easy to add a file parser, or a multi-line parser). Moreover, we may seek flexibility in adding more buttons/functions in order to give the user more power, such as setting up some default values. 

<br>Alternatives:
<br>As an alternative to our design, we were considering using an interface for our different command sets. We chose to do this with an abstract class hierarchy instead, as it would allow us to define these certain methods for each different type of command.  We could also define some of the methods as “do-nothing” methods in order to make only certain commands move, for example.
We also could have had the Turtle class perform all of the necessary position calculations and command parsing. We decided against this to break up the work between classes and to make the turtle class as short as possible. This way, the turtle class’ only externally called method would be draw().

<br>

Model Layout
  * AbstractParser
    * CommandParser
      * CommandParser()
      * List<String> Parse(String s)
  * ICommand
    * void execute()
    * String getCommandType()
    * subclasses:
      * FdCommand
        * FdCommand(int dist, List<Turtle> turtles)
        * void execute()
        * String getCommandType()
  * CommandFactory
    * CommandFactory()
    * makeCommands(List<String> cmds)
  * ModelManager
    * ModelManager()
    * String receiveTextInput(TextInput txt)

View Layout
  * Text Layout //Will be the Text Input file
     * sendCode(ParserObject)
     * clearCode()
     * setPreferences(Preferences p)
  * AbstractTurtle //assuming we use JGame, this will extend JGObject
    *AbstractTurtle()
    * doFrame() //assuming JGame
    * paintFrame() //assuming Jgame
    * clearLines()
    * setPreferences(Preferences p)
    * goToNewPosition(double x, double y, boolean drawTrail)
    * subclasses:
      * Turtle
        * Turtle()
        * paintFrame()


<br>Example Code:

The user types into the TextInput: Fd 50

1. ModelManager.receiveTextInput(TextInput text) //the view passes in a TextInput object to the model manager, returns string, "Fd 50"
2. myCommandParser.parseText(String text) //the manager parses the text into a list of strings representing commands ("Fd 50")
3. myCommandFactory.makeCommands(List<String> commandStrings) //the manager passes the list of commands into the factory, returning a list of commands, Fd 50 in this case. 
4. private void runCommands(List<String> commands) //the manager executes the commands (command.execute()), which will affect the view. 
5. commands.get(0).execute() //Execute the first command in the list (there is only FdCommand right now), command has instance(s) of turtle(s)
6. turtle.paintFrame(double newX, double newY);

Explanation:

The view will have an instance of the ModelManager, which will serve as the bridge between the front end and the back end. The front end will pass whatever is in the TextInput as an object into the manager, which receives the input and stores it as an instance variable. It then uses this to parse, and then create commands from the parsing result. The commands (possible multiple lines of code) for each text input will be stored in a list for the ModelManager, which will then run down the list of commands and execute them. In the case of fd 50, the class Turtle has an instance variable that remembers its last position (x, y). Suppose we initialize a Turtle t at the beginning of the program. Once t.goToNewPosition(double newX, double newY) is called, the turtle t will move to the new position (newX, newY) and leaves a trail between the point (x, y) and the point (newX, newY). In order to do so, if we use a JGame, while the ModelManager goes through the list of commands, which currenlty has only one command "fd 50", the execute() method within the class FdCommand of the command will call the goToNewPosition(double newX, double newY) method and the doFrame() method of its list of turtles (which currenlty has only one turtle). The viewer then would be able to use the turtle moving in the GUI view interface. 

<br>List of each team member's role in the project:
<br>
<br>Front-end Team: Chris Dee, Oscar Wang 
<br>Both working on turtle module and rest of front end. Projects haven’t been broken up.
<br>
<br>Back-end Team: Justin Zhang, Dan Zhang
<br>Justin Zhang: ICommand, CommandFactory, ModelManager
<br>Dan Zhang: AbstractParser, ICommand

<br>See image attached for the UML diagram and our intended view interface

=======


Empty repository for SLogo project

Slogo_2014

>>>>>>> 826000fdb42448598f1781fcccc5fe3c9791a257
