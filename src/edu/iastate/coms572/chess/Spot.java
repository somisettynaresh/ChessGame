package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.Piece;

import java.io.Serializable;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Spot implements Serializable{
    int row;
    int col;
    Piece piece;

    public Spot(int row, int col, Piece piece) {
        super();
        this.row = row;
        this.col = col;
        this.piece = piece;
    }

    public Spot(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        this.piece = null;
    }

    // return original piece
    public Piece occupySpot(Piece piece){
        Piece origin = this.piece;
        //if piece already here, delete it, i. e. set it dead
        if(this.piece != null) {
            this.piece.setAlive(false);
        }
        piece.setRow(row);
        piece.setCol(col);
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