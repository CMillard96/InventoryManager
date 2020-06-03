import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class MazeSolver {
	
	
		
	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<Maze> mazes = readMazes();
		
		int i = 0;
		while(i<mazes.size()) {
			if(solveMaze(mazes.get(i))) {
				System.out.println("You won!");
			} else{ 
				System.out.println("No path");
			}
			i++;
		}
		

	}
	
	private static ArrayList<Maze> readMazes() throws FileNotFoundException {
		ArrayList<Maze> mazes = new ArrayList<Maze>();
		
		Scanner in = new Scanner(new File("mazes.txt"));
		
		while(in.hasNext()) {
			
			Maze m = new Maze();
			
			//fill list from file
			
			int rows = Integer.parseInt(in.nextLine());
			m.maze = new int[rows][];
					
			for(int i = 0; i < rows; i++) {
				String line = in.nextLine();
				m.maze[i] = Arrays.stream(line.split(", ")).mapToInt(Integer::parseInt).toArray();
			}
			
			m.start = new Position(Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine()));
			in.nextLine();	// remove the extra space
			mazes.add(m);
		}
		in.close();
		
		return mazes;
	}

	private static boolean solveMaze(Maze m) {
		
		Position p = m.start;
		m.path.push(p);		
		
		while(true) {
			
			int x = m.path.peek().x;
			int y = m.path.peek().y;			
			m.maze[x][y] = 0;
			
			//down
			
			
			if(isValid(x+1, y, m)) {
				
				if(m.maze[x+1][y] == 2) {
					System.out.println("Moved down");
					System.out.println("Winning path: " + m.path);
					return true;
				} else if(m.maze[x+1][y] == 1) {
					System.out.println("Moved down");
					m.path.push(new Position(x+1, y));
					continue;
				}
			}
			
			//left
			if(isValid(x, y-1, m)) {
				
				if(m.maze[x][y-1] == 2) {
					System.out.println("Moved left");
					System.out.println("Winning path: " + m.path);
					return true;
				} else if(m.maze[x][y-1] == 1) {
					System.out.println("Moved left");
					m.path.push(new Position(x, y-1));
					continue;
				}
			}
			
			//up
			if(isValid(x-1, y, m)) {
				
				if(m.maze[x-1][y] == 2) {
					System.out.println("Moved up");
					System.out.println("Winning path: " + m.path);
					return true;
				} else if(m.maze[x-1][y] == 1) {
					System.out.println("Moved up");
					m.path.push(new Position(x-1, y));
					continue;
				}
			}
			
			//right
			if(isValid(x, y+1, m)) {
				
				if(m.maze[x][y+1] == 2) {
					System.out.println("Moved right");
					System.out.println("Winning path: " + m.path);
					return true;
				} else if(m.maze[x][y+1] == 1) {
					System.out.println("Moved right");
					m.path.push(new Position(x, y+1));
					continue;
				}
			}
			
			
			m.path.pop();
			System.out.println("Moved back");
			if(m.path.size() <= 0) {
				
				return false;
			}
		}
		
	}

	public static boolean isValid(int x, int y, Maze m) {
		
		
		if(x < 0 || x >= m.maze.length || y < 0 || y >= m.maze[x].length) {
			return false;
		} else {
			return true;
		}
	}

}
