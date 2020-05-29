import java.util.LinkedList;

public class MazeSolver {

	static int[][] maze = {
			{2, 0, 1, 1},
			{1, 1, 0, 1},
			{0, 1, 1, 1}
	};
	//0 = wall
	//1 = path
	//2 = destination
	
	static LinkedList<Position> path = new LinkedList<Position>();
	
	public static void main(String[] args) {
		
		Position p = new Position(0, 3);		
		path.push(p);
		
		maze[path.peek().x][path.peek().y] = 0;
		
		

	}

}
