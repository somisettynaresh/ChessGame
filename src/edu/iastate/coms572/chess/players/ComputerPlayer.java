
package edu.iastate.coms572.chess.players;

import edu.iastate.coms572.chess.*;
import edu.iastate.coms572.chess.pieces.PieceColor;

import java.util.ArrayList;
import java.util.List;

import static edu.iastate.coms572.chess.Utility.getHeuristicValue;

/**
 * Created by Naresh on 11/17/2016.
 */
public class ComputerPlayer extends Player {
    public ComputerPlayer(PieceColor color, String name) {
        super(color, name);
    }

    public Move getMove1() {
        Board board = Game.getInstance().getBoard();
        List<Move> legalMoves = getLegalMoves(board);
        return getMaxHValueMove(legalMoves);
    }

    @Override
    public Move getMove() {
        return AlphaBetaSearch.createFor(Game.getInstance()).makeDecision(Game.getInstance().getBoard());
    }


    private Move getMaxHValueMove(List<Move> legalMoves) {
        double maxHValue = Integer.MIN_VALUE;
        Move maxHValueMove = null;
        for (Move legalMove : legalMoves) {
            Board simulatedBoard = simulateMove(Game.getInstance().getBoard(), legalMove);
            double heuristicValue = getHeuristicValue(simulatedBoard, Game.getComputerPlayer());
            System.out.println("Heuristic Value for Move: " + legalMove.getPiece().getPieceType() + "from "
                    + legalMove.getCurRow() + " , " + legalMove.getCurCol() + " to " +
                    legalMove.getDesX() + " , " + legalMove.getDesCol() + " is " + heuristicValue);
            if (heuristicValue > maxHValue) {
                maxHValue = heuristicValue;
                maxHValueMove = legalMove;
            }
        }
        System.out.println("******************Move selected*********");
        return maxHValueMove;
    }



}
