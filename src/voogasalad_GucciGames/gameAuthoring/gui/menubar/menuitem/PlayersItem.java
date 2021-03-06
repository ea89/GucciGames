package voogasalad_GucciGames.gameAuthoring.gui.menubar.menuitem;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import voogasalad_GucciGames.gameAuthoring.AGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.PlayerDialog;

class PlayersItem extends MenuItem {
	PlayersItem(String name, AGuiGaeController controller) {
		super(name);
		setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
		setOnAction(e -> {
			PlayerDialog playerDialog = new PlayerDialog(controller, controller.getNumberOfPlayers());
			playerDialog.showAndWait();

			// TODO
		});
	}
}
