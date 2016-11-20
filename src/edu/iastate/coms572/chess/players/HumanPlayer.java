package edu.iastate.coms572.chess.players;

import edu.iastate.coms572.chess.Board;
import edu.iastate.coms572.chess.Move;
import edu.iastate.coms572.chess.Player;
import edu.iastate.coms572.chess.pieces.PieceColor;

/**
 * Created by Naresh on 11/17/2016.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(PieceColor color, String name) {
        super(color, name);
    }

    @Override
    public Move getMove() {
        return null;
    }
}
