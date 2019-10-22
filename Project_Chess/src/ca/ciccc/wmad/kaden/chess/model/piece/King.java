package ca.ciccc.wmad.kaden.chess.model.piece;

import ca.ciccc.wmad.kaden.chess.model.board.Board;
import ca.ciccc.wmad.kaden.chess.model.board.Direction;
import ca.ciccc.wmad.kaden.chess.model.board.Position;

import java.util.ArrayList;

import static ca.ciccc.wmad.kaden.chess.model.board.Position.*;

public class King extends Piece {

    public King(Position position, boolean player) {
        super('K', position, player);
    }

    @Override
    public ArrayList<Movement> getPotentialMovement() {
        ArrayList<Movement> potentialMovement = new ArrayList<>();
        Board board = Board.getInstance();
        Direction[] directions = Direction.values();

        for (Direction direction : directions) {
            try {
                Position tmpPos = getPosition().getPositionByDirection(direction);
                if (board.isEmpty(tmpPos)) {
                    // Move to empty square
                    potentialMovement.add(new Movement(tmpPos));
                } else if (isOpponent(board.getPiece(tmpPos))) {
                    // Capture Opponent
                    potentialMovement.add(new Movement(tmpPos, MOVE_CAPTURE));
                }
            } catch (IllegalArgumentException e) {
                // Do nothing...continue
            }
        }

        // Castling
        if (!isMoved() && !board.isCheck()) {
            // TODO: Need to post process, e.g., C1(Check) and D1 must not be attacked
            if (getPlayer() == PLAYER1) {
                if (!board.getPiece(A1).isMoved() && board.isEmpty(B1) && board.isEmpty(C1) && board.isEmpty(D1)) {
                    potentialMovement.add(new Movement(C1, NOTE_CASTLING));
                }
                if (!board.getPiece(H1).isMoved() && board.isEmpty(F1) && board.isEmpty(G1)) {
                    potentialMovement.add(new Movement(G1, NOTE_CASTLING));
                }
            } else {
                if (!board.getPiece(A8).isMoved() && board.isEmpty(B8) && board.isEmpty(C8) && board.isEmpty(D8)) {
                    potentialMovement.add(new Movement(C8, NOTE_CASTLING));
                }
                if (!board.getPiece(H8).isMoved() && board.isEmpty(F8) && board.isEmpty(G8)) {
                    potentialMovement.add(new Movement(G8, NOTE_CASTLING));
                }
            }
        }

        return potentialMovement;
    }
}
