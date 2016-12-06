package edu.iastate.coms572.chess.pieces;

import edu.iastate.coms572.chess.Board;
import edu.iastate.coms572.chess.Move;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Naresh on 11/17/2016.
 */


public abstract class Piece implements Serializable{
    private int row;
    private int col;

    private boolean alive; // mark the live or dead
    private PieceColor color; // mark the owner
    private PieceType pieceType;

    /**
     * Getter for property 'color'.
     *
     * @return Value for property 'color'.
     */
    public PieceColor getColor() {
        return color;
    }

    /**
     * Setter for property 'color'.
     *
     * @param color Value to set for property 'color'.
     */
    public void setColor(PieceColor color) {
        this.color = color;
    }

    /**
     * Getter for property 'pieceType'.
     *
     * @return Value for property 'pieceType'.
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * Setter for property 'pieceType'.
     *
     * @param pieceType Value to set for property 'pieceType'.
     */
    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public Piece(boolean alive, int row, int col, PieceColor color, PieceType pieceType) {
        super();
        this.alive = alive;
        this.row = row;
        this.col = col;
        this.color = color;
        this.pieceType = pieceType;
    }


    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }

    public abstract boolean isValidMove(Board board, int fromRow, int fromCol, int toRow, int toCol);
    public abstract List<Move> getPossibleMoves(Board board, int fromRow, int fromCol);

    public String getPath() {
        return getColor().toString() +"_" + getPieceType().toString() + ".png";
    }
    public abstract int getValue();
}
