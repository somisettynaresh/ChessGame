package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Board{

    private Spot[][] spots;
    private boolean win; // mark the win or not
    private List<Move> history = new ArrayList();
    public Board(){
        win = false;
        spots = new Spot[8][8];
    }

    public void initialize(Player p){
        // put the pieces with initial status
        for(int i=0; i<p.getPieces().size(); i++){
            spots[p.getPieces().get(i).getX()][p.getPieces().get(i).getY()].occupySpot(p.getPieces().get(i));
        }
    }

    public boolean executeMove(Player p) {
        Move cmd = p.getMove();
        Piece piece = cmd.getPiece();


        // check the two pieces side
        if(spots[cmd.desX][cmd.desY] != null && spots[cmd.desX][cmd.desY].getPiece().getColor() == piece.getColor())
            return false;

        // check and change the state on spot
        Piece taken = spots[cmd.desX][cmd.desY].occupySpot(piece);
        if(taken != null &&taken.getClass().getName().equals("King"))
            this.win = true;
        spots[cmd.curX][cmd.curY].releaseSpot();
        return true;
    }

    public boolean getWin() {
        return win;
    }
}