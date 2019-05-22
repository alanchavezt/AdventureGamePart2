import java.io.*;

public class Adventure {

    public static void main(String[] args) throws IOException 
    {  
    	String fileMap = "";
    	
		if (args.length < 1) 
		{
		    throw new IOException("No Parameters" );
		} 
		else 
		{
		    System.out.print("\n" + args.length + " Parameters: ");
		    int i = 0;
		    fileMap = args[i];
		    		
		    for (i = 0; i < args.length; i++)
		    {
		        System.out.print(args[i] + "\n");
		    }
		}
		
    	try 
    	{
			Map map = new Map(fileMap);
			map.printMap();
			
			GameChar gm = new GameChar(map.getMap(), map.getRows(), map.getColumns());
			gm.go();
			
		} 
    	catch (IOException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	
    }

}