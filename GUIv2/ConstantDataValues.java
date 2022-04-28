
/******************************************************************************************
 * 
 * Programmer Name: Team 5
 * 
 * Class Name: ConstantdataValues
 *
 ******************************************************************************************
 * 
 * Description: This class defines and formats the text within the UI module
 * 
 * ****************************************************************************************
 * 
 * Custom Defined Methods
 * ______________________
 * 
 * +getCustomFont()
 * 
 ******************************************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class ConstantDataValues {

	/****************************
	 * Color for the menu lables
	 ****************************/
	public static final Color menuLabelColor = new Color(50, 50, 50);

	/*************************************
	 * Color for the note modifier buttons
	 ************************************/
	public static final Color modifierColor = new Color(156, 209, 159); // (178, 200, 255)
	// (218, 223, 252)
	public static final Color controllerPanelColor = new Color(55, 105, 150);
	public static final Color noteColor = new Color(235, 230, 220);

	/***************************************************
	 * Setting dimensions for musical instrument buttons
	 **************************************************/
	public static final Dimension BUTTONINSTRUMENTSIZE = new Dimension(200, 150);
	public static final Dimension BUTTONSMUSICNOTES = new Dimension(100, 125);

	/****************************
	 * Modeling the JFrame Window
	 ****************************/
	public static final int FRAMEWIDTH = 1300;
	public static final int FRAMEHEIGHT = 760;
	public static final String FRAMETITLE = "Music Editor for SNES Super Mario World";

	/****************************
	 * Setup the menu-bar frame
	 ****************************/
	public static final String[] MENUNAME = { "File", "Help", "Channels", "Playback" };

	/*******************************************************
	 * used for the menu bar shortcut (alt+F, alt+E, alt+C, alt+P)
	 ******************************************************/
	public static final int[] MENUBARSHORTCUT = { KeyEvent.VK_F, KeyEvent.VK_H, KeyEvent.VK_C, KeyEvent.VK_P };
	public static final int MAXMENUSIZE = 255; // max menu size

	/*****************************
	 * used to set up the file box
	 *****************************/
	public static final String[] FILEITEM = { "Load", "Save", "Export" };

	/******************************************************
	 * used for the menu bar shortcut (alt+L, alt+S, alt+E)
	 *****************************************************/
	public static final int[] FILEITEMSHORTCUT = { KeyEvent.VK_L, KeyEvent.VK_S, KeyEvent.VK_E };

	// used to set up the edit box
	public static final String[] PLAYITEM = { "Music Player" };
	// used to set up the help box
	public static final String[] HELPITEM = { "User Manual" };
	// used for the menu bar shortcut (alt+M)
	public static final int[] PLAYITEMSHORTCUT = { KeyEvent.VK_M };

	/*****************************************************
	 * used for the menu bar shotcut (alt+L, alt+S, alt+E)
	 ****************************************************/
	public static final int[] HELPITEMSHORTCUT = { KeyEvent.VK_U };

	/*********************************
	 * used to set up the testing menu
	 ********************************/
	public static final String[] CHANITEM = { "Add Channel", "Remove Last Added Channel", "Play Current Channel" };

	/*****************************************************
	 * used for the menu bar shortcut (alt+L, alt+S, alt+E)
	 *****************************************************/
	public static final int[] CHANSHORTCUT = { KeyEvent.VK_A, KeyEvent.VK_R, KeyEvent.VK_P };

	/********************
	 * Getting note info
	 ********************/
	public static final String LABELNAME = "Music Interface";

	/*********************************
	 * Setting an array of JFuge notes
	 ********************************/
	public static final String[] NOTETYPE = { "W", "H", "Q", "I", "S", "T", "X", "O" };

	/*********************************
	 * used to set the octave buttons
	 ********************************/
	public static final String[] OCTAVEBUTTONLABEL = { ">>", "<<" };
	public static final Dimension BUTTONSOCTAVE = new Dimension(95, 95);
	public static final Color BUTTONOCTAVECOLOR = new Color(218, 223, 252);
	public static final String[] OCTAVEPOSTITION = { BorderLayout.EAST, BorderLayout.WEST };
	public static final int MINOCTLEVEL = 1;
	public static final int MAXOCTLEVEL = 5;

	/***************************
	 * used to set channel data
	 ***************************/
	public static final Font channelFont = new Font("Arial", Font.PLAIN, 18);
	public static final String[] channels = { "Channel 0", "Channel 1", "Channel 2", "Channel 3", "Channel 4",
			"Channel 5" };

	/******************************
	 * used to set note length data
	 ******************************/
	public static final String[] noteLength = { "1", "2", "4", "8", "16", "32", "64" };
	public static final Font noteLengthFont = new Font("Arial", Font.PLAIN, 18);

	/************************************************
	 * 
	 * Name: getCustomFont
	 * Description: Setting custom font for the icons
	 * 
	 ************************************************/
	public static Font getCustomFont() {

		Font customFont = null;
		InputStream fontStream = ConstantDataValues.class.getResourceAsStream("Icons/SuperMario256.ttf");
		try {
			// create the font to use. Specify the size!
			customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream)
					.deriveFont(24f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			// register the font
			ge.registerFont(customFont);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		customFont = customFont.deriveFont(20f);
		return customFont;
	}

	public static void playRickAshley() {
		Player play = new Player();
		try {
			Pattern pattern = MidiFileManager
					.loadPatternFromMidi(ConstantDataValues.class.getResourceAsStream("Never-Gonna-Give-You-Up-3.mid"));

			Pattern p2 = new Pattern(":CON(4,100) " + pattern);
			// System.out.println( pattern);
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					play.play(p2);
				}
			}).start();

		} catch (IOException | InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
