package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;

public class TileTab extends AbstractTab {
	
	public TileTab(){
		super();
		setText("Tiles");
		allImagePaths = Arrays.asList("voogasalad_GucciGames/graphics/fire.png", "voogasalad_GucciGames/graphics/water.jpg");
		addImages();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
