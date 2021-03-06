package edu.iastate.coms572.chess.pieces;

import java.io.Serializable;

public enum PieceType implements Serializable{

    Pawn {
        @Override
        public String toString() {
            return "Pawn";
        }
    },
    Knight {
        @Override
        public String toString() {
            return "Knight";
        }
    },
    Bishop {
        @Override
        public String toString() {
            return "Bishop";
        }
    },
    Rook {
        @Override
        public String toString() {
            return "Rook";
        }
    },
    Queen {
        @Override
        public String toString() {
            return "Queen";
        }
    },
    King {
        @Override
        public String toString() {
            return "King";
        }
    }

}

