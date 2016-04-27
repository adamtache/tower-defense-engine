 package auth_environment.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import auth_environment.IAuthEnvironment;
import auth_environment.Models.Interfaces.ILevelOverviewTabModel;
import game_engine.game_elements.Level;
import game_engine.game_elements.Wave;

public class LevelOverviewTabModel implements ILevelOverviewTabModel{
	
	private IAuthEnvironment myAuthEnvironment; 
	
    private Map<String, Wave> myCreatedWaves;
    private List<Level> myCreatedLevels;
    private int myCurrentLevelIndex;
    
    public LevelOverviewTabModel(IAuthEnvironment auth){
    	this.myAuthEnvironment = auth; 
    	this.myCreatedLevels = new ArrayList<Level>(); 
        this.myCreatedWaves = new HashMap<String, Wave>(); // ex. Level1Wave1
        this.myCurrentLevelIndex = 0; 
    }
    
    public void refresh(IAuthEnvironment auth) {
    	this.clear();
        this.myCreatedLevels = auth.getLevels();
        this.myCreatedLevels.stream().forEach(level -> this.addWavesToLevel(level));

    }
    
    private void clear() {
    	this.myCreatedLevels.clear();
    	this.myCreatedWaves.clear();
        this.myCurrentLevelIndex = 0; 
    }
    
    private void addWavesToLevel(Level level) {
    	String levelNum = Integer.toString(this.myCreatedLevels.indexOf(level)); 
    	for (int i=0; i<level.getWaves().size(); i++) {
    		String label =  levelNum + " " + Integer.toString(i); 
    		this.addToCreatedWaves(label, level.getWaves().get(i));
    	}
    }
    
    private boolean checkBounds(int levelNum){
        return (levelNum >= 0) && (levelNum < this.myCreatedLevels.size());
    }
    
    @Override
    public void changeEditedLevel(int editLevel){
        if(!this.checkBounds(editLevel-1)){
            // TODO: throw an exception for the front-end
        }
        this.myCurrentLevelIndex = editLevel-1;
    }
    
    @Override
    public void addLevel(String name, int numLives){
        this.myCreatedLevels.add(new Level(name, numLives));
    }
    
    @Override
    public void addLevels(String name, int numLives, int numLevelsToAdd){
        for(int i = 1; i <= numLevelsToAdd; i++){
            this.addLevel(name + i, numLives);
        }
    }
    
    @Override
    public void addWaveToCurrentLevel(int waveIndex){
        Wave w = this.myCreatedWaves.get(waveIndex);
        this.myCreatedLevels.get(this.myCurrentLevelIndex).addWave(w);
    }
    
    @Override
    public List<String> getLevelNames(){
        List<String> levelNames = new ArrayList<String>();
        this.myCreatedLevels.forEach(l -> levelNames.add(l.getName()));
        return levelNames;
    }
    
    @Override
    public List<String> getCurrentLevelWaveNames(){
        List<String> names = new ArrayList<String>();
        this.myCreatedLevels.get(this.myCurrentLevelIndex).getWaves().forEach(w -> names.add(w.getName()));
        return names;
    }
    
    @Override
    public List<Level> getCreatedLevels(){
        return new ArrayList<Level>(this.myCreatedLevels);
    }
    
    // example of levelPlusWaveName: Level 1 Wave 1 
    public void addToCreatedWaves(String levelPlusWaveName, Wave wave) {
        this.myCreatedWaves.put(levelPlusWaveName, wave);
//        int levelNum = Integer.parseInt(levelPlusWaveName.split(" ")[0]); 
//        this.getCreatedLevels().get(levelNum).addWave(wave);
    }
}