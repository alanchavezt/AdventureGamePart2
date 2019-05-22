import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Map 
{
	private Scanner fileIn = null;
	private String fileMapName;
	List<String> fileLines;
	private char[][] map;
	private int rows;
	private int columns;
	
	public Map(String fileMapName) throws IOException
	{
		this.fileMapName = fileMapName;
		openFile();
		readMapSize();
		readMap();
		setMapDimensions();
    	createMap();
	}
	
	private void openFile()
    {
    	System.out.println("File Map: " + fileMapName);
    	try {
    		fileIn = new Scanner(new File(fileMapName));
		} catch (FileNotFoundException x) {
			System.out.println("File open failed.");
			x.printStackTrace();
			System.exit(0);   // TERMINATE THE PROGRAM
		}
    }
	
	public void readMapSize() throws IOException
	{
		String line;
		
		if( fileIn.hasNextLine() == false )
            throw new IOException("No lines in map file" );

        line = fileIn.nextLine();
        String[] mapSize = line.split(" +");

    	for (int i = 0; i < mapSize.length; i++) {
    		if(i == 0 && i < 2) 
    		{
    			setNumberRows(Integer.parseInt(mapSize[i]));
    		}
    		else if(i == 1 && i < 2)
    		{
    			setNumberColumns(Integer.parseInt(mapSize[i]));
    		}
    	}
	}
	
	public void readMap()
	{
		String line;
        fileLines = new ArrayList<String>();

    	while(fileIn.hasNextLine())
        {
        	line = fileIn.nextLine();
        	
            if( line.length( ) == columns && fileLines.size() < rows) 
            {
            	fileLines.add(line);
            }
            else if(line.length() != columns)
            {
            	System.err.println( "Map is not rectangular; skipping row" );
            }
        }
	}
	
	public void createMap()
	{
		Iterator<String> itr = fileLines.iterator();
        
        for( int r = 0; r < rows; r++ )
        {
            String theLine = (String) itr.next();
            map[r] = theLine.toCharArray();
        }
	}
	
	public void setNumberRows(int r)
	{
	       this.rows = r;
	       System.out.println("# ROW " + rows);
	}
	
	public void setNumberColumns(int c)
	{
	       this.columns = c;
	       System.out.println("# COLUMN " + columns);
	}
	
	public void setMapDimensions()
	{
		map = new char[rows][columns];
	    System.out.println("....Setting Map Dimensions....\n");
	}
	
	public int getRows()
	{
		return rows;
	}
	
	public int getColumns()
	{
		return columns;
	}
	
	public char[][] getMap()
	{
		return map;
	}
	
	public void printMap()
    {
    	System.out.println("**********Map**********\n");
    	
    	for( int r = 0; r < rows; r++ )
        {
    		for( int c = 0; c < columns; c++ )
            {
    			System.out.print(map[r][c] + " ");
            }
    		System.out.println("\n");
        }
    }
	
	
	
}
