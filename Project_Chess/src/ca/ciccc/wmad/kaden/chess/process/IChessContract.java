package ca.ciccc.wmad.kaden.chess.process;

import ca.ciccc.wmad.kaden.chess.model.board.Position;

public interface IChessContract {

    interface View {

        void refreshScreen();

    }

    interface Presenter {

        void gameStart();

        void gameEnd();

        int checkSelection(String input);

        boolean undo();

    }
}
