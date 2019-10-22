package ca.ciccc.wmad.kaden.chess.model.piece;

import ca.ciccc.wmad.kaden.chess.model.board.Board;
import ca.ciccc.wmad.kaden.chess.model.board.Direction;
import ca.ciccc.wmad.kaden.chess.model.board.Position;

import java.util.ArrayList;

import static ca.ciccc.wmad.kaden.chess.model.board.Direction.*;

public class Rook extends Piece {

    public Rook(Position position, boolean player) {
        super('R', position, player);
    }

    @Override
    public ArrayList<Movement> getPotentialMovement() {
        ArrayList<Movement> potentialMovement = new ArrayList<>();
        Board board = Board.getInstance();
        Direction[] directions = {E, N, W, S};

        for (Direction direction : directions) {
            Position tmpPos = getPosition();
            try {
                while (true) {
                    tmpPos = tmpPos.getPositionByDirection(direction);
                    if (board.isEmpty(tmpPos)) {
                        // Move to empty square
                        potentialMovement.add(new Movement(tmpPos));
                    } else {
                        // Capture Opponent
                        if (isOpponent(board.getPiece(tmpPos))) {
                            potentialMovement.add(new Movement(tmpPos, MOVE_CAPTURE));
                            break;
                        } else {
                            break;
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                // Do nothing, continue
            }
        }
        return potentialMovement;
    }
}
