package auth_environment.view.tabs;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ResourceBundle;

import auth_environment.Models.LevelOverviewTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.ILevelOverviewTabModel;
import javafx.scene.layout.BorderPane;

public class LevelOverviewTab extends Tab {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);

	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	private BorderPane myRoot;
	private TabPane myTabs;
	private IAuthModel myAuthModel;
	private ILevelOverviewTabModel overviewModel;
	
	public LevelOverviewTab(String name, IAuthModel authModel){
		super(name);
		this.myAuthModel = authModel;
		init();
	}
	
	private void init() {
		this.myRoot = new BorderPane();
		this.myTabs = new TabPane();
		this.addRefresh();
		this.setupBorderPane();
		this.setContent(myRoot);
	}
	
	private void setupBorderPane() {
		myRoot.setTop(this.buildNewLevelButton());
		myRoot.setLeft(myTabs);
	}
	
	private void addRefresh() {
		this.myRoot.setOnMouseEntered(e -> {
			this.refresh();
		});
	}
	
	private Node buildNewLevelButton() {
		Button addNewLevelButton = new Button(this.myNamesBundle.getString("levelItemLabel"));
		addNewLevelButton.setOnAction(e -> {
			Tab tab = new LevelTab("Level " + (myTabs.getTabs().size() + 1), myAuthModel);
			// TODO: add new Level to the Game Data
			myTabs.getTabs().addAll(tab);
			myTabs.getSelectionModel().select(tab);
		});
		return addNewLevelButton;
	}
	
	private void refresh() {
		this.overviewModel = new LevelOverviewTabModel(this.myAuthModel.getIAuthEnvironment());
	}
	
	public Node getRoot(){
		return this.myRoot;
	}
}