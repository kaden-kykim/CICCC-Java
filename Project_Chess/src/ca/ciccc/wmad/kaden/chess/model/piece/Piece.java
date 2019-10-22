package ca.ciccc.wmad.kaden.chess.model.piece;

import ca.ciccc.wmad.kaden.chess.model.board.Position;

import java.util.ArrayList;

public abstract class Piece {

    public static final boolean PLAYER1 = true, PLAYER2 = false;
    public static final int NOTE_NONE = 0, NOTE_CASTLING = 1, NOTE_EN_PASSANT = 2, NOTE_PROMOTION = 3;
    public static final int MOVE_NONE = 0, MOVE_CAPTURE = 10;

    private final char notation;
    private final String name;
    private final boolean player;

    private Position position;
    private int moveCount;

    private ArrayList<Movement> possibleMovement;

    Piece(char notation, Position position, boolean player) {
        this.notation = notation;
        this.name = this.getClass().getSimpleName().trim();
        this.moveCount = 0;
        this.player = player;
        this.position = position;
    }

    public Piece clonePiece() {
        Piece clonePiece;
        if (this instanceof King) {
            clonePiece = new King(this.position, this.player);
        } else if (this instanceof Queen) {
            clonePiece = new Queen(this.position, this.player);
        } else if (this instanceof Bishop) {
            clonePiece = new Bishop(this.position, this.player);
        } else if (this instanceof Knight) {
            clonePiece = new Knight(this.position, this.player);
        } else if (this instanceof Rook) {
            clonePiece = new Rook(this.position, this.player);
        } else if (this instanceof Pawn){
            clonePiece = new Pawn(this.position, this.player);
        } else {
            return null;
        }

        clonePiece.moveCount = this.moveCount;
        clonePiece.possibleMovement = new ArrayList<>();
        for (Movement movement : this.possibleMovement) {
            clonePiece.possibleMovement.add(new Movement(movement.position, movement.note));
        }

        return clonePiece;
    }

    public boolean isMoved() {
        return (moveCount != 0);
    }

    public boolean isOpponent(Piece piece) {
        return (this.player != piece.getPlayer());
    }

    public int getMoveCount() {
        return moveCount;
    }

    public char getNotation() {
        return notation;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        this.moveCount++;
    }

    public boolean getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Movement> getPossibleMovement() {
        return possibleMovement;
    }

    public void setPossibleMovement(ArrayList<Movement> possibleMovement) {
        this.possibleMovement = possibleMovement;
    }

    public void updatePossibleMovement() {
        setPossibleMovement(getPotentialMovement());
    }

    public abstract ArrayList<Movement> getPotentialMovement();
}
