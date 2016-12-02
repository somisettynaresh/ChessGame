package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.PieceColor;
import edu.iastate.coms572.chess.players.ComputerPlayer;
import edu.iastate.coms572.chess.players.HumanPlayer;

import javax.swing.*;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Game {
    /**
     * Getter for property 'board'.
     *
     * @return Value for property 'board'.
     */
    public static Board getBoard() {
        return board;
    }

    final static Board board = new Board();
    private static Player currentPlayer;
    static Player p1;
    static Player p2;
    private static ChessGUI chessGUI;

    public Game() {

    }

    void initGame() {
        enterPlayer(new HumanPlayer(PieceColor.White, "Bill"));
        enterPlayer(new ComputerPlayer(PieceColor.Black, "Computer"));
        chessGUI = new ChessGUI(board);
        this.currentPlayer = p1;
        initGUI();
    }

    public boolean enterPlayer(Player p) {
        if (p1 == null)
            this.p1 = p;
        else if (p2 == null)
            this.p2 = p;
        else
            return false;

        board.initialize(p);
        return true;
    }

    public void startGame() {
        // player enter the game:
            processTurn(p1);
            if (this.board.getWin()) {
                System.out.println("human Wins");
            }

    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    private static void setCurrentPlayer(Player curPlayer) {
        currentPlayer = curPlayer;
    }


    //THis will return the player associated with the color
    public static Player getPlayerByColor(PieceColor color) {
        if (color == p1.color) {
            return p1;
        } else {
            return p2;
        }
    }

    static void processTurn(Player player)
    {
        currentPlayer = player;
        if(!player.getClass().getName().contains("HumanPlayer")) {
            Move move = player.getMove();
            board.executeMove(move);
            chessGUI.updatePiecesUI();
            processTurn(getOpponent());
        }
    }

    public void initGUI() {

        JFrame f = new JFrame("ChessChamp");
        f.add(chessGUI.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.pack();
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.initGame();
        game.startGame();
    }


    public static Player getOpponent() {
        if(getCurrentPlayer() == p1)
            return p2;
        return p1;
    }
}