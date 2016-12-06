package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.Piece;

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
     * Getter for property 'curRow'.
     *
     * @return Value for property 'curRow'.
     */
    public int getCurRow() {
        return curRow;
    }

    /**
     * Setter for property 'curRow'.
     *
     * @param curRow Value to set for property 'curRow'.
     */
    public void setCurRow(int curRow) {
        this.curRow = curRow;
    }

    /**
     * Getter for property 'curCol'.
     *
     * @return Value for property 'curCol'.
     */
    public int getCurCol() {
        return curCol;
    }

    /**
     * Setter for property 'curCol'.
     *
     * @param curCol Value to set for property 'curCol'.
     */
    public void setCurCol(int curCol) {
        this.curCol = curCol;
    }

    /**
     * Getter for property 'desRow'.
     *
     * @return Value for property 'desRow'.
     */
    public int getDesX() {
        return desRow;
    }

    /**
     * Setter for property 'desRow'.
     *
     * @param desX Value to set for property 'desRow'.
     */
    public void setDesX(int desX) {
        this.desRow = desX;
    }

    /**
     * Getter for property 'desCol'.
     *
     * @return Value for property 'desCol'.
     */
    public int getDesCol() {
        return desCol;
    }

    /**
     * Setter for property 'desCol'.
     *
     * @param desCol Value to set for property 'desCol'.
     */
    public void setDesCol(int desCol) {
        this.desCol = desCol;
    }

    Piece piece;
    int curRow, curCol, desRow, desCol;
    public Move(Piece piece, int desRow, int desCol) {
        this.piece = piece;
        this.curRow = piece.getRow();
        this.curCol = piece.getCol();
        this.desRow = desRow;
        this.desCol = desCol;
    }

    public Move(Piece piece, int fromRow, int fromCol, int desRow, int desCol) {
        this.piece = piece;
        this.curRow = fromRow;
        this.curCol = fromCol;
        this.desRow = desRow;
        this.desCol = desCol;
    }
}