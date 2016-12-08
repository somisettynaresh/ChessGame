package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.Piece;
import edu.iastate.coms572.chess.pieces.PieceColor;
import edu.iastate.coms572.chess.pieces.PieceType;

import java.util.*;

/**
 * Created by Naresh on 12/1/2016.
 */

enum PAWN_STRUCTURE {
    ISOLATED(-1),
    DOUBLED(-1),
    BACKWARD(-1),
    TRIPLED(-1);
    //TODO: TRIPLED & QUADRAPLED

    /**
     * Getter for property 'value'.
     *
     * @return Value for property 'value'.
     */
    public int getValue() {
        return value;
    }

    int value;

    PAWN_STRUCTURE(int val) {
        value = val;
    }
}

public class Utility {

    public static double getHeuristicValue(Board board, Player player) {
        double currentMaterialHeuristicValue = getMaterialHeuristicValue(board, player);
        double materialHValue = 10*(currentMaterialHeuristicValue - getMaterialHeuristicValue(Game.getInstance().getBoard(), player))/9;
       /* System.out.print("HValue Value for Move: " + legalMove.getPiece().getPieceType() + "from "
                + legalMove.getCurRow() + " , " + legalMove.getCurCol() + " to " +
                legalMove.getDesX() + " , " + legalMove.getDesCol() + " is " + materialHValue);
*/
        double pawnStructHValue = getPawnStructureHeuristicValue(board, player);
      //  System.out.print(", " + pawnStructHValue);
        double spaceHValue = getSpaceHeuristicValue(board, player);
        //System.out.print(", " + spaceHValue);
        double developmentHValue = getDevelopmentHeuristicValue(board, player);
        //System.out.print(", " + developmentHValue);
        double kingSafetyHValue = getKingSafetyHValue(board, player);
        //System.out.print(", " + kingSafetyHValue + "\n");
        return 100 * materialHValue + (board.getHistory().size() < 20 ? 15 * pawnStructHValue : 20 * pawnStructHValue) + 20 * spaceHValue + 20 * developmentHValue + (board.getHistory().size() < 20 ? 25 * kingSafetyHValue : 20 * kingSafetyHValue);

    }

    private static double getDoublePawnHValue(Map<Integer, List<Piece>> pawnsByCol, int noOfPawns) {
        int hValue = 0;
        for (List<Piece> pieces : pawnsByCol.values()) {
            if (pieces.size() == 2) {
                hValue += PAWN_STRUCTURE.DOUBLED.getValue();
            }
        }
        return 2 * hValue / noOfPawns;
    }

    private static double getTripledPawnHValue(Map<Integer, List<Piece>> pawnsByCol, int noOfPawns) {
        int hValue = 0;
        for (List<Piece> pieces : pawnsByCol.values()) {
            if (pieces.size() == 3) {
                hValue += PAWN_STRUCTURE.DOUBLED.getValue();
            }
        }
        return 2 * hValue / noOfPawns;
    }

    private static double getIsolatedPawnHValue(Map<Integer, List<Piece>> pawnsByCol, int noOfPawns) {
        int hValue = 0;
        if (pawnsByCol.get(1).size() == 0) {
            hValue += pawnsByCol.get(0).size() * PAWN_STRUCTURE.ISOLATED.getValue();
        }
        for (int i = 1; i < 6; i++) {
            if (pawnsByCol.get(i - 1).size() == 0 && pawnsByCol.get(i + 1).size() == 0) {
                hValue += pawnsByCol.get(i).size() * PAWN_STRUCTURE.ISOLATED.getValue();
            }
        }
        if (pawnsByCol.get(6).size() == 0) {
            hValue += pawnsByCol.get(7).size() * PAWN_STRUCTURE.ISOLATED.getValue();
        }
        return 2 * hValue / noOfPawns;
    }

    private static double getBackwardPawnHValue(Map<Integer, List<Piece>> pawnsByCol, int noOfPawns) {
        int hValue = 0;
        if (pawnsByCol.get(0).size() != 0 && pawnsByCol.get(1).size() != 0) {
            for (Piece pawn : pawnsByCol.get(0)) {
                int row = pawn.getColor().equals(PieceColor.Black) ? 7 - pawn.getRow() : pawn.getRow();
                if (row < getMinRow(pawnsByCol.get(1))) {
                    hValue += PAWN_STRUCTURE.BACKWARD.getValue();
                }
            }
        }
        for (int i = 1; i < 6; i++) {
            if (pawnsByCol.get(i).size() != 0) {
                for (Piece pawn : pawnsByCol.get(0)) {
                    int row = pawn.getRow();
                    if (row < getMinRow(pawnsByCol.get(i - 1)) && row < getMinRow(pawnsByCol.get(i + 1)))
                        hValue += pawnsByCol.get(i).size() * PAWN_STRUCTURE.ISOLATED.getValue();
                }
            }
        }
        if (pawnsByCol.get(6).size() == 0) {
            hValue += pawnsByCol.get(7).size() * PAWN_STRUCTURE.ISOLATED.getValue();
        }
        return 2 * hValue / noOfPawns;
    }

    private static int getMinRow(List<Piece> pieces) {
        int minRow = Integer.MAX_VALUE;
        for (Piece piece : pieces) {
            if ((piece.getColor().equals(PieceColor.Black) ? 7 - piece.getRow() : piece.getRow()) < minRow) {
                minRow = piece.getColor().equals(PieceColor.Black) ? 7 - piece.getRow() : piece.getRow();
            }
        }
        return minRow;
    }

    private static double getPawnStructureHeuristicValue(Board board, Player player) {
        Map<Integer, List<Piece>> pawnPosition = getPositionOfPawns(board, player);
        int noOfPawns = countAlivePawns(board, player);
        return getIsolatedPawnHValue(pawnPosition, noOfPawns) + getDoublePawnHValue(pawnPosition, noOfPawns) +
                getTripledPawnHValue(pawnPosition, noOfPawns) + getBackwardPawnHValue(pawnPosition, noOfPawns);
    }

    private static int countAlivePawns(Board board, Player player) {
        List<Piece> pieces = player.getPieces(board);
        int count = 0;
        for (Piece piece : pieces) {
            if (piece.getPieceType().equals(PieceType.Pawn) && piece.isAlive()) {
                count++;
            }
        }
        return count;
    }

    private static Map<Integer, List<Piece>> getPositionOfPawns(Board board, Player player) {
        Map<Integer, List<Piece>> getPawnsByColumn = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            getPawnsByColumn.put(i, new ArrayList<>());
        }
        Spot[][] spots = board.getSpots();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (spots[i][j].piece != null) {
                    if (spots[i][j].piece.getColor() == player.color && spots[i][j].piece.getPieceType().equals(PieceType.Pawn)) {
                        if (!getPawnsByColumn.containsKey(j)) {
                            List<Piece> pieceList = new ArrayList<>();
                            pieceList.add((spots[i][j].piece));
                            getPawnsByColumn.put(j, pieceList);
                        } else {
                            List<Piece> tempPawns = getPawnsByColumn.get(j);
                            tempPawns.add(spots[i][j].piece);
                            getPawnsByColumn.put(j, tempPawns);
                        }
                    }
                }
            }
        }
        return getPawnsByColumn;
    }

    private static double getMaterialHeuristicValue(Board board, Player player) {
        double playersValue = 0;
        double opponentValue = 0;
        Spot[][] spots = board.getSpots();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (spots[i][j].piece != null) {
                    if (spots[i][j].piece.getColor() == player.color) {
                        playersValue += spots[i][j].piece.getValue();
                    } else {
                        opponentValue += spots[i][j].piece.getValue();
                    }
                }

            }
        }
        return playersValue-opponentValue;
    }

    private static double getSpaceHeuristicValue(Board board, Player player) {
        int hValue = 0;
        List<Move> playersMoves = player.getLegalMoves(board);
        List<Move> opponentMoves = Game.getOpponent(player).getLegalMoves(board);
        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                hValue += (int) (getValueForSPot(row, col) * (moveCountToDestination(playersMoves, row, col) > moveCountToDestination(opponentMoves, row, col) ? 1 : -1));
            }
        }
        return (10 * hValue) / 64;
    }

    private static float getValueForSPot(int row, int col) {
        return ((float) (8 - row) * (8 - col)) / 64;
    }

    private static double moveCountToDestination(List<Move> playersMoves, int row, int col) {
        int moveCount = 0;
        for (Move playersMove : playersMoves) {
            if (playersMove.getDesX() == row && playersMove.getDesCol() == col) {
                moveCount++;
            }
        }
        return moveCount;
    }

    private static double getDevelopmentHeuristicValue(Board board, Player player) {
        double hValue = 0;
        List<Piece> pieces = player.getPieces(board);
        for (Piece piece : pieces) {
            if(piece.isHasMoved()){
                hValue++;
            }
        }
        return (10 * hValue) / totalAlivePieces(board, player);
    }

    private static double totalAlivePieces(Board board, Player player) {
        List<Piece> pieces = player.getPieces(board);
        int count=0;
        for (Piece piece : pieces) {
            if(piece.isAlive()) {
                count++;
            }
        }
        return count;
    }

    private static double getKingSafetyHValue(Board board, Player player) {
        Piece king = player.getKing(board);
        double hValue = 0;
        int valid = 0;
        Spot[][] spots = board.getSpots();
        if (king.getRow() + 1 <= 7) {
            valid++;
            if (spots[king.getRow() + 1][king.getCol()].piece != null &&
                    spots[king.getRow() + 1][king.getCol()].piece.getColor() == player.color) {
                hValue++;
            }
        }
        if (king.getRow() - 1 >= 0) {
            valid++;
            if (spots[king.getRow() - 1][king.getCol()].piece != null &&
                    spots[king.getRow() - 1][king.getCol()].piece.getColor() == player.color) {
                hValue++;
            }
        }
        if (king.getCol() - 1 >= 0) {
            valid++;
            if (spots[king.getRow()][king.getCol() - 1].piece != null &&
                    spots[king.getRow()][king.getCol() - 1].piece.getColor() == player.color) {
                hValue++;
            }
        }
        if (king.getCol() + 1 <= 7) {
            valid++;
            if (spots[king.getRow()][king.getCol() + 1].piece != null
                    && spots[king.getRow()][king.getCol() + 1].piece.getColor() == player.color) {
                hValue++;
            }
        }
        if (king.getCol() + 1 <= 7 && king.getRow() + 1 <= 7) {
            valid++;
            if (spots[king.getRow() + 1][king.getCol() + 1].piece != null &&
                    spots[king.getRow() + 1][king.getCol() + 1].piece.getColor() == player.color) {
                hValue++;
            }
        }

        if (king.getCol() - 1 >= 0 && king.getRow() + 1 <= 7) {
            valid++;
            if (spots[king.getRow() + 1][king.getCol() - 1].piece != null &&
                    spots[king.getRow() + 1][king.getCol() - 1].piece.getColor() == player.color) {
                hValue++;
            }
        }
        if (king.getCol() + 1 <= 7 && king.getRow() - 1 >= 0) {
            valid++;
            if(spots[king.getRow() - 1][king.getCol() + 1].piece != null &&
                    spots[king.getRow() - 1][king.getCol() + 1].piece.getColor() == player.color) {
                hValue++;

            }
        }

        if (king.getCol() - 1 >= 0 && king.getRow() - 1 >= 0) {
            valid++;
            if(spots[king.getRow() - 1][king.getCol() - 1].piece != null &&
                    spots[king.getRow() - 1][king.getCol() - 1].piece.getColor() == player.color) {
                hValue++;
            }
        }

        return 10 * hValue / valid;
    }

}
