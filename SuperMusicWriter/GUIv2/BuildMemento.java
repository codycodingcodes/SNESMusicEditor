
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BuildMemento implements ActionListener {

    MusicNotesConvertor convert = new MusicNotesConvertor();
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Would you like to save your progress?");
    JFileChooser fileChooser;
    MusicEditorFrame m;
    JButton yes = new JButton("yes");
    JButton no = new JButton("no");

    ArrayList<ChannelValues> cV;
    ArrayList<MusicNotesSets> nS;

    public BuildMemento(ArrayList<ChannelValues> channelValues, ArrayList<MusicNotesSets> noteSets) {

        this.cV = channelValues;
        this.nS = noteSets;

        label.setBounds(25, 0, 300, 100);
        label.setFont(new Font(null, Font.PLAIN, 15));

        yes.setBounds(50, 130, 50, 50);
        yes.setFocusable(false);
        yes.addActionListener(this);
        yes.setSize(60, 30);

        no.setBounds(200, 130, 50, 50);
        no.setFocusable(false);
        no.addActionListener(this);
        no.setSize(60, 30);

        frame.add(label);
        frame.add(yes);
        frame.add(no);

        frame.setTitle("Music Editor for SNES Super Mario World");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 250);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == yes) {

            // save progress
            System.out.println("**saving\n");
            // file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            fileChooser = new JFileChooser();

            /*******************
             * Overwirting a file
             ********************/
            // fileChooser.setSelectedFile(new File("Untitled"));
            String dir = System.getProperty("user.dir");
            File tempFile = new File(dir + "\\SuperMusicWriter\\GUIv2\\Music\\prevSession.txt");

            convert.saveFile(tempFile, cV, nS);
            System.out.println(tempFile);

            // close entire program
            System.exit(0);
        } else {
            // just close entire program
            System.exit(0);
        }

    }
}
