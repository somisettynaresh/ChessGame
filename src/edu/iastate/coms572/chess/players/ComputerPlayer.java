package edu.iastate.coms572.chess.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.iastate.coms572.chess.Board;
import edu.iastate.coms572.chess.Game;
import edu.iastate.coms572.chess.Move;
import edu.iastate.coms572.chess.Player;
import edu.iastate.coms572.chess.pieces.Piece;
import edu.iastate.coms572.chess.pieces.PieceColor;

/**
 * Created by Naresh on 11/17/2016.
 */
public class ComputerPlayer extends Player{
    public ComputerPlayer(PieceColor color, String name) {
        super(color, name);
    }

    @Override
    public Move getMove() {
    	List<Move> legalMoves = getLegalMoves(Game.getBoard());
        return legalMoves.get(new Random().nextInt(legalMoves.size()-1));
    }
    

    public List<Move> getLegalMoves(Board board){
    	//1. Get list of all pieces of the computer
    	List<Piece> pieceList = getPieces();
    	
    	//2. Get the list of all legal moves
    	ArrayList<Move> legalMoves = new ArrayList<Move>();
    	for(Piece pc : pieceList){
            List<Move> moves = pc.getPossibleMoves(board, pc.getX(), pc.getY());
            if(moves!=null)
    		    legalMoves.addAll(moves);
    	}
    	
    	//At this point, for each legal move, we need to do alpha-beta pruning
    	return legalMoves;
    }
    
}
