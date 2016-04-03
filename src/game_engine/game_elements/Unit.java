package game_engine.game_elements;

import java.util.ArrayList;
import java.util.List;
import game_engine.affectors.Affector;
import game_engine.affectors.AffectorFactory;
import game_engine.properties.UnitProperties;

/**
 * This class is the superclass for game units and implements GameElement. 
 * It represents any physical game unit and holds its ID, UnitProperties, and list of current Affectors to be applied.
 * @author adamtache
 *
 */

public abstract class Unit extends GameElement{

	private UnitProperties myProperties;
	private List<Affector> myAffectors;
	private AffectorFactory affectorFactory;

	public Unit(String name){
		super(name);
		initialize();
		myProperties = new UnitProperties(getWorkspace());
	}

	public Unit(String ID, UnitProperties properties){
		super(ID);
		this.myProperties = properties;
		initialize();
	}

	private void initialize(){
		myAffectors = new ArrayList<>();
		this.affectorFactory = new AffectorFactory();
	}

	public void update(){
		myAffectors.forEach(a -> a.apply(myProperties));
		myAffectors.forEach(a -> a.decrementTTL());
		myAffectors.removeIf(a -> a.getTTL() != 0);
	}

	public void addAffector(Affector affector) {
		myAffectors.add(affector);
	}

	public UnitProperties getProperties(){
		return myProperties;
	}

	public void setProperties(UnitProperties properties) {
		this.myProperties = properties;
	}

	public List<Affector> getAffectors() {
		return myAffectors;
	}

	public void setAffectors(List<Affector> affectors) {
		this.myAffectors = affectors;
	}

	public AffectorFactory getAffectorFactory () {
		return affectorFactory;
	}
}