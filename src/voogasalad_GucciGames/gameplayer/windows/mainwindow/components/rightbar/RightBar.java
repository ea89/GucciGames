package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.rightbar;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.cell.contents.PlayerMapObjectInterface;


public class RightBar extends WindowComponent implements Observer {
	private PlayerMapObjectInterface activeMapObject;
    private VBox container;
    private double spacing = 5;
    ResourceBundle myBundle;
	public RightBar(GameScene scene, GameControllerInterface controller, ResourceBundle bundle) {
		super(scene, controller);
        container = new VBox(spacing);
        myBundle=bundle;
        initializeData();
	}

	private void initializeData() {
		myController.addMOObserver(this);
        GameStatsDisplay gameStats = new GameStatsDisplay();
        ActionDisplay actions = new ActionDisplay(myController);
        container.getChildren().add(actions.getNodeToDraw());
        container.getChildren().add(gameStats.getNodeToDraw());
        container.getStyleClass().add(myBundle.getString("RightVBox"));
	}

	@Override
	public Parent getParent() {
		return container;
	}

	@Override
	public void update(Observable o, Object arg) {
        if (arg!=null && arg.getClass().equals(PlayerMapObjectInterface.class)) {
        	activeMapObject = (PlayerMapObjectInterface)arg;
        	
        }
        
	}

}
