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
    public Board getBoard() {
        return board;
    }

    private static Game game;
    final Board board = new Board();
    private static Player currentPlayer;
    static Player humanPlayer;

    /**
     * Getter for property 'humanPlayer'.
     *
     * @return Value for property 'humanPlayer'.
     */
    public static Player getHumanPlayer() {
        return humanPlayer;
    }

    /**
     * Getter for property 'computerPlayer'.
     *
     * @return Value for property 'computerPlayer'.
     */
    public static Player getComputerPlayer() {
        return computerPlayer;
    }

    static Player computerPlayer;
    private static ChessGUI chessGUI;

    private Game() {

    }

    void initGame() {
        enterPlayer(new HumanPlayer(PieceColor.White, "Bill"));
        enterPlayer(new ComputerPlayer(PieceColor.Black, "Computer"));
        chessGUI = new ChessGUI();
        this.currentPlayer = humanPlayer;
        initGUI();
    }

    public boolean enterPlayer(Player p) {
        if (humanPlayer == null)
            this.humanPlayer = p;
        else if (computerPlayer == null)
            this.computerPlayer = p;
        else
            return false;

        board.initialize(p);
        return true;
    }

    public void startGame() {
        // player enter the game:
        processTurn(humanPlayer);
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
        if (color == humanPlayer.color) {
            return humanPlayer;
        } else {
            return computerPlayer;
        }
    }

     void processTurn(Player player) {
        currentPlayer = player;
        if (!player.getClass().getName().contains("HumanPlayer")) {
            Move move = player.getMove();
            board.executeMove(board, move);
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
        Game game = Game.getInstance();
        game.initGame();
        game.startGame();
    }


    public static Player getOpponent() {
        if (getCurrentPlayer() == humanPlayer)
            return computerPlayer;
        return humanPlayer;
    }

    public static Game getInstance() {
        if (game == null)
            game = new Game();
        return game;
    }
}