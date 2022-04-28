import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EntryWindow implements ActionListener{

    JFrame frame = new JFrame();
    JLabel label = new JLabel("Would you like load your saved work?");

    JButton yes = new JButton("yes");
    JButton no = new JButton("no");
    EntryWindow(){
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

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300,250);
        frame.setLayout(null);
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == yes){
            //save progress

            //close entire program
        }
        else{
            //just close entire program
        }
        
    }
}