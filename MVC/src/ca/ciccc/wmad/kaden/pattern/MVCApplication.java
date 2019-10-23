package ca.ciccc.wmad.kaden.pattern;

import ca.ciccc.wmad.kaden.pattern.mvc.CLIController;
import ca.ciccc.wmad.kaden.pattern.mvc.CLIModel;
import ca.ciccc.wmad.kaden.pattern.mvc.CLIView;

public class MVCApplication {

    public static void main(String[] args) {
        CLIController controller = new CLIController();
        controller.setModel(new CLIModel());
        controller.setView(new CLIView());

        controller.storeArguments(args);
        controller.printAllArguments();
    }
}
