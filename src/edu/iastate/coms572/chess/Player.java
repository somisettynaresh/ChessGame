package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

import static edu.iastate.coms572.chess.pieces.PieceColor.Black;
import static edu.iastate.coms572.chess.pieces.PieceColor.White;

/**
 * Created by Naresh on 11/17/2016.
 */
public abstract class Player {

    public PieceColor color;

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    public String name;
    private List<Piece> pieces = new ArrayList<>();

    public Player(PieceColor color, String name) {
        super();
        this.color = color;
        this.name = name;
        initializePieces();
    }

    public List<Piece> getPieces() {
        return pieces;
    }


    public void initializePieces(){
        if(this.color.equals(White)){
            for(int i=0; i<8; i++){ // draw pawns
                pieces.add(new Pawn(true,i,2, White));
            }
            pieces.add(new Rook(true, 0, 0, White));
            pieces.add(new Rook(true, 7, 0, White));
            pieces.add(new Bishop(true, 2, 0, White));
            pieces.add(new Bishop(true, 5, 0, White));
            pieces.add(new Knight(true, 1, 0, White));
            pieces.add(new Knight(true, 6, 0, White));
            pieces.add(new Queen(true, 3, 0, White));
            pieces.add(new King(true, 4, 0, White));
        }
        else{
            for(int i=0; i<8; i++){ // draw pawns
                pieces.add(new Pawn(true,i,6, Black));
            }
            pieces.add(new Rook(true, 0, 7, Black));
            pieces.add(new Rook(true, 7, 7, Black));
            pieces.add(new Bishop(true, 2, 7, Black));
            pieces.add(new Bishop(true, 5, 7, Black));
            pieces.add(new Knight(true, 1, 7, Black));
            pieces.add(new Knight(true, 6, 7, Black));
            pieces.add(new Queen(true, 3, 7, Black));
            pieces.add(new King(true, 4, 7, Black));
        }

    }

    public abstract Move getMove();
}