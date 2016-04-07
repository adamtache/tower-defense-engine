package game_engine.affectors;

import java.util.List;

import game_engine.functions.Function;
import game_engine.properties.UnitProperties;

public abstract class Affector {

	private List<Double> baseNumbers;
	// this specifies how many ticks the affector applies its effect (it's "time to live")
	private int TTL;
	private int elapsedTime;
	private List<Function> myFunctions;

	/*
	 * Applies an effect to a unit by altering the 
	 * UnitProperties of a GameElement object. The effect is determined
	 * by the implementation of the method (this could involve)
	 * decrementing health, increasing/decreasing speed, etc.
	 * The overall effect is dependent on which properties are changed
	 *
	 * @param  properties  A UnitProperties object that represents the current state of the GameElement 
	 *
	 *
	 */

//	public Affector(List<Double> baseNumbers, int TTL) {
//		this.baseNumbers = baseNumbers;
//		this.TTL = TTL;
//		this.elapsedTime = 0;
//	}
	
	public Affector(List<Function> functions, int TTL){
		this.myFunctions = functions;
		this.TTL = TTL;
		this.elapsedTime = 0;
	}

	public abstract void apply(UnitProperties properties);
	
	public int getElapsedTime(){
		return elapsedTime;
	}
	
	public void updateElapsedTime(){
		elapsedTime++;
	}

	public List<Double> getBaseNumbers () {
		return baseNumbers;
	}

	public void decrementTTL() {
		this.TTL--;
	}

	public int getTTL () {
		return TTL;
	}
	
	public List<Function> getFunctions(){
		return myFunctions;
	}
	
}