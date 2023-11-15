package edu.cmu.cs.cs214.rec09.plugin;

import edu.cmu.cs.cs214.rec09.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec09.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec09.games.TicTacToe;
import edu.cmu.cs.cs214.rec09.games.TicTacToe.*;

public class TTTPlugin implements GamePlugin<Player>{
    private static final String GAME_NAME = "Tic Tac Toe";

    private static final int WIDTH = 3;
    private static final int HEIGHT = 3;

    private static final String CUR_PLAYER = "Current Player: ";

    private GameFramework framework;
    private TicTacToe game;
    private boolean played;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return WIDTH;
    }

    @Override
    public int getGridHeight() {
        return HEIGHT;
    }

    @Override
    public void onRegister(GameFramework f) {
        this.framework = f;
    }

    @Override
    public void onNewGame() {
        game = new TicTacToe();
        played = false;

        framework.setFooterText(CUR_PLAYER + game.currentPlayer().toString());
    }

    @Override
    public void onNewMove() {
        framework.setFooterText(CUR_PLAYER + game.currentPlayer().toString());
        played = false;
    }

    @Override
    public boolean isMoveValid(int x, int y) {
        return game.isValidPlay(x, y);
    }

    @Override
    public boolean isMoveOver() {
        return played;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        Player cur = game.currentPlayer();
        game.play(x, y);
        framework.setSquare(x, y, cur.toString());
        played = true;
    }
    
    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        if (game.winner() != null) return ("WINNER: " + game.winner().toString());
        else return "NO WINNER";
    }

    @Override
    public void onGameClosed() {}

    @Override
    public Player currentPlayer() {
        return game.currentPlayer();
    }
    

}
