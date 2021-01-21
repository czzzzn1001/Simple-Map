import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Explanation: Implementation of FindMinPath
 * Known Bugs:None
 * Zheng Chu
 * zhengchu@brandeis.edu
 * Dec 8, 2020
 * COSI 21A PA3
 */
public class FindMinPath {
	public static void main(String [] args) throws FileNotFoundException{
		MinQueue Q=new MinQueue(100);
		GraphWrapper gw=new GraphWrapper(true);
		GraphNode home=gw.getHome();
		home.priority=0;
		boolean found=false;
		Q.insert(home);
		GraphNode goal=null;
		while(!Q.isEmpty()&&!found) {
			GraphNode curr=Q.pullHighestPriorityElement();
			if(curr.isGoalNode()) {
				goal=curr;
				found=true;
			}
			else {
				//check east
				if(curr.hasEast()) {
					GraphNode east=curr.getEast();
					int x=curr.priority+curr.getEastWeight();
					if(!Q.map.hasKey(east)) {
						east.priority=x;
						east.previousNode=curr;
						east.previousDirection="East";
						Q.insert(east);
					}
					else {
						if(x<east.priority) {
							east.priority=x;
							Q.rebalance(east);
							east.previousNode=curr;
							east.previousDirection="East";
						}
					}
				}
				//check south
				if(curr.hasSouth()) {
					GraphNode south=curr.getSouth();
					int x=curr.priority+curr.getSouthWeight();
					if(!Q.map.hasKey(south)) {
						Q.insert(south);
						south.priority=x;
						south.previousNode=curr;
						south.previousDirection="South";
					}
					else {
						if(x<south.priority) {
							south.priority=x;
							Q.rebalance(south);
							south.previousNode=curr;
							south.previousDirection="South";
						}
					}
				}
				//check west
				if(curr.hasWest()) {
					GraphNode west=curr.getWest();
					int x=curr.priority+curr.getWestWeight();
					if(!Q.map.hasKey(west)) {
						Q.insert(west);
						west.priority=x;
						west.previousNode=curr;
						west.previousDirection="West";
					}
					else {
						if(x<west.priority) {
							west.priority=x;
							Q.rebalance(west);
							west.previousNode=curr;
							west.previousDirection="West";
						}
					}
				}
				//check north
				if(curr.hasNorth()) {
					GraphNode north=curr.getNorth();
					int x=curr.priority+curr.getNorthWeight();
					if(!Q.map.hasKey(north)) {
						Q.insert(north);
						north.priority=x;
						north.previousNode=curr;
						north.previousDirection="North";
					}
					else {
						if(x<north.priority) {
							north.priority=x;
							Q.rebalance(north);
							north.previousNode=curr;
							north.previousDirection="North";
						}
					}
				}
			}
		}
		String path="";
		while(goal!=null&&goal.previousNode!=null) {
			path+=goal.previousDirection;
			if(goal.previousNode.previousNode!=null) {
				path+=",";
			}
			goal=goal.previousNode;
		}
		String[] arr=path.split(",");
		PrintStream out=new PrintStream(new File("answer.txt"));
		for(int i=arr.length-1;i>=0;i--) {
			out.println(arr[i]);
		}
	}
}
