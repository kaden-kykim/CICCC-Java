package ca.ciccc.wmad.kaden.chess.model.piece;

import ca.ciccc.wmad.kaden.chess.model.board.Position;

public class Movement {
    Position position;
    int note;

    public Movement(Position position) {
        this(position, Piece.NOTE_NONE);
    }

    public Movement(Position position, int note) {
        this.position = position;
        this.note = note;
    }

    public Position getPosition() {
        return position;
    }

    public int getNote() {
        return note;
    }
}
