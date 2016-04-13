package auth_environment.paths;

import java.util.List;

import game_engine.game_elements.Enemy;
import game_engine.game_elements.Terrain;
import game_engine.properties.Position;

public class PathsFactory {

	private PathGraphForest myForest;
	private int currentGraphID;
	private int currentPathID;

	public PathsFactory(){
		this.myForest = new PathGraphForest();
	}

	public PathGraph createGraph(){
		return new PathGraph(currentGraphID++);
	}

	/**
	 * @param newPath
	 * @param GraphID
	 * Inserts path with newPath positions into forest of path graphs
	 */
	public void insertPath(List<Position> newPath){
		PathGraph myGraph = myForest.getGraphByPos(newPath.get(0));
		if(myGraph != null){
			PathNode newPathNode = new PathNode(currentPathID++);
			newPathNode.addPositions(newPath);
			configure(newPathNode, myGraph);
		}
		else{
			if(newPath.size() > 0){
				PathGraph newGraph = createGraph();
				PathNode path = new PathNode(null, newPath, currentPathID++);
				newGraph.setRoot(path);
				myForest.addGraph(newGraph);
			}
		}
	}

	public void configure(PathNode newPathNode, PathGraph myGraph){
		List<Position> positions = newPathNode.getPositions();
		Position startingPos = positions.get(0);
		Position endingPos = positions.get(positions.size()-1);
		List<PathNode> currentStartingPaths = myGraph.getPathByEdgePosition(startingPos);
		List<PathNode> currentEndingPaths = myGraph.getPathByEdgePosition(endingPos);
		for(PathNode path : currentStartingPaths){
			List<PathNode> pathNeighbors = path.getNeighbors();
			for(PathNode p : pathNeighbors){
				p.addNeighbor(newPathNode);
			}
			newPathNode.addNeighbors(pathNeighbors);
		}
		for(PathNode path : currentEndingPaths){
			List<PathNode> pathChildren = path.getNeighbors();
			for(PathNode p : pathChildren){
				p.addNeighbor(newPathNode);
			}
			newPathNode.addNeighbors(pathChildren);
		}
		PathNode currentMidStartingPath = myGraph.getPathByMidPosition(startingPos);
		PathNode currentMidEndingPath = myGraph.getPathByMidPosition(endingPos);
		if(currentMidStartingPath != null){
			List<Position> cutoffPositions = currentMidStartingPath.cutoffByPosition(startingPos);
			Position lastCutoff = cutoffPositions.get(cutoffPositions.size()-1);
			List<PathNode> cutoffConnectedPaths = myGraph.getPathByEdgePosition(lastCutoff);
			PathNode newSplitPath = new PathNode(currentPathID++);
			newSplitPath.addPositions(cutoffPositions);
			newSplitPath.addNeighbor(currentMidStartingPath);
			for(PathNode path : cutoffConnectedPaths){
				newSplitPath.addNeighbors(currentMidStartingPath.removeNeighbors(path.getNeighbors()));
			}
			newPathNode.addNeighbor(currentMidStartingPath);
			newPathNode.addNeighbor(newSplitPath);
			newSplitPath.addNeighbor(newPathNode);
		}
		if(currentMidEndingPath != null){
			List<Position> cutoffPositions = currentMidEndingPath.cutoffByPosition(startingPos);
			Position lastCutoff = cutoffPositions.get(cutoffPositions.size()-1);
			List<PathNode> cutoffConnectedPaths = myGraph.getPathByEdgePosition(lastCutoff);
			PathNode newSplitPath = new PathNode(currentPathID++);
			newSplitPath.addPositions(cutoffPositions);
			newSplitPath.addNeighbor(currentMidEndingPath);
			for(PathNode path : cutoffConnectedPaths){
				newSplitPath.addNeighbors(currentMidEndingPath.removeNeighbors(path.getNeighbors()));
			}
			newPathNode.addNeighbor(currentMidEndingPath);
			newPathNode.addNeighbor(newSplitPath);
			newSplitPath.addNeighbor(newPathNode);
		}
	}

	// Adds enemy to Graph on correct path
	public void addEnemy(Enemy enemy, int pathID){
		myForest.getPathByID(pathID).addEnemy(enemy);
	}

	// Adds terrain to Graph on correct path
	public void addTerrain(Terrain terrain, int pathID){
		myForest.getPathByID(pathID).addTerrain(terrain);
	}

	public PathGraphForest getForest(){
		return myForest;
	}

}