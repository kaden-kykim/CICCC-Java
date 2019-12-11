public class App {

    private static Keyboard keyboard;

    // A Reference to our CommandLine object we created in CommandLine.java
    private static CommandLine cmdLine;

    // Set a default value to false will keep our program running
    private static boolean _shouldTerminate = false;

    // If true, we will run our tests
    private static boolean _shouldRunTests = false;

    public static void main(String[] args) {
        keyboard = new Keyboard();

        // create an 'instance' of CommandLine object with the name cmdLine
        cmdLine = new CommandLine(args);

        // set this depending on if -runTests is on the command line or not
        _shouldRunTests = cmdLine.doesArgumentExists("-runTests");

        if (_shouldRunTests) {
            Debug.log("Found -runTests in argument list: - will run test now");
            // lets run some tests
            Test.Test_Console_RunAll();
        }

        // this code will be run at least 'once' and if _shouldTerminate == false, then it will continue to loop
        do {
            Console.print("Cmd(Math/Quit/Test/TestMath) > ");
            String cmd = keyboard.nextLine();

            switch (cmd.toLowerCase()) {
                case "quit":
                    _shouldTerminate = true;
                    break;
                case "test":
                    Test.Test_Console_RunAll();
                    break;
                case "testmath":
                    Test.Test_MathRunAll();
                    break;
//                case "math":
//                    Console.print("Cmd(Add/Sub/Mul/Div) > ");
//                    String operator = keyboard.nextLine();
            }
        } while (!_shouldTerminate);
    }
}
