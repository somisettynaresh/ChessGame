package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.Piece;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Spot{
    int x;
    int y;
    Piece piece;

    public Spot(int x, int y, Piece piece) {
        super();
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public Spot(int i, int j) {
        super();
        this.x = i;
        this.y = j;
        this.piece = null;
    }

    // return original piece
    public Piece occupySpot(Piece piece){
        Piece origin = this.piece;
        //if piece already here, delete it, i. e. set it dead
        if(this.piece != null) {
            this.piece.setAlive(false);
        }
        piece.setX(x);
        piece.setY(y);
        //place piece here
        this.piece = piece;
        return origin;
    }

    public boolean isOccupied() {
        if(piece != null)
            return true;
        return false;
    }

    public Piece releaseSpot() {
        Piece releasedPiece = this.piece;
        this.piece = null;
        return releasedPiece;
    }

    public Piece getPiece() {
        return this.piece;
    }


}