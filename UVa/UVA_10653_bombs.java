import java.util.LinkedList;
import java.util.Scanner;

public class UVa_10653_bombs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int gridLength, gridWidth, rows, row, num, column, startX, startY, endX, endY, pop1, pop2;
		int[][] next = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		
		Scanner s = new Scanner(System.in);

		while(true){
			gridLength = s.nextInt();
			gridWidth = s.nextInt();
	
			
			
			if(gridLength == 0 && gridWidth == 0) break;
			else{
				int[][] grid = new int[gridLength][gridWidth];
				int[][] dist = new int[gridLength][gridWidth];
	
				for (int i = 0; i < gridLength; i++) {
					for (int j = 0; j < gridWidth; j++) {
						grid[i][j] = 1; // I flagged possible traversable grid
										// location with 1
						dist[i][j] = -1;
					}
				}
	
				rows = s.nextInt();
	
				while (rows > 0) {
					row = s.nextInt();
					num = s.nextInt();
					for (int i = 0; i < num; i++) {
						column = s.nextInt();
						grid[row][column] = 0; // I flagged possible bomb infested
												// grid location with 0
					}
					rows--;
				}
	//To print the array			
	//			for(int i = 0; i < grid.length; i++){
	//				for(int j = 0; j<grid[0].length; j++){
	//					System.out.print(grid[i][j]+" ");
	//				}
	//				System.out.println();
	//			}
	
				startX = s.nextInt();
				startY = s.nextInt();
				endX = s.nextInt();
				endY = s.nextInt();
				
				if(startX==endX && startY==endY){
					System.out.println("0");
				}
				
				else{
					LinkedList<Integer> q = new LinkedList<Integer>();
					q.add(startX);
					q.add(startY);
					dist[startX][startY] = 0;
		
					while (!q.isEmpty()) {
						if(dist[endX][endY] != -1) break;
						pop1 = q.poll();
						pop2 = q.poll();
						int t1, t2;
		
						for (int i = 0; i < next.length; i++) {
							t1 = pop1 + next[i][0];
							t2 = pop2 + next[i][1];
		
							if (inRange(t1, gridLength) && inRange(t2, gridWidth)
									&& dist[t1][t2] == -1 && grid[t1][t2] != 0) {
								dist[t1][t2] = dist[pop1][pop2] + 1;
								q.add(t1);
								q.add(t2);
							}
							
							if (t1==endX && t2==endY) break;
						}
		
		
					}
	
				System.out.println(dist[endX][endY]);
				
				}
	
			}
		}
	

		s.close();

	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < y;
	}

}
