package auth_environment.backend;

import java.util.Collection;

import game_engine.properties.Position;

public interface ISelector {
	
	// Single click (only) 
	public void chooseElement(int index);
	
	// Returns index of the selected GameElement (we will only have one GameElement selected at a time) 
	public int getElementIndex();
	
	// For debugging and testing
	public void printIndex(); 
	public void printPositions(); 
	public void printMostRecentPosition(); 
	
	// Maintain a List of selected points (SHIFT + click), used for Terrain and Path
	public void choosePosition(double x, double y);
	public Collection<Position> getPositions(); 

}
