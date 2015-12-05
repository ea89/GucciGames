package voogasalad_GucciGames.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameData.wrapper.GameEngine;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.CommunicationParameters.BasicParameters;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.AttackCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.HealthCharacteristic;
import voogasalad_GucciGames.gameEngine.defaultCharacteristics.MovableCharacteristic;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gameConditions.defaultConditions.CheckOnePlayerLeft;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.EndGame;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.Outcome;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.OutcomeParams;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.gamePlayer.MovablePlayerCharacteristic;
import voogasalad_GucciGames.gameEngine.gameRules.Rules;
import voogasalad_GucciGames.gameEngine.gameRules.defaultRules.PlayersActivePerTurn;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.objectActions.AttackEvent;
import voogasalad_GucciGames.gameEngine.objectActions.MapObjectEventHandler;
import voogasalad_GucciGames.gameEngine.objectActions.MoveEvent;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;
import voogasalad_GucciGames.gameplayer.windows.GameWindowManager;

public class DemoMaker extends Application{

	private static String defaultGameLocation = "./src/games/demo.xml";

	@Override
	public void start(Stage stage) throws Exception {
		XStreamGameEngine xStream = new XStreamGameEngine();
		System.out.println("Creating and saving engine.");
		xStream.saveGameInfo(createGame());
		GameWindowManager windowmanager = new GameWindowManager("Duvall Tag");
	}

	public static void main(String[] args){
		launch(args);
	}

<<<<<<< HEAD
	private static GameEngine createGame() {
		MainGameEngine level1 = makeLevel(8, 8);
		MainGameEngine level2 = makeLevel(20,20);
	       
		GameEngine game = new GameEngine("Duvall Tag");
		game.addLevel("Level 1");
		game.addLevel("Level 2");
=======
	private static GameInfo createGame() {
		MainGameEngine level1 = makeLevel(4,4);
		MainGameEngine level2 = makeLevel(8, 8);
		MainGameEngine level3 = makeLevel(20,20);

		GameInfo game = new GameInfo("Duvall Tag");
		game.addLevel("Easy");
		game.addLevel("Medium");
		game.addLevel("Hard");
>>>>>>> 7193ebf3fa5242a17f1527500d5991846afb27c0
		game.getLevelsMap().get(0).assignEngine(level1);
		game.getLevelsMap().get(1).assignEngine(level2);
		game.getLevelsMap().get(2).assignEngine(level3);
		return game;
	}

	private static MainGameEngine makeLevel(int width, int height) {
		Map<Integer,GamePlayerPerson> myMapOfPlayers = new TreeMap<Integer,GamePlayerPerson>();
		myMapOfPlayers.put(-1,new GamePlayerPerson(-1)); //neutral player
		myMapOfPlayers.put(0,new GamePlayerPerson(0)); //player 1

		myMapOfPlayers.put(1,new GamePlayerPerson(1)); //player 2

		MapObject soldier = new MapObject(new TargetCoordinateSingle(1,0),0,1,"Duvall", "player/images/duvall.png");
		//MapObjectType archer = new MapObjectType("Student" , "player/images/smile.png");
		//MapObjectType ground = new MapObjectType("TileCharacteristics", "player/images/dummytexture.jpg");
		//MapObjectType water = new MapObjectType("TileCharacteristics", "player/images/dummytexture2.jpg");

		//MapObject soldier1 = new MapObject(soldier,new TargetCoordinateSingle(1,0),0,1);


		myMapOfPlayers.get(-1).setMovable(new MovablePlayerCharacteristic(2));
		myMapOfPlayers.get(0).setMovable(new MovablePlayerCharacteristic(2));
		myMapOfPlayers.get(1).setMovable(new MovablePlayerCharacteristic(2));

		MovableCharacteristic myMovableCharacteristic = new MovableCharacteristic(1, 3);
		HealthCharacteristic myHealthCharacteristic = new HealthCharacteristic(5);
                PlayersActivePerTurn moveOwn = new PlayersActivePerTurn();

                List<Rules> moveRules = new ArrayList<Rules>();
                moveRules.add(moveOwn);
		MoveEvent myMoveEvent = new MoveEvent("Move",moveRules,new ArrayList<Outcome>());
		soldier.addEvent("Move", myMoveEvent);

		Conditions onePlayerLeft = new CheckOnePlayerLeft(new HashMap<String,Object>());
		List<Conditions> endGameConditions = new ArrayList<Conditions>();
		endGameConditions.add(onePlayerLeft);
		OutcomeParams oParams = new OutcomeParams();
		oParams.setPlayerID(0);
                oParams.setPlayerID(1);
		Outcome endGame = new EndGame(endGameConditions, oParams);
		List<Outcome> attackOutcomes = new ArrayList<Outcome>();
		attackOutcomes.add(endGame);
		List<Rules> attackRules = new ArrayList<Rules>();
		attackRules.add(moveOwn);
		AttackEvent myAttackEvent = new AttackEvent("Attack",attackRules,attackOutcomes);
		AttackCharacteristic myAttackCharacteristic = new AttackCharacteristic(3, 100, 2);


		soldier.addCharacteristic("MovableCharacteristic", myMovableCharacteristic);
		soldier.addCharacteristic("HealthCharacteristic", myHealthCharacteristic);
		soldier.addCharacteristic("AttackCharacteristic", myAttackCharacteristic);

		soldier.addEvent("Attack", myAttackEvent);

		for (int i=0;i<width;i++) {
			for (int j=0;j<height;j++) {
				MapObject newObj;
				if((i+j)%2==0){
					newObj = new MapObject(new TargetCoordinateSingle(i,j),-1,0,"TileCharacteristic", "player/images/dummytexture2.jpg");
				}
				else{
					newObj = new MapObject(new TargetCoordinateSingle(i,j),-1,0,"TileCharacteristic", "player/images/dummytexture.jpg");
				}
				myMapOfPlayers.get(-1).getMapObjects().add(newObj);
				if ((i+j)%9==0) {
					MapObject arch = new MapObject(new TargetCoordinateSingle(i,j),1,1,"Student" , "player/images/smile.png");
					arch.addCharacteristic("HealthCharacteristic", new HealthCharacteristic(10));
					arch.addCharacteristic("AttackCharacteristic", new AttackCharacteristic(3,5,2));
					arch.addCharacteristic("MovableCharacteristic", new MovableCharacteristic(5,1));
					arch.addEvent("Move", myMoveEvent);

					arch.addEvent("Attack", myAttackEvent);

					myMapOfPlayers.get(1).getMapObjects().add(arch);
				}
			}
		}

		myMapOfPlayers.get(0).getMapObjects().add(soldier);

		AllPlayers myPlayers = new AllPlayers(myMapOfPlayers);

		MainGameEngine engine = new MainGameEngine(myPlayers);
		engine.setMapHeight(height);
		engine.setMapWidth(width);
		for(Integer key: myMapOfPlayers.keySet()){
			myMapOfPlayers.get(key).getMapObjects().stream().forEach(mo -> {
				mo.setMapObjectEventHandler(new MapObjectEventHandler(new BasicParameters(null,engine)));;
			});
		}
		return engine;
	}

}
