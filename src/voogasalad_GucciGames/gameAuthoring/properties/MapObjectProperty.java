package voogasalad_GucciGames.gameAuthoring.properties;

import java.util.HashMap;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.guiexceptions.InvalidInputException;

public class MapObjectProperty {
	
	private Map<String, String> myMap = new HashMap<String, String>();
	

	public void addPropertyElement(String propName, String prop) throws InvalidInputException {
		// TODO Auto-generated method stub
		myMap.put(propName, prop);
		printProperty(myMap);
	}
	
	public void printProperty(Map myMap){
		myMap.forEach((k,v) -> System.out.println("key: " + k + " value: " + v));
	}


}
