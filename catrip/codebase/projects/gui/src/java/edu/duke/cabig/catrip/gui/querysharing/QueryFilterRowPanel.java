package edu.duke.cabig.catrip.gui.querysharing;

import edu.duke.cabig.catrip.gui.simplegui.CDEComboboxBean;
import gov.nih.nci.catrip.cagrid.catripquery.server.ClassDb;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class QueryFilterRowPanel extends JPanel {

	private JComboBox cbFilter = null;
	private JButton delFilterButton = null;
	private QueryServiceUI containterPanel;
	private ClassDb aClass = null;

	/**
	 * This method initializes 
	 * 
	 */
	public QueryFilterRowPanel(int comboBoxSelection) {
		super();
		initialize();
		getCbFilter().setSelectedIndex(comboBoxSelection);
	}
	public QueryFilterRowPanel() {
		super();
		initialize();
	}

	public QueryFilterRowPanel(QueryServiceUI p) {
		super();
		initialize();
		containterPanel = p;
	}
        
        public QueryFilterRowPanel(QueryServiceUI p, ArrayList listItems) {
		super();
		initialize();
		containterPanel = p;
                // fill the combo with the classList..  this is of type CDEComboBoxBeans.. 
                javax.swing.JComboBox filterBox = getCbFilter();
                for (int i = 0; i < listItems.size(); i++) {
                    CDEComboboxBean cdeBean = (CDEComboboxBean) listItems.get(i); 
                    filterBox.addItem(cdeBean);
                }
                
	}
        
        private void createSelectionObject(){
        	Random generator = new Random();
        	aClass = new ClassDb();
        	aClass.setId(generator.nextInt());

        	CDEComboboxBean cdeBean = (CDEComboboxBean)getCbFilter().getSelectedItem();
        	if (cdeBean!=null){
        		aClass.setName(cdeBean.getClassBean().getFullyQualifiedName());
        		System.out.println("Created class : " + aClass.getName());
        		// if (!cdeBean.getAttributeBean().isNull()){ // set the attribute here..
        		if (cdeBean.getAttributeBean() != null){ // set the attribute here..
        			aClass.addAttribute(cdeBean.getAttributeBean().getAttributeName());
        			System.out.println("Created attribute : " + cdeBean.getAttributeBean().getAttributeName());
        		}
        	}

        }
	public ClassDb getSelectedClass(){
		return aClass;
	}
	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
         
        this.setLayout(null);
        this.setSize(new Dimension(538, 26));
        // add concepts to combo box
//        getCbFilter().addItem("");
//        getCbFilter().addItem("edu.pitt.cabig.cae.domain.general.AnnotationEventParameters");
//        getCbFilter().addItem("edu.duke.catrip.cae.domain.general.Participant");
        this.add(getCbFilter(), null);
        this.add(getDelFilterButton(), null);
			
	}

	/**
	 * This method initializes cbFilter	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCbFilter() {
		if (cbFilter == null) {
			cbFilter = new JComboBox();
			cbFilter.setBounds(new Rectangle(61, 1, 468, 21));
			cbFilter.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					createSelectionObject();
				}
			});
		}
		return cbFilter;
	}
	/**
	 * This method initializes delFilterButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDelFilterButton() {
		if (delFilterButton == null) {
			delFilterButton = new JButton();
			delFilterButton.setBounds(new Rectangle(7, 1, 34, 21));
			delFilterButton.setFont(new java.awt.Font("Tahoma", 1, 11));
			delFilterButton.setForeground(new java.awt.Color(255, 0, 0));
			//delFilterButton.setText(org.openide.util.NbBundle.getMessage(FilterRowPanel.class, "FilterRowPanel.delFilterBtn.text")); // NOI18N
			delFilterButton.setText("X");
			delFilterButton.setIconTextGap(0);
			delFilterButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
			delFilterButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					removeFilter();
					System.out.println("delete");
				}
			});
		}
		return delFilterButton;
	}
	protected void removeFilter() {
		containterPanel.removeFilter(this);
//		containterPanel.getFilterPanel().revalidate();
//		containterPanel.getFilterPanel().repaint();
		

	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
