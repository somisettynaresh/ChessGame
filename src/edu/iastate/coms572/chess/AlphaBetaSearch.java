package edu.iastate.coms572.chess;

import java.util.ArrayList;
import java.util.List;

public class AlphaBetaSearch {

    Game game;
    int nodesExpanded;
    public static List<Integer> nodesExpandedList = new ArrayList<>();

    /**
     * Creates a new search object for a given game.
     */
    public static AlphaBetaSearch createFor(
            Game game) {
        return new AlphaBetaSearch(game);
    }

    public AlphaBetaSearch(Game game) {
        this.game = game;
    }

    public Move makeDecision(Board board) {
        Move result = null;
        double resultValue = Double.NEGATIVE_INFINITY;
        nodesExpanded = 0;
        Player player = game.getCurrentPlayer();
        for (Move move : player.getLegalMoves(board)) {
            nodesExpanded++;
            double value = minValue(player.simulateMove(board,move), player ,
                    Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, game.getHistory().size()+1);
            System.out.println( "Value for Move " + move.getPiece().getPieceType() + " from - " + move.curRow +
                    " , " + move.curCol + " to - " + move.getDesX() + " , " + move .getDesCol()  + " is : " + value);
            if (value > resultValue) {
                result = move;
                resultValue = value;
            }
        }
        nodesExpandedList.add(nodesExpanded);
        System.out.println("Nodes Expanded : " + nodesExpanded);
        System.out.println("Move selected : " +  result.getPiece().getPieceType() + " from - " + result.curRow +
                " , " + result.curCol + " to - " + result.getDesX() + " , " + result.getDesCol()  + " is : " + resultValue);
        return result;
    }

    public double maxValue(Board state, Player player, double alpha, double beta, int historySize) {
        if (game.isTerminal(state, historySize)) {
            return Utility.getHeuristicValue(state, player);
        }
        double value = Double.NEGATIVE_INFINITY;
        for (Move move : player.getLegalMoves(state)) {
      //  for (Move move : game.prioritize(state, game.validMovesForPlayer(state, player), player)) {
            nodesExpanded++;
            value = Math.max(value, minValue( //
                    player.simulateMove(state,move), Game.getOpponent(player), alpha, beta, historySize+1));
         /*   System.out.println( "Max - Value for Move " + move.getPiece().getPieceType() + " from - " + move.curRow +
                    " , " + move.curCol + " to - " + move.getDesX() + " , " + move .getDesCol()  + " is : " + value);
*/
            if (value >= beta)
                return value;
            alpha = Math.max(alpha, value);
        }
        return value;
    }

    public double minValue(Board state, Player player, double alpha, double beta, int historySize) {
       if (game.isTerminal(state, historySize))
            return Utility.getHeuristicValue(state, player);
        double value = Double.POSITIVE_INFINITY;
        for (Move move : player.getLegalMoves(state)) {
       // for (Move move : game.prioritize(state, game.validMovesForPlayer(state, player), player)) {
            nodesExpanded++;
            value = Math.min(value, maxValue( //
                    player.simulateMove(state, move), Game.getOpponent(player), alpha, beta, historySize+1));
  /*          System.out.println( " MinValue - Value for Move " + move.getPiece().getPieceType() + " from - " + move.curRow +
                    " , " + move.curCol + " to - " + move.getDesX() + " , " + move .getDesCol()  + " is : " + value);

  */          if (value <= alpha)
                return value;
            beta = Math.min(beta, value);
        }
        return value;
    }

}