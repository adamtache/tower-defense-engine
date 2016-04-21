package auth_environment.Models;

import java.util.ArrayList;
import java.util.List;

import auth_environment.IAuthEnvironment;

/**
 * Created by BrianLin on 4/20/16
 * Team member responsible: Brian
 *
 * This Tab is for placing Paths on the Map 
 */

import auth_environment.Models.Interfaces.IPathTabModel;
import auth_environment.paths.PathHandler;
import game_engine.game_elements.Branch;
import game_engine.properties.Position;

public class PathTabModel implements IPathTabModel {
	
	private IAuthEnvironment myAuthData;  
	private PathHandler myPathHandler = new PathHandler(); 
	
	private List<Position> myCurrentPositions = new ArrayList<Position>(); 
	private double myPathWidth; 
	
	public PathTabModel(IAuthEnvironment auth) {
		this.myAuthData = auth; 
	}

	// TODO: should all Paths have the same width? Where to set this? 
	@Override
	public void setPathWidth(double width) {
		this.myPathWidth = width; 
	}

	@Override
	public double getPathWidth() {
		return this.myPathWidth;
	}

	@Override
	public void addPosition(double x, double y) {
		this.myCurrentPositions.add(new Position(x, y)); 
	}
	
	@Override
	public void submitBranch() {
		this.myPathHandler.processStraightLine(this.myCurrentPositions);
		this.myCurrentPositions.clear();
	}
	
	@Override
	public void printCurrentPositions() {
		this.myCurrentPositions.stream().forEach(s -> System.out.println(s.getX() + " " + s.getY()));
	}

	@Override
	public void load() {
		List<Branch> branches = this.myPathHandler.getPGF().getBranches();
		branches.stream().forEach(b -> this.loadBranch(b));
	}

	@Override
	public void loadBranch(Branch branch) {
		Position firstPos = branch.getFirstPosition();
		Position lastPos = branch.getLastPosition();
		double firstX = firstPos.getX();
		double firstY = firstPos.getY();
		double lastX = lastPos.getX();
		double lastY = lastPos.getY();
	}
	
	
}
