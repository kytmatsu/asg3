/*
 * Kyle Matsumoto
 * kytmatsu
 * CMPS012B
 * 11/21/14
 * This is the xref class. it holds almighty power in this program Note please read the readme if you are curious as to why my implementation is a bit off.
 * */
import java.io.*; 
import java.util.Scanner; 
import static java.lang.System.*; 


class xref { 

	static final String STDIN_NAME = "-"; 
	static boolean debug = false; 

	private static void printColons() 
	{  	
	for(int x = 0 ; x < 25; ++x) 
		out.printf(":"); 
	out.printf("%n"); 
	} 

	private static void printFilename(String filename) 
	{ 
		out.printf("%n"); 
		printColons(); 
		out.printf("%s%n", filename); 
		printColons(); 
		out.printf("%n"); 
	} 

	static class printer implements X <String, Queue <Integer>> 
	{ 
		public void visit (String key, Queue <Integer> value)
		{ 
			out.printf ("%s " + " : ", key); 
			for (int x: value) 
				out.printf (" %d", x); 
			out.printf ("%n"); 
		} 
	} 

	static boolean isCommand (String arg)
	{ 
		if (arg.charAt(0) == '-' && arg.length() > 1) 
			return true; 
		else  
			return false;    
	} 

	static void getopt (String args_zero)
	{  
		int length = args_zero.length();  
		for (int x = 1; x < length; ++x)
		{ 
			switch (args_zero.charAt(x))
			{ 
			case 'd':  
				debug = true;  
				break; 
			default:  
				auxlib.warn("invalid command",args_zero.charAt(x)); 
				break; 
			} 
		} 
	} 
	static void processFile (String filename, Scanner scan)
	{    
		printFilename(filename); 
		Tree <String, Queue <Integer>> t = new Tree <String, Queue <Integer>> (); 

		for (int x = 1; scan.hasNextLine (); ++x)
		{ 
			for (String word: scan.nextLine().split ("\\W+"))
			{ 
				if (word.matches ("^\\d*$")) 
					continue; 
				Queue<Integer> tempQ; 
				tempQ = t.get(word); 

				if (tempQ == null)
				{ 
					Queue<Integer> newQ = new Queue<Integer>(); 
					newQ.insert(x); 
					t.put(word, newQ); 
				}
				else
				{  
					tempQ.insert(x); 
				} 
			} 
		} 

		if (debug== true) 
		{ 
			t.debugDump(); 
		}
		else
		{ 
			X <String, Queue <Integer>> print = new printer (); 
			t.doVisit (print); 
		} 
	} 


	public static void main (String[] args)
	{  
		if ((args.length != 0) && isCommand(args[0]))  
			getopt(args[0]); 

		if (args.length == 0) 
		{ 
			processFile (STDIN_NAME, new Scanner (in)); 
		}
		else if(args.length == 1 && isCommand(args[0]))
		{ 
			processFile (STDIN_NAME, new Scanner (in)); 
		}
		else 
		{ 
			for (int argi = 0; argi < args.length; ++argi)
			{ 
				String filename = args[argi]; 
				if (filename.equals (STDIN_NAME))
				{ 
					processFile (STDIN_NAME, new Scanner (in)); 
				}
				else
				{ 
					try 
					{ 
						if(isCommand(args[argi])) continue; 
						Scanner scan = new Scanner (new File (filename)); 
						processFile (filename, scan); 
						scan.close (); 
					}
					catch (IOException error) 
					{ 
						auxlib.warn (error.getMessage ()); 
					} 
				} 
			} 
		} 
		auxlib.exit (); 
	} 
} 
