package ca.ciccc.wmad.kaden.chess.process.progress;

import ca.ciccc.wmad.kaden.chess.model.board.Board;
import ca.ciccc.wmad.kaden.chess.model.piece.Movement;
import ca.ciccc.wmad.kaden.chess.model.piece.Piece;

import java.util.ArrayList;

public class ProgressTree {

    private ProgressNode root, currentNode;

    public ProgressTree(Board board, boolean turn) {
        root = new ProgressNode(board, 0, turn, -1, -1);
        currentNode = root;
    }

    public void initialize() {
        pruning(currentNode);
    }

    public void destroy() {
        currentNode = null;
        root = null;
        System.gc();
    }

    public ProgressNode getCurrentNode() {
        return currentNode;
    }

    public void toChildNode(int nthPiece, int nthMovement) {
        currentNode = currentNode.getChild(nthPiece, nthMovement);
    }

    public void toParentNode() {
        currentNode = currentNode.getParent();
    }

    public void pruning(ProgressNode node) {
        Piece[] allPieces = node.getBoard().getAllPieces(node.getTurn());
        for (Piece piece : allPieces) {
            piece.updatePossibleMovement();
        }

        for (int nthPiece = 0; nthPiece < allPieces.length; ++nthPiece) {
            Piece piece = node.getPiece(nthPiece);
            ArrayList<Movement> possibleMovement = piece.getPossibleMovement();
            for (int nthMovement = 0; nthMovement < possibleMovement.size(); ++nthMovement) {
                Board board = node.getBoard().cloneBoard();
                board.movePiece(piece.getPosition(), possibleMovement.get(nthMovement));
                node.addChild(new ProgressNode(board, node.getTurnCount() + 1, !node.getTurn(), nthPiece, nthMovement));
            }
        }
    }

}
