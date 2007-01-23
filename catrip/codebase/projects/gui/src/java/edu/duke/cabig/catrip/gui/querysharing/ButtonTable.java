package edu.duke.cabig.catrip.gui.querysharing;

import gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ButtonTable extends JTable {

	public ButtonTable(){
		
	}
	public ButtonTable(Collection collection){
	       DefaultTableModel model = new DefaultTableModel();
	       model.setColumnCount(5);
			Vector<String> columnHeaders = new Vector<String>(5);
			columnHeaders.add("Query Name");
			columnHeaders.add("User Name");
			columnHeaders.add("Description");
			columnHeaders.add("View/Modify");
			columnHeaders.add("Run");
			model.setColumnIdentifiers(columnHeaders);
		
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			QueryDb e = (QueryDb) iter.next();
			
            // create another vector, this one will be used as a row in the table
            Vector<Serializable> rowData = new Vector<Serializable> () ;

            // populate this row with the data out of the objects, using wrapper classes where primitive
            // data types are used.
            rowData.add (e.getName()) ;
            rowData.add (e.getFirstName()+ " " + e.getLastName()) ;
            rowData.add (e.getDescription()) ;
            rowData.add (new JButton("View/Modify")) ;
            rowData.add (new JButton("Run")) ;

            // add row to model 
            model.addRow(rowData) ;
	}
		this.setModel(model);

		
		
		
//		
//        String[] columnNames = {"Date", "String", "Integer", "Decimal", ""};
//        Object[][] data =
//        {
//            {new Date(), "A", new Integer(1), new Double(5.1), "Delete0"},
//            {new Date(), "B", new Integer(2), new Double(6.2), "Delete1"},
//            {new Date(), "C", new Integer(3), new Double(7.3), "Delete2"},
//            {new Date(), "D", new Integer(4), new Double(8.4), "Delete3"}
//        };
 
        // model.set
        //model.setColumnIdentifiers(columnNames);
        //this.setModel(model);
        //  Create button column
        ButtonColumn buttonColumn = new ButtonColumn(this, 4);

	}
	/**
	 * @param args
	 */
    public Class getColumnClass(int column){
        return getValueAt(0, column).getClass();
    }

//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		ButtonTable table = new ButtonTable();
//		JPanel panel = new JPanel();
//		panel.add(table);
//		frame.add(panel);
//		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//		frame.pack();
//		frame.setVisible(true);	
//	}
	class ButtonColumn extends AbstractCellEditor
	implements TableCellRenderer, TableCellEditor, ActionListener
	{
		JTable table;
		JButton renderButton;
		JButton editButton;
		String text;

		public ButtonColumn(JTable table, int column)
		{
			super();
			this.table = table;
			renderButton = new JButton();

			editButton = new JButton();
			editButton.setFocusPainted( false );
			editButton.addActionListener( this );

			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(column).setCellRenderer( this );
			columnModel.getColumn(column).setCellEditor( this );
		}

		public Component getTableCellRendererComponent(
				JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			if (hasFocus)
			{
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager.getColor("Button.background"));
			}
			else if (isSelected)
			{
				renderButton.setForeground(table.getSelectionForeground());
				renderButton.setBackground(table.getSelectionBackground());
			}
			else
			{
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager.getColor("Button.background"));
			}

			renderButton.setText( (value == null) ? "" : value.toString() );
			return renderButton;
		}

		public Component getTableCellEditorComponent(
				JTable table, Object value, boolean isSelected, int row, int column)
		{
			text = (value == null) ? "" : value.toString();
			editButton.setText( text );
			return editButton;
		}

		public Object getCellEditorValue()
		{
			return text;
		}

		public void actionPerformed(ActionEvent e)
		{
			fireEditingStopped();
			System.out.println( e.getActionCommand() + " : " + table.getSelectedRow());
		}
	}

}
