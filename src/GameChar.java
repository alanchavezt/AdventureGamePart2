import java.util.Scanner;

public class GameChar 
{
	private char[][] map;
	int[][] deltas = {{0,1}, {0,-1}, {-1,0}, {1,0}, {0,0}};
	private int rows;
	private int columns;
	private int rowPosition = 0;
	private int colPosition = 0;
	
	public GameChar(char[][] map, int r, int c)
	{
		this.map = map;
		this.rows = r;
		this.columns = c;
	}
	
	public void go()
	{
		Scanner scan = new Scanner(System.in);
		String str;
		String command;
    	String parameter;
    	int[][] currentDelta = new int[1][2];
		boolean flag = true;
		
		do{   
	    	System.out.print("> ");
	    	str = scan.nextLine();
	    	str = str.toLowerCase();
	        String[] arrOfStr = str.split(" +");
    	
	    	if(arrOfStr.length == 1) {
	        	command = arrOfStr[0];
	        	
	        	if(command.equals("inventory") || command.equals("i"))
	        	{
		        	inventory();
	        	}
	        	else if(command.equals("quit") || command.equals("q")) 
	        	{
	        		System.out.println("Farewell");
	        	    System.out.println("You are at location " + rowPosition + "," + colPosition);
		        	flag = false;
		    	}
	        	else 
	        	{
		        	System.out.println("Invalid command: " + command);
		        }
	        }
	    	else if(arrOfStr.length == 2) 
	        {
	    		command = arrOfStr[0];
		        parameter = arrOfStr[1];
		        
		        if(command.equals("go") || command.equals("g"))
		        {
			    	currentDelta = getDirection(parameter);
			    	
			    	if(currentDelta[0][0] == 0 && currentDelta[0][1] == 0) 
			    	{
			    		//System.out.println("Do nothing");
			    	}
			    	else 
			    	{
			    		rowPosition = rowPosition + currentDelta[0][0];
				    	colPosition = colPosition + currentDelta[0][1];
				    	
			        	if( (rowPosition >= 0 && rowPosition < rows) && (colPosition >=0 && colPosition < columns))
						{
							System.out.println("Moving " + parameter + "...");
							System.out.println("You are at location " + rowPosition + "," + colPosition + " in terrain " + map[rowPosition][colPosition]);
							showTerrain();
						}
			        	else 
			        	{
			        		rowPosition = rowPosition - currentDelta[0][0];
					    	colPosition = colPosition - currentDelta[0][1];
					    	
			    			System.out.println("You can't go that far " + parameter + ".");
			    		}
			    	}
		        }
		        else 
		        {
		        	System.out.println("Invalid command: " + command);
		        }
		       
	        }
	    	
		}while(flag);
		
		scan.close();
    		
	}
	
	public void showTerrain()
	{
		int tempRowPos = rowPosition - 1;
		int tempColPos = colPosition - 1;
		
		for( int r = tempRowPos; r < tempRowPos + 3; r++ )
        {
			
    		for( int c = tempColPos; c < tempColPos + 3; c++ )
            {
    			
    			if( (r < 0 || r >= rows) || (c < 0 || c >= columns))
    			{
    				System.out.print("X ");
    			}
    			else if( (r >= 0 && r < rows) && (c >=0 && c < columns))
    			{
    				System.out.print(map[r][c] + " ");
    			}
    			
            }
    		System.out.println("\n");
        }
		
	}
	
	public int[][] getDirection(String parameter)
	{
		int[][] currentDelta = new int[1][2];
		
		if(parameter.equals("east"))
    	{
			currentDelta[0][0] = deltas[0][0];
			currentDelta[0][1] = deltas[0][1];
			
    		return currentDelta;
    	}
    	else if(parameter.equals("west"))
    	{
    		currentDelta[0][0] = deltas[1][0];
			currentDelta[0][1] = deltas[1][1];
			
    		return currentDelta;
        }
    	else if(parameter.equals("north"))
        {
    		currentDelta[0][0] = deltas[2][0];
			currentDelta[0][1] = deltas[2][1];
			
    		return currentDelta;
        }
    	else if(parameter.equals("south"))
        {
    		currentDelta[0][0] = deltas[3][0];
			currentDelta[0][1] = deltas[3][1];
			
    		return currentDelta;
        }
    	else if(parameter.equals("inside"))
        {
    		currentDelta[0][0] = deltas[4][0];
			currentDelta[0][1] = deltas[4][1];
			
        	System.out.println("You can't go that way.");
        }
    	else 
        {
    		currentDelta[0][0] = deltas[4][0];
			currentDelta[0][1] = deltas[4][1];
			
        	System.out.println("Invalid parameter: " + parameter);
        }
		
		return currentDelta;
	}
	
	public void inventory()
	{
		System.out.println("You are carrying:");
    	System.out.println("brass lantern");
    	System.out.println("rope");
    	System.out.println("rations");
    	System.out.println("staff");
    	System.out.println("You are at location " + rowPosition + "," + colPosition);
	}
	
	

}
