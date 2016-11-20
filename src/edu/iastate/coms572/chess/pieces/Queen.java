package edu.iastate.coms572.chess.pieces;

import edu.iastate.coms572.chess.Board;
import edu.iastate.coms572.chess.Move;
import edu.iastate.coms572.chess.Spot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Queen extends Piece {
    public Queen(boolean isAlive, int x, int y, PieceColor pieceColor) {
        super(isAlive,x,y,pieceColor, PieceType.Queen);
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
    		legalMoves = this.getPossibleMovesForQueenAsRook(board, fromX, fromY);
    		legalMoves.addAll(this.getPossibleMovesForQueenAsBishop(board, fromX, fromY));
    		return legalMoves;
    	}
    	
        return null;
    }
    
    
   //Getting all possible moves the Queen can make as a Rook
    private List<Move> getPossibleMovesForQueenAsRook(Board board, int fromX, int fromY){
    	
    	Spot[][] curSpots = board.getSpots();
    	ArrayList<Move> legalMoves = new ArrayList<Move>();
    	
    	//Keep going Up or Down until you find a target
    	//1. UP
    	int i = 1;
    	while((this.getY()+i) < 8){
    		if(curSpots[this.getX()][this.getY()+i].isOccupied()){ //Occupied
    			
    			if(curSpots[this.getX()][this.getY()+i].getPiece().getColor() != this.getColor()){//Occupied and Different color
        			//Add as a legal move
        			legalMoves.add(new Move(this,
        					this.getX(),
    						this.getY(),
    						this.getX(),
    						this.getY()+i));
    			}

    			//In any case, you need to join out of loop
    			break;
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					this.getX(),
										this.getY(),
										this.getX(),
										this.getY()+i));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
    	//2. DOWN
    	i = 1;
    	while((this.getY()-i) > -1){
    		if(curSpots[this.getX()][this.getY()-i].isOccupied()){ //Occupied
    			
    			if(curSpots[this.getX()][this.getY()-i].getPiece().getColor() != this.getColor()){//Occupied and Different color
        			//Add as a legal move
        			legalMoves.add(new Move(this,
        					this.getX(),
    						this.getY(),
    						this.getX(),
    						this.getY()-i));
    			}

    			//In any case, you need to join out of loop
    			break;
    			
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					this.getX(),
										this.getY(),
										this.getX(),
										this.getY()-i));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
    	
    	//3. LEFT
    	i = 1;
    	while((this.getX()-i) > -1){
    		if(curSpots[this.getX()-i][this.getY()].isOccupied()){ //Occupied
    			if(curSpots[this.getX()-i][this.getY()].getPiece().getColor() != this.getColor()){//Occupied and Different color
        			//Add as a legal move
        			legalMoves.add(new Move(this,
        					this.getX(),
    						this.getY(),
    						this.getX()-i,
    						this.getY()));
    			}

    			//In any case, you need to join out of loop
    			break;
    			
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					this.getX(),
										this.getY(),
										this.getX()-i,
										this.getY()));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
    	//4. RIGHT
    	i = 1;
    	while((this.getX()+i) < 8){
    		if(curSpots[this.getX()+i][this.getY()].isOccupied()){ //Occupied
    			if(curSpots[this.getX()+i][this.getY()].getPiece().getColor() != this.getColor()){//Occupied and Different color
        			//Add as a legal move
        			legalMoves.add(new Move(this,
        					this.getX(),
    						this.getY(),
    						this.getX()+i,
    						this.getY()));
    			}

    			//In any case, you need to join out of loop
    			break;
    			
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					this.getX(),
										this.getY(),
										this.getX()+i,
										this.getY()));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
		return legalMoves;
    }
    
    //Get all moves the Queen can make as a Bishop
    private List<Move> getPossibleMovesForQueenAsBishop(Board board, int fromX, int fromY){
    	Spot[][] curSpots = board.getSpots();
    	ArrayList<Move> legalMoves = new ArrayList<Move>();
    	
    	
    	//Keep going Up or Down until you find a target
    	//1. upper left side
    	int i = 1;
    	while((this.getY()+i) < 8 && (this.getX()-i) > -1){
    		if(curSpots[this.getX()][this.getY()+i].isOccupied()){//Occupied
    			//Include move only if Piece if of other color
				if(curSpots[this.getX()][this.getY()+i].getPiece().getColor() != this.getColor()){
					legalMoves.add(new Move(this,
	    					this.getX(),
							this.getY(),
							this.getX()-i,
							this.getY()+i));
				}
				
				break;
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					this.getX(),
										this.getY(),
										this.getX()-i,
										this.getY()+i));
    			
    		}//End occupied If condition
    		
    		i++;
    	}
    	
    	//2. Upper Right
    	i = 1;
    	while((this.getY()+i) < 8 && (this.getX()+i) < 8){
    		if(curSpots[this.getX()+i][this.getY()+i].isOccupied()){
    			//Include move only if Piece if of other color
				if(curSpots[this.getX()][this.getY()+i].getPiece().getColor() != this.getColor()){
					legalMoves.add(new Move(this,
	    					this.getX(),
							this.getY(),
							this.getX()+i,
							this.getY()+i));
				}
				
				break;
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					this.getX(),
										this.getY(),
										this.getX()+i,
										this.getY()+i));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
    	
    	//3. lower left
    	i = 1;
    	while((this.getX()-i) > -1 && (this.getY()-i) > -1){
    		if(curSpots[this.getX()-i][this.getY()-i].isOccupied()){
    			//Include move only if Piece if of other color
				if(curSpots[this.getX()][this.getY()+i].getPiece().getColor() != this.getColor()){
					legalMoves.add(new Move(this,
	    					this.getX(),
							this.getY(),
							this.getX()-i,
							this.getY()-i));
				}
				
				break;
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					this.getX(),
										this.getY(),
										this.getX()-i,
										this.getY()-i));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
    	//4. Lower Right
    	i = 1;
    	while((this.getX()+i) < 8 && (this.getY()-i) > -1){
    		if(curSpots[this.getX()+i][this.getY()-i].isOccupied()){
    			//Include move only if Piece if of other color
				if(curSpots[this.getX()][this.getY()+i].getPiece().getColor() != this.getColor()){
					legalMoves.add(new Move(this,
	    					this.getX(),
							this.getY(),
							this.getX()+i,
							this.getY()-i));
				}
				
				break;
    		}else{//Not Occupied
    			legalMoves.add(new Move(this,
				    					this.getX(),
										this.getY(),
										this.getX()+i,
										this.getY()-i));
    		}//End occupied If condition
    		
    		i++;
    	}
    	
		return legalMoves;
    }
}
