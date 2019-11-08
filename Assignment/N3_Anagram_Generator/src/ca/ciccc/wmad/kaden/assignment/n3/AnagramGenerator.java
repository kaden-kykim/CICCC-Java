package ca.ciccc.wmad.kaden.assignment.n3;

import ca.ciccc.wmad.kaden.assignment.n3.contract.AGContract;
import ca.ciccc.wmad.kaden.assignment.n3.view.AGConsoleView;
import ca.ciccc.wmad.kaden.assignment.n3.view.AGView;

import java.text.DecimalFormat;

public class AnagramGenerator {

    public static void main(String[] args) {
        AGContract.View view = (args.length > 0 && "gui".equals(args[0])) ? new AGView() : new AGConsoleView();
        view.start();
    }
}
