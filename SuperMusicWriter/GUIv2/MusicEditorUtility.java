
/******************************************************************************************
 * 
 * Programmer Name: Team 5
 * 
 * Class Name: MusicEditorUtility
 *
 ******************************************************************************************
 * 
 * Description: This class creates the User Interface for the program
 * 
 * ****************************************************************************************
 * 
 * Custom Defined Methods
 * ______________________
 * 
 * +actionperformed(ActionEvent):Void -userManual():Void -noteInterfacePanel(JFrame):void
 * -setUpMenuBar(JFrame):Void -setUpButtonsInstrument(JFrame):void 
 * -setUpMusicNotesButtonsControls(JPanel):Void -setUpOctaveControls(JPanel):Void
 * -setUpLengthAndLoopControls(JPanel):Void -setUpTempoVolumeControl(JPanel):Void
 * -setUpVolumeControls(JPanel):Void -setUpRestUndoControl(JPanel):void
 * -setUnstrument(String, int): Void -getInstrument<String> -setChannel(int):Void
 * -getChannel():int
 * 
 ******************************************************************************************/

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.metal.MetalToggleButtonUI;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MusicEditorUtility extends JFrame implements ActionListener, MusicEditorIF {

    /****************************
     * Private instance variables
     ****************************/
    private static final long serialVersionUID = 1L;
    private ArrayList<String> musicInstrumentUsed;
    private ArrayList<MusicNotesSets> noteSets;
    private ArrayList<ChannelValues> channelValues;
    private final String[] NOTES = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
    private String octaveLevel;
    private Pattern[] pattern;
    private JSlider octSlider;
    private Player player;// used for the playback
    private int currentChannel;
    private int totalChannel;
    JComboBox<String> lengthMenu;
    private String note;
    JSlider volumeSlider;
    JSlider tempoSlider;

    private ChannelPanels chPanels;

    /*********************************
     * 
     * Name: MusicEditorUI
     * Description: Class Constructor
     * 
     *********************************/
    public MusicEditorUtility() {

        /*****************************
         * Defining instance variables
         *****************************/
        player = new Player();
        pattern = new Pattern[7];
        pattern[currentChannel] = new Pattern(" ");
        musicInstrumentUsed = new ArrayList<String>();

        currentChannel = 0;
        totalChannel = 1;
        channelValues = new ArrayList<ChannelValues>();
        channelValues.add(new ChannelValues("0", "255", "50", InstrumentEnum.PIANO.VALUE, "125", "4", "1", false));
        noteSets = new ArrayList<MusicNotesSets>();
        noteSets.add(new MusicNotesSets());

        octaveLevel = "5";
        musicInstrumentUsed.add(new String("PIANO"));
        octSlider = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
        lengthMenu = new JComboBox<String>(ConstantDataValues.noteLength);

        chPanels = new ChannelPanels();

        /*****************************
         * Set up the JFrame Properties
         *****************************/
        this.setTitle(ConstantDataValues.FRAMETITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(ConstantDataValues.FRAMEWIDTH, ConstantDataValues.FRAMEHEIGHT);

        /***************************************
         * sets up the menu bar and its functions
         ***************************************/
        setUpMenuBar(this);

        /*******************************
         * Sets up the instrument buttons
         ********************************/
        setUpButtonsInstrument(this);

        /*************************************
         * Sets up the editor and notes buttons
         *************************************/
        noteInterfacePanel(this);

        this.setVisible(true);

        chPanels.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO
                JTabbedPane paneIndex = (JTabbedPane) e.getSource();
                currentChannel = paneIndex.getSelectedIndex();
                System.out.println("Current:" + currentChannel);

                // octSlider.setValue(Integer.parseInt(channelValues.get(currentChannel).getOctaveLevel()));
                // volumeSlider.setValue(Integer.parseInt(channelValues.get(currentChannel).getMasterVolume()));
                // tempoSlider.setValue(Integer.parseInt(channelValues.get(currentChannel).getTempo()));

            }
        });

        chPanels.setNoteLabelText(currentChannel, noteSets.get(currentChannel), channelValues.get(currentChannel));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /****************
         * Local variables
         *****************/
        MusicNotesConvertor convert = new MusicNotesConvertor();
        File file;
        JFileChooser fileChooser;

        /*****************************
         * Load a file/item
         *****************************/
        if (e.getActionCommand().equals(ConstantDataValues.FILEITEM[0])) {
            System.out.println("**loading\n");

            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "GUIv2" + System.getProperty("file.separator") + "Music"));

            // fileChooser.setCurrentDirectory(new File(".\\Music"));
            fileChooser.setFileFilter(new FileFilter() {

                @Override
                public boolean accept(File f) {

                    if (f.isDirectory())
                        return true;
                    else
                        return f.getName().toLowerCase().endsWith(".txt");
                }

                @Override
                public String getDescription() {

                    /*****************************
                     * Adds a .txt filter
                     *****************************/
                    return "Text File (*.txt)";
                }
            });

            int response = fileChooser.showOpenDialog(null); // select file to open

            if (response == JFileChooser.APPROVE_OPTION) {
                ArrayList<String> n = new ArrayList<String>();
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file.getAbsolutePath());
                // TODO load from filea mml fortmat .txt file

                channelValues = convert.readMMLChannelValues(file.getName());
                System.out.println("before notes");
                noteSets = convert.returnNoteSets(file.getName(), channelValues);
                System.out.println("after notes");

                // convert.readMMLChannelValues(file.getName(),noteSets,channelValues);
                // System.out.println("Printing in UI:"+noteSets.get(0).noteSequence());
                for (int i = 0; i < channelValues.size(); i++) {
                    if (i == 0) {
                        for (int j = totalChannel; j > 1; j--) {
                            chPanels.removeLastTab();
                            totalChannel--;
                        }
                        octSlider.setValue(Integer.parseInt(channelValues.get(i).getOctaveLevel()));
                        volumeSlider.setValue(Integer.parseInt(channelValues.get(i).getMasterVolume()));
                        tempoSlider.setValue(Integer.parseInt(channelValues.get(i).getTempo()));

                    } else {
                        chPanels.addNewTab();
                        octSlider.setValue(Integer.parseInt(channelValues.get(i).getOctaveLevel()));
                        volumeSlider.setValue(Integer.parseInt(channelValues.get(i).getMasterVolume()));
                        tempoSlider.setValue(Integer.parseInt(channelValues.get(i).getTempo()));

                    }
                    chPanels.setNoteLabelText(i, noteSets.get(i), channelValues.get(i));

                }

            }

        }

        /***********************************
         * Saves the selected notes as a file
         ***********************************/
        else if (e.getActionCommand().equals(ConstantDataValues.FILEITEM[1])) {
            System.out.println("**saving\n");
            fileChooser = new JFileChooser();

            /*******************
             * Overwirting a file
             ********************/
            fileChooser.setSelectedFile(new File("Untitled"));
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "GUIv2" + System.getProperty("file.separator") + "Music"));

            fileChooser.setFileFilter(new FileFilter() {

                @Override
                public boolean accept(File f) {
                    if (f.isDirectory())
                        return true;
                    else
                        return f.getName().toLowerCase().endsWith(".txt");
                }

                @Override
                public String getDescription() {

                    /********************
                     * Adds a .txt filter
                     ********************/
                    return "Text File (*.txt)";
                }
            });

            /**************************
             * Selecting a file to open
             **************************/
            int response = fileChooser.showSaveDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {

                file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

                convert.saveFile(file, channelValues, noteSets);
                System.out.println(file);
            }
        }

        /*****************************
         * TODO export to addmusic
         * TODO ask user for addmusick directory
         * TODO ,jar support
         *****************************/

        else if (e.getActionCommand().equals(ConstantDataValues.FILEITEM[2])) {
            System.out.println("**exporting\n");
            String dir = System.getProperty("user.dir");// find directory of our program
            String exportName = (String) JOptionPane.showInputDialog("Enter the name you wish to export the song as"); // ask
                                                                                                                       // for
                                                                                                                       // user
                                                                                                                       // input
                                                                                                                       // for
                                                                                                                       // name
            File tempFile = new File(dir + "\\AddmusicK_1.0.8\\music\\" + exportName + ".txt");// create temp .txt file
                                                                                               // for addmusic in
                                                                                               // addmusick folder
            convert.saveFile(tempFile, channelValues, noteSets);// convert from file from jfugue to mml
            ProcessBuilder pbAddMusicK = new ProcessBuilder(dir + "\\AddmusicK_1.0.8\\AddmusicK.exe", "-norom",
                    exportName + ".txt");//
            pbAddMusicK.inheritIO();// redirect AddMusicK terminal output to java console
            pbAddMusicK.directory(new File(dir + "\\AddmusicK_1.0.8"));// set AddmusicK working directory to its actual
                                                                       // one
            try {
                Process proAddMusicK = pbAddMusicK.start();// run AddMusick
                proAddMusicK.waitFor();// wait for AddMusicK to be done
                System.out.println("SPC File is in " + dir + "\\AddmusicK_1.0.8\\SPCs\\");
            } catch (IOException e1) {
                System.out.println("AddMusick.exe not found");
            } catch (InterruptedException e1) {
                System.out.println("AddMusick.exe proccess error");
            }
            tempFile.delete();// delete temp .txt
        }

        // OPENS MUSIC PLAYER
        // opens a frame to play
        else if (e.getActionCommand().equals(ConstantDataValues.PLAYITEM[0])) {
            musicPlayer();
        }

        // HELP MANUAL
        else if (e.getActionCommand().equals(ConstantDataValues.HELPITEM[0])) {
            try {
                userManual();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                System.out.println("No file found.");
            }
        }
        /***************************
         * Limits the octave controls
         ***************************/
        else if (e.getActionCommand().equals(">>")) {
            if (Integer.parseInt(this.octaveLevel) < ConstantDataValues.MAXOCTLEVEL) {
                this.octaveLevel = String.valueOf(Integer.parseInt(this.octaveLevel) + 1);
                this.octSlider.setValue(Integer.valueOf(this.octaveLevel));
            } else {
                System.out.println("Sorry is at limit:" + this.octaveLevel);
            }
        } else if (e.getActionCommand().equals("<<")) {
            if (Integer.parseInt(this.octaveLevel) > ConstantDataValues.MINOCTLEVEL) {
                this.octaveLevel = String.valueOf(Integer.parseInt(this.octaveLevel) - 1);
                this.octSlider.setValue(Integer.valueOf(this.octaveLevel));
            } else {
                System.out.println("Sorry is at limit:" + this.octaveLevel);
            }
        }

        /*****************************************************************************
         * 
         * 
         * THIS RIGHT HERE IS THE TEST CASES MENU BUTTONS RIGHT NOW WE CAN ADD AND
         * REMOVE CHANNELS BUT WE CANNOT JUMP TO A SPECIFIC CHANNEL YET. ALSO ATM NEEDS
         * TO BE WORKED ON ADJUSTING CHANNEL VALUES ACCORDINGLY. ALSO LOTS OF OTHER
         * SMALL CAVEOT NEEDS TO BE WORKED ON.
         * 
         * 
         * 
         ******************************************************************************/

        /************************************************
         * Plays back the music notes based on the channel
         ************************************************/
        else if (e.getActionCommand().equals(ConstantDataValues.CHANITEM[2])) {

            // pattern[currentChannel] = null;
            pattern[currentChannel] = new Pattern("V" + currentChannel + " I[" + getInstrument().get(getChannel())
                    + "] " + noteSets.get(currentChannel).noteSequence() + " ");

            new Thread(new Runnable() {

                @Override
                public void run() {
                    player.play(pattern[currentChannel]
                            .repeat(Integer.parseInt(channelValues.get(currentChannel).getRepeatValue()))
                            .setTempo(Integer.parseInt(channelValues.get(currentChannel).getTempo())));

                }
            }).start();

        }
        /******************************
         * Remove the last added channel
         ******************************/
        else if (e.getActionCommand().equals(ConstantDataValues.CHANITEM[1])) {
            if (totalChannel > 1) {
                totalChannel -= 1;

                noteSets.remove(currentChannel);
                channelValues.remove(currentChannel);
                musicInstrumentUsed.remove(currentChannel);
                chPanels.removeLastTab();
                // setChannel(getChannel() - 1);
                System.out.println("Last add channel removed");

            } else {
                System.out.println("Cannot remove anymore channels");
            }
        }

        /*********************************************************************
         * Adds to the end of the channel and sets it to be the current channel
         **********************************************************************/
        else if (e.getActionCommand().equals(ConstantDataValues.CHANITEM[0])) {
            if (totalChannel < 6) {
                totalChannel++;
                setChannel(getChannel() + 1);
                chPanels.addNewTab();
                pattern[getChannel()] = new Pattern(" ");

                noteSets.add(new MusicNotesSets());
                channelValues.add(new ChannelValues(String.valueOf(getChannel())));

                musicInstrumentUsed.add(new String(InstrumentEnum.PIANO.toString()));

                octSlider.setValue(Integer.parseInt(channelValues.get(currentChannel).getOctaveLevel()));
                volumeSlider.setValue(Integer.parseInt(channelValues.get(currentChannel).getMasterVolume()));
                tempoSlider.setValue(Integer.parseInt(channelValues.get(currentChannel).getTempo()));

                System.out.println("Another channel added");
                // chPanels.setNoteLabelText(currentChannel,
                // noteSets.get(currentChannel),channelValues.get(currentChannel));
            } else {
                System.out.println("Cannot add anymore channels");
            }
        }

        else if (e.getActionCommand().equals(ConstantDataValues.CHANITEM[4])) {

            // playAll();

        }

    }

    public void musicPlayer() {
        new MusicPlayer(totalChannel, pattern, channelValues, noteSets, this, player);
    }

    /***************************************************
     * 
     * Name: userManual
     * Description: Showcase the user manual to the user
     * 
     ***************************************************/
    private void userManual() throws IOException {
        BufferedReader br;

        try {
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("UserManual.txt")));
            StringBuilder fileText = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                fileText.append(text);
                fileText.append(System.lineSeparator());
            }
            // ConstantDataValues.playRickAshley();
            JOptionPane.showMessageDialog(null, fileText, "User Manual", JOptionPane.INFORMATION_MESSAGE);
            br.close();
            // player.play("R");
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /***********************************************
     * 
     * Name: noteInterFacePanel
     * Description: Creates the note interface panel
     * 
     * @param JFrame
     * 
     ************************************************/
    private void noteInterfacePanel(JFrame frame) {

        /******************
         * Local Variables
         ******************/

        /****************************
         * Display the selected notes
         ***************************/
        JPanel editorPanel = new JPanel();

        /*******************************
         * Controller panel for selection
         *******************************/
        JPanel controllerPanel = new JPanel();
        JPanel panel = new JPanel();
        /************
         * Image icons
         *************/
        ImageIcon headerIcon = new ImageIcon(getClass().getResource("Icons/SMW2.png"));
        JLabel headerLabel = new JLabel(headerIcon, SwingConstants.CENTER);
        JPanel noteAttributePanel = new JPanel(); // note modifiers

        /**********************************************
         * Bottom right corner icon for a little pizzazz
         *********************************************/
        JLabel iconLabel = new JLabel(new ImageIcon(getClass().getResource("Icons/mario.png")));

        /*******************************************
         * Sets up the panel we'll use for the layout
         ******************************************/
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BorderLayout());

        /**********************************
         * Sets up the layout for the editor
         *********************************/
        editorPanel.setLayout(new BorderLayout());
        editorPanel.setBorder(new LineBorder(Color.BLACK, 1));
        editorPanel.add(headerLabel, BorderLayout.NORTH);
        editorPanel.setPreferredSize(new Dimension(1000, 320));
        editorPanel.add(chPanels, BorderLayout.CENTER);

        /*******************************
         * Controls the note attributes
         *******************************/
        noteAttributePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 1));
        noteAttributePanel.setBackground(ConstantDataValues.controllerPanelColor);
        // noteAttributePanel.setPreferredSize(new Dimension(1050,1000));
        // noteAttributePanel.setBorder(new LineBorder(Color.RED,6));
        setUpOctaveControls(noteAttributePanel);
        setUpTempoVolumeControl(noteAttributePanel);
        setUpLengthAndLoopControls(noteAttributePanel);
        setUpRestUndoControl(noteAttributePanel);
        noteAttributePanel.add(iconLabel);

        /*******************************
         * Whole bottom controller panel
         *******************************/
        controllerPanel.setLayout(new BorderLayout(5, 5));

        controllerPanel.add(noteAttributePanel, BorderLayout.CENTER);
        controllerPanel.setBackground(ConstantDataValues.controllerPanelColor);
        controllerPanel.setPreferredSize(new Dimension(1000, 380));

        setUpMusicNotesButtonsControls(controllerPanel);

        /**********************************************
         * Adding all the other panels to the main panel
         **********************************************/
        panel.add(editorPanel, BorderLayout.NORTH);
        panel.add(controllerPanel, BorderLayout.CENTER);

        frame.add(panel, BorderLayout.CENTER);

    }

    /*****************************************
     * 
     * Name: setUpMenuBar
     * Description: Creates the menu bar panel
     * 
     * @param JFrame
     * 
     *******************************************/
    private void setUpMenuBar(JFrame frame) {

        /****************
         * Local Variables
         ****************/
        /*****************************
         * Used to set up the menu bar
         *****************************/
        JMenuBar menuBar = new JMenuBar();
        JMenu[] menuList = new JMenu[ConstantDataValues.MENUNAME.length];
        JMenuItem[] menu = new JMenuItem[ConstantDataValues.MAXMENUSIZE];
        String[] tempItem = null;
        int[] tempKey = null;

        /*******************************
         * Loop through the menu bar
         *******************************/
        for (int i = 0; i < ConstantDataValues.MENUNAME.length; i++) {
            tempItem = null;
            tempKey = null;

            /*****************************
             * Sets the name of the menubar
             *****************************/
            menuList[i] = new JMenu(ConstantDataValues.MENUNAME[i]);
            /***********************
             * Sets the shortcut key
             ************************/
            menuList[i].setMnemonic(ConstantDataValues.MENUBARSHORTCUT[i]);

            /*********************
             * Defines the menu bar
             *********************/
            if (i == 0) {
                tempItem = ConstantDataValues.FILEITEM;
                tempKey = ConstantDataValues.FILEITEMSHORTCUT;
            } else if (i == 1) {
                tempItem = ConstantDataValues.HELPITEM;
                tempKey = ConstantDataValues.HELPITEMSHORTCUT;

            } else if (i == 2) {
                tempItem = ConstantDataValues.CHANITEM;
                tempKey = ConstantDataValues.CHANSHORTCUT;
            } else if (i == 3) {
                tempItem = ConstantDataValues.PLAYITEM;
                tempKey = ConstantDataValues.PLAYITEMSHORTCUT;
            }

            for (int j = 0; j < tempItem.length; j++) {

                menu[j] = new JMenuItem(tempItem[j]);
                menu[j].addActionListener(this);
                if (i == 0)
                    menu[j].setIcon(
                            new ImageIcon(getClass().getResource("Icons/" + tempItem[j].toLowerCase() + ".png")));
                menu[j].setMnemonic(tempKey[j]);

                menuList[i].add(menu[j]);

            }
            menuBar.add(menuList[i]);

            frame.setJMenuBar(menuBar);

        }
    }

    /**************************************************************
     * 
     * Name: setUpButtonsInstrument
     * Description: Creates the frame to hold the instrument buttons
     * 
     * @param JFrame
     * 
     ***************************************************************/
    private void setUpButtonsInstrument(JFrame frame) {

        /****************
         * Local Variables
         ****************/
        BufferedReader readTxt;
        String instrumentButtonText = new String();
        int index = 0;
        ArrayList<CustomButtonHelper> musicButtons = new ArrayList<CustomButtonHelper>();
        /********************************
         * Used for the instrument buttons
         ********************************/
        JPanel instrument = new JPanel();
        JScrollPane instrumentScroll = new JScrollPane(instrument);

        instrument.setLayout(new GridLayout(0, 1));

        try {
            readTxt = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("InstrumentStuff.txt")));
            while ((instrumentButtonText = readTxt.readLine()) != null) {

                musicButtons.add(new CustomButtonHelper(ConstantDataValues.BUTTONINSTRUMENTSIZE, instrumentButtonText,
                        JButton.TOP, JButton.CENTER, true));
                instrument.add(musicButtons.get(index));
                index++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < musicButtons.size(); i++) {
            musicButtons.get(i).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    /****************
                     * Local Variables
                     ****************/

                    String newInstrument = new String();
                    int index = 0;

                    /*************************************************
                     * Loop through the music buttons to get the index
                     * of the instrument to get the defined code
                     **************************************************/
                    for (int i = 0; i < musicButtons.size(); i++) {
                        // gets the index of the instrument which will help us get the instrument code
                        if (e.getActionCommand().equals(musicButtons.get(i).getInstrumentName())) {
                            index = i;
                            break;
                        }
                    }

                    System.out.println(musicButtons.get(index).getInstrumentCode());
                    // ASSIGN THE INSTRUMENT CODE
                    newInstrument = InstrumentEnum.getInstrument(musicButtons.get(index).getInstrumentCode())
                            .toString();
                    System.out.println(newInstrument);
                    channelValues.get(currentChannel).setInstrumentCode(musicButtons.get(index).getInstrumentCode());
                    setInstrument(newInstrument, getChannel());

                    chPanels.setNoteLabelText(currentChannel, noteSets.get(currentChannel),
                            channelValues.get(currentChannel));
                }

            });
        }

        instrumentScroll.getVerticalScrollBar().setUnitIncrement(25);
        instrumentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(instrumentScroll, BorderLayout.WEST);
    }

    /****************************************************************
     * 
     * Name: setUpMusicNotesButtonsControls
     * Description: Sets up the panel for the musica note buttons
     * 
     * @param JPanel
     * 
     ***************************************************************/
    private void setUpMusicNotesButtonsControls(JPanel panel) {

        CustomButtonHelper[] notes = new CustomButtonHelper[NOTES.length];
        MusicNotesConvertor convert = new MusicNotesConvertor();
        /*****************
         * Note key buttons
         *****************/
        JPanel noteKeysPanel = new JPanel();

        /*********************
         * BEGIN NOTE KEY PANEL
         *********************/
        note = "";
        // here is where we create the music note buttons
        for (int i = 0; i < NOTES.length; i++) {
            notes[i] = new CustomButtonHelper(ConstantDataValues.BUTTONSMUSICNOTES, NOTES[i], JButton.CENTER,
                    JButton.CENTER, false);

        }

        noteKeysPanel.setBackground(Color.BLACK);
        noteKeysPanel.setLayout(new GridLayout(1, 0, 2, 1));

        // here is where we add the music note buttons to the noteKeysPanel
        for (int i = 0; i < NOTES.length; i++) {
            noteKeysPanel.add(notes[i]);

            notes[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    new Thread(new Runnable() {

                        @Override
                        public void run() {

                            note = e.getActionCommand() + octaveLevel
                                    + convert.convertNoteToJFugue((String) lengthMenu.getSelectedItem());
                            System.out.println(note);
                            noteSets.get(currentChannel).add(note);
                            chPanels.setNoteLabelText(currentChannel, noteSets.get(currentChannel),
                                    channelValues.get(currentChannel));
                            player.play("T30" + " I["
                                    + getInstrument().get(getChannel()) + "] " + e.getActionCommand() + octaveLevel);

                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                    }).start();

                }
            });
            // notes[i].setFont(new Font("Arial", Font.PLAIN, 36));
        }
        /*********************
         * END NOTE KEY PANEL
         *********************/
        panel.add(noteKeysPanel, BorderLayout.NORTH);
    }

    /**********************************************************
     * 
     * Name: setUpOctaveControls
     * Description: Creates a panel for controlling the octaves
     * 
     * @param JPanel
     * 
     ***********************************************************/
    private void setUpOctaveControls(JPanel panel) {
        /************************
         * BEGIN OCTAVE NOTE PANEL
         ************************/
        CustomButtonHelper octIncrease = new CustomButtonHelper(">>", new Dimension(95, 95));
        CustomButtonHelper octDecrease = new CustomButtonHelper("<<", new Dimension(95, 95));
        JPanel octChoice = new JPanel();
        JLabel octLabel = new JLabel("Octave Controls", SwingConstants.CENTER);
        JPanel octavePanel = new JPanel();

        octLabel.setForeground(Color.white);

        // octIncrease.setBackground(ConstantDataValues.modifierColor);

        // octDecrease.setBackground(ConstantDataValues.modifierColor);

        octavePanel.setLayout(new BorderLayout());
        octavePanel.add(octLabel, BorderLayout.NORTH);
        octavePanel.add(octIncrease, BorderLayout.EAST);
        octavePanel.add(octDecrease, BorderLayout.WEST);
        octavePanel.setBorder(new LineBorder(Color.BLACK, 1));
        octavePanel.setBackground(ConstantDataValues.menuLabelColor);

        // octavePanel.setPreferredSize(new Dimension(200, 80));

        octChoice.setBackground(Color.WHITE);
        octChoice.setPreferredSize(new Dimension(190, 130));
        octIncrease.addActionListener(this);
        octDecrease.addActionListener(this);

        octSlider.setPreferredSize(new Dimension(180, 100));
        octSlider.setMajorTickSpacing(1);
        octSlider.setPaintTicks(true);
        octSlider.setPaintLabels(true);
        octSlider.setValue(Integer.parseInt(this.octaveLevel));
        octSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                octaveLevel = String.valueOf(octSlider.getValue());
                if (noteSets.get(currentChannel).isEmpty()) {
                    channelValues.get(currentChannel).setOctaveLevel(octaveLevel);
                }
                System.out.println(octaveLevel);
            }
        });
        octChoice.add(octSlider);
        octavePanel.add(octChoice, BorderLayout.SOUTH);
        /**********************
         * END OCTAVE NOTE PANEL
         **********************/
        panel.add(octavePanel);
    }

    /**********************************************************
     * 
     * Name: setUpLengthAndLoopControls
     * Description: Creates a panel for note length and looping
     * 
     * @param JPanel
     * 
     ***********************************************************/
    private void setUpLengthAndLoopControls(JPanel panel) {

        /********************************
         * Note length and loop variables
         *******************************/
        JPanel lengthLoopPanel = new JPanel();
        JPanel lengthPanel = new JPanel();
        JPanel loopPanel = new JPanel();
        JPanel lengthMenuPanel = new JPanel();
        JLabel lengthLabel = new JLabel("Note Length", SwingConstants.CENTER);
        JLabel loopLabel = new JLabel("Loop Controls", SwingConstants.CENTER);
        JButton loopButton = new JButton("Loop");

        /***********************************
         * Note Length and Loop Panel
         * Layout for the length and loop panel
         ***********************************/
        lengthPanel.setLayout(new BorderLayout());
        loopPanel.setLayout(new BorderLayout());
        lengthMenuPanel.setLayout(new GridBagLayout());
        lengthLoopPanel.setLayout(new GridLayout(0, 1, 0, 0));

        // font for length combo box
        lengthMenu.setFont(ConstantDataValues.noteLengthFont);

        // font for loop button
        loopButton.setFont(ConstantDataValues.getCustomFont());

        // loop background color
        loopButton.setBackground(ConstantDataValues.modifierColor);

        // actionlistener for loop button
        loopButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                channelValues.get(currentChannel).setRepeateValue(
                        JOptionPane.showInputDialog("How many times would you like to repeat this loop?"));
            }
        });

        /*****************************
         * Length and loop menu details
         *****************************/
        lengthMenu.setPreferredSize(new Dimension(60, 50));
        lengthMenu.setSelectedIndex(3);
        lengthLoopPanel.setPreferredSize(new Dimension(200, 243));

        /*******************
         * Background colors
         ********************/
        lengthMenuPanel.setBackground(ConstantDataValues.modifierColor);
        lengthPanel.setBackground(ConstantDataValues.menuLabelColor);
        loopPanel.setBackground(ConstantDataValues.menuLabelColor);

        /************
         * Text colors
         *************/
        lengthLabel.setForeground(Color.WHITE);
        loopLabel.setForeground(Color.WHITE);

        /*********************************
         * Add objects to appropriate panel
         *********************************/
        lengthMenuPanel.add(lengthMenu);
        lengthPanel.add(lengthLabel, BorderLayout.NORTH);
        lengthPanel.add(lengthMenuPanel, BorderLayout.CENTER);
        loopPanel.add(loopLabel, BorderLayout.NORTH);
        loopPanel.add(loopButton);
        lengthLoopPanel.add(lengthPanel);
        lengthLoopPanel.add(loopPanel);
        lengthLoopPanel.setBorder(new LineBorder(Color.BLACK, 1));
        /////// END NOTE LOOP AND LENGTH PANEL /////////////////////

        panel.add(lengthLoopPanel);
    }

    /**********************************************************
     * 
     * Name: setUpTempoVolumeControl
     * Description: Creates a panel for tempo and volume controls
     * 
     * @param JPanel
     * 
     ***********************************************************/
    private void setUpTempoVolumeControl(JPanel panel) {
        ///////////////////////////////

        // LOCAL VARIABLES //

        //////////////////////////////
        JPanel tempoVolPanel = new JPanel();
        JPanel tempoPanel = new JPanel();
        JLabel tempoLabel = new JLabel("Tempo", SwingConstants.CENTER);
        tempoSlider = new JSlider(JSlider.HORIZONTAL, 0, 70, 35);

        /*************************
         * BEGIN TEMPO VOLUME PANEL
         *************************/

        // tempo layout, and background and text color
        tempoPanel.setLayout(new BorderLayout());
        tempoLabel.setForeground(Color.WHITE);
        tempoPanel.setBackground(ConstantDataValues.menuLabelColor);

        // tempo slider details
        tempoSlider.setMajorTickSpacing(10);
        tempoSlider.setMinorTickSpacing(1);
        tempoSlider.setPaintTicks(true);
        tempoSlider.setPaintLabels(true);

        tempoSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent arg0) {
                // TODO Auto-generated method stub
                channelValues.get(currentChannel).setTempo(String.valueOf(tempoSlider.getValue()));
            }
        });

        // add label and slider to tempo panel
        tempoPanel.add(tempoLabel, BorderLayout.NORTH);
        tempoPanel.add(tempoSlider);

        // tempoVolPanel is tempo volume panel
        tempoVolPanel.setLayout(new GridLayout(0, 1, 0, 0));
        tempoVolPanel.setPreferredSize(new Dimension(300, 243));
        // ctvPanel.add(channelPanel);
        setUpVolumeControls(tempoVolPanel);
        // tempoVolPanel.add(volumePanel);
        tempoVolPanel.add(tempoPanel);
        tempoVolPanel.setBorder(new LineBorder(Color.BLACK, 1));
        /*************************
         * BEGIN TEMPO VOLUME PANEL
         *************************/

        panel.add(tempoVolPanel);
    }

    /*************************************************
     * 
     * Name: setUpVolumeControls
     * Description: Creates a panel for volume controls
     * 
     * @param JPanel
     * 
     **************************************************/
    private void setUpVolumeControls(JPanel panel) {

        /*****************
         * Volume Varibles
         *****************/
        JPanel volumePanel = new JPanel();
        JLabel volumeLabel = new JLabel("Volume Control", SwingConstants.CENTER);
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 250, 125);
        JToggleButton volumeSwitch = new JToggleButton("Master Volume");

        /***********************************************
         * Volume panel layout, background and text color
         ***********************************************/
        volumePanel.setLayout(new BorderLayout());
        volumeLabel.setForeground(Color.WHITE);
        volumePanel.setBackground(ConstantDataValues.menuLabelColor);

        /**********************
         * Volume silder details
         **********************/
        volumeSlider.setMajorTickSpacing(50);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);

        /***************************************************
         * Volume switch between master and instrument volume
         ***************************************************/
        volumeSwitch.setBackground(ConstantDataValues.modifierColor);
        volumeSwitch.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return ConstantDataValues.modifierColor;
            }
        });
        volumeSwitch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (volumeSwitch.isSelected()) {
                    volumeSlider.setMaximum(150);
                    volumeSwitch.setText("Instrument Volume");
                    volumeSwitch.setBackground(ConstantDataValues.modifierColor);
                } else {
                    volumeSlider.setMaximum(250);
                    volumeSwitch.setText("Master Volume");
                    volumeSwitch.setBackground(ConstantDataValues.modifierColor);
                }
            }
        });

        volumeSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent arg0) {
                if (volumeSwitch.getText().equals("Instrument Volume")) {
                    channelValues.get(currentChannel).setVolume(String.valueOf(volumeSlider.getValue()));
                } else {
                    channelValues.get(currentChannel).setMasterVolume(String.valueOf(volumeSlider.getValue()));
                }

            }
        });

        /**************************************************************************
         * Add volume label, volume slider, and volume switch botton to volume panel
         **************************************************************************/
        volumePanel.add(volumeLabel, BorderLayout.NORTH);
        volumePanel.add(volumeSlider);
        volumePanel.add(volumeSwitch, BorderLayout.SOUTH);

        panel.add(volumePanel);

    }

    /*************************************************
     * 
     * Name: setUpRestUndoControl
     * Description: Creates a panel for rest and undo functions
     * 
     * @param JPanel
     * 
     **************************************************/
    private void setUpRestUndoControl(JPanel panel) {

        /***************
         * Local Varibles
         ****************/
        MusicNotesConvertor convert = new MusicNotesConvertor();

        /************************
         * Rest and undo variables
         ************************/
        JPanel restUndoPanel = new JPanel();
        JPanel restPanel = new JPanel();
        JPanel undoPanel = new JPanel();
        JLabel restLabel = new JLabel("Rest Control", SwingConstants.CENTER);
        JLabel undoLabel = new JLabel("Undo Control", SwingConstants.CENTER);

        JButton restButton = new JButton("Rest Note");
        JButton undoButton = new JButton("Undo");

        /*************************
         * Rest and undo note panel
         *************************/
        // layout for rest and undo panel
        restPanel.setLayout(new BorderLayout());
        undoPanel.setLayout(new BorderLayout());
        restUndoPanel.setLayout(new GridLayout(0, 1, 0, 0));

        // font for undo and rest buttons
        undoButton.setFont(ConstantDataValues.getCustomFont());
        restButton.setFont(ConstantDataValues.getCustomFont());

        // buttons background color
        undoButton.setBackground(ConstantDataValues.modifierColor);
        restButton.setBackground(ConstantDataValues.modifierColor);

        // panel size
        restUndoPanel.setPreferredSize(new Dimension(200, 243));

        // background colors
        restPanel.setBackground(ConstantDataValues.menuLabelColor);
        undoPanel.setBackground(ConstantDataValues.menuLabelColor);
        // text colors
        restLabel.setForeground(Color.WHITE);
        undoLabel.setForeground(Color.WHITE);

        restButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        note = "R" + octaveLevel
                                + convert.convertNoteToJFugue((String) lengthMenu.getSelectedItem());
                        // System.out.println(convert.convertNoteToJFugue((String)
                        // lengthMenu.getSelectedItem()));
                        noteSets.get(currentChannel).add(note);
                        chPanels.setNoteLabelText(currentChannel, noteSets.get(currentChannel),
                                channelValues.get(currentChannel));
                        player.play("T" + channelValues.get(currentChannel).getTempo() + " I["
                                + getInstrument().get(getChannel()) + "] " + note);

                    }
                }).start();

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        undoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (noteSets.get(currentChannel).getSize() != 0) {// if note set is empty, dont bother it will break
                    noteSets.get(currentChannel).removeAtEnd();
                    chPanels.setNoteLabelText(currentChannel, noteSets.get(currentChannel),
                            channelValues.get(currentChannel));
                }

            }

        });
        // add objects to appropriate panel
        restPanel.add(restLabel, BorderLayout.NORTH);
        restPanel.add(restButton);
        undoPanel.add(undoLabel, BorderLayout.NORTH);
        undoPanel.add(undoButton);
        restUndoPanel.add(restPanel);
        restUndoPanel.add(undoPanel);
        restUndoPanel.setBorder(new LineBorder(Color.BLACK, 1));
        /////// END REST NOTE AND UNDO PANEL /////////////////////

        panel.add(restUndoPanel);
    }

    /******************************************************
     * 
     * Name: setInstrument
     * Description: Select an instrument to write its notes
     * 
     * @param musicInstrmentUsed, @param channel
     * 
     ******************************************************/
    private void setInstrument(String musicInstrumentUsed, int channel) {
        this.musicInstrumentUsed.set(channel, musicInstrumentUsed);
    }

    /******************************************
     * 
     * Name: getInstrument
     * Description: Gets the selected instrument
     * 
     * @return Arraylist<String>
     * 
     ******************************************/
    public ArrayList<String> getInstrument() {
        return this.musicInstrumentUsed;
    }

    /***************************************
     * 
     * Name: setChannel
     * Description: Set the channel selected
     * 
     * @param dim, @param name
     * 
     **************************************/
    private void setChannel(int channel) {
        this.currentChannel = channel;
    }

    /***************************************
     * 
     * Name: getChannel
     * Description: Get the channel selected
     * 
     * @return int
     * 
     **************************************/
    public int getChannel() {
        return currentChannel;
    }

}