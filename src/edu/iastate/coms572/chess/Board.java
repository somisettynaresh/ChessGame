package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Board implements Serializable, Closeable {

    /**
     * Getter for property 'spots'.
     *
     * @return Value for property 'spots'.
     */
    public Spot[][] getSpots() {
        return spots;
    }

    private Spot[][] spots;

    /**
     * Getter for property 'win'.
     *
     * @return Value for property 'win'.
     */
    public boolean isWin() {
        return win;
    }

    /**
     * Setter for property 'win'.
     *
     * @param win Value to set for property 'win'.
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    boolean win; // mark the win or not
    private List<Move> history = new ArrayList();

    public Board() {
        win = false;
        spots = new Spot[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                spots[i][j] = new Spot(i, j);
            }
        }
    }


    public void initialize(Player p) {
        // put the pieces with initial status
        List<Piece> pieces = p.getPieces();
        for (int i = 0; i < pieces.size(); i++) {
            Spot spot = new Spot(pieces.get(i).getRow(), pieces.get(i).getCol(), pieces.get(i));
            spots[pieces.get(i).getRow()][pieces.get(i).getCol()] = spot;
        }
    }

    public boolean executeMove(Board board, Move move) {
        history.add(move);
        move.getPiece().setHasMoved(true);
        //resetPiecePositions(board);
        Piece piece = move.getPiece();
        Spot[][] spots = board.getSpots();
        // check and change the state on spot
        spots[move.curRow][move.curCol].piece = null;
        Piece taken = spots[move.desRow][move.desCol].occupySpot(piece);
        if (taken != null && taken.getPieceType().equals(PieceType.King)) {
            Game.getInstance().setWin(true);
            Game.getInstance().setWinner(Game.getCurrentPlayer());
            Game.chessGUI.endGame(Game.getCurrentPlayer().color.name());
        }

        return true;
    }

    private Piece resetPiece(Board board, Piece piece) {
        Spot[][] spots = board.getSpots();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (spots[row][col].getPiece() == piece) {
                    piece.setCol(col);
                    piece.setRow(row);
                }
            }
        }
        return piece;
    }

    private void resetPiecePositions(Board board) {
        Spot[][] spots = board.getSpots();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (spots[row][col].getPiece() != null) {
                    spots[row][col].piece.setCol(col);
                    spots[row][col].piece.setRow(row);
                }
            }
        }
    }

    public boolean simulateExecuteMove(Board board, Move move) {
        Spot[][] spots = board.getSpots();
        Piece piece = spots[move.curRow][move.curCol].getPiece();
        piece.setHasMoved(true);
        // check and change the state on spot
        spots[move.curRow][move.curCol].piece = null;
        Piece taken = spots[move.desRow][move.desCol].occupySpot(piece);
        if (taken != null && taken.getPieceType().equals(PieceType.King)) {
            board.setWin(true);

        }
        return true;
    }

    public boolean getWin() {
        return win;
    }

    public Board deepClone1() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Board) ois.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public Board deepClone() {
        Board board = new Board();
        Spot[][] spots = new Spot[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Spot cSpot = this.spots[row][col];
                Piece cPiece = this.spots[row][col].getPiece();
                Piece piece = null;
                if (cPiece != null) {
                    switch (cPiece.getPieceType()) {
                        case Pawn:
                            piece = new Pawn(cPiece);
                            break;
                        case Bishop:
                            piece = new Bishop(cPiece);
                            break;
                        case Queen:
                            piece = new Queen(cPiece);
                            break;
                        case King:
                            piece = new King(cPiece);
                            break;
                        case Knight:
                            piece = new Knight(cPiece);
                            break;
                        case Rook:
                            piece = new Rook(cPiece);
                            break;

                    }
                }
                spots[row][col] = new Spot(cSpot.row, cSpot.col, piece);
                board.win = this.win;
                board.history = this.history;
            }
        }
        board.spots = spots;
        return board;
    }

    public Board clone() {
        try {
            return (Board) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }


    @Override
    public void close() throws IOException {

    }

    public List<Move> getHistory() {
        return history;
    }

    public void setHistory(List<Move> history) {
        this.history = history;
    }
}