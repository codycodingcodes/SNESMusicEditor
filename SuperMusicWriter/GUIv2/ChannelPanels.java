
/******************************************************************************************
 * 
 * Programmer Name: Team 5
 * 
 * Class Name: ChannelPanels
 *
 ******************************************************************************************
 * 
 * Description: This class creates the buttons for selecting the notes for each instrument
 * 
 * ****************************************************************************************
 * 
 * Custom Defined Methods
 * ______________________
 * 
 * +removeLastTab():void +addNewTab():Void +addChangeListener():Void
 * +getTabbedPane():JTabbedPane setNoteLabelText(int, String):Void
 * 
 ******************************************************************************************/

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class ChannelPanels extends JPanel {
    /****************************
     * Private instance variables
     ****************************/
    private final int MAXSIZE = 6;
    private JTabbedPane tabbedPane;
    private JScrollPane[] chScroll;
    private JPanel[] chPanels;
    private JLabel[] noteLabel;
    private JLabel[] iconInstrument; // instrument icon picture
    int size = 0;

    /*********************************
     * 
     * Name: ChannelPanels
     * Description: Class Constructor
     * 
     ********************************/
    public ChannelPanels() {

        int i = size;

        tabbedPane = new JTabbedPane();
        chPanels = new JPanel[MAXSIZE];
        chScroll = new JScrollPane[MAXSIZE];
        noteLabel = new JLabel[MAXSIZE];
        iconInstrument = new JLabel[MAXSIZE];

        tabbedPane.setPreferredSize(new Dimension(1050, 200));

        /***********************************
         * For-loop for creating the buttons
         ***********************************/

        noteLabel[i] = new JLabel("Channel " + (i + 1) + " Notes will be displayed here.");
        chPanels[i] = new JPanel();
        chPanels[i].setLayout(new BorderLayout());
        chPanels[i].setPreferredSize(new Dimension(6000, 200));
        chPanels[i].setBackground(new Color(144, 45, 65));
        noteLabel[i].setForeground(Color.white);
        noteLabel[i].setFont(new Font("Arial", Font.PLAIN, 26));
        chPanels[i].add(noteLabel[i]);
        chScroll[i] = new JScrollPane(chPanels[i]);
        chScroll[i].getHorizontalScrollBar().setUnitIncrement(25);
        chScroll[i].setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        iconInstrument[i] = new JLabel(new ImageIcon());
        chPanels[i].add(iconInstrument[i], BorderLayout.WEST);
        tabbedPane.add("Channel " + (i + 1), chScroll[i]);
        size++;

        this.setLayout(new BorderLayout());
        tabbedPane.setBackground(new Color(239, 224, 90));

        this.add(tabbedPane);

    }

    /***********************************************
     * 
     * Name: removeLastTab
     * Description: Remove the last tab in the panel
     * 
     ***********************************************/
    public void removeLastTab() {

        size--;
        tabbedPane.remove(size);

    }

    /*******************************************************************
     * 
     * Name: addNewTab
     * Description:Method for adding new tab and note display to console
     * TODO FIX
     *******************************************************************/
    public void addNewTab() {
        int i = size;
        noteLabel[i] = new JLabel("Channel " + (i + 1) + " Notes will be displayed here.");
        chPanels[i] = new JPanel();
        chPanels[i].setLayout(new BorderLayout());
        chPanels[i].setPreferredSize(new Dimension(6000, 200));
        chPanels[i].setBackground(new Color(144, 45, 65));
        noteLabel[i].setForeground(Color.white);
        noteLabel[i].setFont(new Font("Arial", Font.PLAIN, 26));
        chPanels[i].add(noteLabel[i]);
        chScroll[i] = new JScrollPane(chPanels[i]);
        chScroll[i].getHorizontalScrollBar().setUnitIncrement(25);
        chScroll[i].setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        iconInstrument[i] = new JLabel(new ImageIcon());
        chPanels[i].add(iconInstrument[i], BorderLayout.WEST);
        size++;
        tabbedPane.add("Channel " + (i + 1), chScroll[i]);
        tabbedPane.setSelectedIndex(size - 1);
        // this.add(tabbedPane);
    }

    /*********************************
     * 
     * Name: addChangeListener
     * Description: Add change listner
     * 
     ********************************/
    public void addChangeListener(ChangeListener c) {
        tabbedPane.addChangeListener(c);
    }

    /********************************
     * 
     * Name: getTabbedPane
     * Description: get tabbed pane
     * 
     * @return tabbedPane
     * 
     ********************************/
    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    /**********************************************
     * 
     * Name: setNoteLabelText
     * Description: Sets the settings for the notes
     * 
     *********************************************/
    public void setNoteLabelText(int channel, MusicNotesSets musicNotesSets, ChannelValues channelValues) {
        // update note sequence text
        noteLabel[channel].setText(musicNotesSets.noteSequence());
        // converts channel's instrument to image filename string
        String strInstrument = InstrumentEnum.getInstrument(channelValues.getInstrumentCode()).toString().toLowerCase()
                .replace('_', ' ');
        if (strInstrument.equals("synth drum")) {
            strInstrument = "snare drum";
        } else if (strInstrument.equals("taiko drum")) {
            strInstrument = "bongo";
        } else if (strInstrument.equals("slap bass 1")) {
            strInstrument = "slap bass";
        }
        // update icon
        iconInstrument[channel].setIcon(new ImageIcon(getClass().getResource("Icons/" + strInstrument + ".png")));
    }
}
