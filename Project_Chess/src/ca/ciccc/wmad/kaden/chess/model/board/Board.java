package ca.ciccc.wmad.kaden.chess.model.board;

import ca.ciccc.wmad.kaden.chess.global.Setting;
import ca.ciccc.wmad.kaden.chess.model.piece.*;

import java.util.ArrayList;
import java.util.Arrays;

import static ca.ciccc.wmad.kaden.chess.model.board.Position.*;
import static ca.ciccc.wmad.kaden.chess.model.piece.Piece.*;

public class Board {

    private static Board instance = null;

    private Piece[][] board;
    private ArrayList<Piece> p1Pieces, p2Pieces;

    private boolean isCheck;

    // TODO: Remove all public method using int[] as a position, then instead using Position

    private Board() {
        board = new Piece[8][8];
        p1Pieces = new ArrayList<>();
        p2Pieces = new ArrayList<>();
        resetBoard();
    }
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public void resetBoard() {
        clearBoard();
        initBoard();
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Board cloneBoard() {
        Board cloneBoard = new Board();

        for (int row = 0; row < 8; ++row) {
            for (int col = 0; col < 8; ++col) {
                if (this.board[row][col] != null) {
                    Piece piece = this.board[row][col].clonePiece();
                    cloneBoard.board[row][col] = piece;
                    if (piece.getPlayer() == PLAYER1) {
                        p1Pieces.add(piece);
                    } else {
                        p2Pieces.add(piece);
                    }
                }
            }
        }

        return cloneBoard;
    }

    public void loadBoard(Board loadBoard) {
        instance = loadBoard;
    }

    public boolean isEmpty(int[] position) {
        return (getPiece(position) == null);
    }

    public boolean isEmpty(Position position) {
        return (getPiece(position) == null);
    }

    public Piece getPiece(int[] position) {
        return board[position[ROW_INDEX]][position[COL_INDEX]];
    }

    public Piece getPiece(Position position) {
        return getPiece(position.convertPositionToIndexes());
    }

    public Piece[] getAllPieces(boolean player) {
        return (Piece[]) ((player == PLAYER1) ? p1Pieces.toArray() : p2Pieces.toArray());
    }

    public void setPiece(Piece piece) {
        int[] pos = piece.getPosition().convertPositionToIndexes();
        board[pos[ROW_INDEX]][pos[COL_INDEX]] = piece;
        ((piece.getPlayer() == PLAYER1) ? p1Pieces : p2Pieces).add(piece);
    }

    public Piece removePiece(int[] pos) {
        Piece piece = board[pos[ROW_INDEX]][pos[COL_INDEX]];
        board[pos[ROW_INDEX]][pos[COL_INDEX]] = null;
        ((piece.getPlayer() == PLAYER1) ? p1Pieces : p2Pieces).remove(piece);
        return piece;
    }

    public void movePiece(Position moveFrom, Movement moveTo) {
        int[] oriPos = moveFrom.convertPositionToIndexes();
        int[] destPos = moveTo.getPosition().convertPositionToIndexes();
        Piece oriPiece = board[oriPos[ROW_INDEX]][oriPos[COL_INDEX]];

        board[oriPos[ROW_INDEX]][oriPos[COL_INDEX]] = null;
        if (moveTo.getNote() > MOVE_CAPTURE) {
            if (moveTo.getNote() % 10 == NOTE_EN_PASSANT) {
                removePiece(new int[]{oriPos[ROW_INDEX], destPos[COL_INDEX]});
            } else {
                removePiece(destPos);
            }
        } else {
            if (moveTo.getNote() == NOTE_CASTLING) {
                if (moveTo.getPosition() == C1) {
                    movePiece(A1, new Movement(D1));
                }
            }
        }
        board[destPos[ROW_INDEX]][destPos[COL_INDEX]] = oriPiece;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private void clearBoard() {
        for (Piece[] row : board) {
            Arrays.fill(row, null);
        }
        p1Pieces.clear();
        p2Pieces.clear();
        this.isCheck = false;
    }

    private void initBoard() {
        setPiece(new Rook(Position.A8, Piece.PLAYER2));
        setPiece(new Knight(Position.B8, Piece.PLAYER2));
        setPiece(new Bishop(Position.C8, Piece.PLAYER2));
        setPiece(new Queen(Position.D8, Piece.PLAYER2));
        setPiece(new King(Position.E8, Piece.PLAYER2));
        setPiece(new Bishop(Position.F8, Piece.PLAYER2));
        setPiece(new Knight(Position.G8, Piece.PLAYER2));
        setPiece(new Rook(Position.H8, Piece.PLAYER2));
        setPiece(new Pawn(Position.A7, Piece.PLAYER2));
        setPiece(new Pawn(Position.B7, Piece.PLAYER2));
        setPiece(new Pawn(Position.C7, Piece.PLAYER2));
        setPiece(new Pawn(Position.D7, Piece.PLAYER2));
        setPiece(new Pawn(Position.E7, Piece.PLAYER2));
        setPiece(new Pawn(Position.F7, Piece.PLAYER2));
        setPiece(new Pawn(Position.G7, Piece.PLAYER2));
        setPiece(new Pawn(Position.H7, Piece.PLAYER2));
        setPiece(new Pawn(Position.A2, PLAYER1));
        setPiece(new Pawn(Position.B2, PLAYER1));
        setPiece(new Pawn(Position.C2, PLAYER1));
        setPiece(new Pawn(Position.D2, PLAYER1));
        setPiece(new Pawn(Position.E2, PLAYER1));
        setPiece(new Pawn(Position.F2, PLAYER1));
        setPiece(new Pawn(Position.G2, PLAYER1));
        setPiece(new Pawn(Position.H2, PLAYER1));
        setPiece(new Rook(Position.A1, PLAYER1));
        setPiece(new Knight(Position.B1, PLAYER1));
        setPiece(new Bishop(C1, PLAYER1));
        setPiece(new Queen(Position.D1, PLAYER1));
        setPiece(new King(Position.E1, PLAYER1));
        setPiece(new Bishop(Position.F1, PLAYER1));
        setPiece(new Knight(Position.G1, PLAYER1));
        setPiece(new Rook(Position.H1, PLAYER1));
    }
}
