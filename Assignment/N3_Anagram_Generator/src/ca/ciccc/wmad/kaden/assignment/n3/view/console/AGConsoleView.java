package ca.ciccc.wmad.kaden.assignment.n3.view.console;

import ca.ciccc.wmad.kaden.assignment.n3.contract.AGContract;
import ca.ciccc.wmad.kaden.assignment.n3.presenter.AGPresenter;

import java.util.Scanner;

public class AGConsoleView implements AGContract.View {

    private static final String FILL_LINE = "                                                                     ";

    private AGContract.Presenter presenter;
    private AGSettingCLI settingCLI;
    private String inputString;
    private Scanner scanner;

    private long totalNumberOfCombination;
    private long foundAnagram;
    private boolean isInterrupted = false, interruptedByThread = false;

    public AGConsoleView() {
        presenter = new AGPresenter(this);
    }

    @Override
    public void start() {
        scanner = new Scanner(System.in);
        settingCLI = new AGSettingCLI(scanner);
        while (!"q".equals(inputString)) {
            foundAnagram = 0;
            System.out.print("\nInput text to generate anagram[(S)etting / (Q)uit]: ");
            inputString = scanner.nextLine().toLowerCase();
            isInterrupted = interruptedByThread = false;
            switch (inputString) {
                case "":
                    System.out.println("Wrong input. Please try again.");
                    break;
                case "q":
                    return;
                case "s":
                    settingCLI.settingMenu(0);
                    break;
                default:
                    System.out.println("Press [Enter] to start, then press [Enter] again to stop progress");
                    scanner.nextLine();
                    Thread preTask = new Thread(() -> presenter.processPreTask(inputString));
                    Thread task = new Thread(() -> {
                        presenter.processTask();
                        setTaskProgress(totalNumberOfCombination);
                        interruptedByThread = true;
                    });
                    preTask.start();
                    task.start();

                    scanner.nextLine();
                    if (!interruptedByThread) {
                        preTask.interrupt();
                        task.interrupt();
                    }
                    isInterrupted = true;

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // Nothing to do, just be interrupted
                    }
                    break;
            }
        }
        scanner.close();
    }

    @Override
    public void setTaskProgress(long progress) {
        if (!isInterrupted) {
            String progressStr = String.format("Progress: (%d / %d) %s",
                    progress, totalNumberOfCombination, (progress == totalNumberOfCombination) ? "Done!" : "");
            String foundedStr = String.format("Founded Anagram: %d\r", foundAnagram);
            String blankLine = FILL_LINE.substring((progressStr.length() + foundedStr.length()));
            System.out.print(String.format("%s%s%s", progressStr, blankLine, foundedStr));
        }
    }

    @Override
    public void setStatusText(String statusText) {
        // There is nowhere to show the status text :(
    }

    @Override
    public void addOutput(String anagram) {
        if (!isInterrupted) {
            System.out.println(anagram + FILL_LINE);
            ++foundAnagram;
        }
    }

    @Override
    public void setTotalNumberOfCombination(long totalNumberOfCombination) {
        this.totalNumberOfCombination = totalNumberOfCombination;
    }
}
