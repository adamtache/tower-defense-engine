package auth_environment.paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game_engine.game_elements.Branch;
import game_engine.libraries.PathLibrary;
import game_engine.properties.Position;

public class PathGraphFactory {

	private PathLibrary myPathLibrary;
	private PositionHandler myPositionHandler;
	private int currentPathID;
	private int currentBranchID;

	public PathGraphFactory(){
		myPathLibrary = new PathLibrary();
		myPositionHandler = new PositionHandler();
		currentPathID = -1;
		currentBranchID = -1;
	}

	public PathGraphFactory(PathLibrary pathLibrary){
		this.myPathLibrary = pathLibrary;
		currentPathID = myPathLibrary.getLastPathID();
		currentBranchID = myPathLibrary.getLastBranchID();
		myPositionHandler = new PositionHandler();
	}
	
	/**
	 * @param newPath
	 * @param GraphID
	 * Inserts branch with newPath positions into path graph
	 */
	public void insertBranch(List<Position> branchPos){
		if(branchPos.size() == 0)
			return;
		Branch newBranch = new Branch(getNextBranchID(), branchPos);
		PathNode currentPath = myPathLibrary.getPathGraph().getPathByPos(branchPos.get(0));
		if(currentPath != null){
			configureBranchInPath(newBranch, currentPath);
		}
		currentPath = myPathLibrary.getPathGraph().getPathByPos(branchPos.get(branchPos.size()-1));
		if(currentPath != null){
			configureBranchInPath(newBranch, currentPath);
		}
		if(myPathLibrary.getPathGraph().getBranchByID(newBranch.getID()) == null){
			if(branchPos.size() > 0){
				createNewPath(newBranch);
			}
		}
	}

	public void createUnlimitedPathGraph(double width, double length, double sideLength){
		double centerX = sideLength/2;
		double centerY = sideLength/2;
		PathNode pathGrid = new PathNode(getNextPathID());
		Branch[][] myBranchGrid = new Branch[(int)Math.floor(width/sideLength)][(int)Math.floor(length/sideLength)];
		for(int x=0; x<myBranchGrid.length; x++){
			for(int y=0; y<myBranchGrid[x].length; y++){
				List<Position> pos = Arrays.asList(new Position(centerX, centerY));
				Branch branch = new Branch(getNextBranchID(), pos);
				pathGrid.addBranch(branch);
				myBranchGrid[x][y] = branch;
				centerX += sideLength;
				if(centerX > width-sideLength/2){
					centerX = sideLength/2;
					centerY += sideLength;
				}
			}
		}
		configureGridNeighbors(myBranchGrid);
		myPathLibrary.getPathGraph().addPath(pathGrid);
	}

	private void configureGridNeighbors(Branch[][] grid){
		for(int r=0; r<grid.length; r++){
			for(int c=0; c<grid[r].length; c++){
				Branch b = grid[r][c];
				for(int x=-1; x<2; x++){
					for(int y=-1; y<2; y++){
						int neighborX = r+x;
						int neighborY = c+y;
						if(neighborX >= 0 && neighborX < grid.length){
							if(neighborY >= 0 && neighborY < grid[r].length){
								if(!(x==0 && y==0)){
									Branch neighbor = grid[neighborX][neighborY];
									if(!b.getNeighbors().contains(neighbor)){
										b.addNeighbor(neighbor);
										if(!neighbor.getNeighbors().contains(b)){
											List<Position> bothCellPositions = Arrays.asList(b.getFirstPosition(), neighbor.getFirstPosition());
											List<Position> interpolated = myPositionHandler.getInterpolatedPositions(bothCellPositions, false);
											b.setPositions(interpolated);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private PathNode createNewPath(Branch branch){
		myPathLibrary.getPathGraph().addPath(new PathNode(getNextPathID(), branch));
		return myPathLibrary.getPathGraph().getLastPath();
	}

	private int getNextPathID(){
		currentPathID++;
		return currentPathID;
	}

	private int getNextBranchID(){
		currentBranchID++;
		return currentBranchID;
	}

	private void configureBranchInPath(Branch newPathNode, PathNode myPath){
		Position startPos = newPathNode.getFirstPosition();
		Position endPos = newPathNode.getLastPosition();
		configureBranchesWithEdgePos(newPathNode, myPath, startPos);
		if(!startPos.equals(endPos)){
			configureBranchesWithEdgePos(newPathNode, myPath, endPos);
		}
		configureMidBranchSplits(newPathNode, myPath, startPos);
		if(!startPos.equals(endPos)){
			configureMidBranchSplits(newPathNode, myPath, endPos);
		}
	}

	private void configureBranchesWithEdgePos(Branch newBranch, PathNode myPath, Position pos){
		List<Branch> branchesAtPos = myPath.getBranchesByEdgePosition(pos);
		List<Branch> branchesChecked = new ArrayList<>();
		for(Branch branch : branchesAtPos){
			if(!branchesChecked.contains(branch)){
				branchesChecked.add(branch);
				if(!branch.equals(newBranch)){
					newBranch.addNeighbor(branch);
					branch.addNeighbor(newBranch);
				}
			}
		}
		if(!myPath.getBranches().contains(newBranch))
			myPath.addBranch(newBranch);
	}

	private void configureMidBranchSplits(Branch newBranch, PathNode myPath, Position pos){
		List<Branch> branchesToSplit = myPath.getBranchesByMidPosition(pos);
		branchesToSplit.stream().filter(b -> b.equals(newBranch));
		for(Branch b : branchesToSplit){
			List<Position> cutoffPositions = b.cutoffByPosition(pos);
			Position lastCutoff = cutoffPositions.get(cutoffPositions.size()-1);
			Branch newSplitBranch = new Branch(getNextBranchID());
			newSplitBranch.addPositions(cutoffPositions);
			newSplitBranch.addNeighbor(b);
			newSplitBranch.addNeighbor(newBranch);
			b.addNeighbor(newSplitBranch);
			b.addNeighbor(newBranch);
			List<Branch> cutoffConnectedBranches = myPath.getBranchesByEdgePosition(lastCutoff);
			for(Branch br : cutoffConnectedBranches){
				newSplitBranch.addNeighbors(b.removeNeighbors(br.getNeighbors()));
			}
			newBranch.addNeighbor(b);
			newBranch.addNeighbor(newSplitBranch);
			myPath.addBranch(newSplitBranch);
		}
	}

	public PathLibrary getPathLibrary() {
		return myPathLibrary;
	}

}