package auth_environment.Models;

import auth_environment.Models.Interfaces.IUnitView;
import game_engine.game_elements.Unit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by BrianLin on 4/20/16
 * Team member responsible: Brian
 *
 * The Auth Environment's way of displaying a Unit. 
 * Contains both the Unit and an ImageView to be displayed.
 * 
 *  DO NOT write this to XML
 * 
 */

public class UnitView extends ImageView implements IUnitView {
	
	private Unit myUnit;
	
	public UnitView(Unit unit) {
		super();
		this.myUnit = unit;
	}
	
	public UnitView(Unit unit, Image image) {
		super(image); 
		this.myUnit = unit; 
	}
	
	@Override
	public void setUnit(Unit unit) {
		this.myUnit = unit; 
	}

	@Override
	public Unit getUnit() {
		return this.myUnit;
	}

}
