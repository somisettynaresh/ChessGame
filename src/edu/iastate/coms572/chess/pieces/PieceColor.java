package edu.iastate.coms572.chess.pieces;

import java.io.Serializable;

/**
 * Created by Naresh on 11/17/2016.
 */
public enum PieceColor implements Serializable {
    White {
        @Override
        public String toString() {
            return "White";
        }
    }, Black {
        @Override
        public String toString() {
            return "Black";
        }
    }
}
