package voogasalad_GucciGames.gameAuthoring.model;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import voogasalad_GucciGames.gameAuthoring.IModelGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.map.GridPoint;
//import voogasalad_GucciGames.gameData.XMLWriter;
import voogasalad_GucciGames.gameData.XStreamGameEngine;
import voogasalad_GucciGames.gameData.wrapper.GameInfo;
import voogasalad_GucciGames.gameEngine.MainGameEngine;
import voogasalad_GucciGames.gameEngine.gamePlayer.AllPlayers;
import voogasalad_GucciGames.gameEngine.gamePlayer.GamePlayerPerson;
import voogasalad_GucciGames.gameEngine.mapObject.MapObject;
import voogasalad_GucciGames.gameEngine.targetCoordinate.TargetCoordinateSingle;

public class GAEModel implements IGAEModel{
    private TypeData typeData;
    private MapData mapData;
    private IModelGaeController myController;
	private Map<Integer, GamePlayerPerson> mapOfPlayers;	
    private AllPlayers players;
	private MainGameEngine engine;
	private List<DisplayMapObject> myMapObjects;
        private List<String> leftComponents;
        private List<String> rightComponents;
        private List<String> bottomComponents;
    
    public GAEModel(IModelGaeController controller) {
    	myController = controller;
    	typeData = new TypeData();
    	mapData = new MapData();
    	mapOfPlayers = new HashMap<>();
    	myMapObjects = new ArrayList<>();
        leftComponents = new ArrayList<String>();
        rightComponents = new ArrayList<String>();
        bottomComponents = new ArrayList<String>();
    	// Probs need to change this
		mapOfPlayers.put(-1, new GamePlayerPerson(-1));
		mapOfPlayers.put(0, new GamePlayerPerson(0));
		mapOfPlayers.put(1, new GamePlayerPerson(1));
		mapOfPlayers.put(2, new GamePlayerPerson(2));
    }
    

    @Override
    public void deleteComponent (DisplayMapObject mapObj) {
        int owner = mapObj.getOwnerID();
        mapOfPlayers.get(owner).getMapObjects().remove(mapObj);
    }
    
    @Override
    public DisplayMapObject addObject(GridPoint gridpoint, MapObjectType mapObjType, int ownerID) {
    	TargetCoordinateSingle targCoordSingle = new TargetCoordinateSingle(gridpoint.getX(), gridpoint.getY());
    	int layer = mapObjType.isTile() ? 0 : 1;
    	DisplayMapObject mapObject = new DisplayMapObject(mapObjType, targCoordSingle, ownerID,layer);
    	//mapOfPlayers.get(ownerID).addMapObject(mapObject);
    	myMapObjects.add(mapObject);
    	//Validate with engine, if failed, return null, else return this mapObject
    	return mapObject;
    }
    
    @Override
	public List<DisplayMapObject> getMapObjects() {
		return mapData.getMapObjects();
	}

    @Override
    public void clearMap () {
        mapData.clearMap();
    }

    @Override
    public void createCustomTileType (Map<String, String> m) {
    	MapObjectType objType = new DefaultMapObjectType(m.get("name"), m.get("imagePath"));//TODO: properties file
        typeData.addTileType(objType);
    }

    @Override
    public void createCustomUnitType (Map<String, String> m) {  
    	MapObjectType objType = new DefaultMapObjectType(m.get("name"), m.get("imagePath"));//TODO: properties file
        typeData.addUnitType(objType);
    }

    @Override
    public ObservableList<MapObjectType> getImmutableTileTypes () {
        return typeData.getImmutableTileTypes();
    }

    @Override
    public ObservableList<MapObjectType> getImmutableUnitTypes () {
        return typeData.getImmutableUnitTypes();
    }
    
    @Override
	public ObservableList<MapObjectType> getImmutableStructureTypes() {
		return typeData.getImmutableStructureTypes();
	}

    @Override
    public void saveToXML (File file) {    	
    	XStreamGameEngine saver = new XStreamGameEngine();
		AllPlayers myPlayers = new AllPlayers(mapOfPlayers);
		MainGameEngine engine = new MainGameEngine(myPlayers);
		//TODO: saving GameInfo instead of MainGameEngine
	        if (leftComponents.size()==0 && rightComponents.size()==0) {
	                leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayMapObjectImage");
	                leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayMapObjectDetails");
	                leftComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.DisplayChat");
	                rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.ActionDisplay");
	                rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.GameStatsDisplay");
	                rightComponents.add("voogasalad_GucciGames.gameplayer.windows.mainwindow.components.bar.EndTurnButton");
	        }
	        
	    //ASK ABOUT THIS, might not need engine to be passed into game info anymore
	        
		GameInfo game = new GameInfo(engine,leftComponents,rightComponents, bottomComponents);
		saver.saveGameInfo(game, file);
    }
    public void saveToXML(String filePath) {
        
    }
    
    private boolean validate(){ //TODO
        return false;
    }


    public void addComponent (Map<String,String> objParams) {
        DisplayMapObject mapObj = new DisplayMapObject(null, null, 0, 0);// TODO:MapObject(objParams);
        validate();
        mapData.addToMap(mapObj);
    }


	@Override
	public void changeOwner(MapObject mapObject, int playerID) {
		int oldID = mapObject.getPlayerID(); 
		mapOfPlayers.get(oldID).getMapObjects().remove(mapObject);
		mapObject.setOwnerID(playerID);
		mapOfPlayers.get(playerID).addMapObject(mapObject);
	}


    @Override
    public List<String> getLeftComponents () {
        return leftComponents;
    }
    @Override
    public List<String> getRightComponents () {
        return rightComponents;
    }
    @Override
    public List<String> getBottomComponents () {
        return bottomComponents;
    }
    @Override
    public void setLeftComponents (List<String> components) {
        leftComponents=components;
    }
    @Override
    public void setRightComponents (List<String> components) {
        rightComponents=components;
    }
    @Override
    public void setBottomComponents (List<String> components) {
        bottomComponents=components;
    }
    
//	public static void main(String[] args){
//		Map<Integer, GamePlayerPerson> mapOfPlayers = new HashMap<Integer, GamePlayerPerson>();	
////		List<GamePlayerPerson> listOfPlayers = new ArrayList<GamePlayerPerson>();	
//		AllPlayers myPlayers = new AllPlayers(mapOfPlayers);
//		MainGameEngine engine = new MainGameEngine(myPlayers, null, null);
//		System.out.println("made game engine");
//		XMLWriter writer = new XMLWriter();
//		writer.write(engine);
//		
//	}


}
