package ca.ciccc.wmad.kaden.chess.view.console;

import ca.ciccc.wmad.kaden.chess.global.Setting;
import ca.ciccc.wmad.kaden.chess.global.Status;

public enum Instruction {

    INST_MAIN_MENU(new String[]{
            "1. " + (Status.getInstance().isGaming() ? "Res" : "S") + "tart Game",
            "2. Resume Game", "3. Setting", "Q. Exit"
    }),
    INST_SETTING(new String[]{
            "1. Difficulty", "2. First player", "3. Show Possible Movement", "4. Fill Column", "B. Back to Menu"
    }),
    INST_GAME_MENU(new String[]{
            "===================================  [Game Menu]  ===================================",
            "U) Undo                                    E) End Game",
            "R) Restart Game                            Q/B) Back to Main Menu"
    }),
    INST_SET_VS_COMPUTER(new String[]{
            "With whom do you want to play? (Default: 1. vs Computer)",
            "1. vs Computer             2. vs Player"
    }),
    INST_SET_DIFFICULTY(new String[]{
            "Please select difficulty (Default: 1. Easy)",
            "1. Easy                    2. Hard"
    }),
    INST_SET_FIRST(new String[]{
            "Who is going to be first? (Default: 1. Player" + ((Setting.getInstance().isFirst()) ? ")" : " 1)"),
            "1. Player" + ((Setting.getInstance().isVsComputer()) ? "" : " 1")
                    + "                 2. " + ((Setting.getInstance().isVsComputer()) ? "Computer" : "Player 2")
    }),
    INST_HINT_MOVE(new String[]{
            "Do you want the possible movement to show? (Default: 1. Show)",
            "1. Show                    2. Hide"
    }),
    INST_FILL_SQ_CHAR(new String[]{
            "Which character do you want to fill square? (Default: 1. /)",
            "1. /                   2. :                    3. None"
    });

    private String[] instruction;

    Instruction(String[] instruction) {
        this.instruction = instruction;
    }

    public String[] getInstruction() {
        return getDynamicStrings();
    }

    private String[] getDynamicStrings() {
        switch (this) {
            case INST_SET_FIRST:
                return instruction = new String[]{
                        "Who is going to be first? (Default: 1. Player" + ((Setting.getInstance().isFirst()) ? ")" : " 1)"),
                        "1. Player" + ((Setting.getInstance().isVsComputer()) ? "" : " 1")
                                + "\t\t2. " + ((Setting.getInstance().isVsComputer()) ? "Computer" : "Player 2")};
            case INST_MAIN_MENU:
                return instruction = new String[]{
                        "1. " + (Status.getInstance().isGaming() ? "Res" : "S") + "tart Game",
                        "2. Resume Game", "3. Setting", "Q. Exit"};
            default:
                return instruction;
        }
    }
}
