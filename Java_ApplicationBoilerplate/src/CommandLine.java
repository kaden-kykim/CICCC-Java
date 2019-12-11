public class CommandLine {

    private String[] arguments = new String[0];

    // the constructor will be called with whatever arguments (if any) were known from the 'main' method
    public CommandLine(String[] arguments) {
        // initialize our internal argument array to the same size as the one passed to the constructor
        this.arguments = new String[arguments.length];

        // begin a loop and start copying arguments to this.arguments, one element at a time
        for (int i = 0; i < arguments.length; ++i) {
            this.arguments[i] = arguments[i];
        }
        System.out.println("I see arguments: Count(" + arguments.length + ")");
    }

    public boolean doesArgumentExists(String argument) {
        for (int i = 0; i < argument.length(); ++i) {
            if (this.arguments[i].contains(argument)) {
                return true;
            }
        }
        return false;
    }

    public int getArgumentIndex(String argument) {
        for (int i = 0; i < argument.length(); ++i) {
            if (this.arguments[i] == argument) {
                return i;
            }
        }
        return -1;
    }
}
