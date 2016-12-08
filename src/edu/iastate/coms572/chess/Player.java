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
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (spots[row][col].piece != null && spots[row][col].piece.getColor() == color) {
                    spots[row][col].piece.setCol(col);
                    spots[row][col].piece.setRow(row);
                    playerPieces.add(spots[row][col].piece);
                }
            }
        }
        return pieces;
    }

    public void initializePieces() {
        if (this.color.equals(White)) {
            for (int i = 0; i < 8; i++) { // draw pawns
                pieces.add(new Pawn(true, 1, i, White));
            }
            pieces.add(new Rook(true, 0, 0, White));
            pieces.add(new Rook(true, 0, 7, White));
            pieces.add(new Bishop(true, 0, 2, White));
            pieces.add(new Bishop(true, 0, 5, White));
            pieces.add(new Knight(true, 0, 1, White));
            pieces.add(new Knight(true, 0, 6, White));
            pieces.add(new Queen(true, 0, 4, White));
            pieces.add(new King(true, 0, 3, White));
        } else {
            for (int i = 0; i < 8; i++) { // draw pawns
                pieces.add(new Pawn(true, 6, i, Black));
            }
            pieces.add(new Rook(true, 7, 0, Black));
            pieces.add(new Rook(true, 7, 7, Black));
            pieces.add(new Bishop(true, 7, 2, Black));
            pieces.add(new Bishop(true, 7, 5, Black));
            pieces.add(new Knight(true, 7, 1, Black));
            pieces.add(new Knight(true, 7, 6, Black));
            pieces.add(new Queen(true, 7, 4, Black));
            pieces.add(new King(true, 7, 3, Black));
        }

    }

    private List<Move> filterMoves(List<Move> legalMoves) {
        List<Move> filteredLegalMoves = new ArrayList<>();
        for (Move legalMove : legalMoves) {
            Board simulatedBoard = Game.getInstance().getBoard().deepClone();
            simulatedBoard.simulateExecuteMove(simulatedBoard, legalMove);
            if (!hasCheck(simulatedBoard)) {
                filteredLegalMoves.add(legalMove);
            }
        }
        return filteredLegalMoves;
    }

    private List<Move> resolveCheck(List<Move> legalMoves) {
        List<Move> resolvableMoves = new ArrayList<>();
        for (Move legalMove : legalMoves) {
            Board simulatedBoard = Game.getInstance().getBoard().deepClone();
            simulatedBoard.simulateExecuteMove(simulatedBoard, legalMove);
            if(!hasCheck(simulatedBoard)){
                resolvableMoves.add(legalMove);
            }
        }
        return resolvableMoves;
    }

    public List<Move> getLegalMoves(Board board) {
        if(hasCheck(board)){
            return resolveCheck(getAllLegalMoves(board));
        }
        return getAllLegalMoves(board);
    }
    public List<Move> getAllLegalMoves(Board board) {

        //1. Get list of all pieces of the computer
        List<Piece> pieceList = getPieces(board);

        //2. Get the list of all legal moves
        ArrayList<Move> legalMoves = new ArrayList<Move>();
        for (Piece pc : pieceList) {
            List<Move> moves = pc.getPossibleMoves(board, pc.getRow(), pc.getCol());
            if (moves != null)
                legalMoves.addAll(moves);
        }

        //At this point, for each legal move, we need to do alpha-beta pruning
        return legalMoves;
    }

     List<Piece> getPieces(Board board) {
        List<Piece> pieces = new ArrayList<>();
        Spot[][] spots = board.getSpots();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (spots[row][col].piece != null && spots[row][col].piece.getColor() == color) {
                    pieces.add(spots[row][col].getPiece());
                }
            }
        }
        return pieces;
    }

    public List<Move> filteredLegalMoves(Board board) {
        return filterMoves(getLegalMoves(board));
    }

    public boolean hasCheck(Board board) {
        Piece king = getKing(board);
        Spot[][] spots = board.getSpots();
        Piece firstPieceInUpDirection = null, firstPieceInDownDirection =null, firstPieceInDirection=null;
        for (int row = king.getRow()-1; row > 0; row--) {
            if (spots[row][king.getCol()].getPiece() != null) {
                if (spots[row][king.getCol()].getPiece().getColor() == color) {
                    if (firstPieceInUpDirection == null)
                        firstPieceInUpDirection = spots[row][king.getCol()].getPiece();
                    else
                        break;
                } else {
                    if (firstPieceInUpDirection == null) {
                        PieceType pieceType = spots[row][king.getCol()].getPiece().getPieceType();
                        if (pieceType.equals(PieceType.Rook) || pieceType.equals(PieceType.Queen)) {
                            return true;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        for (int row = king.getRow(); row < 7; row++) {
            if (spots[row][king.getCol()].getPiece() != null) {
                if (spots[row][king.getCol()].getPiece().getColor() == color) {
                    if (firstPieceInDownDirection == null)
                        firstPieceInDownDirection = spots[row][king.getCol()].getPiece();
                    else
                        break;
                } else {
                    if (firstPieceInDownDirection == null) {
                        PieceType pieceType = spots[row][king.getCol()].getPiece().getPieceType();
                        if (pieceType.equals(PieceType.Rook) || pieceType.equals(PieceType.Queen)) {
                            return true;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        for (int col = king.getCol()+1; col < 7; col++) {
            if (spots[king.getRow()][col].getPiece() != null) {
                if (spots[king.getRow()][col].getPiece().getColor() == color) {
                    if (firstPieceInDirection == null)
                        firstPieceInDirection = spots[king.getRow()][col].getPiece();
                    else
                        break;
                } else {
                    if (firstPieceInDirection == null) {
                        PieceType pieceType = spots[king.getRow()][col].getPiece().getPieceType();
                        if (pieceType.equals(PieceType.Rook) || pieceType.equals(PieceType.Queen)) {
                            return true;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        firstPieceInDirection = null;
        for (int col = king.getCol()-1; col >0; col--) {
            if (spots[king.getRow()][col].getPiece() != null) {
                if (spots[king.getRow()][col].getPiece().getColor() == color) {
                    if (firstPieceInDirection == null)
                        firstPieceInDirection = spots[king.getRow()][col].getPiece();
                    else
                        break;
                } else {
                    if (firstPieceInDirection == null) {
                        PieceType pieceType = spots[king.getRow()][col].getPiece().getPieceType();
                        if (pieceType.equals(PieceType.Rook) || pieceType.equals(PieceType.Queen)) {
                            return true;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        firstPieceInDirection = null;

        for (int col = king.getCol()-1 ,row=king.getRow()-1; col > 0 && row >0; col--,row--) {
            if (spots[row][col].getPiece() != null) {
                if (spots[row][col].getPiece().getColor() == color) {
                    if (firstPieceInDirection == null)
                        firstPieceInDirection = spots[row][col].getPiece();
                    else
                        break;
                } else {
                    if (firstPieceInDirection == null) {
                        PieceType pieceType = spots[row][col].getPiece().getPieceType();
                        if (pieceType.equals(PieceType.Bishop) || pieceType.equals(PieceType.Queen)) {
                            return true;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        firstPieceInDirection = null;

        for (int col = king.getCol()-1 ,row=king.getRow(); col > 0 && row < 7; col--,row++) {
            if (spots[row][col].getPiece() != null) {
                if (spots[row][col].getPiece().getColor() == color) {
                    if (firstPieceInDirection == null)
                        firstPieceInDirection = spots[row][col].getPiece();
                    else
                        break;
                } else {
                    if (firstPieceInDirection == null) {
                        PieceType pieceType = spots[row][col].getPiece().getPieceType();
                        if (pieceType.equals(PieceType.Bishop) || pieceType.equals(PieceType.Queen)) {
                            return true;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        firstPieceInDirection = null;

        for (int col = king.getCol(),row=king.getRow()-1; col < 7 && row > 0; col++,row--) {
            if (spots[row][col].getPiece() != null) {
                if (spots[row][col].getPiece().getColor() == color) {
                    if (firstPieceInDirection == null)
                        firstPieceInDirection = spots[row][col].getPiece();
                    else
                        break;
                } else {
                    if (firstPieceInDirection == null) {
                        PieceType pieceType = spots[row][col].getPiece().getPieceType();
                        if (pieceType.equals(PieceType.Bishop) || pieceType.equals(PieceType.Queen)) {
                            return true;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        firstPieceInDirection = null;

        for (int col = king.getCol() ,row=king.getRow(); col < 7 && row < 7; col++,row++) {
            if (spots[row][col].getPiece() != null) {
                if (spots[row][col].getPiece().getColor() == color) {
                    if (firstPieceInDirection == null)
                        firstPieceInDirection = spots[row][col].getPiece();
                    else
                        break;
                } else {
                    if (firstPieceInDirection == null) {
                        PieceType pieceType = spots[row][col].getPiece().getPieceType();
                        if (pieceType.equals(PieceType.Bishop) || pieceType.equals(PieceType.Queen)) {
                            return true;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        if(king.getRow()>2) {
            if (spots[king.getRow() - 2][king.getCol()-1].getPiece()!=null) {
                Piece piece = spots[king.getRow() - 2][king.getCol()-1].getPiece();
                if(piece.getColor()!=color && piece.getPieceType().equals(PieceType.Knight))
                    return true;
            }
            if (spots[king.getRow() - 2][king.getCol()+1].getPiece()!=null) {
                Piece piece = spots[king.getRow() - 2][king.getCol()+1].getPiece();
                if(piece.getColor()!=color && piece.getPieceType().equals(PieceType.Knight))
                    return true;
            }
        }
        if(king.getRow()<6) {
            if (spots[king.getRow() + 2][king.getCol()-1].getPiece()!=null) {
                Piece piece = spots[king.getRow() + 2][king.getCol()-1].getPiece();
                if(piece.getColor()!=color && piece.getPieceType().equals(PieceType.Knight))
                    return true;
            }
            if (spots[king.getRow() + 2][king.getCol()+1].getPiece()!=null) {
                Piece piece = spots[king.getRow() + 2][king.getCol()+1].getPiece();
                if(piece.getColor()!=color && piece.getPieceType().equals(PieceType.Knight))
                    return true;
            }
        }
        return false;
    }

    public Piece getKing(Board board) {
        Spot[][] spots = board.getSpots();
        for(int row=0; row<8;row++) {
            for(int col=0;col<8;col++) {
                if((spots[row][col].piece!=null && spots[row][col].piece.getPieceType()==PieceType.King)
                        && spots[row][col].piece.getColor() == color){
                    return spots[row][col].piece;
                }
            }
        }
        return null;
    }

    public Board simulateMove(Board board, Move legalMove) {
        Board simualtedBoard = board.deepClone();
        simualtedBoard.simulateExecuteMove(simualtedBoard, legalMove);
        return simualtedBoard;
    }

    public abstract Move getMove();
}