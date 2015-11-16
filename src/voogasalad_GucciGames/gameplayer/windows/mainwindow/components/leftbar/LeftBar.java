package voogasalad_GucciGames.gameplayer.windows.mainwindow.components.leftbar;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import voogasalad_GucciGames.gameplayer.controller.GameControllerInterface;
import voogasalad_GucciGames.gameplayer.controller.GameEngineToGamePlayerInterface;
import voogasalad_GucciGames.gameplayer.windows.GameScene;
import voogasalad_GucciGames.gameplayer.windows.WindowComponent;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.map.MapInterface;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.DisplayComponent;

public class LeftBar extends WindowComponent implements DisplayComponent{
    private VBox container;
    private double spacing = 5;
    private MapInterface myMap;
    ResourceBundle myBundle;

    private DisplayMapObjectDetails objectDetails;
    private DisplayChat chatDisplay;

    public LeftBar (GameScene scene, GameControllerInterface controller, ResourceBundle bundle) {
        super(scene, controller);
        container = new VBox(spacing);
        myBundle=bundle;
        initializeData();
        myMap=myController.getMap();
    }
    
    private void initializeData() {
        objectDetails = new DisplayMapObjectDetails(myMap,myController);//TODO: create in properties file?
        chatDisplay = new DisplayChat();
        container.getChildren().add(objectDetails.getNodeToDraw());
        container.getChildren().add(chatDisplay.getNodeToDraw());
        container.getStyleClass().add(myBundle.getString("LeftVBox"));
        container.setPrefWidth(Double.parseDouble(myBundle.getString("leftprefwidth")));
    }

    @Override
    public Parent getParent () {
        return container;
    }
    
    public Node getNodeToDraw() {
        return container;
    }
    public ListChangeListener requestListener() {
        return objectDetails;
    }

}