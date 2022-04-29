
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

public class ExitWindow implements ActionListener{
    MusicNotesConvertor convert = new MusicNotesConvertor();
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Would you like to save your progress?");
    JFileChooser fileChooser;
    MusicEditorFrame m;
    JButton yes = new JButton("yes");
    JButton no = new JButton("no");
    public ExitWindow(){
        label.setBounds(25,0,300,100);
        label.setFont(new Font(null,Font.PLAIN,15));

        yes.setBounds(50,130,50,50);
        yes.setFocusable(false);
        yes.addActionListener(this);
        yes.setSize(60,30);

        no.setBounds(200,130,50,50);
        no.setFocusable(false);
        no.addActionListener(this);
        no.setSize(60,30);

        frame.add(label);
        frame.add(yes);
        frame.add(no);

        frame.setTitle("Music Editor for SNES Super Mario World");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300,250);
        frame.setLayout(null);
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        

        
        File file;
        ArrayList<ChannelValues> channelValues;
        ArrayList<MusicNotesSets> noteSets;
        if(e.getSource() == yes){
            
            //so obviously the below commands wont work cause all its doing is a comparison check
            //e.getActionCommand().equals(ConstantDataValues.FILEITEM[1]);
            //m.actionPerformed(e);
            
            //not sure why this wont work
            //e.setSource(ConstantDataValues.FILEITEM[1]);
            //m.actionPerformed(e);


            //so i also tried implementing all the methods in here directly but it wont save anything because im 
            //initializing several things that have saved musicEditorFrame maybe if we add getter functions in
            //MusicEditorFrame I could call them and recieve all the values needed to save the file, just an idea
            //hoping there is a way simpler way to just call the actionPerformed
            
            //save progress
            System.out.println("**saving\n");
            file = new File(fileChooser.getSelectedFile().getAbsolutePath());
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
                channelValues = convert.readMMLChannelValues(file);
                noteSets = convert.returnNoteSets(file, channelValues);
                convert.saveFile(file, channelValues, noteSets);
                System.out.println(file);
            }
            
            //close entire program
            System.exit(0);
        }
        else{
            //just close entire program
            System.exit(0);
        }
        
    }
}
