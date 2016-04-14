package game_data;

import java.util.ArrayList;
import java.util.List;

import auth_environment.backend.GameSettings;
import auth_environment.backend.ISettings;
import game_engine.EngineWorkspace;
import game_engine.IPlayerEngineInterface;
import game_engine.game_elements.Enemy;
import game_engine.game_elements.Level;
import game_engine.game_elements.Path;
import game_engine.game_elements.Terrain;
import game_engine.game_elements.Tower;
import game_engine.game_elements.Unit;
import game_engine.properties.Position;

public class GameData implements IGameData {
	private List<Level> myLevels;
	private List<Tower> myTowerTypes;
	private List<Path> myPaths;
	private List<Unit> myTerrains;
	private List<Unit> myEnemies;
	private ISettings mySettings;
	//	private List<Unit> myTerrains;
	private IDataConverter<IPlayerEngineInterface> mySerializer;
	private List<List<Position>> myPositionLists;

	public GameData(){
		myLevels = new ArrayList<>();
		myTowerTypes = new ArrayList<>();
		myPaths = new ArrayList<>();
		myTerrains = new ArrayList<>();
		mySettings = new GameSettings();
		mySerializer = new AuthSerializer<IPlayerEngineInterface>();
	}

	@Override
	public void setLevels(List<Level> levels) {
		myLevels = levels;
	}
	@Override
	public void addLevel(Level levelToAdd) {
		myLevels.add(levelToAdd);
	}

	@Override
	public void setTowerTypes(List<Tower> towerTypes) {
		myTowerTypes = towerTypes;
	}
	@Override
	public void addTowerType(Tower towerTypeToAdd) {
		myTowerTypes.add(towerTypeToAdd);
	}

	//	@Override
	//	public void setPaths(List<Path> paths) {
	//		myPaths = paths;
	//	}
	//	@Override
	//	public void addPath(Path pathToAdd) {
	//		myPaths.add(pathToAdd);
	//	}
	@Override
	public void addGameSettings(ISettings settings) {
		mySettings = settings;
	}

	//	@Override
	//	public void setTerrains(List<Unit> terrains) {
	//		myTerrains = terrains;
	//	}

	// Getters
	@Override
	public List<Level> getLevels() {
		return myLevels;
	}
	@Override
	public List<Tower> getTowerTypes() {
		return myTowerTypes;
	}
	@Override
	public List<Path> getPaths() {
		return myPaths;
	}
	@Override
	public ISettings getSettings() {
		return mySettings;
	}


	@Override
	public void saveGameData() {
		IPlayerEngineInterface workspace = new EngineWorkspace();
		workspace.setPaths(myPaths);
		workspace.setTerrains(myTerrains);
		workspace.setTowerTypes (myTowerTypes);
		workspace.setLevels (myLevels);
		mySerializer.saveElement(workspace);
	}

	public void addPositions(List<Position> list) {
		myPositionLists.add(list);
	}

	public List<List<Position>> getPositions() {
		return myPositionLists;
	}
	
	public void setEnemies(List<Unit> enemies) {
		this.myEnemies = enemies;
	}
	
	public void setTerrains(List<Unit> terrains) {
		this.myTerrains = terrains;
	}
	
	public List<Unit> getEnemies() {
		return myEnemies;
	}
	
	public List<Unit> getTerrains() {
		return myTerrains;
	}

}