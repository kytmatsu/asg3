/*
 * Kyle Matsumoto
 * kytmatsu
 * CMPS012B
 * 11/21/14
 * The auxlib for asg3 literraly just auxillary support.basically.pretty much.
 * */
import static java.lang.System.*; 
import static java.lang.Integer.*; 


 public final class auxlib{ 
	 
    public static final String PROGNAME =  basename (getProperty ("java.class.path")); 
    public static final int EXIT_SUCCESS = 0; 
    public static final int EXIT_FAILURE = 1; 
    public static int exitvalue = EXIT_SUCCESS; 
 
    private auxlib () { 
       throw new UnsupportedOperationException (); 
    } 
 
    public static String basename (String pathname) 
    { 
       if (pathname == null || pathname.length () == 0)
    	   return "."; 
       String[] paths = pathname.split ("/"); 
       for (int x = paths.length - 1; x >= 0; --x)
       { 
         if (paths[x].length () > 0) return paths[x]; 
       } 
       return "/";
    } 
 

    public static void whine (int exitval, Object... args) 
    { 
       exitvalue = exitval; 
       err.printf ("%s", PROGNAME); 
      for (Object argi : args) 
    	  err.printf (": %s", argi); 
       err.printf ("%n"); 
   } 
    
    public static void warn (Object... args)
    { 
       whine (EXIT_FAILURE, args); 
    } 
    
    public static void die (Object... args)
    { 
       warn (args); 
       exit (); 
    } 
 
   public static void usage_exit (String optsargs)
   { 
       exitvalue = EXIT_FAILURE; 
      err.printf ("Usage: %s %s%n", PROGNAME, optsargs); 
       exit (); 
    } 

    public static void exit ()
    { 
       System.exit (exitvalue); 
    } 
 
 
  
   public static String identity (Object object)
   { 
       return object == null ? "(null)" : object.getClass().getName() + "@" 
            + toHexString (identityHashCode (object)); 
   } 
 
 } 
