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
