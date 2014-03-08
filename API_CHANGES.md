##Front end API changes

Our API had originally called for turtle movement methods to be passed directly to the parser, however, the addition of multiple turtles made that plan obsolete, and so our front-end API now includes the TurtleManager class to pick out the correct turtles and to feed the filtered turtles into the command manager. This did not create many new dependencies, as  both the list of turtles and the movement metods themselves are fed into the commands separately. 

We also had to add several color/image and properties attributes to our API, and had to include more methods than we had originally planned, in order to account for the various panels, buttons and viewers (variables and functions) than we had planned. We also had more turtle commands than expected, as there were signiicantly more commands to be implmented than we had expected.

##Back end API changes

Our old API was very basic and general, not accounting for any of the more complex features of language, such as variables and user-defined commands, or even loops. We had to add more public methods, whether it was for error checking or adding a necessary manager into a class. 

The unexpectedly large number of commands required a new organization for commands themselves. While we stuck with the AbstractCommand as the overarching umbrella, there were subclasses under that to handle the different kinds of commands available, generally organized by the number of parameters the command has. 

We also ultimately had to create multiple managers to handle the many features in Slogo, such as a language manager, a user command manager, and a variable manager. As explained before, we did not anticipate these more complex aspects of the language, so we failed to include them in our original design.

Main Layout
  * MainSlogo 
    * main(String[])

Model Layout
  * AbstractParser
    * boolean isVariable(String)
    * boolean isParameter(String)
    * double convertToDouble(String)
    * List<String> convertFromStringToList(String)
    * TextParser
      * void setLanguageManager(LanguageManager)
      * List<String> Parse(String s)
      * void setUserCommandManager(UserCommandManager)
      * boolean hasErrors(List<StringNode>)

  * AbstractCommand
    * void execute()
    * OneParameterOperationCommand
      * void setMagnitude(String)
      * void setVariableManager (VariableManager)
      * OneParameterTurtleCommand
        * void setTurtles(List<Turtle>)
        * void execute()
        * Forward 

  * CommandFactory
    * void setUserCommandManager(UserCommandManager)
    * void setTurtleManager(TurtleManager)
    * double runCommands(List<StringNode>, List<Turtle>)
    * void setVariableManager(VariableManager)
    * ForFactory
      * double runForLoopCommands(List<StringNode>, List<StringNode>, List<Turtle>)
    * LoopFactory
      * double runAutoLoopCommands(List<StringNode>, String, double, List<Turtle>)

  * SlogoModel
    * double receiveTextInput(String s)
    * void setLanguageManager(LanguageManager)
    * void setViewer(SlogoView)
    * void setTurtleManager(TurtleManager)
    * void setLanguage(String)
    * void setVariableManager(VariableManager)
    * void setUserCommandManager(UserCommandManager)
    * List<String> getHistory()
    * void updateHistory(String)

  * Managers
    * LanguageManager
      * StringNode translateNode(StringNode)
      * void setLanguage(String)
      * String getLanguage()
    * UserCommandManager
      * void createNewUserCommand(String, int, UserCommand) 
      * int getNumParameterCommand(String)
      * void removeUserCommand(String)
      * boolean hasUserCommand(String)
      * UserCommand getUserCommand(String)
      * Map<String, Integer> getCommandParameterMap()
      * Map<String, UserCommand> getCommandMap()
      * void setCommandMap(Map<String, UserCommand>)
    * VariableManager
      * boolean isVariable(String)
      * Map<String, Double> getVariableMap()
      * void setVariableMap(Map<String, Double>)
      * void setValueToVariable(String, double)
      * void removeVariable(String)
      * double getValueOfVariable(String)
      * void readFromFile(File)
      * String mapToString(String)

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
