package edu.iastate.coms572.chess.pieces;

/**
 * Created by Naresh on 11/17/2016.
 */
public enum PieceColor {
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
