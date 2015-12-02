package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.listelements;

import javafx.scene.text.Text;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.ScrollBarField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.CharacteristicsParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.stylesheets.IListView;

public class CharacteristicsListItem extends ListItem{
	
	private String name;
	private String displayName;
	private double min;
	private double max;
	private ScrollBarField scrollBar;
	
	
	
	public CharacteristicsListItem(String displayName, String name, double min, double max, double increm){
		this.displayName = displayName;
		this.name = name;
		
		scrollBar = new ScrollBarField(name, min, max, increm);
		this.getChildren().addAll( scrollBar);
	}
	
	public CharacteristicsParam createNewCharacteristics(){
		return new CharacteristicsParam(displayName, scrollBar.getSelectedDouble());
		
	}
	
	public double getParamDouble(){
		return this.scrollBar.getSelectedDouble();
	}
	
	public void setParamValue(Double val){
		this.scrollBar.setSelected(val);
	}

	@Override
	public void actionForRemoveBtn(IListView listViewInterface) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setSelected(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSelected() {
		// TODO Auto-generated method stub
		return null;
	}

}
