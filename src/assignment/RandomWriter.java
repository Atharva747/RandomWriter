package assignment;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.lang.String;

/*
 * CS 314H Assignment 2 - Random Writing
 *
 * Your task is to implement this RandomWriter class
 */
public class RandomWriter implements TextProcessor {
	
	String a=""; // a is the input string
	String b=""; //b is the string in which the output will be stored.
	int level=0;

    public static void main (String[] args) {
    	if(Integer.parseInt(args[2])<0||Integer.parseInt(args[3])<0)
    	{
    		System.err.println("Error: Invalid numerical input");
    		System.exit(0);
    	}
    	
    	RandomWriter r = new RandomWriter(Integer.parseInt(args[2]));
    	r.level = Integer.parseInt(args[2]);
    	r.readText(args[0]);
    	r.writeText(args[1], Integer.parseInt(args[3]));
    }

    public static TextProcessor createProcessor(int level) {
      return new RandomWriter(level);
    }

    private RandomWriter(int level) {
    	
    	//Constructor for class RandomWriter
    }


    //The following method reads the input file and stores the contents in string a.
    public void readText(String inputFilename) {
    	Scanner filein = null;
    	try{
    		
        	filein = new Scanner(new FileReader(inputFilename));
    	while( filein.hasNextLine())
    	{
    		String oneline = filein.nextLine();
    		a += oneline;
    	}
    	}
    	catch(IOException e)
    	{
    		System.err.println("Warning: IOException");
    	}
    	finally
    	{
    		filein.close();
    	}
    }
    
    //This method does all the string processing and writes the output to a file.
    public void writeText(String outputFilename, int length) {

    	BufferedWriter bw = null;
    	FileWriter fw = null;
    	
    	if(a.length() < level)
    	{
    		System.err.println("Invalid k value.");
    		System.exit(0);
    	}
    	
    	try{
    	ArrayList <String> c= new ArrayList<String>();
    	int n= a.length();
    	Random rand = new Random();
    	/*Although the hash map implementation requires more memory,
    	it is much faster than the array list implementation.*/
    	HashMap<String, ArrayList<String> > hmap = new HashMap<String, ArrayList<String> >();
    	for (int i = 0; i <= n-level-1; i++)
    	{
    		String sub = a.substring(i, i+level);
    		c.add(sub);
    		if(hmap.containsKey(sub)==false)
    		{
    			//If substring is not present, create an hmap entry for it.
    			ArrayList<String> v = new ArrayList<String>();
    			v.add(a.substring(i+level, i+level+1));
    			hmap.put(sub, v);
    		}
    		
    		else
    		{
    			/*If the substring is present in the hmap,
    			add the following character to its arrayList.*/
    			ArrayList<String> al = hmap.get(sub);
    			al.add(a.substring(i+level,i+level+1));
    		}
    	}
    	
    	//special edge case for k = 0.
    	if(level==0)
    	{
    		for(int i = 0; i<length; i++)
    		{
    			/*Generates a random value 'randvalue' and adds the character 
    			at position randvalue to string b. Then it removes that character from
    			the string a.
    			*/
    			String temp = a;
    			int randvalue = rand.nextInt(a.length());
    			b+= a.charAt(randvalue);
    			a = a.substring(0, randvalue) + a.substring(randvalue+1);
    			if(a == ""){a=temp;}
    		}
    	}
    	
    	//all other non-edge cases
    	if(level>=1)
    	{
    		//Initial random seed
    		int randvalue = rand.nextInt(c.size());
    		b+=c.get(randvalue);
    		c.remove(randvalue);
    		
    		
    		for(int currentIndex = level; currentIndex<length; currentIndex++)
    		{
    			String currentSeed = b.substring(currentIndex-level, currentIndex);
    			
    			if(hmap.containsKey(currentSeed)==true)
    			{	//if there is a key corresponding to the seed, get a random character
    				//from its array list
    				int randomint2 = rand.nextInt(hmap.get(currentSeed).size());
    				b+=hmap.get(currentSeed).get(randomint2);
    			}
    			
    			else
    			{
    				//If there is no such substring in the hashmap, 
    				//generate a new random seed and start from there.
    				b+=c.get(rand.nextInt(c.size()));
    				currentIndex+=(level-1);
    			}
    		 }
    	 }
    	//writes the string b to the file outputFilename
    	fw = new FileWriter(outputFilename);
    	bw = new BufferedWriter(fw);
    	bw.write(b);
    	
    	
    	}
    	catch(IOException e)
    	{
    		System.err.println("Warning: IOException");
    	}
    	
    	finally
    	{
    		try{
    		if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();}
    		catch(IOException e)
    		{
    			System.err.println("Warning: IOException");
    		}

		}
    }
}
