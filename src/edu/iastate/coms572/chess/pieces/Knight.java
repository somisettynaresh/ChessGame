package edu.iastate.coms572.chess.pieces;

import edu.iastate.coms572.chess.Board;
import edu.iastate.coms572.chess.Move;
import edu.iastate.coms572.chess.Spot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Knight extends Piece {
    public Knight(boolean isAlive, int x, int y, PieceColor pieceColor) {
        super(isAlive,x,y,pieceColor,PieceType.Knight);
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
    		legalMoves = this.getPossibleMovesForKnight(board, fromX, fromY);
    		return legalMoves;
    	}
    	
        return null;
    }
    
    
    //Getting all possible moves if the piece were Knight
    private List<Move> getPossibleMovesForKnight(Board board, int fromX, int fromY){
    	
    	Spot[][] curSpots = board.getSpots();
    	ArrayList<Move> legalMoves = new ArrayList<Move>();
    	boolean canMove = true;
    	
    	//Keep all 8 possibilities
    	//1. Up & Right
    	if((this.getY()+2) < 8 || (this.getX()+1) < 8 ){ //Move should not send knight out of board
    		if(curSpots[this.getX()+1][this.getY()+2].isOccupied()){ // Occupied
        		if(curSpots[this.getX()+1][this.getY()+2].getPiece().getColor() == this.getColor()){ //Same color
        			canMove = false;
        		}	
    		}
    		
    		if(canMove){
    			legalMoves.add(new Move(this,
    					this.getX(),
						this.getY(),
						this.getX()+1,
						this.getY()+2));
    		}
    	}
    	
    	
    	//2. Up & Left
		canMove = true;	
		if((this.getY()+2) <8 || (this.getX()-1) > -1 ){ //Move should not send knight out of board
    		if(curSpots[this.getX()-1][this.getY()+2].isOccupied()){ // Occupied
        		if(curSpots[this.getX()-1][this.getY()+2].getPiece().getColor() == this.getColor()){ //Same color
        			canMove = false;
        		}
    		}
    		
    		if(canMove){
    			legalMoves.add(new Move(this,
    					this.getX(),
						this.getY(),
						this.getX()-1,
						this.getY()+2));
    		}
    	}
    	
		
		//3. Left & Up
		canMove = true;	
		if((this.getY()+1) <8 || (this.getX()-2) > -1 ){ //Move should not send knight out of board
    		if(curSpots[this.getX()-2][this.getY()+1].isOccupied()){ // Occupied
        		if(curSpots[this.getX()-2][this.getY()+1].getPiece().getColor() == this.getColor()){ //Same color
        			canMove = false;
        		}
    		}
    		
    		if(canMove){
    			legalMoves.add(new Move(this,
    					this.getX(),
						this.getY(),
						this.getX()-2,
						this.getY()+1));
    		}
    	}
		
		//4. Left & Down
		canMove = true;	
		if((this.getY()-1) > -1 || (this.getX()-2) > -1 ){ //Move should not send knight out of board
    		if(curSpots[this.getX()-2][this.getY()-1].isOccupied()){ // Occupied
        		if(curSpots[this.getX()-2][this.getY()-1].getPiece().getColor() == this.getColor()){ //Same color
        			canMove = false;
        		}
    		}
    		
    		if(canMove){
    			legalMoves.add(new Move(this,
    					this.getX(),
						this.getY(),
						this.getX()-2,
						this.getY()-1));
    		}
    	}
		
		//5. Down & Left
		canMove = true;	
		if((this.getY()-2) > -1 || (this.getX()-1) > -1 ){ //Move should not send knight out of board
    		if(curSpots[this.getX()-1][this.getY()-2].isOccupied()){ // Occupied
        		if(curSpots[this.getX()-1][this.getY()-2].getPiece().getColor() == this.getColor()){ //Same color
        			canMove = false;
        		}	
    		}
    		
    		if(canMove){
    			legalMoves.add(new Move(this,
    					this.getX(),
						this.getY(),
						this.getX()-1,
						this.getY()-2));
    		}
    	}
		
		//6. Down & Right
		canMove = true;	
		if((this.getY()-2) > -1 || (this.getX()+1) <8 ){ //Move should not send knight out of board
    		if(curSpots[this.getX()+1][this.getY()-2].isOccupied()){ // Occupied
        		if(curSpots[this.getX()+1][this.getY()-2].getPiece().getColor() == this.getColor()){ //Same color
        			canMove = false;
        		}
    		}
    		
    		if(canMove){
    			legalMoves.add(new Move(this,
    					this.getX(),
						this.getY(),
						this.getX()+1,
						this.getY()-2));
    		}
    	}
		
		//7. Right & Down
		canMove = true;	
		if((this.getY()-1) > -1 || (this.getX()+2) <8 ){ //Move should not send knight out of board
    		if(curSpots[this.getX()+2][this.getY()-1].isOccupied()){ // Occupied
        		if(curSpots[this.getX()+2][this.getY()-1].getPiece().getColor() == this.getColor()){ //Same color
        			canMove = false;
        		}
    		}
    		
    		if(canMove){
    			legalMoves.add(new Move(this,
    					this.getX(),
						this.getY(),
						this.getX()+2,
						this.getY()-1));
    		}
    	}
		
		//8. Right & Up
		canMove = true;	
		if((this.getY()+1) < 8 || (this.getX()+ 2) <8 ){ //Move should not send knight out of board
    		if(curSpots[this.getX()+2][this.getY()+1].isOccupied()){ // Occupied
        		if(curSpots[this.getX()+2][this.getY()+1].getPiece().getColor() == this.getColor()){ //Same color
        			canMove = false;
        		}
    		}
    		
    		if(canMove){
    			legalMoves.add(new Move(this,
    					this.getX(),
						this.getY(),
						this.getX()+2,
						this.getY()-1));
    		}
    	}
    	
		return legalMoves;
    }
}
