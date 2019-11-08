package ca.ciccc.wmad.kaden.assignment.n3.view.console;

import ca.ciccc.wmad.kaden.assignment.n3.model.setting.AGSetting;

import java.util.Scanner;

public class AGSettingCLI {

    private Scanner scanner;

    public AGSettingCLI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void settingMenu(int option) {
        String inputString;

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
