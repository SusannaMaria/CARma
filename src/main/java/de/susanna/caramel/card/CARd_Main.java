package de.susanna.caramel.card;


import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

public class CARd_Main {
	public static Logger logger;
	
	CARd_Loader cl;
	public CARd_Loader getCl() {
		return cl;
	}



	public void setCl(CARd_Loader cl) {
		this.cl = cl;
	}

	String rym_user;
	int waittime;
	String location;
	enum Action {
	    TEST, COMPLETE, UPDATE, DUMPPIC, MOSAIC
	}
	
	
	public void fullImport(String rymuser, String localdir) {
		CARd_Loader cl = new CARd_Loader();

		
		cl.initLib(rymuser, "28.11.2015",localdir);

		try {

			for (int i = 1; i <= 1000; i++) {

				if (cl.LoadFromURL("https://rateyourmusic.com/collection/"+rymuser+"/recent/"
						+ i,0) == false) {
					break;
				}
				
				System.out.print("... waiting 10s");
				Thread.sleep(10000);
				System.out.println("... done");
			}

			//cl.dump();
			cl.writeXML();
		} catch (InterruptedException e) {
			logger.error("IOEXception occured:", e);
		}
	}
	
	
	
	public void readrym(String rymuser, String localdir) {
		cl = new CARd_Loader();

		cl.readXML(localdir);
		//cl.getImgs();
		//cl.dump();
		//CARd_MPD car_mpd = new CARd_MPD();
	}
	
	public void loadrec(String rymuser, String localdir) {
		CARd_Loader cl = new CARd_Loader();

		cl.readXML(localdir);
		cl.loadRecords();
		
	}	
	
	public void loadimg(String localdir) {
		CARd_Loader cl = new CARd_Loader();

		cl.readXML(localdir);
		cl.getImgs();
		
	}	
	
	public void readXML() {

	}

	public static void main(String[] args) {

		CARd_Main cm = new CARd_Main();

		cm.parseCLI(args);

		 
		//
		// CARd_Loader cl = new CARd_Loader();
		// cl.readXML();
		//
		// cl.getImgs();

	}

	public void parseCLI(String[] args) {

		 Map<String,String> mp=new HashMap<String, String>();
		 
		
		// create the Options
		Options options = new Options();

		Option help = new Option("help", "print this message");
		Option fullscan = new Option("fullscan", "perform full scan of lib");
		Option loadrec = new Option("loadrec", "load record pages");
		Option readrym = new Option("readrym", "just read rym.xml");
		Option loadimg = new Option("loadimg", "load image");
		Option projecthelp = new Option("projecthelp",
				"print project help information");
		
		Option version = new Option("version",
				"print the version information and exit");
		
		Option quiet = new Option("quiet", "be extra quiet");
		Option verbose = new Option("verbose", "be extra verbose");
		Option debug = new Option("debug", "print debugging information");
		Option emacs = new Option("emacs",
				"produce logging information without adornments");

		Option rymuser = OptionBuilder.withArgName("rymuser").hasArg()
				.withDescription("use given rymuser").create("rymuser");
		
		Option localdir = OptionBuilder.withArgName("localdir").hasArg()
				.withDescription("use given local directory").create("localdir");
		
		Option logfile = OptionBuilder.withArgName("file").hasArg()
				.withDescription("use given file for log").create("logfile");

		Option logger2 = OptionBuilder.withArgName("classname").hasArg()
				.withDescription("the class which it to perform " + "logging")
				.create("logger");

		Option listener = OptionBuilder
				.withArgName("classname")
				.hasArg()
				.withDescription(
						"add an instance of class as " + "a project listener")
				.create("listener");

		Option buildfile = OptionBuilder.withArgName("file").hasArg()
				.withDescription("use given buildfile").create("buildfile");

		Option find = OptionBuilder
				.withArgName("file")
				.hasArg()
				.withDescription(
						"search for buildfile towards the "
								+ "root of the filesystem and use it")
				.create("find");

		Option property = OptionBuilder.withArgName("property=value")
				.hasArgs(2).withValueSeparator()
				.withDescription("use value for given property").create("D");

		options.addOption(help);
		options.addOption(fullscan);
		options.addOption(loadrec);
		options.addOption(readrym);
		options.addOption(rymuser);
		options.addOption(localdir);
		
		options.addOption(projecthelp);
		options.addOption(version);
		options.addOption(quiet);
		options.addOption(verbose);
		options.addOption(debug);
		options.addOption(emacs);
		options.addOption(logfile);
		options.addOption(logger2);
		options.addOption(listener);
		options.addOption(buildfile);
		options.addOption(find);
		options.addOption(property);
		options.addOption(loadimg);
		
		// create the parser
		CommandLineParser parser = new DefaultParser();
		try {
			// parse the command line arguments
			CommandLine line = parser.parse(options, args);

			
			if (line.hasOption("localdir")) {
				System.setProperty("logfile.path",line.getOptionValue("localdir"));
				//mp.put("logfile.path", line.getOptionValue("localdir"));
			}else{
				mp.put("logfile.path", "./logs");
				//System.setProperty("logfile.path","./logs");
			}
			
			//setEnv(mp);
			//System.out.println(System.getenv("logfile.path"));
			logger=Logger.getLogger("CARd");
			
		
			if (line.hasOption("help")) {
				// automatically generate the help statement
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("card", options);
			}
			
			if (line.hasOption("help")) {
				// automatically generate the help statement
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("card", options);
			}

			// has the buildfile argument been passed?
			if (line.hasOption("buildfile")) {
				// initialise the member variable
				System.out.println(line.getOptionValue("buildfile"));
			}
			
			// has the buildfile argument been passed?
			if (line.hasOption("fullscan")) {

				
				if (line.hasOption("rymuser")){
					System.out.println(line.getOptionValue("rymuser"));
				}else{
					System.err.println("rymuser not set");
					System.exit(2);
				}
				
				if (line.hasOption("localdir")){
					System.out.println(line.getOptionValue("localdir"));
				}else{
					System.err.println("localdir not set");
					System.exit(2);
				}		
				CARd_Main cm = new CARd_Main();
				cm.fullImport(line.getOptionValue("rymuser"),line.getOptionValue("localdir"));
				
			}else if (line.hasOption("readrym")){
				if (line.hasOption("rymuser")){
					System.out.println(line.getOptionValue("rymuser"));
				}else{
					System.err.println("rymuser not set");
					System.exit(2);
				}
				
				if (line.hasOption("localdir")){
					System.out.println(line.getOptionValue("localdir"));
				}else{
					System.err.println("localdir not set");
					System.exit(2);
				}	
				CARd_Main cm = new CARd_Main();
				cm.readrym(line.getOptionValue("rymuser"),line.getOptionValue("localdir"));
			}else if (line.hasOption("loadrec")){
				if (line.hasOption("rymuser")){
					System.out.println(line.getOptionValue("rymuser"));
				}else{
					System.err.println("rymuser not set");
					System.exit(2);
				}
				
				if (line.hasOption("localdir")){
					System.out.println(line.getOptionValue("localdir"));
				}else{
					System.err.println("localdir not set");
					System.exit(2);
				}	
				CARd_Main cm = new CARd_Main();
				cm.loadrec(line.getOptionValue("rymuser"),line.getOptionValue("localdir"));
			}else if(line.hasOption("loadimg")){
				if (line.hasOption("localdir")){
					System.out.println(line.getOptionValue("localdir"));
				}else{
					System.err.println("localdir not set");
					System.exit(2);
				}					
				CARd_Main cm = new CARd_Main();
				cm.loadimg(line.getOptionValue("localdir"));				
			}
			

		} catch (ParseException e) {
			// oops, something went wrong
			logger.error("IOEXception occured:", e);
		}

	}
	
	protected static void setEnv(Map<String, String> newenv)
	{
	  try
	    {
	        Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
	        Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
	        theEnvironmentField.setAccessible(true);
	        Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
	        env.putAll(newenv);
	        Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
	        theCaseInsensitiveEnvironmentField.setAccessible(true);
	        Map<String, String> cienv = (Map<String, String>)     theCaseInsensitiveEnvironmentField.get(null);
	        cienv.putAll(newenv);
	    }
	    catch (NoSuchFieldException e)
	    {
	      try {
	        Class[] classes = Collections.class.getDeclaredClasses();
	        Map<String, String> env = System.getenv();
	        for(Class cl : classes) {
	            if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
	                Field field = cl.getDeclaredField("m");
	                field.setAccessible(true);
	                Object obj = field.get(env);
	                Map<String, String> map = (Map<String, String>) obj;
	                map.clear();
	                map.putAll(newenv);
	            }
	        }
	      } catch (Exception e2) {
	        e2.printStackTrace();
	      }
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    } 
	}

}
