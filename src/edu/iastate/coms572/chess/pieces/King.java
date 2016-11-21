package edu.iastate.coms572.chess.pieces;

import edu.iastate.coms572.chess.Board;
import edu.iastate.coms572.chess.Move;
import edu.iastate.coms572.chess.Player;
import edu.iastate.coms572.chess.Spot;
import edu.iastate.coms572.chess.players.HumanPlayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Naresh on 11/17/2016.
 */
public class King extends Piece {
	
    public King(boolean isAlive, int x, int y, PieceColor pieceColor) {
        super(isAlive, x, y, pieceColor, PieceType.King);
    }
    
    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) {
    	if(fromX < 0 || fromX > 7 || 
			fromY < 0 || fromY > 7||
			toX < 0 || toX > 7||
			toY < 0 || toY > 7){
    		return false;
    	}
    	
    	List<Move> legalMoves = getPossibleMovesForKing(board, fromX, fromY);
    	
    	for(Move possibleMove : legalMoves){
    		if(possibleMove.getDesX() == toX && possibleMove.getDesY() == toY)
    			return true;
    	}
    	
        return false;
    }

    @Override
    public List<Move> getPossibleMoves(Board board, int fromX, int fromY) {
    	
    	List<Move> legalMoves = null;
    	
    	if(isAlive()){
    		legalMoves = this.getPossibleMovesForKing(board, fromX, fromY);		
    		return legalMoves;
    	}
    	
        return null;
    }
    
    public List<Move> getPossibleMovesForKing(Board board, int fromX, int fromY) {
    	Spot[][] curSpots = board.getSpots();
    	ArrayList<Move> legalMoves = new ArrayList<Move>();
    	
    	if(fromX < 0 || fromX > 7 || 
			fromY < 0 || fromY > 7){
    		return null;
    	}
    	
    	
    	boolean canMove = true;
    	//Look at all neighboring square for possible moves
    	//1. Upper 3 spots
    	if((fromY+1) < 8){
    		//Look at all 3 possible values of X and add to legal moves
    		for(int j=-1; j<=1;j++){
    			if((fromX+j) >=0 && (fromX+j) < 8){
    				canMove = true;
    				if(curSpots[fromX+j][fromY+1].isOccupied()){ //Occupied
    	    			if(curSpots[fromX+j][fromY+1].getPiece().getColor() == this.getColor()){
    	    				canMove = false;
    	    			}
    	    		}
    				
    				//If possible, add the MOVE
    				if(canMove){
    		    		legalMoves.add(new Move(this,
    							fromX,
    							fromY,
    							fromX+j,
    							fromY+1));
    		    	}
    			}
    		}
    	}
    	
    	canMove = true;
    	//Look at all neighboring square for possible moves
    	//1. Lower 3 spots
    	if((fromY-1) > -1){
    		//Look at all 3 possible values of X and add to legal moves
    		for(int j=-1; j<=1;j++){
    			if((fromX+j) >=0 && (fromX+j) < 8){
    				canMove = true;
    				if(curSpots[fromX+j][fromY-1].isOccupied()){ //Occupied
    	    			if(curSpots[fromX+j][fromY-1].getPiece().getColor() == this.getColor()){
    	    				canMove = false;
    	    			}
    	    		}
    				
    				//If possible, add the MOVE
    				if(canMove){
    		    		legalMoves.add(new Move(this,
    							fromX,
    							fromY,
    							fromX+j,
    							fromY-1));
    		    	}
    			}
    		}
    	}
    	
    	//Last, the remaining side 2
    	canMove = true;
    	//Look at all neighboring square for possible moves
    	//1. Side 2 spots
    	
		//Look at all 2 possible values of X and add to legal moves
		for(int j=-1; j<=1;j+=2){
			if((fromX+j) >=0 && (fromX+j) < 8){
				canMove = true;
				if(curSpots[fromX+j][fromY].isOccupied()){ //Occupied
	    			if(curSpots[fromX+j][fromY].getPiece().getColor() == this.getColor()){
	    				canMove = false;
	    			}
	    		}
				
				//If possible, add the MOVE
				if(canMove){
		    		legalMoves.add(new Move(this,
							fromX,
							fromY,
							fromX+j,
							fromY));
		    	}
			}
		}
    	
        return legalMoves;
    }

}
