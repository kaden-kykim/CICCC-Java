package ca.ciccc.wmad.kaden.assignment.n3.view;

import ca.ciccc.wmad.kaden.assignment.n3.contract.AGContract;
import ca.ciccc.wmad.kaden.assignment.n3.model.setting.AGSetting;
import ca.ciccc.wmad.kaden.assignment.n3.presenter.AGPresenter;

import java.util.Scanner;

public class AGConsoleView implements AGContract.View {

    private static final String FILL_LINE = "                                                                     ";

    private AGContract.Presenter presenter;
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
        label:
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
                    break label;
                case "s":
                    settingMenu(0);
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

    private void settingMenu(int option) {
        do {
            for (int i = 0; i < 80; ++i) {
                System.out.println();
            }
            System.out.println("=============================================");
            System.out.println("                S E T T I N G                ");
            System.out.println("=============================================");
            switch (option) {
                case 0:
                    System.out.print("1. Process ");
                    switch (AGSetting.getInstance().getSettingSpeed()) {
                        case AGSetting.SETTING_SPEED_STABLE:
                            System.out.println("[Stable]");
                            break;
                        case AGSetting.SETTING_SPEED_NORMAL:
                            System.out.println("[Normal]");
                            break;
                        case AGSetting.SETTING_SPEED_FAST:
                            System.out.println("[Fast]");
                            break;
                        case AGSetting.SETTING_SPEED_FASTER:
                            System.out.println("[Faster]");
                            break;
                    }
                    System.out.println("2. Allow Repeated Words "
                            + ((AGSetting.getInstance().allowRepeat()) ? "[Yes]" : "[No]"));
                    System.out.println("3. Minimum letters in a word ["
                            + AGSetting.getInstance().getMinWordLength() + "]");
                    System.out.println("4. Maximum letters in a word ["
                            + AGSetting.getInstance().getMaxWordLength() + "]");
                    System.out.println("B. Back");
                    System.out.println("============================================");
                    System.out.print("\nInput menu: ");

                    switch (inputString = scanner.nextLine().toLowerCase()) {
                        case "1":
                        case "2":
                        case "3":
                        case "4":
                            settingMenu(Integer.parseInt(inputString));
                            break;
                        case "b":
                            for (int i = 0; i < 80; ++i) {
                                System.out.println();
                            }
                            return;
                        default:
                            System.out.print("Wrong input. Please try again.");
                            scanner.nextLine();
                    }
                    break;

                case 1:
                    System.out.println("              Process  Setting              ");
                    System.out.println("============================================");
                    System.out.println("1. Stable");
                    System.out.println("2. Normal");
                    System.out.println("3. Fast");
                    System.out.println("4. Faster");
                    System.out.println("B. Back");
                    System.out.println("============================================");
                    System.out.print("\nInput menu: ");

                    switch (inputString = scanner.nextLine().toLowerCase()) {
                        case "1":
                        case "2":
                        case "3":
                        case "4":
                            AGSetting.getInstance().setSettingSpeed(Integer.parseInt(inputString));
                        case "b":
                            return;
                        default:
                            System.out.print("Wrong input. Please try again.");
                            scanner.nextLine();
                    }
                    break;

                case 2:
                    System.out.print("Allow Repeated Words? [(Y)es / (N)o / (B)ack]: ");
                    switch (inputString = scanner.nextLine().toLowerCase()) {
                        case "y":
                        case "n":
                            AGSetting.getInstance().setAllowRepeat(("y".equals(inputString)));
                        case "b":
                            return;
                        default:
                            System.out.print("Wrong input. Please try again.");
                            scanner.nextLine();
                    }
                    break;

                case 3:
                    int maxVal = AGSetting.getInstance().getMaxWordLength();
                    System.out.print("Input Minimum Letters(inclusive) in a word [1 ~ " + maxVal + " / (B)ack]: ");
                    if ("b".equals(inputString = scanner.nextLine())) {
                        return;
                    }
                    try {
                        int minVal = Integer.parseInt(inputString);
                        if (minVal <= 0 || minVal > maxVal) {
                            throw new NumberFormatException();
                        }
                        AGSetting.getInstance().setMinWordLength(minVal);
                        return;
                    } catch (NumberFormatException e) {
                        System.out.print("Wrong input. Please try again.");
                        scanner.nextLine();
                    }
                    break;

                case 4:
                    int minVal = AGSetting.getInstance().getMinWordLength();
                    System.out.print("Input Maximum Letters(exclusive) in a word ["
                            + (minVal + 1) + " ~ 20 / (B)ack]: ");
                    if ("b".equals(inputString = scanner.nextLine())) {
                        return;
                    }
                    try {
                        int maxValue = Integer.parseInt(inputString);
                        if (maxValue > 20 || maxValue <= minVal) {
                            throw new NumberFormatException();
                        }
                        AGSetting.getInstance().setMaxWordLength(maxValue);
                        return;
                    } catch (NumberFormatException e) {
                        System.out.print("Wrong input. Please try again.");
                        scanner.nextLine();
                    }
                    break;

                default:
                    System.out.print("Wrong input. Please try again.");
                    scanner.nextLine();
            }
        } while (true);
    }
}
