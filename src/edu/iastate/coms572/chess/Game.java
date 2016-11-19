package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.PieceColor;
import edu.iastate.coms572.chess.players.ComputerPlayer;
import edu.iastate.coms572.chess.players.HumanPlayer;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Game{
    final static Board board = new Board();
    Player p1;
    Player p2;

    public Game() {
    }

    public boolean enterPlayer(Player p) {
        if(p1 == null)
            this.p1 = p;
        else if(p2 == null)
            this.p2 = p;
        else
            return false;

        board.initialize(p);
        return true;
    }

    public void processTurn(Player p) {
        // edu.iastate.coms572.chess.Player make a command and until it is valid
        // System input
      /*  do{
            Move cmd = new Move(input);
            p.addCommand(cmd);
        }while(!board.executeMove(p));*/
    }

    public void startGame(){
        // player enter the game:
        enterPlayer(new ComputerPlayer(PieceColor.Black,"Computer"));
        enterPlayer(new HumanPlayer(PieceColor.White, "Bill"));

        while(true) {
            processTurn(p1);
            if(this.board.getWin()) {
                System.out.println("P1 win!");
                break;
            }
            processTurn(p2);
            if(this.board.getWin()) {
                System.out.println("P2 win!");
                break;
            }
        }
    }
}