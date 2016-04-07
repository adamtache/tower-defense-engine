package auth_environment.view.Menus;

import java.util.ResourceBundle;

import auth_environment.view.Workspace;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 3/31/16.
 */
public class TabMenu extends Menu {
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);

	private TabPane myTabs;
	
	public TabMenu(TabPane tabs){
		this.myTabs = tabs;
		this.setText(myNamesBundle.getString("tabMenuLabel"));
		this.init();
	}
	
	private void init() {
		MenuItem newTabItem = new MenuItem(myNamesBundle.getString("levelItemLabel")); 
		newTabItem.setOnAction(e -> createNewTab());
		this.getItems().add(newTabItem);
	}
	
	// TODO: pop up an alert asking the Developer to name gthe new Wave (or auto-generate a Wave name) 
	private void createNewTab(){
		Workspace newWorkspace = new Workspace(myTabs);
		myTabs.getTabs().add(new Tab(myNamesBundle.getString("levelItemLabel"), newWorkspace.getRoot()));
	}
}