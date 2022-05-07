
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RestoreMemento implements ActionListener {

    JFrame frame = new JFrame();
    JLabel label = new JLabel("Restore previous session?");
    JFileChooser fileChooser;
    // JLabel img = new javax.swing.JLabel();
    ImageIcon img = new ImageIcon(getClass().getResource("/Icons/exit_entry.png"));
    JLabel pic = new JLabel("testing");

    JButton yes = new JButton("yes");
    JButton no = new JButton("no");

    MusicNotesConvertor convert = new MusicNotesConvertor();
    ArrayList<ChannelValues> cV;
    ArrayList<MusicNotesSets> nS;

    MusicEditorFrame mF;

    public RestoreMemento(MusicEditorFrame meF) {

        this.mF = meF;

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

        pic.setIcon(img);
        pic.setVisible(true);
        pic.setBounds(25, 0, 300, 100);
        pic.setSize(20, 20);

        frame.add(label);
        frame.add(yes);
        frame.add(no);
        frame.add(pic);

        frame.setTitle("Music Editor for SNES Super Mario World");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(320, 250);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yes) {

            mF.restoreMemento();

            // then close only this window
            frame.dispose();
        } else {
            // simply close
            frame.dispose();
        }

    }
}