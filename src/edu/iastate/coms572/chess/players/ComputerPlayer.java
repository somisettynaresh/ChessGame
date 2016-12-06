package edu.iastate.coms572.chess.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.iastate.coms572.chess.*;
import edu.iastate.coms572.chess.pieces.Piece;
import edu.iastate.coms572.chess.pieces.PieceColor;

import static edu.iastate.coms572.chess.Utility.getHeursticValue;

/**
 * Created by Naresh on 11/17/2016.
 */
public class ComputerPlayer extends Player{
    public ComputerPlayer(PieceColor color, String name) {
        super(color, name);
    }

    @Override
    public Move getMove() {
    	List<Move> legalMoves = getLegalMoves(Game.getInstance().getBoard());
        return getMaxHValueMove(legalMoves);
    }

    private Move getMaxHValueMove(List<Move> legalMoves) {
        int maxHValue = Integer.MIN_VALUE;
        Move maxHValueMove = null;
        for (Move legalMove : legalMoves) {
            Board simulatedBoard = simulateMove(Game.getInstance().getBoard(), legalMove);
            int heuristicValue = getHeursticValue(simulatedBoard, Game.getComputerPlayer());
            System.out.println("Heuristic Value for Move: " + legalMove.getPiece().getPieceType() + "from "
                    + legalMove.getCurRow() + " , " + legalMove.getCurCol() + " to " +
                    legalMove.getDesX() + " , " + legalMove.getDesCol() + " is " + heuristicValue);
            if(heuristicValue > maxHValue){
                maxHValue = getHeursticValue(Game.getInstance().getBoard(),Game.getComputerPlayer());
                maxHValueMove = legalMove;
            }
        }
        System.out.println("******************Move selected*********");
        return maxHValueMove;
    }

    private Board simulateMove(Board board, Move legalMove) {
        Board simualtedBoard = board.deepClone();
        simualtedBoard.executeMove(simualtedBoard, legalMove);
        return simualtedBoard;
    }


    public List<Move> getLegalMoves(Board board){
    	//1. Get list of all pieces of the computer
    	List<Piece> pieceList = getPieces();
    	
    	//2. Get the list of all legal moves
    	ArrayList<Move> legalMoves = new ArrayList<Move>();
    	for(Piece pc : pieceList){
            List<Move> moves = pc.getPossibleMoves(board, pc.getRow(), pc.getCol());
            if(moves!=null)
    		    legalMoves.addAll(moves);
    	}
    	
    	//At this point, for each legal move, we need to do alpha-beta pruning
    	return legalMoves;
    }
    
}
