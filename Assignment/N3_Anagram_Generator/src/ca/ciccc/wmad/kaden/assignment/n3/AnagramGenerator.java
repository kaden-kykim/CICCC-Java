package ca.ciccc.wmad.kaden.assignment.n3;

import ca.ciccc.wmad.kaden.assignment.n3.contract.AGContract;
import ca.ciccc.wmad.kaden.assignment.n3.view.AGConsoleView;
import ca.ciccc.wmad.kaden.assignment.n3.view.AGView;

public class AnagramGenerator {

    private static final boolean USING_GUI = true;

    public static void main(String[] args) {
        AGContract.View view = (USING_GUI) ? new AGView() : new AGConsoleView();
        view.start();
    }
}
