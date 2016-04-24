package game_engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import auth_environment.IAuthEnvironment;
import auth_environment.paths.GridFactory;
import auth_environment.paths.PathGraphFactory;
import auth_environment.paths.PathHandler;
import auth_environment.paths.PathNode;
import exceptions.WompException;
import game_engine.IDFactory;
import game_engine.affectors.Affector;
import game_engine.factories.AffectorFactory;
import game_engine.factories.EnemyFactory;
import game_engine.factories.FunctionFactory;
import game_engine.factories.TerrainFactory;
import game_engine.factories.TimelineFactory;
import game_engine.factories.TowerFactory;
import game_engine.game_elements.Branch;
import game_engine.game_elements.Level;
import game_engine.game_elements.Unit;
import game_engine.game_elements.Wave;
import game_engine.games.Timer;
import game_engine.libraries.AffectorLibrary;
import game_engine.libraries.FunctionLibrary;
import game_engine.physics.CollisionDetector;
import game_engine.physics.EncapsulationController;
import game_engine.properties.Position;
import game_engine.properties.UnitProperties;
import game_engine.score_updates.EnemyDeathScoreUpdate;
import game_engine.score_updates.ScoreUpdate;
import game_engine.store_elements.Store;
import game_engine.wave_goals.EnemyNumberWaveGoal;
import game_engine.wave_goals.WaveGoal;

public class EngineWorkspace implements GameEngineInterface{

	private int nextWaveTimer;
	private List<Branch> myGridBranches;
	private List<Branch> myPathBranches; 
	private List<Level> myLevels;
	private List<Unit> myTowers;
	private List<Unit> myEnemies;
	private List<Unit> myTerrains;
	private List<Unit> myProjectiles; 
	private List<Affector> myAffectors;
	private List<Unit> myPlacedUnits;
	private Level myCurrentLevel;
	private boolean pause;
	private CollisionDetector myCollider;
	private EncapsulationController myEncapsulator;
	private double myBalance;
    private Store myStore;
    private double score;
	private Timer myTimer;
	private FunctionFactory myFunctionFactory;
	private List<PathNode> myPaths;
	private WaveGoal waveGoal;
    private ScoreUpdate scoreUpdate;
    private List<Position> myGoals;

	public void setUpEngine (IAuthEnvironment data) {
		waveGoal = new EnemyNumberWaveGoal();
        scoreUpdate = new EnemyDeathScoreUpdate();
		myGridBranches = data.getGridBranches();
		myPathBranches = data.getPathBranches();
		myLevels = data.getLevels();
		myTowers = data.getTowers();
		myEnemies = data.getEnemies();
		myTerrains = data.getTerrains();
		myProjectiles = data.getProjectiles();
		myAffectors = data.getAffectors();
		myPlacedUnits = data.getPlacedUnits();
		myFunctionFactory = new FunctionFactory();
		myGoals = data.getGoals();
		if(myLevels.size() > 0){
			myCurrentLevel = myLevels.get(0);
			myCurrentLevel.setGoals(data.getGoals());
			myCurrentLevel.setSpawns(data.getSpawns());
		}
		initialize();
	}

	private void initialize(){
		myTimer = new Timer();
		myCollider = new CollisionDetector(this);
		myEncapsulator = new EncapsulationController(this);
		if(myGridBranches == null)	this.myGridBranches = new ArrayList<Branch>();
		if(myPathBranches == null)	this.myPathBranches = new ArrayList<Branch>();
		if(myLevels == null)	this.myLevels = new ArrayList<Level>();
		if(myTowers == null)	this.myTowers = new ArrayList<Unit>();
		if(myEnemies == null)	this.myEnemies = new ArrayList<Unit>();
		if(myTerrains == null)	this.myTerrains = new ArrayList<Unit>();
		if(myProjectiles == null)	this.myProjectiles = new ArrayList<Unit>();
		if(myAffectors == null)	this.myAffectors = new ArrayList<Affector>();
		if(myPlacedUnits == null)	this.myPlacedUnits = new ArrayList<Unit>();
		if(myStore == null)		myStore = new Store(500);
		if(myLevels.size() > 0){
			myCurrentLevel = myLevels.get(0);
		}
	}

	public String getGameStatus () {
		if (myCurrentLevel.getMyLives() <= 0) {
			return "Waves remaining: " + myCurrentLevel.wavesLeft() + ", Lives remaining: " + "0";
		}
		return "Waves remaining: " + myCurrentLevel.wavesLeft() + 
				", Lives remaining: " + myCurrentLevel.getMyLives();
	}

	public void addBalance (double money) {
		myBalance += money;
	}

	public void addLevel (Level level) {
		myLevels.add(level);
	}

	public int getLives () {
		return myCurrentLevel.getMyLives();
	}

	public void clearProjectiles () {
		myProjectiles.forEach(t -> {
			t.setInvisible();
			t.setHasCollided(true);
		});
	}

	public List<Unit> getTerrains () {
		return myTerrains;
	}

	public List<String> saveGame () {
		// TODO Auto-generated method stub
		return null;
	}

	public void playLevel (int levelNumber) {
		myCurrentLevel = myLevels.get(levelNumber);
		pause = false;
	}

	public void playWave (int waveNumber) {
		// TODO: pause current wave
		myCurrentLevel.setCurrentWave(waveNumber);
	}

	public void continueWaves () {
		myCurrentLevel.playNextWave();
		pause = false;
	}
	
	private void initializeStore(){
		if(myStore == null){
			myStore = new Store(500);
		}
	}
	
	// change this
	@Override
	public boolean addTower (String name, double x, double y) {
		initializeStore(); // TODO: remove this
		Unit purchased = myStore.purchaseUnit(name);
		if(purchased != null){
			Unit copy = purchased.copyUnit();
			copy.getProperties().setPosition(x,y);
			myTowers.add(copy);
			return true;
		}
		return false;
	}

	@Override
	public List<Unit> getTowerTypes () {
		return myStore.getTowerList();
	}

	public List<Affector> getAffectors(){
		return myAffectors;
	}

	@Override
	public void update (){
		List<Unit> placingUnits = myCurrentLevel.getCurrentWave().getPlacingUnits();
        myStore.clearBuyableUnits();
        placingUnits.stream().forEach(u -> myStore.addBuyableUnit(u, 100));
        nextWaveTimer++;
        boolean gameOver = myCurrentLevel.getMyLives() <= 0;
        if (!pause && !gameOver) {
            myTowers.forEach(t -> t.update());
            myEnemies.forEach(e -> e.update());
            myCollider.resolveEnemyCollisions(myProjectiles);
            myEncapsulator.resolveEncapsulations(myTerrains);
            Unit newE = myCurrentLevel.update();
            if (newE != null) {
            	myEnemies.add(newE);
            }// tries to spawn new enemies using Waves
            // myStore.applyItem("Interesting", this.myEnemys);

        }
        if (myCurrentLevel.getNextWave() != null && waveGoal.reachedGoal(this)) {
            nextWaveTimer = 0;
            System.out.println("NEXT WAVE");
            continueWaves();
        }
        if (myEnemies.size() == 0) {
            clearProjectiles();
        }
        myProjectiles.forEach(p -> p.update());
        myProjectiles.removeIf(p -> !p.isVisible());
        myTerrains.forEach(t -> t.update());
        scoreUpdate.updateScore(this, myCurrentLevel);

	}

	public void decrementLives () {
		myCurrentLevel.decrementLife();
	}

	public List<Unit> getAllUnits(){
		List<Unit> units = new ArrayList<>();
		units.addAll(myTowers);
		units.addAll(myEnemies);
		units.addAll(myProjectiles);
		units.addAll(myTerrains);
		return units;
	}

	@Override
	public List<Branch> getGridBranches() {
		return myGridBranches;
	}

	@Override
	public void modifyTower(int activeTowerIndex, UnitProperties newProperties) {
		// TODO Auto-generated method stub
		
	}

	public List<Unit> getEnemies() {
		return myEnemies;
	}

	@Override
	public List<Unit> getTowers() {
		return myTowers;
	}

	@Override
	public List<Level> getLevels() {
		return myLevels;
	}

	@Override
	public List<Unit> getProjectiles() {
		return myProjectiles;
	}

	@Override
	public List<Branch> getBranches() {
		return myPathBranches;
	}

	@Override
	public boolean isPaused() {
		return myTimer.isPaused();
	}

	@Override
	public void setPaused() {
		myTimer.pause();
	}

	@Override
	public boolean isGameOver() {
		return myLevels.get(myLevels.size()-1).wavesLeft() == 0;
	}

	@Override
	public Timer getTimer() {
		return myTimer;
	}

	@Override
	public Level getCurrentLevel() {
		return myCurrentLevel;
	}

	@Override
	public FunctionFactory getFunctionFactory() {
		return myFunctionFactory;
	}
	
	public List<PathNode> getPaths(){
		return myPaths;
	}
	
	@Override
    public double getScore () {
        return score;
    }

    public void setScore (double score) {
        this.score = score;
    }
    
    public List<Affector> getUpgrades(Unit unitToUpgrade) {
        return myStore.getUpgrades(unitToUpgrade);
    }
    
    public void applyUpgrade(Unit unitToUpgrade, Affector affector) {
        myStore.buyUpgrade(unitToUpgrade, affector);
    }
    
    @Override
    public List<Position> getGoals () {
        return myGoals;
    }
    
    @Override
    public int getNextWaveTimer () {
        return nextWaveTimer;
    }

    public void decrementLives (int lives) {
        myCurrentLevel.decrementLives(lives);
    }

    @Override
    public void sellUnit (Unit name) {
        // TODO Auto-generated method stub
        
    }

}