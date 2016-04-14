package auth_environment.view;

import java.util.ResourceBundle;

import auth_environment.backend.ISettings;
import auth_environment.view.Menus.MenuToolBar;
import game_data.GameData;
import game_data.IGameData;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

/**
 * Created by BrianLin on 3/31/16.
 * Team member responsible: Brian
 *
 * This class represents a single tab (ie Level) within our View.
 */

public class Workspace {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private TabPane myTabPane; 
	private BorderPane myBorderPane = new BorderPane(); 
	private MapDisplay myDisplay = new MapDisplay();
	private ElementPicker myPicker = new ElementPicker(); 
	
	private IGameData myGameData;; 
	
	public Workspace(TabPane tabPane, IGameData gameData) {
		this.myTabPane = tabPane; 
		this.myGameData = gameData; 
		this.setupBorderPane();
	}
	
	private void setupBorderPane() {
	    ElementPicker myPicker = new ElementPicker();
		this.myBorderPane.setPrefSize(Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneWidth")),
									  Double.parseDouble(myDimensionsBundle.getString("defaultBorderPaneHeight")));
		this.myBorderPane.setTop(new MenuToolBar(this.myTabPane, this.myPicker, this.myGameData));
//		this.myBorderPane.setLeft(hello);
		myPicker.setPrefSize(400,400);
		this.myBorderPane.setRight(myPicker);
		this.myBorderPane.setCenter(myDisplay);
		this.myBorderPane.setRight(new ElementPicker());
	}
	
	public void writeToGameData() {
		GameData gameData = new GameData(); 
		gameData.setTowers(myPicker.getTowers());
	}
	
    public Node getRoot() {
    	return this.myBorderPane; 
    }
}
