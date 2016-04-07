package game_engine.functions;

import java.util.ArrayList;
import java.util.List;

import game_engine.EngineWorkspace;

/**
 * This class represents a discrete function based on any number of independent variable as arguments.
 * A function is made up of Terms (see Term.java), which are themselves made up of constants and variables.
 * @author adamtache
 *
 */
public class Function {
	
	private List<Term> myTerms;
	private EngineWorkspace myWorkspace;
	private String myName;
	
	public Function(String type, String strength, EngineWorkspace workspace){
		myTerms = new ArrayList<>();
		this.myWorkspace = workspace;
		myTerms = myWorkspace.getFunctionLibrary().getTerms(type, strength);
	}
	
	public Function(String name, List<Term> terms){
		this.myName = name;
		this.myTerms = terms;
	}
	
	public Function(String equation, EngineWorkspace workspace){
		myTerms = new ArrayList<>();
		this.myWorkspace = workspace;
	}
	
	public Function(List<Term> terms){
		this.myTerms = terms;
	}
	
	public double evaluate(int index){
		double evaluation = 0;
		for(Term term : myTerms){
			evaluation += term.evaluate(index);
		}
		return evaluation;
	}
	
	public String getName(){
		return myName;
	}
	
	public List<Term> getTerms(){
		return myTerms;
	}
	
	public String toString(){
		String str = "";
		for(Term term : myTerms){
			List<Variable> variables = term.getVariables();
			for(Variable var : variables){
				str += var+"*";
			}
			List<Constant> constants = term.getConstants();
			for(Constant c : constants){
				str += c+"*";
			}
		}
		return str;
	}
	
}