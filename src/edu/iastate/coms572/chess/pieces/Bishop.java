package edu.iastate.coms572.chess.pieces;

import edu.iastate.coms572.chess.Board;
import edu.iastate.coms572.chess.Move;
import edu.iastate.coms572.chess.Spot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Bishop extends Piece {
    public Bishop(boolean isAlive, int x, int y, PieceColor pieceColor) {
        super(isAlive,x,y,pieceColor,PieceType.Bishop);
    }

    @Override
    public boolean isValidMove(Board board, int fromRow, int fromCol, int toRow, int toCol) {
    	if(fromRow < 0 || fromRow > 7 ||
			fromCol < 0 || fromCol > 7||
			toRow < 0 || toRow > 7||
			toCol < 0 || toCol > 7){
    		return false;
    	}
    	
    	List<Move> legalMoves = getPossibleMoves(board, fromRow, fromCol);
    	
    	for(Move possibleMove : legalMoves){
    		if(possibleMove.getDesX() == toRow && possibleMove.getDesCol() == toCol)
    			return true;
    	}
    	
        return false;
    }

    @Override
    public List<Move> getPossibleMoves(Board board, int fromRow, int fromCol) {
    	
    	List<Move> legalMoves = null;
    	
    	if(isAlive()){
    		legalMoves = this.getPossibleMovesForBishop(board, fromRow, fromCol);
    		return legalMoves;
    	}
    	
        return null;
    }

	@Override
	public int getValue() {
		return 3;
	}


	private List<Move> getPossibleMovesForBishop(Board board, int fromX, int fromY){
    	Spot[][] curSpots = board.getSpots();
    	ArrayList<Move> legalMoves = new ArrayList<Move>();
    	
    	
    	//Keep going Up or Down until you find a target
    	//1. upper left side
    	int i = 1;
    	while((fromY+i) < 8 && (fromX-i) > -1){
    		if(curSpots[fromX-i][fromY+i].isOccupied()){//Occupied
    			//Include move only if Piece if of other color
				if(curSpots[fromX-i][fromY+i].getPiece().getColor() != this.getColor()){
					legalMoves.add(new Move(this,
	    					fromX,
							fromY,
							fromX-i,
							fromY+i));
				}
				
				break;
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					fromX,
										fromY,
										fromX-i,
										fromY+i));
    			
    		}//End occupied If condition
    		
    		i++;
    	}
    	
    	//2. Upper Right
    	i = 1;
    	while((fromY+i) < 8 && (fromX+i) < 8){
    		if(curSpots[fromX+i][fromY+i].isOccupied()){
    			//Include move only if Piece if of other color
				if(curSpots[fromX+i][fromY+i].getPiece().getColor() != this.getColor()){
					legalMoves.add(new Move(this,
	    					fromX,
							fromY,
							fromX+i,
							fromY+i));
				}
				
				break;
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					fromX,
										fromY,
										fromX+i,
										fromY+i));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
    	
    	//3. lower left
    	i = 1;
    	while((fromX-i) > -1 && (fromY-i) > -1){
    		if(curSpots[fromX-i][fromY-i].isOccupied()){
    			//Include move only if Piece if of other color
				if(curSpots[fromX-i][fromY-i].getPiece().getColor() != this.getColor()){
					legalMoves.add(new Move(this,
	    					fromX,
							fromY,
							fromX-i,
							fromY-i));
				}
				
				break;
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					fromX,
										fromY,
										fromX-i,
										fromY-i));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
    	//4. Lower Right
    	i = 1;
    	while((fromX+i) < 8 && (fromY-i) > -1){
    		if(curSpots[fromX+i][fromY-i].isOccupied()){
    			//Include move only if Piece if of other color
				if(curSpots[fromX+i][fromY-i].getPiece().getColor() != this.getColor()){
					legalMoves.add(new Move(this,
	    					fromX,
							fromY,
							fromX+i,
							fromY-i));
				}
				
				break;
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					fromX,
										fromY,
										fromX+i,
										fromY-i));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
		return legalMoves;
    }
    
}
