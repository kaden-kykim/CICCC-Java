package ca.ciccc.wmad.kaden.chess.model.piece;

import ca.ciccc.wmad.kaden.chess.model.board.Board;
import ca.ciccc.wmad.kaden.chess.model.board.Position;

import java.util.ArrayList;

import static ca.ciccc.wmad.kaden.chess.model.board.Direction.*;
import static ca.ciccc.wmad.kaden.chess.model.board.Position.ROW_INDEX;

public class Pawn extends Piece {

    public Pawn(Position position, boolean player) {
        super('P', position, player);
    }

    @Override
    public ArrayList<Movement> getPotentialMovement() {
        ArrayList<Movement> potentialMovement = new ArrayList<>();
        Board board = Board.getInstance();
        Position tmpPos;

        // Move to empty square
        try {
            tmpPos = getPosition().getPositionByDirection((getPlayer() == PLAYER1) ? N : S);
            if (board.isEmpty(tmpPos)) {
                int row = tmpPos.convertPositionToIndexes()[ROW_INDEX];
                if (row == 0 || row == 7) {
                    potentialMovement.add(new Movement(tmpPos, NOTE_PROMOTION));
                } else {
                    potentialMovement.add(new Movement(tmpPos));
                }
                tmpPos = tmpPos.getPositionByDirection((getPlayer() == PLAYER1) ? N : S);
                if (!isMoved() && board.isEmpty(tmpPos)) {
                    potentialMovement.add(new Movement(tmpPos));
                }
            }
        } catch (IllegalArgumentException e) {
            // Do nothing, continue
        }

        // Capture opponent
        try {
            tmpPos = getPosition().getPositionByDirection((getPlayer() == PLAYER1) ? NW : SW);
            if (!board.isEmpty(tmpPos) && isOpponent(board.getPiece(tmpPos))) {
                potentialMovement.add(new Movement(tmpPos, MOVE_CAPTURE));
            }
        } catch (IllegalArgumentException e) {
            // Do nothing, continue
        }
        try {
            tmpPos = getPosition().getPositionByDirection((getPlayer() == PLAYER1) ? NE : SE);
            if (!board.isEmpty(tmpPos) && isOpponent(board.getPiece(tmpPos))) {
                potentialMovement.add(new Movement(tmpPos, MOVE_CAPTURE));
            }
        } catch (IllegalArgumentException e) {
            // Do nothing, continue
        }

        // En passant
        if (getPosition().convertPositionToIndexes()[ROW_INDEX] == ((getPlayer() == PLAYER1) ? 3 : 4)) {
            try {
                tmpPos = getPosition().getPositionByDirection(E);
                if (!board.isEmpty(tmpPos)) {
                    Piece piece = board.getPiece(tmpPos);
                    if (isOpponent(piece) && (piece instanceof Pawn) && (piece.getMoveCount() == 1)) {
                        potentialMovement
                                .add(new Movement(getPosition().getPositionByDirection((getPlayer() == PLAYER1) ? NE : SE),
                                        NOTE_EN_PASSANT + MOVE_CAPTURE));
                    }
                }
            } catch (IllegalArgumentException e) {
                // Do nothing, continue
            }

            try {
                tmpPos = getPosition().getPositionByDirection(W);
                if (!board.isEmpty(tmpPos)) {
                    Piece piece = board.getPiece(tmpPos);
                    if (isOpponent(piece) && (piece instanceof Pawn) && piece.getMoveCount() == 1) {
                        potentialMovement
                                .add(new Movement(getPosition().getPositionByDirection((getPlayer() == PLAYER1) ? NW : SW),
                                        NOTE_EN_PASSANT + MOVE_CAPTURE));
                    }
                }
            } catch (IllegalArgumentException e) {
                // Do nothing, continue
            }
        }

        return potentialMovement;
    }
}
