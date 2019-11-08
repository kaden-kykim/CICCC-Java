package ca.ciccc.wmad.kaden.assignment.n3.view;

import ca.ciccc.wmad.kaden.assignment.n3.contract.AGContract;
import ca.ciccc.wmad.kaden.assignment.n3.presenter.AGPresenter;

import java.util.Scanner;

public class AGConsoleView implements AGContract.View {

    private static final String FILL_LINE = "                                                                         ";

    private AGContract.Presenter presenter;
    private String inputString;
    private Scanner scanner;

    private long totalNumberOfCombination;
    private boolean isInterrupted = false;

    public AGConsoleView() {
        presenter = new AGPresenter(this);
    }

    @Override
    public void start() {
        scanner = new Scanner(System.in);
        while (!"q".equals(inputString)) {
            System.out.print("Input text to generate anagram['q' to exit]: ");
            inputString = scanner.nextLine();
            isInterrupted = false;
            if (inputString == null || "".equals(inputString)) {
                System.out.println("Wrong input. Please again.");
            } else if ("q".equals(inputString)) {
                break;
            } else {
                System.out.println("[Enter] to stop progress");
                Thread preTask = new Thread(() -> presenter.processPreTask(inputString));
                Thread task = new Thread(() -> presenter.processTask());
                preTask.start();
                task.start();

                scanner.nextLine();
                isInterrupted = true;

                preTask.interrupt();
                task.interrupt();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // Nothing to do, just be interrupted
                }
            }
        }
    }

    @Override
    public void setTaskProgress(long progress) {
        System.out.print(String.format("Progress: (%d / %d)\r", progress, totalNumberOfCombination));
    }

    @Override
    public void setStatusText(String statusText) {
        // There is nowhere to show the status text :(
    }

    @Override
    public void addOutput(String anagram) {
        if (!isInterrupted) {
            System.out.println(anagram + FILL_LINE);
        }
    }

    @Override
    public void setTotalNumberOfCombination(long totalNumberOfCombination) {
        this.totalNumberOfCombination = totalNumberOfCombination;
    }
}
