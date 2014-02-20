Design Goals:
<br>In terms of modules, the back-end of our program would consist of a text parser for the commands input, a module to run the commands, an interface between the front-end and back-end,  and either an abstract class hierarchy with certain methods implemented and others not, to be able to extensibly implement all instructions that can be input.
<br>On the front-end, we would have a text input for the commands, and a module to represent the turtle, which would need to consist of a physical turtle (potentially) and the trail that the turtle leaves behind as it moves.  We could also potentially have a run button, so that multiple lines could be input and then run. This would allow for looping and more complex designs. We may also have a help button that serves as a tutorial and shows the user some sample instructions. 
<br>Our initial design calls for flexibility in adding, removing and editing existing commands, and makes it easy to change the recognized command set. We also thought it would be useful to have flexibility in which source to read input from (i.e. make it easy to add a file parser, or a multi-line parser). Moreover, we may seek flexibility in adding more buttons/functions in order to give the user more power, such as setting up some default values. 

<br>Alternatives:
<br>As an alternative to our design, we were considering using an interface for our different command sets. We chose to do this with an abstract class hierarchy instead, as it would allow us to define these certain methods for each different type of command.  We could also define some of the methods as “do-nothing” methods in order to make only certain commands move, for example.
We also could have had the Turtle class perform all of the necessary position calculations and command parsing. We decided against this to break up the work between classes and to make the turtle class as short as possible. This way, the turtle class’ only externally called method would be draw().

<br>

Module Layout
  * AbstractParser
    * CommandParser
      * CommandParser()
      * List<String> Parse(String s)
  * ICommand
    * void execute()
    * String getCommandType()
    * subclasses:
      * FdCommand
        * FdCommand(int dist)
        * void execute()
        * String getCommandType()
  * CommandFactory
    * CommandFactory()
    * makeCommands(List<String> cmds)
  * ModelManager
    * ModelManager()
    * String receiveTextInput(TextInput txt)


<br>Example Code:
<br>User types fd 50
<br>Command c = Parser.parseText(string s, Turtle t);  //creates command from factory
	<br>c.moveTurtle();
		<br>t.draw(double newX, double newY); 
<br>Explanation:
<br>The Module Command is responsible for reading in the parsed user instruction and calls the functions relevant to the instruction.
The class Turtle has an instance variable that remembers its last position (x, y). Once t.draw(double newX, double newY) is called, the turtle t will move to the new position (newX, newY) and leaves a trail between the point (x, y) and the point (newX, newY). 

<br>List of each team member's role in the project:
<br>
<br>Front-end Team: Chris Dee, Oscar Wang 
<br>Both working on turtle module and rest of front end. Projects haven’t been broken up.
<br>
<br>Back-end Team: Justin Zhang, Dan Zhang
<br>Justin Zhang: ICommand, ModelManager
<br>Dan Zhang: AbstractParser, CommandFactory 

<br>See image attached for the UML diagram and our intended view interface

