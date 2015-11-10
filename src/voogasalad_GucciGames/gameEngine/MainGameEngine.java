package voogasalad_GucciGames.gameEngine;

import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gameRule.GlobalGameRule;

public class MainGameEngine {

	private AllPlayers myGamePlayers;
	private int currentTurn;
	private GlobalGameRule myGlobalRule;
	
	private GameMap myGameMap;
	
	public MainGameEngine(AllPlayers gamePlayers, GlobalGameRule globalRule, GameMap gameMap){
	
		
		myGamePlayers = gamePlayers;
		currentTurn = 1;
		myGlobalRule = globalRule;
		
		myGameMap = gameMap;
	}

	public void takeTurn() {

		myGamePlayers.takeTurn(currentTurn);
		currentTurn++;
		
	}
	
	
	
	
	
	
	
	
}