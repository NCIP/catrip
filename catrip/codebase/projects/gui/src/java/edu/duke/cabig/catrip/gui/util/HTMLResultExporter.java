
package edu.duke.cabig.catrip.gui.util;


import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.query.DCQLGenerator;
import edu.duke.cabig.catrip.gui.query.DCQLRegistry;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.xmlbeans.XmlOptions;


/**
 *
 * @author Sanjeev Agarwal
 */
public class HTMLResultExporter {
    
    
    public static void exportToHtml(JTable table){
        if(GUIConstants.resultAvailable){
            export(table);
        }
    }
    
    public static void export(JTable table){
        try {
            // right now dump it into a file in user.home/.caTRIP/ results.html later on pop up a file chooser.
            String file = GUIConstants.CATRIP_HOME+File.separator+"caTRIP_Query_Results.html";
            
            TableModel model = table.getModel();
            FileWriter out = new FileWriter(file);
            out.write("<HTML><BODY>");
            
            ClassBean targetObject = DCQLRegistry.getTargetNode().getAssociatedClassObject();
            
            out.write("<div style=\"text-align: center; font-weight: bold;\">caTRIP Query Results:<br>" +
                    "Executed against Service: "+targetObject.getServiceName()+"<br>Dated : "+new Date()+"<br>For Target Domain " +
                    "Object : "+targetObject.getClassName()+"</div><br><br>");
            
            int rows = model.getRowCount();
            int columns = model.getColumnCount();
            
            out.write("<TABLE  border=\"1\" cellpadding=\"3\">");  out.write("\n");
            // write column names..
            out.write("<TR>");  out.write("\n");
            for(int i=0; i<columns;i++) {
                out.write("<TD style=\"background-color: rgb(204, 204, 255);\" >");
                out.write(model.getColumnName(i));
                out.write("</TD>");  out.write("\n");
            }
            out.write("</TR>");  out.write("\n\n");
            
            
            boolean even = false; // paint alternate row in differnt color.
            //
            for(int i=0; i< model.getRowCount();i++){
                out.write("<TR>");
                for(int j=0;j< model.getColumnCount();j++){
                    if(even){
                        out.write("<TD style=\"background-color: rgb(255, 255, 204);\">");
                    }else {
                        out.write("<TD>");
                    }
                    String val = "-";
                    
                    if ((model.getValueAt(i, j) != null) && !(model.getValueAt(i, j).toString().toString().equals("")) && !(model.getValueAt(i, j).toString().toString().equals(" ")) ){
                        val = model.getValueAt(i, j).toString();
                    }
                    out.write(val);
                    out.write("</TD>");;  out.write("\n");
                }
                out.write("</TR>");
                if(even){
                    even = false;
                }else {
                    even = true;
                }
            }
            
            out.write("</TABLE>");
            out.write("<br>");
            out.write("<br>");
            out.write("<br>");
            
            out.write("<div style=\"font-weight: bold;\">The DCQL for the query:</div><br>");
            out.write("<pre>");
            XmlOptions xmlOptions = new XmlOptions();
            xmlOptions.setSavePrettyPrint();
            xmlOptions.setSavePrettyPrintIndent(4);
            xmlOptions.setUseDefaultNamespace();
            String dcql = DCQLGenerator.getDCQLText(xmlOptions);
            dcql = dcql.replaceAll("<", "&lt;");
            dcql = dcql.replaceAll(">", "&gt;");
            out.write(dcql);
            out.write("</pre>");
            
            out.write("</BODY></HTML>");  out.write("\n");
            out.close();
            System.out.println("write to " + file);
            
            JOptionPane jpane = new JOptionPane();
            jpane.setLocation(25,25);
            jpane.showMessageDialog(table ,"The results are exported to HTML file :\n"+file);
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    
    
    
    
    
    
    
}
