package auth_environment.view.Menus;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Created by BrianLin on 4/1/16.
 */
public class ElementMenu extends Menu {
	
	private static final String DIMENSIONS_PACKAGE = "auth_environment/properties/dimensions";
	private ResourceBundle myDimensionsBundle = ResourceBundle.getBundle(DIMENSIONS_PACKAGE);
	
	private static final String NAMES_PACKAGE = "auth_environment/properties/names";
	private ResourceBundle myNamesBundle = ResourceBundle.getBundle(NAMES_PACKAGE);
	
	public ElementMenu() {
		this.init();
	}
	
	private void init() {
		this.setText(this.myNamesBundle.getString("elementMenuLabel"));
		MenuItem towerItem = new MenuItem(this.myNamesBundle.getString("towerItemLabel"));
		towerItem.setOnAction(e -> this.createTower());
		this.getItems().addAll(towerItem); 
	}
	
	private void createTower() {
		
	}
	
}