package auth_environment;

import java.util.List;

import game_engine.affectors.Affector;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

/**
 * This interface is the external API for the Auth Environment (excluding Factory calls). 
 * @author Brian Lin
 *
 */

public interface IAuthEnvironment {
	
	// GlobalGameTab - Brian
	
	public void setGameName(String name);
	
	public String getGameName(); 
	
	public void setSplashScreen(String fileName); 
	
	public String getSplashScreen(); 
	
	// Path Tab - Brian
	
	public List<Branch> getPathBranches();
	
	public void setPathBranches(List<Branch> branches); 
	
	public List<Branch> getGridBranches();
	
	public void setGridBranches(List<Branch> branches); 
	
	public List<Position> getGoals();
	
	public void setGoals(List<Position> goals);
	
	public List<Position> getSpawns();
	
	public void setSpawns(List<Position> spawns); 
	
	// All Levels Tab - Austin
	
	public void setLevels(List<Level> levels); 
	
	public List<Level> getLevels();
	
	public void addLevel(Level level); 
	
	// These Units have been placed on the Map. Not to be confused with available Enemy and Tower Units.
	
	public void placeUnit(Unit unit); // add this Unit to current List of placed Units 
	
	public void setPlacedUnits(List<Unit> units); 
	
	public List<Unit> getPlacedUnits(); 
	
	// Austin will also use getTowers(), getTerrains(), and getEnemies() (seen below) 
	
	// Create Units + Affector Tab - Virginia
	
	public void setTowers(List<Unit> towers); 
	
	public List<Unit> getTowers(); 
	
	public void setTerrains(List<Unit> terrains); 
	
	public List<Unit> getTerrains(); 
	
	public void setEnemies(List<Unit> enemies); 
	
	public List<Unit> getEnemies(); 
	
	public void setProjectiles(List<Unit> projectiles); 
	
    public List<Unit> getProjectiles();
    
    public void setAffectors(List<Affector> affectors); 
    
    public List<Affector> getAffectors();
    
}