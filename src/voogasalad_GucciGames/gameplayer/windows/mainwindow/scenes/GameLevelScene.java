package voogasalad_GucciGames.gameplayer.windows.mainwindow.scenes;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import voogasalad_GucciGames.gameplayer.gameloader.GameLoader;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.GameSceneManager;
import voogasalad_GucciGames.gameplayer.windows.GameWindow;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.SplashScreen;

public class GameLevelScene extends GameScene {

        private Scene myScene;
	
	public GameLevelScene(GameSceneManager manager, GameWindow window, String config) {
		super(manager, window, config);
	}
	
	@Override
	protected void readConfig(){
		super.readConfig();
	}

	@Override
	public void load() {
		System.out.println("LOADED " + getName());
		myManager.sceneFinished();
	}

    @Override
    public void refresh () {
        // TODO Auto-generated method stub
        
    }
}
