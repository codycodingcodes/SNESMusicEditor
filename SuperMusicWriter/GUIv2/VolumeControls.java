
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalToggleButtonUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VolumeControls {

    public VolumeControls() {

    }

    public void setUp(JPanel panel, JSlider volumeSlider, JToggleButton volumeSwitch,
            ArrayList<ChannelValues> channelValues, int currentChannel) {
        /*****************
         * Volume Varibles
         *****************/
        JPanel volumePanel = new JPanel();
        JLabel volumeLabel = new JLabel("Volume Control", SwingConstants.CENTER);

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

}
