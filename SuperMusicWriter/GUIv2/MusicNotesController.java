import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MusicNotesController extends JPanel {
	CustomButtonHelper[] notes;
	private final String[] NOTES = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
	private JLabel headerLabel;

	public MusicNotesController() {
		headerLabel = new JLabel("Music Interface", SwingConstants.CENTER);
		this.setBorder(new LineBorder(Color.BLACK, 1));
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());

		editorControls();
		notesControl();

	}

	private void editorControls() {
		JPanel noteUI = new JPanel();
		noteUI.setBorder(new LineBorder(Color.BLACK, 1));
		noteUI.setPreferredSize(new Dimension(1000, 460));
		noteUI.add(headerLabel);

		this.add(noteUI, BorderLayout.NORTH);
	}

	private void notesControl() {

		///////////////////////////////

		// LOCAL VARIABLE //

		//////////////////////////////

		JPanel noteKeysPanel = new JPanel();
		JPanel controllerPanel = new JPanel();

		////////////////////////////

		notes = new CustomButtonHelper[NOTES.length];

		noteKeysPanel.setBorder(new LineBorder(Color.BLACK, 1));
		noteKeysPanel.setBackground(Color.GRAY);
		noteKeysPanel.setLayout(new GridLayout(1, 0, 2, 1));

		// LOOP FOR SETTING UP THE MUSIC NOTES BUTTON
		for (int i = 0; i < NOTES.length; i++) {
			notes[i] = new CustomButtonHelper(ConstantDataValues.BUTTONSMUSICNOTES, NOTES[i], JButton.CENTER,
					JButton.CENTER, false);
			noteKeysPanel.add(notes[i]);
			notes[i].setFont(new Font("Arial", Font.PLAIN, 36));
		}

		controllerPanel.setLayout(new BorderLayout(5, 5));
		controllerPanel.add(noteKeysPanel, BorderLayout.NORTH);
		controllerPanel.setBorder(new LineBorder(Color.BLACK, 1));
		controllerPanel.setBackground(Color.lightGray);
		controllerPanel.setPreferredSize(new Dimension(1000, 380));

		this.add(controllerPanel, BorderLayout.CENTER);
	}

	public void setActionListenerToAllButtons(ActionListener a) {
		for (int i = 0; i < NOTES.length; i++) {
			notes[i].addActionListener(a);
		}
	}

}
