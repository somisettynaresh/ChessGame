package edu.iastate.coms572.chess.pieces;

import edu.iastate.coms572.chess.Board;
import edu.iastate.coms572.chess.Move;

import java.util.List;

/**
 * Created by Naresh on 11/17/2016.
 */


public abstract class Piece {
    private int x;
    private int y;

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

    public Piece(boolean alive, int x, int y, PieceColor color, PieceType pieceType) {
        super();
        this.alive = alive;
        this.x = x;
        this.y = y;
        this.color = color;
        this.pieceType = pieceType;
    }


    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public abstract boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY);
    public abstract List<Move> getPossibleMoves(Board board, int fromX, int fromY);

}
