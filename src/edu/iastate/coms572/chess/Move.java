package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.Piece;
import edu.iastate.coms572.chess.pieces.Rook;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Move {


    /**
     * Getter for property 'piece'.
     *
     * @return Value for property 'piece'.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Setter for property 'piece'.
     *
     * @param piece Value to set for property 'piece'.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Getter for property 'curX'.
     *
     * @return Value for property 'curX'.
     */
    public int getCurX() {
        return curX;
    }

    /**
     * Setter for property 'curX'.
     *
     * @param curX Value to set for property 'curX'.
     */
    public void setCurX(int curX) {
        this.curX = curX;
    }

    /**
     * Getter for property 'curY'.
     *
     * @return Value for property 'curY'.
     */
    public int getCurY() {
        return curY;
    }

    /**
     * Setter for property 'curY'.
     *
     * @param curY Value to set for property 'curY'.
     */
    public void setCurY(int curY) {
        this.curY = curY;
    }

    /**
     * Getter for property 'desX'.
     *
     * @return Value for property 'desX'.
     */
    public int getDesX() {
        return desX;
    }

    /**
     * Setter for property 'desX'.
     *
     * @param desX Value to set for property 'desX'.
     */
    public void setDesX(int desX) {
        this.desX = desX;
    }

    /**
     * Getter for property 'desY'.
     *
     * @return Value for property 'desY'.
     */
    public int getDesY() {
        return desY;
    }

    /**
     * Setter for property 'desY'.
     *
     * @param desY Value to set for property 'desY'.
     */
    public void setDesY(int desY) {
        this.desY = desY;
    }

    Piece piece;
    int curX, curY, desX, desY;
    public Move(Piece piece,int desX, int desY) {
        this.piece = piece;
        this.curX = piece.getX();
        this.curY = piece.getY();
        this.desX = desX;
        this.desY = desY;
    }

    public Move(Piece piece,int fromY, int fromX, int desX, int desY) {
        this.piece = piece;
        this.curX = fromX;
        this.curY = fromY;
        this.desX = desX;
        this.desY = desY;
    }
}