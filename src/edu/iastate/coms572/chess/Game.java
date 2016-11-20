package edu.iastate.coms572.chess;

import edu.iastate.coms572.chess.pieces.PieceColor;
import edu.iastate.coms572.chess.players.ComputerPlayer;
import edu.iastate.coms572.chess.players.HumanPlayer;

/**
 * Created by Naresh on 11/17/2016.
 */
public class Game{
    final static Board board = new Board();
    public static Player human;
    public static Player computer;
    
    public static Player currentPlayer;

    public Game() {
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
    	computer = new ComputerPlayer(PieceColor.Black,"Computer");
        board.initialize(computer);
        
        human = new HumanPlayer(PieceColor.White, "Bill");
        board.initialize(human);
        
        while(true) {
        	
        	setCurrentPlayer(human);
            processTurn(human);
            if(this.board.getWin()) {
                System.out.println("human Wins");
                break;
            }
            
            setCurrentPlayer(computer);
            processTurn(computer);
            if(this.board.getWin()) {
                System.out.println("Computer Wins!");
                break;
            }
        }
    }
    
    public static Player getCurrentPlayer(){
    	return currentPlayer;
    }
    
    private static void setCurrentPlayer(Player curPlayer){
    	currentPlayer = curPlayer;
    }
    
    
    //THis will return the player associated with the color
    public static Player getPlayerByColor(PieceColor color){
    	if(color == human.color){
    		return human;
    	}else{
    		return computer;
    	}
    	
    }
}