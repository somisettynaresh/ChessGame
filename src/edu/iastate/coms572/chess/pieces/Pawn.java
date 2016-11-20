package edu.iastate.coms572.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import edu.iastate.coms572.chess.Board;
import edu.iastate.coms572.chess.Game;
import edu.iastate.coms572.chess.Move;
import edu.iastate.coms572.chess.Spot;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Pawn extends Piece {
    public Pawn(boolean alive, int x, int y, PieceColor color) {
        super(alive,x,y,color, PieceType.Pawn);
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
    		//Case for White first
    		if(this.getColor() == PieceColor.White){
    			legalMoves = this.getPossibleMovesForWhite(board, fromX, fromY);
    		}else if(this.getColor() == PieceColor.Black){
    			legalMoves = this.getPossibleMovesForBlack(board, fromX, fromY);
    		}
    		
    		return legalMoves;
    	}
    	
        return null;
    }
    
    
   //Getting all possible moves if the piece were White
    private List<Move> getPossibleMovesForWhite(Board board, int fromX, int fromY){
    	
    	Spot[][] curSpots = board.getSpots();
    	ArrayList<Move> legalMoves = new ArrayList<Move>();
    	
    	//Check for basic forward move
		//If its empty, move
		if(!curSpots[this.getX()][this.getY()+1].isOccupied()){
			legalMoves.add(new Move(this, 
									this.getX(),
									this.getY(),
									this.getX(),
									this.getY()+1));
			
			//SubcaseCase when the piece is moving for the first time
			//In this case, the piece can move 1 step or 2 steps
			if(this.getY() == 1){
				//Check if the spots 2 steps away has a piece.
				if(!curSpots[this.getX()][this.getY()+2].isOccupied()){
    				legalMoves.add(new Move(this, 
    										this.getX(),
    										this.getY(),
    										this.getX(),
    										this.getY()+2));
				}
			}
			
			
		}//end upper if
		
		
		//Now, lets check for KILLING moves on upper left side
		if(this.getX() > 0 ){
			//If occupied by a different color, then add KILL move
			if(curSpots[this.getX()-1][this.getY()+1].isOccupied()){
				if(curSpots[this.getX()-1][this.getY()+1].getPiece().getColor() == PieceColor.Black){
    				//Add this killer move
    				legalMoves.add(new Move(this, 
							this.getX(),
							this.getY(),
							this.getX()-1,
							this.getY()+1));		
    			}
			}
		}//end if
		
		if(this.getX() < 7 ){
			//Now, lets check for KILLING moves on upper right side
			if(curSpots[this.getX()+1][this.getY()+1].isOccupied()){
				//If occupied by a different color, then add KILL move
				if(curSpots[this.getX()+1][this.getY()+1].getPiece().getColor() == PieceColor.Black){
    				//Add this killer move
    				legalMoves.add(new Move(this, 
							this.getX(),
							this.getY(),
							this.getX()+1,
							this.getY()+1));
    			}
			}
			
		}//end if
		
		return legalMoves;
    }
    
    //Getting all possible moves if the piece were Black
    private List<Move> getPossibleMovesForBlack(Board board, int fromX, int fromY){
    	
    	Spot[][] curSpots = board.getSpots();
    	ArrayList<Move> legalMoves = new ArrayList<Move>();
    	
    	//Check for basic downward move
		//If its empty, move
		if(!curSpots[this.getX()][this.getY()-1].isOccupied()){
			legalMoves.add(new Move(this, 
									this.getX(),
									this.getY(),
									this.getX(),
									this.getY()-1));
			
			//SubcaseCase when the piece is moving for the first time
			//In this case, the piece can move 1 step or 2 steps
			if(this.getY() == 6){
				//Check if the spots 2 steps away has a piece.
				if(!curSpots[this.getX()][this.getY()-2].isOccupied()){
    				legalMoves.add(new Move(this, 
    										this.getX(),
    										this.getY(),
    										this.getX(),
    										this.getY()-2));
				}
			}
			
		}//end upper if
		
		
		//Now, lets check for KILLING moves on lower left side
		if(this.getX() > 0 ){
			if(curSpots[this.getX()-1][this.getY()-1].isOccupied()){
				if(curSpots[this.getX()-1][this.getY()-1].getPiece().isAlive() &&
    					curSpots[this.getX()-1][this.getY()+1].getPiece().getColor() == PieceColor.White){
    				//Add this killer move
    				legalMoves.add(new Move(this, 
							this.getX(),
							this.getY(),
							this.getX()-1,
							this.getY()-1));
    				
    			}
			}
		}//end if
		
		if(this.getX() < 7 ){
			//Now, lets check for KILLING moves on upper right side
			if(curSpots[this.getX()+1][this.getY()-1].isOccupied()){
				if(curSpots[this.getX()+1][this.getY()-1].getPiece().isAlive() &&
    					curSpots[this.getX()+1][this.getY()+1].getPiece().getColor() == PieceColor.White){
    				//Add this killer move
    				legalMoves.add(new Move(this, 
							this.getX(),
							this.getY(),
							this.getX()+1,
							this.getY()-1));
    			}
			}
			
		}//end if
		
		return legalMoves;
    }
    
}
