package game_data;

import java.util.ArrayList;
import java.util.List;

import auth_environment.paths.MapHandler;
import game_engine.game_elements.Level;
import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.FunctionFactory;
import game_engine.factories.StoreFactory;
import game_engine.factories.UnitFactory;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Unit;
import game_engine.place_validations.PlaceValidation;
import game_engine.score_updates.ScoreUpdate;
import game_engine.store_elements.Store;
import game_engine.wave_goals.WaveGoal;

public class GameData implements IGameData {
	private List<Level> myLevels = new ArrayList<Level>();
	private List<Unit> myPlacedUnits = new ArrayList<Unit>();
	private List<PlaceValidation> myPlaceValidations = new ArrayList<PlaceValidation>();
	private WaveGoal myWaveGoal;
	private ScoreUpdate myScoreUpdate;
	private double myScore = 0;
	private int myCurrentWaveIndex = 0;
	
	private MapHandler myMapHandler = new MapHandler();

	private FunctionFactory myFunctionFactory = new FunctionFactory();
	private AffectorFactory myAffectorFactory = new AffectorFactory(myFunctionFactory);
	private UnitFactory myUnitFactory = new UnitFactory();
	private StoreFactory myStoreFactory = new StoreFactory(myUnitFactory.getUnitLibrary(), myAffectorFactory.getAffectorLibrary()); 
	
	@Override
	public List<Level> getLevels() {
		return myLevels;
	}
	@Override
	public void setLevels(List<Level> levels) {
		myLevels = levels;
	}

	@Override
	public List<Branch> getBranches() {
		return myMapHandler.getBranches();
	}
	
	@Override
	public List<Unit> getPlacedUnits() {
		return myPlacedUnits;
	}
	@Override
	public void setPlacedUnits(List<Unit> placedUnits) {
		myPlacedUnits = placedUnits;
	}

	@Override
	public List<Affector> getAffectors() {
		return myAffectorFactory.getAffectorLibrary().getAffectors();
	}
	@Override
	public AffectorFactory getAffectorFactory() {
		return myAffectorFactory;
	}
	@Override
	public void setAffectorFactory(AffectorFactory affectorFactory) {
		myAffectorFactory = affectorFactory;
	}

	@Override
	public List<PlaceValidation> getPlaceValidations() {
		return myPlaceValidations;
	}
	@Override
	public void setPlaceValidations(List<PlaceValidation> placeValidations) {
		myPlaceValidations = placeValidations;
	}

	@Override
	public WaveGoal getWaveGoal() {
		return myWaveGoal;
	}
	@Override
    public void setWaveGoal(WaveGoal waveGoal) {
    	myWaveGoal = waveGoal;
    }

	@Override
	public ScoreUpdate getScoreUpdate() {
		return myScoreUpdate;
	}
	@Override
    public void setScoreUpdate(ScoreUpdate scoreUpdate) {
    	myScoreUpdate = scoreUpdate;
    }
   
	@Override
	public double getScore() {
		return myScore;
	}
	@Override
    public void setScore(double score) {
    	myScore = score;
    }
 
	@Override
	public UnitFactory getUnitFactory() {
		return myUnitFactory;
	}
	@Override
	public void setUnitFactory(UnitFactory unitFactory) {
		myUnitFactory = unitFactory;
	}
	
	@Override
	public int getCurrentWaveIndex() {
		return myCurrentWaveIndex;
	}
	@Override
	public void setCurrentWaveIndex(int currentWaveIndex) {
		myCurrentWaveIndex = currentWaveIndex;
	}
	
	@Override
	public MapHandler getMapHandler() {
		return myMapHandler;
	}
	@Override
	public void setMapHandler(MapHandler mapHandler) {
		myMapHandler = mapHandler;
	}
	@Override
	public StoreFactory getStoreFactory() {
		return myStoreFactory;
	}


}
