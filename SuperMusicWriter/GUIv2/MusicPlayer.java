import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class MusicPlayer {

    public MusicPlayer(int totalChannel, Pattern[] pattern, ArrayList<ChannelValues> channelValues,
            ArrayList<MusicNotesSets> noteSets, MusicEditorUtility meU, Player player) {
        ImageIcon playIcon = new ImageIcon(getClass().getResource("Icons/play.png"));
        ImageIcon stopIcon = new ImageIcon(getClass().getResource("Icons/stop.png"));

        JFrame musicPlayer = new JFrame("PLAYER");

        musicPlayer.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        musicPlayer.setLayout(new GridLayout(1, 0, 0, 0));
        musicPlayer.setSize(new Dimension(230, 130));

        JButton play = new JButton();
        play.setBackground(new Color(58, 216, 100));
        play.setIcon(playIcon);

        JButton stop = new JButton();
        stop.setBackground(new Color(213, 72, 72));
        stop.setIcon(stopIcon);

        musicPlayer.add(play);
        musicPlayer.add(stop);

        musicPlayer.setVisible(true);

        play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                playMusic(totalChannel, pattern, channelValues, noteSets, meU, player);
            }
        });

        stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                player.play("R");
            }
        });
    }

    public static void playMusic(int totalChannel, Pattern[] pattern, ArrayList<ChannelValues> channelValues,
            ArrayList<MusicNotesSets> noteSets, MusicEditorUtility meU, Player player) {
        for (int i = 0; i < totalChannel; i++) {
            pattern[i] = null;
            pattern[i] = new Pattern("V" + channelValues.get(i).getChannel() + " I[" + meU.getInstrument().get(i)
                    + "] " + noteSets.get(i).noteSequence());
        }
        new Thread(new Runnable() {
            /**************************************
             * Setting notes based on channel values
             **************************************/
            @Override
            public void run() {

                if (totalChannel == 1) {
                    player.play(pattern[0].repeat(Integer.parseInt(channelValues.get(0).getRepeatValue()))
                            .setTempo(Integer.parseInt(channelValues.get(0).getTempo())));
                }

                else if (totalChannel == 2) {

                    player.play(
                            pattern[0].repeat(Integer.parseInt(channelValues.get(0).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(0).getTempo())),

                            pattern[1].repeat(Integer.parseInt(channelValues.get(1).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(1).getTempo())));
                }

                else if (totalChannel == 3) {

                    player.play(
                            pattern[0].repeat(Integer.parseInt(channelValues.get(0).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(0).getTempo())),

                            pattern[1].repeat(Integer.parseInt(channelValues.get(1).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(1).getTempo())),

                            pattern[2].repeat(Integer.parseInt(channelValues.get(2).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(2).getTempo())));

                } else if (totalChannel == 4) {
                    player.play(
                            pattern[0].repeat(Integer.parseInt(channelValues.get(0).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(0).getTempo())),

                            pattern[1].repeat(Integer.parseInt(channelValues.get(1).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(1).getTempo())),

                            pattern[2].repeat(Integer.parseInt(channelValues.get(2).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(2).getTempo())),

                            pattern[3].repeat(Integer.parseInt(channelValues.get(3).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(3).getTempo())));
                }

                else if (totalChannel == 5) {
                    player.play(
                            pattern[0].repeat(Integer.parseInt(channelValues.get(0).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(0).getTempo())),

                            pattern[1].repeat(Integer.parseInt(channelValues.get(1).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(1).getTempo())),

                            pattern[2].repeat(Integer.parseInt(channelValues.get(2).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(2).getTempo())),

                            pattern[3].repeat(Integer.parseInt(channelValues.get(3).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(3).getTempo())),

                            pattern[4].repeat(Integer.parseInt(channelValues.get(4).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(4).getTempo())));
                }

                else if (totalChannel == 6) {
                    player.play(
                            pattern[0].repeat(Integer.parseInt(channelValues.get(0).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(0).getTempo())),

                            pattern[1].repeat(Integer.parseInt(channelValues.get(1).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(1).getTempo())),

                            pattern[2].repeat(Integer.parseInt(channelValues.get(2).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(2).getTempo())),

                            pattern[3].repeat(Integer.parseInt(channelValues.get(3).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(3).getTempo())),

                            pattern[4].repeat(Integer.parseInt(channelValues.get(4).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(4).getTempo())),

                            pattern[5].repeat(Integer.parseInt(channelValues.get(5).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(5).getTempo())));
                }

                else if (totalChannel == 7) {
                    player.play(
                            pattern[0].repeat(Integer.parseInt(channelValues.get(0).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(0).getTempo())),

                            pattern[1].repeat(Integer.parseInt(channelValues.get(1).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(1).getTempo())),

                            pattern[2].repeat(Integer.parseInt(channelValues.get(2).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(2).getTempo())),

                            pattern[3].repeat(Integer.parseInt(channelValues.get(3).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(3).getTempo())),

                            pattern[4].repeat(Integer.parseInt(channelValues.get(4).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(4).getTempo())),

                            pattern[5].repeat(Integer.parseInt(channelValues.get(5).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(5).getTempo())),

                            pattern[6].repeat(Integer.parseInt(channelValues.get(6).getRepeatValue()))
                                    .setTempo(Integer.parseInt(channelValues.get(6).getTempo())));
                }

            }
        }).start();
    }
}
