package ca.ciccc.wmad.kaden.chess.model.piece;

import ca.ciccc.wmad.kaden.chess.model.board.Board;
import ca.ciccc.wmad.kaden.chess.model.board.Direction;
import ca.ciccc.wmad.kaden.chess.model.board.Position;

import java.util.ArrayList;

import static ca.ciccc.wmad.kaden.chess.model.board.Direction.*;

public class Knight extends Piece {

    public Knight(Position position, boolean player) {
        super('N', position, player);
    }

    @Override
    public ArrayList<Movement> getPotentialMovement() {
        ArrayList<Movement> potentialMovement = new ArrayList<>();
        Board board = Board.getInstance();

        Direction[] directions = {NE, NW, SW, SE};
        for (Direction direction : directions) {
            Position tmpPos;
            try {
                tmpPos = getPosition().getPositionByDirection(direction);
                for (char dirChar : direction.name().toCharArray()) {
                    tmpPos = tmpPos.getPositionByDirection(Direction.valueOf("" + dirChar));
                    if (board.isEmpty(tmpPos)) {
                        // Move to empty square
                        potentialMovement.add(new Movement(tmpPos));
                    } else {
                        // Capture Opponent
                        if (isOpponent(board.getPiece(tmpPos))) {
                            potentialMovement.add(new Movement(tmpPos, MOVE_CAPTURE));
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                // Do nothing...
            }
        }

        return potentialMovement;
    }
}
