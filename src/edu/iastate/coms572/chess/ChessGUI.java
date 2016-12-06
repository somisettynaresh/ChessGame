package edu.iastate.coms572.chess;

/**
 * Created by Naresh on 11/20/2016.
 */

import edu.iastate.coms572.chess.pieces.Piece;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import static edu.iastate.coms572.chess.Game.*;

public class ChessGUI {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    //private Image[][] chessPieceImages = new Image[2][6];
    private JPanel chessBoard;
    private Piece pieceSelected = null;
    private final JLabel message = new JLabel(
            "Chess Champ is ready to play!");
    private static final String COLS = "ABCDEFGH";
    Spot[][] spots = null;
    private int piceSelectedPosX;
    private int piceSelectedPosY;
    private Player opponent;

    /*public static final int QUEEN = 0, KING = 1,
            ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
    public static final int[] STARTING_ROW = {
            ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK
    };
    public static final int BLACK = 0, WHITE = 1;
*/
    ChessGUI() {
        initializeGui();
        spots = Game.getInstance().getBoard().getSpots();
        updatePiecesUI();
    }

    public final void initializeGui() {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("New") {

            @Override
            public void actionPerformed(ActionEvent e) {
                setupNewGame();
            }
        };
        tools.add(newGameAction);
        tools.add(new JButton("Undo Move")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(message);

        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(0, 9)) {
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int) d.getWidth(), (int) d.getHeight());
                } else if (c != null &&
                        c.getWidth() > d.getWidth() &&
                        c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w > h ? h : w);
                return new Dimension(s, s);
            }
        };
        chessBoard.setBorder(new CompoundBorder(
                new EmptyBorder(8, 8, 8, 8),
                new LineBorder(Color.BLACK)
        ));
        // Set the BG to be ochre
        Color ochre = new Color(204, 119, 34);
        chessBoard.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(chessBoard);
        gui.add(boardConstrain);

        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                final int finalIi = ii;
                final int finalJj = jj;
                b.addActionListener(e -> {
                    if (pieceSelected != null && (pieceSelected.getColor() == Game.getCurrentPlayer().color) && (piceSelectedPosX == finalIi && piceSelectedPosY == finalJj)) {
                        chessBoardSquares[piceSelectedPosX][piceSelectedPosY].setBackground(getBackgroundColor(piceSelectedPosX, piceSelectedPosY));
                        pieceSelected = null;
                        piceSelectedPosX = -1;
                        piceSelectedPosY = -1;
                    } else if (pieceSelected != null && (pieceSelected.getColor() == Game.getCurrentPlayer().color) && pieceSelected.isValidMove(Game.getInstance().getBoard(), pieceSelected.getRow(), pieceSelected.getCol(), finalIi, finalJj)) {
                        Game.getInstance().getBoard().executeMove(Game.getInstance().getBoard(), new Move(pieceSelected, finalIi, finalJj));
                        chessBoardSquares[piceSelectedPosX][piceSelectedPosY].setBackground(getBackgroundColor(piceSelectedPosX, piceSelectedPosY));
                        pieceSelected = null;
                        piceSelectedPosX = -1;
                        piceSelectedPosY = -1;
                        updatePiecesUI();
                        Game.getInstance().processTurn(getOpponent());
                    } else if (pieceSelected == null && spots[finalIi][finalJj].getPiece() != null) {
                        if(spots[finalIi][finalJj].getPiece().getColor()==Game.getCurrentPlayer().color) {
                            pieceSelected = spots[finalIi][finalJj].getPiece();
                            piceSelectedPosX = pieceSelected.getRow();
                            piceSelectedPosY = pieceSelected.getCol();
                            b.setBackground(Color.LIGHT_GRAY);
                        }
                    }
                });
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBackground(getBackgroundColor(ii, jj));
                chessBoardSquares[jj][ii] = b;
            }
        }

        /*
         * fill the chess board
         */
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                            SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (9 - (ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }
    }

    private Color getBackgroundColor(int x, int y) {
        if ((y % 2 == 1 && x % 2 == 1)
                //) {
                || (y % 2 == 0 && x % 2 == 0)) {
            return Color.WHITE;
        } else {
            return new Color(113, 198, 113);
        }
    }

    public final JComponent getGui() {
        return gui;
    }

     void updatePiecesUI() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = spots[i][j].getPiece();
                chessBoardSquares[j][i].setBackground(getBackgroundColor(i,j));
                if (piece != null)
                    chessBoardSquares[j][i].setIcon(new ImageIcon("D:/ISU/Courses/572/project/Chess/out/production/Chess/edu/iastate/coms572/chess/images/" + piece.getPath()));
                else
                    chessBoardSquares[j][i].setIcon(null);
            }
        }
    }

    /**
     * Initializes the icons of the initial chess board piece places
     */
    private final void setupNewGame() {
        message.setText("Make your move!");
        updatePiecesUI();
    }


}
