package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Board{

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
            Spot spot = new Spot(p.getPieces().get(i).getX(), p.getPieces().get(i).getY(),p.getPieces().get(i));
            spots[p.getPieces().get(i).getX()][p.getPieces().get(i).getY()] = spot;
        }
    }

    public boolean executeMove(Move move) {
        Piece piece = move.getPiece();

        // check and change the state on spot
        spots[move.curX][move.curY].releaseSpot();
        Piece taken = spots[move.desX][move.desY].occupySpot(piece);
        if(taken != null &&taken.getClass().getName().equals("King"))
            this.win = true;

        return true;
    }

    public boolean getWin() {
        return win;
    }
}