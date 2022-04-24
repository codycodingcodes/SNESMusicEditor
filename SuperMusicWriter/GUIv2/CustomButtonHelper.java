
/******************************************************************************************
 * 
 * Programmer Name: Team 5
 * 
 * Class Name: CustomButtonHelper
 *
 ******************************************************************************************
 * 
 * Description: This class creates buttons/icons within the UI file menu and module controls
 * 
 * ****************************************************************************************
 * 
 * Custom Defined Methods
 * ______________________
 * 
 * +getInstrumentName():String +getInstrumentCode():String
 * 
 ******************************************************************************************/

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomButtonHelper extends JButton {

	/****************************
     * Private instance variables
     ****************************/
	private static final long serialVersionUID = 1L;
	private String buttonName;
	private String buttonCode;
	private Font buttonFont = ConstantDataValues.getCustomFont().deriveFont(30f);
	private JButton custButton;

	/*********************************
	 * 
	 * Name: CustomButtonHelper
     * Description: Class Constructor
	 * @param dim, @param name
	 * 
	 ********************************/
	public CustomButtonHelper(String name, Dimension dim) {
		this(dim, name, JButton.NORTH, JButton.CENTER, false);
	}

	/******************************************************************************
	 * 
	 * Name: CustomButtonHelper
     * Description: Class Constructor
	 * @param dim, @param name, @param vertpos, @param horizPos, @param isInstrument
	 * 
	 *******************************************************************************/
	public CustomButtonHelper(Dimension dim, String name, int vertPos, int horizPos, boolean isInstrument) {

		custButton = this;
		if (isInstrument) {
			buttonCode = name.substring(0, name.indexOf(' '));
			buttonName = name.substring(name.indexOf(' ') + 1);
			custButton.setIcon(new ImageIcon(getClass().getResource("Icons/" + buttonName.toLowerCase() + ".png")));
			custButton.setBackground(Color.WHITE);
			custButton.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					custButton.setBackground(new Color(178, 200, 255));

				}

				@Override
				public void mouseExited(MouseEvent e) {
					custButton.setBackground(Color.WHITE);

				}
			});
		} else {
			buttonName = name;
			custButton.setBackground(ConstantDataValues.noteColor);
			custButton.setFont(buttonFont);
			// custButton.setBackground(new Color(255,10,50));
			// custButton.setForeground(new Color(50,255,50));

			if (name == ">>" || name == "<<") {
				custButton.setBackground(ConstantDataValues.modifierColor);
				custButton.setText(buttonName);
				custButton.setPreferredSize(dim);
				custButton.setVerticalTextPosition(vertPos);
				custButton.setHorizontalTextPosition(horizPos);
				return;
			}

			custButton.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					custButton.setForeground(ConstantDataValues.noteColor);
					custButton.setBackground(ConstantDataValues.menuLabelColor);

				}

				@Override
				public void mouseExited(MouseEvent e) {
					custButton.setBackground(ConstantDataValues.noteColor);
					custButton.setForeground(ConstantDataValues.menuLabelColor);

				}
			});

			// custButton.setForeground(Color.white);
		}
		custButton.setText(buttonName);
		custButton.setPreferredSize(dim);
		custButton.setVerticalTextPosition(vertPos);
		custButton.setHorizontalTextPosition(horizPos);
	}

	/**********************************************************
	 * 
	 * Name: getInstrumentName 
	 * Description: returns the name of the instrument selected 
	 * @return String
	 * 
	 **********************************************************/
	public String getInstrumentName() {
		return buttonName;
	}

	/***********************************************************************
	 * 
	 * Name: getInstrumentCode 
	 * Description: returns the key corresponding to the selected instrument 
	 * @return String
	 * 
	 ***********************************************************************/
	public String getInstrumentCode() {
		return buttonCode;
	}

}
