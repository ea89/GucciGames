package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.properties.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayerDialog extends GaeDialog  {
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;	
	private VBox myContent = new VBox();	
	private Stage playerDialog = new Stage();
	private IDialogGaeController controller;
	
	private Properties prop;
	//private ObjectProperty playerProperty = new ObjectProperty();
	private Map<Integer, ObjectProperty> playerProperties = new HashMap<Integer, ObjectProperty>();
	private ISaveObjProperty saveObjProperty;
	private ISaveCustomObj saveCustomObj;
	private DialogElements dialogElements;
	private Scene scene;
	//private SaveField save;
	private int numOfPlayers;

	
	public PlayerDialog(IDialogGaeController controller, int numberOfPlayers) {
		
		super();
		prop = loadProperties("dialogproperties/playerdialogproperties.properties");			
		this.controller = controller;
		this.numOfPlayers = numberOfPlayers;		
		myContent = initializeDialog();
		//myContent.getChildren().add(save.getContent());
		setScene();
	
		
	}
	
	private void setScene(){
		scene = new Scene(myContent, WIDTH, HEIGHT);		
		scene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
		playerDialog.setScene(scene);	
		
	}

	@Override
	protected VBox initializeDialog() {
		// TODO Auto-generated method stub
		VBox vbox = new VBox();
		Text title = new Text(prop.getProperty("title"));
		title.setId("title");
		vbox.getChildren().add(title);
		int num = 1;
		while(numOfPlayers  >=  num) {
			PlayerContent content = new PlayerContent(num);
			ObjectProperty playerProperty = new ObjectProperty();
			saveObjProperty = setSavePropertyFunction(playerProperty, saveObjProperty);		
			dialogElements = new DialogElements(prop, playerProperty, saveObjProperty, null, saveCustomObj);
			content.setDialogElements(dialogElements);
			content.init();
			vbox.getChildren().add(content.getContent());
			SaveField save = new SaveField(dialogElements, controller);
			vbox.getChildren().add(save.getContent());
			num++;
		}
		vbox.setId("vbox-element");
		return vbox;
	}
	
	public void showDialog(){
		super.showDialog(playerDialog);
	}

}
