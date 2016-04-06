package game_player;

import java.util.ResourceBundle;

import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PlayerMainTab implements IPlayerTab{
	private static final int PANEL_PADDING = 10;
	private Tab myTab;
	private BorderPane myRoot;
	private ResourceBundle myResources;
	
	private IGUIObject gameSpeed;
	
	public PlayerMainTab(ResourceBundle r) {
		myResources = r;
	}
	
	@Override
	public Tab getTab() {
		initializeTab();
		myTab = new Tab();
		myRoot = new BorderPane();
		
		initializeElements();
		createConfigurationPanel();
		
		myTab.setContent(myRoot);
		return myTab;
	}
	
	private void initializeTab() {
	}

	//TODO: Possibly use reflection/other techniques to initialize elements.
	private void initializeElements() {
		gameSpeed = new GameSpeedSlider(myResources);
	}
	
	private void createConfigurationPanel() {
		VBox configurationPanel = new VBox(PANEL_PADDING);
		configurationPanel.getChildren().add(gameSpeed.createNode());
		myRoot.setRight(configurationPanel);
	}
}
