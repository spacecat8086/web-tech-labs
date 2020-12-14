package by.vlados.carrentalsystem.util;

import java.util.ResourceBundle;

/**
 *
 * @author vlados
 * 
 * Manager for retrieving database connection settings, pages' paths information
 */
public class ConfigurationManager {
  private final static ResourceBundle resourceBundle = 
                         ResourceBundle.getBundle("config");

    /**
     *
     */
    public ConfigurationManager(){};
    
    /**
     *
     * @param key
     * @return the value tied to the key
     */
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }   
}
