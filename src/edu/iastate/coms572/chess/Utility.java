package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.Piece;
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

    public static int getHeursticValue(Board board, Player player) {
        int materialHValue = getMaterialHeuristicValue(board, player);
        int pawnStructHValue = getPawnStructureHeuristicValue(board, player);

        return 75*materialHValue + 25*pawnStructHValue;
    }

    private static int getDoublePawnHValue(Map<Integer, List<Piece>> pawnsByCol) {
        int hValue = 0;
        for (List<Piece> pieces : pawnsByCol.values()) {
            if (pieces.size() == 2) {
                hValue += PAWN_STRUCTURE.DOUBLED.getValue();
            }
        }
        return hValue;
    }

    private static int getTripledPawnHValue(Map<Integer, List<Piece>> pawnsByCol) {
        int hValue = 0;
        for (List<Piece> pieces : pawnsByCol.values()) {
            if (pieces.size() == 3) {
                hValue += PAWN_STRUCTURE.DOUBLED.getValue();
            }
        }
        return hValue;
    }

    private static int getIsolatedPawnHValue(Map<Integer, List<Piece>> pawnsByCol) {
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
        return hValue;
    }

    private static int getBackwardPawnHValue(Map<Integer, List<Piece>> pawnsByCol) {
        int hValue = 0;
        if (pawnsByCol.get(0).size() != 0 && pawnsByCol.get(1).size() != 0) {
            for (Piece pawn : pawnsByCol.get(0)) {
                 int row = pawn.getRow();
                 if(row <= getMinRow(pawnsByCol.get(1))) {
                     hValue += PAWN_STRUCTURE.BACKWARD.getValue();
                 }
            }
        }
        for (int i = 1; i < 6; i++) {
            if (pawnsByCol.get(i).size() != 0) {
                for (Piece pawn : pawnsByCol.get(0)) {
                    int row = pawn.getRow();
                    if (row <= getMinRow(pawnsByCol.get(i-1)) && row <= getMinRow(pawnsByCol.get(i+1)))
                            hValue += pawnsByCol.get(i).size() * PAWN_STRUCTURE.ISOLATED.getValue();
                }
            }
        }
        if (pawnsByCol.get(6).size() == 0) {
            hValue += pawnsByCol.get(7).size() * PAWN_STRUCTURE.ISOLATED.getValue();
        }
        return hValue;
    }

    private static int getMinRow(List<Piece> pieces) {
        int minRow = Integer.MAX_VALUE;
        for (Piece piece : pieces) {
            if(piece.getRow() < minRow) {
                minRow = piece.getRow();
            }
        }
        return minRow;
    }

    private static int getPawnStructureHeuristicValue(Board board, Player player) {
        Map<Integer, List<Piece>> pawnPosition = getPositionOfPawns(board, player);

        return getIsolatedPawnHValue(pawnPosition) + getDoublePawnHValue(pawnPosition) + getTripledPawnHValue(pawnPosition);
    }

    private static Map<Integer, List<Piece>> getPositionOfPawns(Board board, Player player) {
        Map<Integer, List<Piece>> getPawnsByColumn = new HashMap<>();
        for(int i=0;i<8;i++) {
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
                        }
                        else {
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

    private static int getMaterialHeuristicValue(Board board, Player player) {
        int playersValue = 0;
        int opponentValue = 0;
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
        return playersValue - opponentValue;
    }
}
