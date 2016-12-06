package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.Piece;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Board implements Serializable,Closeable{

    /**
     * Getter for property 'spots'.
     *
     * @return Value for property 'spots'.
     */
    public Spot[][] getSpots() {
        return spots;
    }

    private Spot[][] spots;
    private boolean win; // mark the win or not
    private List<Move> history = new ArrayList();
    public Board(){
        win = false;
        spots = new Spot[8][8];
        for (int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                spots[i][j] = new Spot(i,j);
            }
        }
    }
    

    public void initialize(Player p){
        // put the pieces with initial status
        for(int i=0; i<p.getPieces().size(); i++){
            Spot spot = new Spot(p.getPieces().get(i).getRow(), p.getPieces().get(i).getCol(),p.getPieces().get(i));
            spots[p.getPieces().get(i).getRow()][p.getPieces().get(i).getCol()] = spot;
        }
    }

    public boolean executeMove(Board board, Move move) {
        Piece piece = move.getPiece();
        Spot[][] spots = board.getSpots();
        // check and change the state on spot
        spots[move.curRow][move.curCol].piece = null;
        Piece taken = spots[move.desRow][move.desCol].occupySpot(piece);
        if(taken != null &&taken.getClass().getName().equals("King"))
            this.win = true;

        return true;
    }

    public boolean getWin() {
        return win;
    }

    public Board deepClone() {
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
}