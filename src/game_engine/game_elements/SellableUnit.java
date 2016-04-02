package game_engine.game_elements;

public abstract class SellableUnit extends Unit{

	
	public SellableUnit(String ID) {
		super(ID);
	}
	
	public double getSellPrice(){
		return getProperties().getPrice().getValue();
	}
	
}