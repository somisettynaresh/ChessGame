package edu.iastate.coms572.chess.pieces;

import edu.iastate.coms572.chess.Board;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Pawn extends Piece {
    public Pawn(boolean alive, int x, int y, PieceColor color) {
        super(alive,x,y,color, PieceType.Pawn);
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
