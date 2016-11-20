package edu.iastate.coms572.chess.players;

import java.util.ArrayList;
import java.util.List;

import edu.iastate.coms572.chess.Board;
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
    	
        return null;
    }
    

    public List<Move> getLegalMoves(Board board){
    	//1. Get list of all pieces of the computer
    	List<Piece> pieceList = getPieces();
    	
    	//2. Get the list of all legal moves
    	ArrayList<Move> legalMoves = new ArrayList<Move>();
    	for(Piece pc : pieceList){
    		legalMoves.addAll(pc.getPossibleMoves(board, pc.getX(), pc.getY()));
    	}
    	
    	//At this point, for each legal move, we need to do alpha-beta pruning
    	return null;
    }
    
}
