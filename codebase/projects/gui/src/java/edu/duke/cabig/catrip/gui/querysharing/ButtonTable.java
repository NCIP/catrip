/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.querysharing;

import gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.text.BreakIterator;
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

@SuppressWarnings("serial")
public class ButtonTable extends JTable {

	public ButtonTable() {

	}

	public String getToolTipText(MouseEvent e) {
		String tip = null;
		java.awt.Point p = e.getPoint();
		int rowIndex = rowAtPoint(p);
		int colIndex = columnAtPoint(p);
		int realColumnIndex = convertColumnIndexToModel(colIndex);

		if (realColumnIndex <= 2) { //Description column
			Object columnData = getValueAt(rowIndex, colIndex);
			if (columnData != null && columnData.toString() != null && columnData.toString().trim() != "") {
				String tempTip = columnData.toString().trim();
				if (tempTip.length() > 15)
					tip = wordWrap(tempTip, 55);
			}
		}
		return tip;
	}

	public ButtonTable(Collection collection) {
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
			Vector<Serializable> rowData = new Vector<Serializable>();

			// populate this row with the data out of the objects, using wrapper classes where primitive
			// data types are used.
			rowData.add(e.getName());
			rowData.add(e.getFirstName() + " " + e.getLastName());
			rowData.add(e.getDescription());
			rowData.add(new JButton("View/Modify"));
			rowData.add(new JButton("Run"));

			// add row to model 
			model.addRow(rowData);
		}
		this.setModel(model);
		//  Create button column
		new ButtonColumn(this, 4);

	}

	class ButtonColumn extends AbstractCellEditor implements TableCellRenderer,
			TableCellEditor, ActionListener {
		JTable table;

		JButton renderButton;

		JButton editButton;

		String text;

		public ButtonColumn(JTable table, int column) {
			super();
			this.table = table;
			renderButton = new JButton();

			editButton = new JButton();
			editButton.setFocusPainted(false);
			editButton.addActionListener(this);

			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(column).setCellRenderer(this);
			columnModel.getColumn(column).setCellEditor(this);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (hasFocus) {
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager
						.getColor("Button.background"));
			} else if (isSelected) {
				renderButton.setForeground(table.getSelectionForeground());
				renderButton.setBackground(table.getSelectionBackground());
			} else {
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager
						.getColor("Button.background"));
			}

			renderButton.setText((value == null) ? "" : value.toString());
			return renderButton;
		}

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			text = (value == null) ? "" : value.toString();
			editButton.setText(text);
			return editButton;
		}

		public Object getCellEditorValue() {
			return text;
		}

		public void actionPerformed(ActionEvent e) {
			fireEditingStopped();
			System.out.println(e.getActionCommand() + " : "
					+ table.getSelectedRow());
		}
	}

	/**
	 * Reformats a string where lines that are longer than <tt>width</tt>
	 * are split apart at the earliest wordbreak or at maxLength, whichever is
	 * sooner. If the width specified is less than 5 or greater than the input
	 * Strings length the string will be returned as is.
	 * <p>
	 * Please note that this method can be lossy - trailing spaces on wrapped
	 * lines may be trimmed.
	 *
	 * @param input the String to reformat.
	 * @param width the maximum length of any one line.
	 * @return a new String with reformatted as needed.
	 */
	public static String wordWrap(String input, int width) {
		// handle invalid input
		if (input == null) {
			return "";
		} else if (width < 5) {
			return input;
		} else if (width >= input.length()) {
			return input;
		}

		StringBuilder buffer = new StringBuilder(input.length());
		int current_index = 0;
		int delimiter_index = 0;
		String seperator = "\n";
		String line;

		// go over the input string and jump from line to line
		while (current_index <= input.length()) {
			// look for the next linebreak
			delimiter_index = input.indexOf(seperator, current_index);

			// get the line that corresponds to it
			if (-1 == delimiter_index) {
				line = new String(input
						.substring(current_index, input.length()));
				current_index = input.length() + 1;
			} else {
				line = new String(input.substring(current_index,
						delimiter_index));
				current_index = delimiter_index + seperator.length();
			}

			// handle the wrapping of the line
			BreakIterator breaks = BreakIterator.getLineInstance();
			breaks.setText(line);

			int line_start = 0;
			int start = breaks.first();
			int end = breaks.next();
			while (end != BreakIterator.DONE) {
				// check if the width has been exceeded
				if (end - 1 - line_start >= width) {
					boolean break_line = true;

					// first check if the last characters were spaces,
					// if they were and by removing them the width is not
					// exceeded, just continue
					if (Character.isWhitespace(line.charAt(end - 1))) {
						for (int j = end - 1; j >= 0; j--) {
							if (!Character.isWhitespace(line.charAt(j))) {
								if (j - line_start < width) {
									break_line = false;
								}
								break;
							}
						}
					}

					if (break_line) {
						String line_breaked = line.substring(line_start, start);
						// this can happen with trailing whitespace
						if (line_breaked.length() > width) {
							line_breaked = line_breaked.substring(0, width);
						}
						buffer.append(line_breaked);

						buffer.append(seperator);

						line_start = start;
					}
				}

				start = end;
				end = breaks.next();
			}

			if (line_start < line.length()) {
				buffer.append(line.substring(line_start));
			}

			if (delimiter_index != -1) {
				buffer.append("\n");
			}
		}
		System.out.println("buffer.toString() : " + buffer.toString());
		return buffer.toString();
	}

}
