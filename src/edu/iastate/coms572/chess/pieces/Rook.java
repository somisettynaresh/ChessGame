package edu.iastate.coms572.chess.pieces;

import edu.iastate.coms572.chess.Board;
import edu.iastate.coms572.chess.Move;
import edu.iastate.coms572.chess.Spot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naresh on 11/17/2016.
 */

public class Rook extends Piece {
    public Rook(boolean isAlive, int x, int y, PieceColor pieceColor) {
        super(isAlive,x,y,pieceColor,PieceType.Rook);
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
    	
    	List<Move> legalMoves = null;
    	
    	if(isAlive()){
    		legalMoves = this.getPossibleMovesForRook(board, fromX, fromY);
    		return legalMoves;
    	}
    	
        return null;
    }
    
    
  //Getting all possible moves if the piece were Rook
    private List<Move> getPossibleMovesForRook(Board board, int fromX, int fromY){
    	
    	Spot[][] curSpots = board.getSpots();
    	ArrayList<Move> legalMoves = new ArrayList<Move>();
    	
    	//Keep going Up or Down until you find a target
    	//1. UP
    	int i = 1;
    	while((fromY+i) < 8){
    		if(curSpots[fromX][fromY+i].isOccupied()){ //Occupied
    			
    			if(curSpots[fromX][fromY+i].getPiece().getColor() != this.getColor()){//Occupied and Different color
        			//Add as a legal move
        			legalMoves.add(new Move(this,
        					fromX,
    						fromY,
    						fromX,
    						fromY+i));
    			}

    			//In any case, you need to join out of loop
    			break;
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					fromX,
										fromY,
										fromX,
										fromY+i));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
    	//2. DOWN
    	i = 1;
    	while((fromY-i) > -1){
    		if(curSpots[fromX][fromY-i].isOccupied()){ //Occupied
    			
    			if(curSpots[fromX][fromY-i].getPiece().getColor() != this.getColor()){//Occupied and Different color
        			//Add as a legal move
        			legalMoves.add(new Move(this,
        					fromX,
    						fromY,
    						fromX,
    						fromY-i));
    			}

    			//In any case, you need to join out of loop
    			break;
    			
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					fromX,
										fromY,
										fromX,
										fromY-i));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
    	
    	//3. LEFT
    	i = 1;
    	while((fromX-i) > -1){
    		if(curSpots[fromX-i][fromY].isOccupied()){ //Occupied
    			if(curSpots[fromX-i][fromY].getPiece().getColor() != this.getColor()){//Occupied and Different color
        			//Add as a legal move
        			legalMoves.add(new Move(this,
        					fromX,
    						fromY,
    						fromX-i,
    						fromY));
    			}

    			//In any case, you need to join out of loop
    			break;
    			
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					fromX,
										fromY,
										fromX-i,
										fromY));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
    	//4. RIGHT
    	i = 1;
    	while((fromX+i) < 8){
    		if(curSpots[fromX+i][fromY].isOccupied()){ //Occupied
    			if(curSpots[fromX+i][fromY].getPiece().getColor() != this.getColor()){//Occupied and Different color
        			//Add as a legal move
        			legalMoves.add(new Move(this,
        					fromX,
    						fromY,
    						fromX+i,
    						fromY));
    			}

    			//In any case, you need to join out of loop
    			break;
    			
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					fromX,
										fromY,
										fromX+i,
										fromY));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
		return legalMoves;
    }
   
}
