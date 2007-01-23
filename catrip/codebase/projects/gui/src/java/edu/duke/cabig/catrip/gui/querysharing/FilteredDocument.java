package edu.duke.cabig.catrip.gui.querysharing;

import javax.swing.text.PlainDocument;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.*;
import java.awt.*;

public class FilteredDocument extends PlainDocument {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char[] m_chrBadChars;
	private int m_nMaxLength;
//	We can also limit text to be the size of a jtextfield.
	private JTextComponent m_jtfLimiter;

	private boolean m_bLengthLimited;
	private boolean m_bCharacterLimited;

	public FilteredDocument() {
		m_bLengthLimited = false;
		m_bCharacterLimited = false;
		m_jtfLimiter = null;
	}

	public void setLengthLimited(final int len) {
		m_bLengthLimited = (len != -1);
		m_nMaxLength = len;
	}

	public void setBadChars(final char[] charList) {
		m_chrBadChars = charList;
		m_bCharacterLimited = (charList != null);
	}

	public void setFieldSizeLimited(final JTextComponent jt) {
		m_jtfLimiter = jt;
	}

	public void insertString(final int offset, final String str, final AttributeSet a) throws BadLocationException {
		try {

			boolean bBadInsert = false;
			final String currText = getText(0, getLength());
			if (m_bLengthLimited) {
				if (currText.length() + str.length() > m_nMaxLength) {
					bBadInsert = true;
				}
			}
			if (m_bCharacterLimited) {
				for (int i = 0; i < m_chrBadChars.length; i++) {
					if (str.indexOf((char)m_chrBadChars[i]) != -1) {
						bBadInsert = true;
					}
				}
			}
			if (m_jtfLimiter != null) {
				String possibleTxt = currText.substring(0, offset);
				possibleTxt += str;
				possibleTxt += currText.substring(offset);
//				See if the last character would be outside of the field.

				if (m_jtfLimiter instanceof JTextField) {
					final FontMetrics fm = m_jtfLimiter.getFontMetrics(m_jtfLimiter.getFont());
					if (fm.stringWidth(possibleTxt)+fm.stringWidth("d") > m_jtfLimiter.getWidth())
						bBadInsert = true;
				} else {
//					System.out.println("NUM LINES: " + ((JTextArea)m_jtfLimiter).getLineCount());
//					Figuring out how to limit text if it is a Jtextarea at the momemnt.
				}
			}
			if (bBadInsert) {
				Toolkit.getDefaultToolkit().beep();
				return;
			}
		} catch(final Exception except) {except.printStackTrace();}

		super.insertString(offset, str, a);
	}
} 