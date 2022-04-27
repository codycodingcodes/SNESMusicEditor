package SetUp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class UserManual {

    public UserManual() throws IOException {
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
}
