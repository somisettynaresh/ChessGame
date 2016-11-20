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
    	
    	List<Move> legalMoves = getPossibleMoves(board, fromX, fromY);
    	
    	for(Move possibleMove : legalMoves){
    		if(possibleMove.getDesX() == toX && possibleMove.getDesY() == toY)
    			return true;
    	}
    	
        return false;
    }

    @Override
    public List<Move> getPossibleMoves(Board board, int fromX, int fromY) {
    	Spot[][] curSpots = board.getSpots();
    	ArrayList<Move> legalMoves = new ArrayList<Move>();
    	
    	if(fromX < 0 || fromX > 7 || 
			fromY < 0 || fromY > 7){
    		return null;
    	}
    	
    	
    	boolean canMove = true;
    	//Look at all neighboring square for possible moves
    	//1. Upper 3 spots
    	if((this.getY()+1) < 8){
    		//Look at all 3 possible values of X and add to legal moves
    		for(int j=-1; j<=1;j++){
    			if((this.getX()+j) >=0 && (this.getX()+j) < 8){
    				canMove = true;
    				if(curSpots[this.getX()+j][this.getY()+1].isOccupied()){ //Occupied
    	    			if(curSpots[this.getX()+j][this.getY()+1].getPiece().getColor() == this.getColor()){
    	    				canMove = false;
    	    			}
    	    		}
    				
    				//If possible, add the MOVE
    				if(canMove){
    		    		legalMoves.add(new Move(this,
    							this.getX(),
    							this.getY(),
    							this.getX()+j,
    							this.getY()+1));
    		    	}
    			}
    		}
    	}
    	
    	canMove = true;
    	//Look at all neighboring square for possible moves
    	//1. Lower 3 spots
    	if((this.getY()-1) > -1){
    		//Look at all 3 possible values of X and add to legal moves
    		for(int j=-1; j<=1;j++){
    			if((this.getX()+j) >=0 && (this.getX()+j) < 8){
    				canMove = true;
    				if(curSpots[this.getX()+j][this.getY()-1].isOccupied()){ //Occupied
    	    			if(curSpots[this.getX()+j][this.getY()-1].getPiece().getColor() == this.getColor()){
    	    				canMove = false;
    	    			}
    	    		}
    				
    				//If possible, add the MOVE
    				if(canMove){
    		    		legalMoves.add(new Move(this,
    							this.getX(),
    							this.getY(),
    							this.getX()+j,
    							this.getY()-1));
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
			if((this.getX()+j) >=0 && (this.getX()+j) < 8){
				canMove = true;
				if(curSpots[this.getX()+j][this.getY()].isOccupied()){ //Occupied
	    			if(curSpots[this.getX()+j][this.getY()].getPiece().getColor() == this.getColor()){
	    				canMove = false;
	    			}
	    		}
				
				//If possible, add the MOVE
				if(canMove){
		    		legalMoves.add(new Move(this,
							this.getX(),
							this.getY(),
							this.getX()+j,
							this.getY()));
		    	}
			}
		}
    	
        return null;
    }

}
