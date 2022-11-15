package logs;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    private final static Logger LOGGER = Logger.getLogger("bitacora");
    
    private final static String PATH = "../logs";
    
    
    public  static void registrarSevereLog(Exception e) {
    	try {
			String dir= Log.class.getProtectionDomain().getCodeSource().getLocation().toString();
			String path=PATH;//dir.substring(dir.indexOf("/")+1,dir.indexOf("/.metadata"));
			Handler consoleHandler = new ConsoleHandler();
			Handler fileHandler = new FileHandler(path+"/bitacora.log", true);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			
			fileHandler.setFormatter(simpleFormatter);
			LOGGER.addHandler(consoleHandler);
			LOGGER.addHandler(fileHandler);
			
			consoleHandler.setLevel(Level.ALL);
			fileHandler.setLevel(Level.ALL);
			
			LOGGER.log(Level.INFO, "Nueva entrada");
			LOGGER.log(Level.SEVERE, Log.getStackTrace(e));    		
    	} catch (IOException ex) {
			ex.printStackTrace();
            LOGGER.log(Level.SEVERE, "Error de IO");
        } 
    }
    
    public  static void registrarFineLog(Exception e) {
    	try {
    		String dir= Log.class.getProtectionDomain().getCodeSource().getLocation().toString();
    		String path=PATH;//dir.substring(dir.indexOf("/")+1,dir.indexOf("/.metadata"));
			Handler consoleHandler = new ConsoleHandler();
			Handler fileHandler = new FileHandler(path+"/bitacora.log", true);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			
			fileHandler.setFormatter(simpleFormatter);
			LOGGER.addHandler(consoleHandler);
			LOGGER.addHandler(fileHandler);
			
			consoleHandler.setLevel(Level.ALL);
			fileHandler.setLevel(Level.ALL);
			
			LOGGER.log(Level.INFO, "Nueva entrada");
			LOGGER.log(Level.FINE, Log.getStackTrace(e));    		
    	} catch (IOException ex) {
			ex.printStackTrace();
            LOGGER.log(Level.SEVERE, "Error de IO");
        } 
    }
    public  static void registrarWarningLog(Exception e) {
    	try {
    		String dir= Log.class.getProtectionDomain().getCodeSource().getLocation().toString();
    		String path=PATH;//dir.substring(dir.indexOf("/")+1,dir.indexOf("/.metadata"));
			Handler consoleHandler = new ConsoleHandler();
			Handler fileHandler = new FileHandler(path+"/bitacora.log", true);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			
			fileHandler.setFormatter(simpleFormatter);
			LOGGER.addHandler(consoleHandler);
			LOGGER.addHandler(fileHandler);
			
			consoleHandler.setLevel(Level.ALL);
			fileHandler.setLevel(Level.ALL);
			
			LOGGER.log(Level.INFO, "Nueva entrada");
			LOGGER.log(Level.WARNING, Log.getStackTrace(e));    		
    	} catch (IOException ex) {
			ex.printStackTrace();
            LOGGER.log(Level.SEVERE, "Error de IO");
        } 
    }

    public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }

}
