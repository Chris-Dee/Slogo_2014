Front end API changes-- Our API had originally called for turtle movement methods to be passed directly to the parser, however, the addition of multiple turtles made that plan obsolete, and so our front-end API now includes the TurtleManager class to pick out the correct turtles and to feed the filtered turtles into the command manager. This did not create many new dependencies, as  both the list of turtles and the movement metods themselves are fed into the commands separately. 
We also had to add several color/image and properties attributes to our API, and had to include more methods than we had originally planned, in order to account for the various panels, buttons and viewers (variables and functions) than we had planned. We also had more turtle commands than expected, as there were signiicantly more commands to be implmented than we had expected.

Back-end API:

Main Layout
  * MainSlogo 
    * main(String[])

Model Layout
  * AbstractParser
    * TextParser
      * CommandParser()
      * List<String> Parse(String s)

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
      * createNewUserCommand()

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

<br>Reasons For Change:
<br>Our old API does not work for the project very well. More specifically, we underestimate the difficulty and the size of our project. Thus, our API only works for part of the simple moves. We
