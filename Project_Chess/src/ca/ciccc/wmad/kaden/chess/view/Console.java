package ca.ciccc.wmad.kaden.chess.view;

import ca.ciccc.wmad.kaden.chess.global.Setting;
import ca.ciccc.wmad.kaden.chess.global.Status;
import ca.ciccc.wmad.kaden.chess.model.board.Board;
import ca.ciccc.wmad.kaden.chess.model.board.Position;
import ca.ciccc.wmad.kaden.chess.model.piece.Piece;
import ca.ciccc.wmad.kaden.chess.process.ChessController;
import ca.ciccc.wmad.kaden.chess.process.IChessContract;
import ca.ciccc.wmad.kaden.chess.view.console.Instruction;

import java.util.Scanner;

import static ca.ciccc.wmad.kaden.chess.global.Status.*;
import static ca.ciccc.wmad.kaden.chess.process.ChessController.GAME_SELECT_MOVE;
import static ca.ciccc.wmad.kaden.chess.view.console.FeedBack.*;
import static ca.ciccc.wmad.kaden.chess.view.console.Instruction.*;

public class Console implements IChessContract.View {

    private static final int MAX_INSTRUCTION_LINES = 5, NUM_FEEDBACK_LINES = 1;
    private static final int NAV_MENU = 0, NAV_SETTING = 1, NAV_GAME = 2, NAV_EXIT = -1;

    private IChessContract.Presenter presenter;
    private Scanner userInput;

    private String feedback;
    private boolean hasFeedBack;

    public Console() {
        presenter = new ChessController(this);
        userInput = new Scanner(System.in);

        feedback = FB_WELCOME_MESSAGE.getFeedback();
        hasFeedBack = true;
    }

    public void start() {
        int next = NAV_MENU;

        do {
            Status.getInstance().setStatus(next);
            switch (next) {
                case NAV_MENU:
                    next = navMenu();
                    break;
                case NAV_SETTING:
                    next = navSetting();
                    break;
                case NAV_GAME:
                    next = navGame();
                    break;
                default:
                    next = NAV_MENU;
            }
        } while (next != NAV_EXIT);
    }

    @Override
    public void refreshScreen() {

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        userInput.close();
    }

    private void showBoard() {
        clearScreen();
        System.out.print("------------------------------------------------------------------------------------");
        for (int i = 0; i < 8; ++i) {
            fillSquare(i);
            fillSquareMid(i);
            fillSquare(i);
            System.out.print("\n------------------------------------------------------------------------------------");
        }
        System.out.print("\n   |");
        for (int i = 0; i < 8; ++i) {
            System.out.print("    " + (char) ('A' + i) + "    |");
        }
        System.out.println("\n=====================================================================================");
    }

    private void fillSquare(int rowIndex) {
        Piece[] pieces = Board.getInstance().getBoard()[rowIndex];
        char fillCol = Setting.getInstance().getFillSqChar();
        System.out.print("\n   |");
        for (int j = 0; j < 8; ++j) {
            char blank = ((rowIndex % 2 == 0) ^ (j % 2 == 0) ? fillCol : ' ');
            System.out.print("" + blank + blank + blank + blank + blank + blank + blank + blank + blank + "|");
        }
    }

    private void fillSquareMid(int rowIndex) {
        Piece[] pieces = Board.getInstance().getBoard()[rowIndex];
        char fillCol = Setting.getInstance().getFillSqChar();
        System.out.print("\n " + (char) ('8' - rowIndex) + " |");
        for (int j = 0; j < 8; ++j) {
            char blank = ((rowIndex % 2 == 0) ^ (j % 2 == 0) ? fillCol : ' ');
            if (pieces[j] != null) {
                Piece piece = pieces[j];
                char playerChar = (piece.getPlayer() == Piece.PLAYER1) ? '*' : '-';
                System.out.print("" + blank + blank + blank + playerChar + piece.getNotation() + playerChar + blank + blank + blank + "|");
            } else {
                System.out.print("" + blank + blank + blank + blank + blank + blank + blank + blank + blank + "|");
            }
        }
    }

    private void clearScreen() {
        for (int i = 0; i < 80; ++i) {
            System.out.println();
        }
        System.out.println("=====================================================================================");
        System.out.println("                  It's hard to believe, but this is a CHESS GAME!                    ");
        System.out.print(getSubCommand(Status.getInstance().getStatus()));
        System.out.println("=====================================================================================\n");
    }

    private void showInstruction(Instruction instruction) {
        String[] instString = instruction.getInstruction();
        int blankLine = MAX_INSTRUCTION_LINES - instString.length - NUM_FEEDBACK_LINES;

        for (int i = 0; i < blankLine; ++i) {
            System.out.println();
        }
        for (int i = 0; (i < instString.length) && (i < MAX_INSTRUCTION_LINES); ++i) {
            System.out.println(instString[i]);
        }

        System.out.println("=====================================================================================");
        if (hasFeedBack) {
            System.out.println("[System] " + feedback);
            hasFeedBack = false;
            feedback = "";
        } else {
            System.out.println();
        }
    }

    private String getUserInput() {
        System.out.print(">>> ");
        return userInput.nextLine();
    }

    private void setFeedback(String feedback) {
        this.feedback = feedback;
        this.hasFeedBack = true;
    }

    private String getSubCommand(int status) {
        switch (status) {
            case STATUS_MENU:
                return "                               [ M A I N   M E N U ]                                \n";
            case STATUS_SETTING:
                return "                                 [ S E T T I N G ]                                  \n";
            case STATUS_GAME:
                return "                                 [ P L A Y I N G ]                                  \n";
            default:
                return "";
        }
    }

    private int inputIncorrectAnswer(int next) {
        setFeedback(FB_INCORRECT_ANSWER.getFeedback());
        return next;
    }

    private int navMenu() {
        clearScreen();
        showInstruction(INST_MAIN_MENU);
        switch (getUserInput()) {
            case "1":
                if (Status.getInstance().isGaming()) {
                    setFeedback(FB_WARNING_PROGRESS_LOST.getFeedback());
                    clearScreen();
                    showInstruction(INST_MAIN_MENU);
                    switch (getUserInput()) {
                        case "Y":
                        case "y":
                            presenter.gameEnd();
                            return NAV_GAME;
                        default:
                            return NAV_MENU;
                    }
                }
                return NAV_GAME;
            case "2":
                if (Status.getInstance().isGaming()) {
                    return NAV_GAME;
                } else {
                    setFeedback(FB_NOT_GAMING.getFeedback());
                    return NAV_MENU;
                }
            case "3":
                return NAV_SETTING;
            case "Q":
            case "q":
                return NAV_EXIT;
            default:
                return inputIncorrectAnswer(NAV_MENU);
        }
    }

    private int navSetting() {
        clearScreen();
        showInstruction(INST_SETTING);
        String answer;
        switch (getUserInput()) {
            case "1":
                setFeedback("Current Difficulty: " +
                        (Setting.getInstance().getDifficulty() == Setting.DIFFICULTY_EASY ? "Easy" : "Hard"));
                clearScreen();
                showInstruction(INST_SET_DIFFICULTY);
                switch (answer = getUserInput()) {
                    case "":
                    case "1":
                    case "2":
                        int ans = ("".equals(answer)) ? 1 : Integer.parseInt(answer);
                        Setting.getInstance().setDifficulty(ans);
                        setFeedback("Changed Difficulty to: " + (ans == Setting.DIFFICULTY_EASY ? "Easy" : "Hard"));
                        return NAV_SETTING;
                    default:
                        return inputIncorrectAnswer(NAV_SETTING);
                }

            case "2":
                boolean isVsCom = Setting.getInstance().isVsComputer();
                setFeedback("Current First Player: " +
                        ((Setting.getInstance().isFirst())
                                ? (isVsCom ? "Player" : "Player1")
                                : (isVsCom ? "Computer" : "Player2")));
                clearScreen();
                showInstruction(INST_SET_FIRST);
                switch (answer = getUserInput()) {
                    case "":
                    case "1":
                    case "2":
                        boolean ans = !("2".equals(answer));
                        Setting.getInstance().setFirst(ans);
                        setFeedback("Changed the Order to: " +
                                (ans ? (isVsCom ? "Player" : "Player1") : (isVsCom ? "Computer" : "Player2")));
                        return NAV_SETTING;
                    default:
                        return inputIncorrectAnswer(NAV_SETTING);
                }

            case "3":
                boolean isMoveHint = Setting.getInstance().isMoveHint();
                setFeedback("Current Possible Movement: " + (isMoveHint ? "Show" : "Hide"));
                clearScreen();
                showInstruction(INST_HINT_MOVE);
                switch (answer = getUserInput()) {
                    case "":
                    case "1":
                    case "2":
                        boolean ans = !("2".equals(answer));
                        Setting.getInstance().setMoveHint(ans);
                        setFeedback("Changed the Showing to: " + (ans ? "Show" : "Hide"));
                        return NAV_SETTING;
                    default:
                        return inputIncorrectAnswer(NAV_SETTING);
                }

            case "4":
                char fillSqChar = Setting.getInstance().getFillSqChar();
                setFeedback("Current Character of Filling Column: " + fillSqChar);
                clearScreen();
                showInstruction(INST_FILL_SQ_CHAR);
                switch (getUserInput()) {
                    case "":
                    case "1":
                        Setting.getInstance().setFillSqChar('/');
                        break;
                    case "2":
                        Setting.getInstance().setFillSqChar(':');
                        break;
                    case "3":
                        Setting.getInstance().setFillSqChar(' ');
                        break;
                    default:
                        return inputIncorrectAnswer(NAV_SETTING);
                }
                setFeedback("Changed the character to fill column to: '" +
                        Setting.getInstance().getFillSqChar() + "'");
                return NAV_SETTING;

            case "B":
            case "b":
                return NAV_MENU;
            default:
                return inputIncorrectAnswer(NAV_SETTING);
        }
    }

    private int navGame() {
        if (!Status.getInstance().isGaming()) {
            presenter.gameStart();
            if (Setting.getInstance().isFirst()) {
                setFeedback(FB_GAME_SELECT_PIECE.getFeedback());
            } else {
                setFeedback(FB_GAME_WAIT_COMPUTER.getFeedback());
                clearScreen();
                showBoard();
                showInstruction(INST_GAME_MENU);
            }
        }
        clearScreen();
        showBoard();
        showInstruction(INST_GAME_MENU);
        String answer = getUserInput();

        int navIndex = checkGameMenu(answer);
        if (navIndex >= 0) {
            return navIndex;
//        } else {
            // TODO Didn't finish to implement yet

//            int gameResult;
//            while (GAME_SELECT_MOVE != (gameResult = presenter.checkSelection(answer))) {
//
//            }
//            int result = presenter.checkSelection(answer);
//
//            try {
//                Position pos = Position.convertInputToPosition(answer);
//
//            } catch (IllegalArgumentException e) {
//
//            }
        }
        return 0;
    }

    private int checkGameMenu(String userInput) {
        switch (userInput) {
            case "B":
            case "b":
            case "Q":
            case "q":
                return NAV_MENU;
            case "U":
            case "u":
                if (!presenter.undo()) {
                    setFeedback(FB_GAME_NOT_UNDO.getFeedback());
                }
                return NAV_GAME;
            case "R":
            case "r":
            case "E":
            case "e":
                setFeedback(FB_WARNING_PROGRESS_LOST.getFeedback());
                clearScreen();
                showBoard();
                showInstruction(INST_MAIN_MENU);
                switch (getUserInput()) {
                    case "Y":
                    case "y":
                        presenter.gameEnd();
                        if ("r".equals(userInput.toLowerCase())) {
                            return NAV_GAME;
                        } else {
                            return NAV_MENU;
                        }
                    default:
                        return NAV_GAME;
                }

            // TODO: Default Promotion Setting - Pawn to Queen(Default) / Knight / Rook / Bishop
            default:
                return NAV_EXIT;
        }
    }
}
