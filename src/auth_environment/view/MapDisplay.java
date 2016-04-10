package auth_environment.view;

import java.util.ResourceBundle;

import auth_environment.backend.ISelector;
import auth_environment.backend.MapDisplayModel;
import auth_environment.backend.SelectorModel;
import game_engine.game_elements.GameElement;
import javafx.scene.layout.Pane;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Xander
 * Refactorings + Packaging: Brian
 *
 * This class is for the main Map Display showing the current Map.
 * The Developer should be able to place Game Elements (as long as they are placeable).
 */

public class MapDisplay extends Pane {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	 
	private MapDisplayModel myModel;
	private Grid myGrid;
	
	public MapDisplay(ISelector selector) {
		this.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultMapWidthPixels")), 
						 Double.parseDouble(myDimensionsBundle.getString("defaultMapHeightPixels")));
		myModel = new MapDisplayModel(Integer.parseInt(myDimensionsBundle.getString("defaultMapWidthCount")), 
									  Integer.parseInt(myDimensionsBundle.getString("defaultMapHeightCount")),
									  selector);
		myGrid = new Grid(myModel, 
						  Double.parseDouble(myDimensionsBundle.getString("defaultMapWidthPixels")), 
						  Double.parseDouble(myDimensionsBundle.getString("defaultMapWidthPixels")));
		this.getChildren().add(myGrid.getRoot());
	}
	
	
    public void displayElement(GameElement element) {
    	
    }

}
