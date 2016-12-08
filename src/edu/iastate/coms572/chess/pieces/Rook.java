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
    		legalMoves = this.getPossibleMovesForRook(board, fromRow, fromCol);
    		return legalMoves;
    	}
    	
        return null;
    }

	@Override
	public int getValue() {
		return 5;
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

	public Rook(Piece piece) {
		super(piece);

	}
}
