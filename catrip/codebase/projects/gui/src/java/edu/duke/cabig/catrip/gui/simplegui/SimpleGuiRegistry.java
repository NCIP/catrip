
package edu.duke.cabig.catrip.gui.simplegui;

import edu.duke.cabig.catrip.gui.config.GUIConfigurationLoader;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.ObjectGraphProcessor;
import java.io.File;

/**
 *
 * @author Sanjeev Agarwal
 */
public class SimpleGuiRegistry {
    // array of the filterpanel..
    private static ObjectGraphProcessor processor = new ObjectGraphProcessor(GUIConfigurationLoader.getGUIConfiguration().getConfigRootLocation()+File.separator+"simplegui"+File.separator+"SimpleGuiObjectGraph.xml");
    
    
    
    /** Creates a new instance of SimpleGuiRegistry */
    public SimpleGuiRegistry() {
    }
    
    
    public static ObjectGraphProcessor getProcessor(){
        return processor;
    }
    
    
    
    
}
