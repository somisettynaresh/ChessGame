package edu.iastate.coms572.chess.pieces;

import edu.iastate.coms572.chess.Board;
import edu.iastate.coms572.chess.Move;
import edu.iastate.coms572.chess.Spot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Queen extends Piece {
    public Queen(boolean isAlive, int x, int y, PieceColor pieceColor) {
        super(isAlive, x, y, pieceColor, PieceType.Queen);
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (fromX < 0 || fromX > 7 ||
                fromY < 0 || fromY > 7 ||
                toX < 0 || toX > 7 ||
                toY < 0 || toY > 7) {
            return false;
        }

        List<Move> legalMoves = getPossibleMoves(board, fromX, fromY);

        for (Move possibleMove : legalMoves) {
            if (possibleMove.getDesX() == toX && possibleMove.getDesY() == toY)
                return true;
        }

        return false;
    }

    @Override
    public List<Move> getPossibleMoves(Board board, int fromX, int fromY) {

        List<Move> legalMoves = null;

        if (isAlive()) {
            legalMoves = this.getPossibleMovesForQueenAsRook(board, fromX, fromY);
            legalMoves.addAll(this.getPossibleMovesForQueenAsBishop(board, fromX, fromY));
            return legalMoves;
        }

        return null;
    }


    //Getting all possible moves the Queen can make as a Rook
    private List<Move> getPossibleMovesForQueenAsRook(Board board, int fromX, int fromY) {
        Rook rook = new Rook(true, fromX, fromY, this.getColor());
        return rook.getPossibleMoves(board, fromX, fromY);
    }

    //Get all moves the Queen can make as a Bishop
    private List<Move> getPossibleMovesForQueenAsBishop(Board board, int fromX, int fromY) {
        Bishop bishop = new Bishop(true, fromX, fromY, this.getColor());
        return bishop.getPossibleMoves(board, fromX, fromY);
    }

}
