package ca.ciccc.wmad.kaden.chess.process.progress;

import ca.ciccc.wmad.kaden.chess.model.board.Board;
import ca.ciccc.wmad.kaden.chess.model.piece.Piece;

public class ProgressNode {

    private Board board;
    private ProgressNode parent;
    private Piece[] pieces;
    private ProgressNode[][] children;

    private final int nthPieceInParent, nthMovement, turnCount;
    private final boolean turn;
    private int depth;
    private int[] scores;

    public ProgressNode(Board board, int turnCount, boolean turn, int nthPieceInParent, int nthMovement) {
        this.board = board;
        this.turnCount = turnCount;
        this.turn = turn;
        this.nthPieceInParent = nthPieceInParent;
        this.nthMovement = nthMovement;
        this.depth = 0;

        pieces = board.getAllPieces(turn);
        children = new ProgressNode[pieces.length][];
        for (int i = 0; i < children.length; ++i) {
            children[i] = new ProgressNode[pieces[i].getPotentialMovement().size()];
        }

        calculateScores();
    }

    public void setParent(ProgressNode parent) {
        this.parent = parent;
        updateParentDepth(parent, 1);
    }

    public int[] getScores() {
        return this.scores;
    }

    public void addChild(ProgressNode node) {
        children[node.nthPieceInParent][node.nthMovement] = node;
        node.setParent(this);
    }

    public ProgressNode[][] getChildren() {
        return children;
    }

    public ProgressNode getChild(int nthPiece, int nthMovement) {
        return children[nthPiece][nthMovement];
    }

    public ProgressNode getParent() {
        return parent;
    }

    public Piece getPiece(int nthPiece) {
        return pieces[nthPiece];
    }

    public int getTurnCount() {
        return turnCount;
    }

    public boolean getTurn() {
        return turn;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isRoot() {
        return (turnCount == 0);
    }

    private void updateParentDepth(ProgressNode parent, int depth) {
        if (this.depth < depth) {
            this.depth = depth;
        }
        if (!isRoot()) {
            updateParentDepth(parent.getParent(), depth + 1);
        }
    }

    private void calculateScores() {
        // TODO: Implement For AI
    }
}
