package logs;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.FileSystems;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    private final static Logger LOGGER = Logger.getLogger("bitacora");
    
    private final static String PATH = String.join("", FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "/Logs");
    
    
    public  static void registrarSevereLog(Exception e) {
    	try {
    		createFolder();
			String path=PATH;
			Handler consoleHandler = new ConsoleHandler();
			Handler fileHandler = new FileHandler(path+"/bitacora.log", true);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			
			fileHandler.setFormatter(simpleFormatter);
			LOGGER.addHandler(consoleHandler);
			LOGGER.addHandler(fileHandler);
			
			consoleHandler.setLevel(Level.ALL);
			fileHandler.setLevel(Level.ALL);
			
			LOGGER.log(Level.INFO, e.getMessage());
			LOGGER.log(Level.ALL, Log.getStackTrace(e));    		
    	} catch (IOException ex) {
			ex.printStackTrace();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            LOGGER.log(Level.ALL, Log.getStackTrace(ex));   
        } 
    }
    
    public  static void registrarFineLog(Exception e) {
    	try {
    		createFolder();
    		System.out.print(FileSystems.getDefault().getPath(".").toString());
    		String path=PATH;
			Handler consoleHandler = new ConsoleHandler();
			Handler fileHandler = new FileHandler(path+"/bitacora.log", true);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			
			fileHandler.setFormatter(simpleFormatter);
			LOGGER.addHandler(consoleHandler);
			LOGGER.addHandler(fileHandler);
			
			consoleHandler.setLevel(Level.ALL);
			fileHandler.setLevel(Level.ALL);
			
			LOGGER.log(Level.INFO, e.getMessage());		
    	} catch (IOException ex) {
    		LOGGER.log(Level.SEVERE, ex.getMessage());
            LOGGER.log(Level.ALL, Log.getStackTrace(ex));   
        } 
    }
    public  static void registrarWarningLog(Exception e) {
    	try {
    		createFolder();
    		String path=PATH;
			Handler consoleHandler = new ConsoleHandler();
			Handler fileHandler = new FileHandler(path+"/bitacora.log", true);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			
			fileHandler.setFormatter(simpleFormatter);
			LOGGER.addHandler(consoleHandler);
			LOGGER.addHandler(fileHandler);
			
			consoleHandler.setLevel(Level.ALL);
			fileHandler.setLevel(Level.ALL);
			
			LOGGER.log(Level.INFO, e.getMessage());
			LOGGER.log(Level.ALL, Log.getStackTrace(e));    		
    	} catch (IOException ex) {
			ex.printStackTrace();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            LOGGER.log(Level.ALL, Log.getStackTrace(ex));   
        } 
    }

    public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }
    
    private static void createFolder() {
    	File file = new File(PATH);
    	
    	if (file.mkdir() == true)
    		return;
    	else
    		return;
    }

}
