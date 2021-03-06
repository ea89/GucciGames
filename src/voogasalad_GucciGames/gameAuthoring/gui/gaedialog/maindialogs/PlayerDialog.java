package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.PlayerParams;

public class PlayerDialog extends AGaeDialog<PlayerCharDialog> {
	private static final String gaeDialogPath = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.";
	private static final String settingsPackagePath = "voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.";

	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	private VBox myContent = new VBox();
	private IDialogGaeController controller;
	private ISwitchSettingsPane settingsPaneController;
	private List<PlayerContent> playerContentList = new ArrayList<PlayerContent>();
	private Properties prop;
	private DialogElements dialogElements;
	private int numOfPlayers;
	private ScrollPane scrollPane = new ScrollPane();

	List<ObjParam> myAllObjParams;
	private PlayerContent playerContent;

	public PlayerDialog(IDialogGaeController controller, int numberOfPlayers) {
		super();
		GaeDialogHelper helper = new GaeDialogHelper();
		prop = helper.loadProperties("dialogproperties/playerdialogproperties.properties");
		this.controller = controller;
		this.numOfPlayers = numberOfPlayers;
		this.myAllObjParams = controller.getPropertiesInterface().getAllPlayerCharParams();

		initialize();
		setScene();
		setSaveAction();

	}

	private void setScene() {
		scrollPane.setContent(myContent);
		scrollPane.setPrefSize(WIDTH, HEIGHT);
		this.getDialogPane().setContent(scrollPane);

	}

	protected void initialize() {

		Text title = new Text(prop.getProperty("title"));
		title.setId("title");
		myContent.getChildren().add(title);
		int num = 1;
		if (numOfPlayers == 0) {
			Text warning = new Text(prop.getProperty("noplayerwarning"));
			warning.setId("light");
			myContent.getChildren().add(warning);
		} else {

			while (numOfPlayers >= num) {
				playerContent = new PlayerContent(num, controller, prop, myAllObjParams);
				playerContentList.add(playerContent);
				dialogElements = new DialogElements(prop, controller);
				playerContent.setDialogElements(dialogElements);
				playerContent.init();
				myContent.getChildren().add(playerContent);
				num++;
			}
			this.getDialogPane().getButtonTypes().add(mySave);
		}

		myContent.setId("vbox-element");
	}

	@Override
	protected void setSaveAction() {
		this.setResultConverter(dialogButton -> {
			if (dialogButton == mySave) {
				for (int i = 0; i < playerContentList.size(); i++) {
					PlayerContent currPlayerContent = playerContentList.get(i);
					int id = currPlayerContent.getPlayerId();
					String name = currPlayerContent.getPlayerName();
					int numMoves = currPlayerContent.getNumMoves();
					controller.addPlayerToList(name, id);
					// Save Player
					PlayerParams params = new PlayerParams(id, name, numMoves);
					controller.savePlayer(params);
					List<ObjParam> currObjParams = currPlayerContent.getAllCheckedPlayerChars();
					String className = gaeDialogPath + "PlayerCharDialog";

					Reflection.createInstance(className, currObjParams, controller, i);
				}
			}
			return null;
		});
	}
}
