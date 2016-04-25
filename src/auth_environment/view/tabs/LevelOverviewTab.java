package auth_environment.view.tabs;

import auth_environment.IAuthEnvironment;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import auth_environment.Models.LevelOverviewTabModel;
import auth_environment.Models.Interfaces.IAuthModel;
import auth_environment.Models.Interfaces.ILevelOverviewTabModel;
import javafx.scene.layout.BorderPane;

public class LevelOverviewTab extends Tab {
	
	private BorderPane myRoot;
	private TabPane myTabs;
	private IAuthModel myAuthModel;
	private IAuthEnvironment myInterface;
	private Button addNewLevelButton;
	private ILevelOverviewTabModel overviewModel;
	
	public LevelOverviewTab(String name, IAuthModel authModel){
		super(name);
		this.myTabs = new TabPane();
		this.addNewLevelButton = new Button("Add New Level");
		this.myAuthModel = authModel;
		this.overviewModel = new LevelOverviewTabModel();
		this.myInterface = this.myAuthModel.getIAuthEnvironment(); 
		init();
	}
	
	private void init(){
		myRoot = new BorderPane();
		myRoot.setTop(addNewLevelButton);
		addSubTabs();
		myRoot.setLeft(myTabs);
		this.setContent(myRoot);
	}
	
	private void addSubTabs(){
		addNewLevelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final Tab tab = new LevelTab("Level " + (myTabs.getTabs().size() + 1), myInterface, myAuthModel);
				myTabs.getTabs().addAll(tab);
				myTabs.getSelectionModel().select(tab);
			}
		});
	}
	
	public Node getRoot(){
		return this.myRoot;
	}
}
