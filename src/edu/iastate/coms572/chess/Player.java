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

    public String getName() {
        return name;
    }

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
        List<Piece> playerPieces = new ArrayList<>();
        Spot[][] spots = Game.getInstance().getBoard().getSpots();
        for (int row=0; row <8; row++) {
            for (int col=0;col<8;col++) {
                if(spots[row][col].piece!=null && spots[row][col].piece.getColor() == color){
                    spots[row][col].piece.setCol(col);
                    spots[row][col].piece.setRow(row);
                    playerPieces.add(spots[row][col].piece);
                }
            }
        }
        return pieces;
    }


    public void initializePieces(){
        if(this.color.equals(White)){
            for(int i=0; i<8; i++){ // draw pawns
                pieces.add(new Pawn(true,1,i, White));
            }
            pieces.add(new Rook(true, 0, 0, White));
            pieces.add(new Rook(true, 0, 7, White));
            pieces.add(new Bishop(true, 0, 2, White));
            pieces.add(new Bishop(true, 0, 5, White));
            pieces.add(new Knight(true, 0, 1, White));
            pieces.add(new Knight(true, 0, 6, White));
            pieces.add(new Queen(true, 0, 3, White));
            pieces.add(new King(true, 0, 4, White));
        }
        else{
            for(int i=0; i<8; i++){ // draw pawns
                pieces.add(new Pawn(true,6,i, Black));
            }
            pieces.add(new Rook(true, 7, 0, Black));
            pieces.add(new Rook(true, 7, 7, Black));
            pieces.add(new Bishop(true, 7, 2, Black));
            pieces.add(new Bishop(true, 7, 5, Black));
            pieces.add(new Knight(true, 7, 1, Black));
            pieces.add(new Knight(true, 7, 6, Black));
            pieces.add(new Queen(true, 7, 3, Black));
            pieces.add(new King(true, 7, 4, Black));
        }

    }

    public abstract Move getMove();
}