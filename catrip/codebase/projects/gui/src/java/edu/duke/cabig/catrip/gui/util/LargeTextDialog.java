package edu.duke.cabig.catrip.gui.util;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.BreakIterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class LargeTextDialog extends JDialog implements ActionListener, ClipboardOwner {

	private Clipboard clipboard = getToolkit ().getSystemClipboard ();
	private JButton closeButton = new JButton("Close");
	private JLabel theLabel;
	//private JTextArea textArea = new JTextArea(8, 40);
	private JButton cmdCopy = new JButton();
	private JScrollPane scrollPane;

	public LargeTextDialog(String column, String text) {
		JPanel p = new JPanel();
//		textArea.setEditable(false);
//		textArea.setWrapStyleWord(true); 
//		textArea.setLineWrap(true);
		//textArea.setText("<html> " + text + " </html>");
		p.add(closeButton);
		this.setModal(true);
		closeButton.addActionListener(this);
		theLabel = new JLabel("<html> " + text + " </html>") {
			public Dimension getPreferredSize() {
				return new Dimension(500, 2000);
			}
			public Dimension getMinimumSize() {
				return new Dimension(200, 200);
			}
			public Dimension getMaximumSize() {
				return new Dimension(800, 800);
			}
		};
		theLabel.setVerticalAlignment(SwingConstants.TOP);
		theLabel.setHorizontalAlignment(SwingConstants.LEFT);
		scrollPane = new JScrollPane(theLabel);
		cmdCopy.setText("Copy to Clipboard");
		p.add(cmdCopy);
		getContentPane().add(p, "South");

		getContentPane().add(scrollPane, "Center");
		if (column == null)
			column = "Text Viewer";
		setTitle(column);
		setSize(550, 300);


		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				getContentPane().setVisible(false);
			}
		});
		cmdCopy.addActionListener (new CmdCopyActionListener ());

	}
	  class CmdCopyActionListener implements ActionListener {

		  public void actionPerformed (ActionEvent event) {
			  StringSelection fieldContent = new StringSelection (theLabel.getText());
			  clipboard.setContents (fieldContent, LargeTextDialog.this);
		  }
	  }


	  public void actionPerformed(ActionEvent evt) {
	    Object source = evt.getSource();
	    if (source == closeButton)
	    	this.setVisible(false);
	    
	  }

	  public static void main(String[] args) {
		  String text = "<html> A. Sentinel node # 1 hot/blue, received in formalin is a 2 x 2 x 1 cm fragment of purple-tan soft tissue which is bisected and submitted entirely in blocks A1-2.&nbsp; A cytokeratin stain is prospectively requested on both blocks.<BR><BR>B. Sentinel node # 2 hot not blue, received in formalin is a 0.3 x 0.3 x 0.2 cm fragment of pink-tan tissue which is submitted in toto in block B1.&nbsp; A<BR>cytokeratin stain is prospectively requested.<BR><BR>C. Sentinel node # 3 hot not blue, received in formalin are three lymph node fragments measuring in aggregate 1 x 0.7 x 0.3 cm.&nbsp; The fragments re submitted in block C1 and a cytokeratin stain is prospectively requested.<BR><BR>D. Left breast cancer. The specimen is received fresh and placed in formalin. The specimen is a 7.9 (S-I) x 4.3 (M-L) x 1.9 (A-P) lumpectomy specimen with an attached needle localization wire. The specimen is inked as follows: anterior-blue, posterior-black, medial-red, lateral-yellow. The specimen is serially sectioned from superior to inferior and then radiographed. The surgical clip is identified radiographically. The specimen reveals a cut surface notable for a 2.4 x 1.2 cm area of firm, tan breast tissue with a 0.5 cm cavity consistent with breast biopsy site. This firm area is present in twelve tissue sections. Although this area&nbsp; is firm to palpation no mass lesions are identified. Remainder of the specimen is mature, yellow, lobulated adipose tissue. Representative sections are submitted from superior to inferior in blocks D1-D32. Please note that the biopsy cavity is in blocks<BR>B5-B25 with the surgical clip in block B16. Please see block diagram for further details.<BR><BR>E. Left superior margin. Received fresh and placed in formalin. The specimen is a 4 x 3 x 2 cm piece of lobulated adipose tissue with stitch on one side.<BR>Please note that the container is marked stitch on final margin. The stitch surface is inked blue, the opposite side is inked black. Please note that blue represents a true margin and black does not represent a true margin. The specimen is serially sectioned to reveal a cut surface that is yellow, glistening adipose tissues. No masses or lesions are identified. The specimen is entirely submitted in blocks E1-E9.<BR><BR>F. Left lateral margin. Received fresh and placed in formalin. The specimen is a 3.5 x 3 x 1 cm fragment of yellow adipose tissue with stitch attached.<BR>Please note containers labeled stitch on final margin. The stitch surface is inked blue, the opposite surface is inked black. Please note that the blue represents true margin and black does not represent true margin. The specimen is serially sectioned to reveal a cut surface that is yellow adipose tissue with a scant amount of admixed tan fibrous breast tissue. No mass lesions are identified.&nbsp; The specimen is entirely submitted in blocks F1-F6.<BR><BR>G. Left lateral superior margin, received fresh and placed in formalin. The specimen is a 4.5 x 3.5 x 2 cm fragment of yellow adipose tissue with stitch one end. Please note that the container is labeled stitch on final margin. The stitch surface is inked blue, the opposite surface is inked black. Please note that blue ink represents a true margin and that black ink does not represent a true margin. The specimen is serially sectioned to reveal a cut surface that is glistening yellow adipose tissue with no masses or lesions. The specimen is entirely submitted in blocks G1-G8.<BR><BR>H. Medial margin, received fresh and placed in formalin. The specimen is a 5 x 3.5 x 2 cm fragment of glistening yellow adipose tissue with attached suture. Please note containers marked stitch on final margin. The stitch surface is inked blue and the opposite surface is inked black. Please note that blue represents true margin and black does not. The specimen is serially sectioned to reveal predominantly glistening adipose tissue with a scant amount of breast tissue coursening throughout the specimen. There are no mass lesions. The specimen is entirely submitted in blocks H1-H10.<BR> </html>";
	    LargeTextDialog f = new LargeTextDialog(null, text);
	    f.setVisible(true);
	  }


	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub
		
	}

}
