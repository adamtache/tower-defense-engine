package game_engine.game_elements;

import java.util.Collection;

/*
* Internal API that will be used to reflect the inclusion of terrain effects in games. 
* Most implementations of the Terrain API will apply some sort of affector to game elements within
* a certain area on the map.
*/
public class Terrain extends MapPiece{
	
	public Terrain(String name) {
		super(name);
		setID(getWorkspace().getIDFactory().createID(this));
	}
	
	/*
	* Grabs all of the units within the area specified by the Terrain 
	*
	* @return	
	*/
	public Collection<Unit> getAffectedUnits(){
		return null; // TEMP
	}
	
	/*
	* Sets the affector for the terrain, which will be applied to each unit within the the terrain
	*/
	public void setAffector(Affector effect){
		
	}

	/*
	* Applies the currently set affector to each unit that is considered inside the terrain.
	*/
	public void applyEffect(){
		
	}
	
}