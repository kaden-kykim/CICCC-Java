package ca.ciccc.wmad.kaden.chess.process;

import ca.ciccc.wmad.kaden.chess.global.Setting;
import ca.ciccc.wmad.kaden.chess.global.Status;
import ca.ciccc.wmad.kaden.chess.model.board.Board;
import ca.ciccc.wmad.kaden.chess.model.board.Position;
import ca.ciccc.wmad.kaden.chess.process.progress.ProgressTree;
import ca.ciccc.wmad.kaden.chess.model.piece.Piece;

import java.util.ArrayList;

public class ChessController implements IChessContract.Presenter {

    public static final int GAME_SELECT_PIECE = 0, GAME_SELECT_MOVE = 1, GAME_INCORRECT_INPUT = -1;
    public static final int STATUS_TURN_PIECE_SELECTED = 0;

    private Piece selectedPiece;
    private ArrayList<Position> possibleMovement;

    private int turnStatus;

    private IChessContract.View view;

    private ProgressTree progressTree;

    public ChessController(IChessContract.View view) {
        this.view = view;
    }

    @Override
    public void gameStart() {
        Status.getInstance().setIsGaming(true);
        progressTree = new ProgressTree(Board.getInstance(), Setting.getInstance().isFirst());
    }

    @Override
    public void gameEnd() {
        progressTree.destroy();
        Board.getInstance().resetBoard();
        Status.getInstance().setIsGaming(false);
    }

    @Override
    public int checkSelection(String input) {
        return GAME_INCORRECT_INPUT;
    }

    @Override
    public boolean undo() {
        return false;
    }
}
