/****************************************************************************
 * 
 * Programmer Name: Kevin Woo
 * Class Name: MusicNotesSets
 * Day Start: 10/14/2021
 * Day Finished : 10/22/2021
 * Updated:11/12/2021
 *
 ****************************************************************************
 * 
 * 
 * Description
 * This class will store the Music notes data into a JFugue format
 * that we will call later to be converted into MML
 * 
 * **************************************************************************
 * 
 * 
 * Custom Defined Methods
 * 
 * +isNoteType(char):boolean
 * +noteSequence():String
 * 
 * 
 * 
 * 
 *****************************************************************************/

public class MusicNotesSets extends ListContainerAbstract {
	///////////////////////////////////

	// PRIVATE GLOBAL VARIABLE //

	//////////////////////////////////

	private final int MAXLIMIT = 100;
	private String[] noteSet;

	private static MusicNotesSets mnSet;

	/////////////////////////////////

	///////////////////////////

	// CONSTRUCTOR //

	/////////////////////////
	public MusicNotesSets() {
		noteSet = new String[MAXLIMIT];
		size = 0;
	}

	public static MusicNotesSets getMusicNotesSetInstance() {
		if (mnSet == null) {
			mnSet = new MusicNotesSets();
		}
		return mnSet;

	}

	///////////////////////////////////////////

	// START OF CUSTOM FUNCTIONS //

	/////////////////////////////////////////

	/*********************************
	 * 
	 * Name: isNoteType
	 * Description: checks if the note type
	 * is a valid one
	 * 
	 * @return boolean
	 ********************************/
	@Override
	public boolean isNoteType(char noteType) {
		switch (noteType) {
			case 'W':
			case 'H':
			case 'Q':
			case 'I':
			case 'S':
			case 'T':
			case 'X':
			case 'O': {
				return true;
			}
			default: {
				return false;
			}

		}

	}

	/**************************************
	 * 
	 * Name: noteSequence
	 * Description: returns the note sequence
	 * 
	 * @return
	 ****************************************/

	public String noteSequence() {
		String sequence = new String();
		for (int i = 0; i < size; i++) {
			if (i == 0)
				sequence = noteSet[i] + " ";
			else
				sequence += noteSet[i] + " ";
		}
		return sequence;
	}

	/*********************************
	 * 
	 * Name: add
	 * Description: adds the note to the end of the list
	 * 
	 *********************************/
	@Override
	public boolean add(String text) {

		if (size <= noteSet.length) {
			// reSize();
			noteSet[size] = text;
			size++;
			return true;
		}

		return false;
	}

	/*********************************
	 * 
	 * Name: removeAtFront
	 * Description: remove element at the front of the list
	 * Return: String
	 * 
	 *********************************/
	@Override
	public String removeAtFront() {

		String temp = noteSet[0];

		shiftUp(0);
		size--;
		return temp;
	}

	/*********************************
	 * 
	 * Name: removeAtEnd
	 * Description: remove element at the end of the list
	 * Return: String
	 * 
	 *********************************/

	public String removeAtEnd() {

		size--;
		String temp = noteSet[size];
		noteSet[size] = null;

		return temp;
	}

	/*********************************
	 * 
	 * Name: removeAtIndex
	 * Description: remove element at the desired index
	 * Return: String
	 * 
	 * @param
	 *********************************/
	@Override
	public String removeAtIndex(int index) {

		String temp;
		if (index == 0)
			return removeAtFront();
		else if (index == size)
			return removeAtEnd();
		else {
			temp = this.noteSet[index];
			this.noteSet = null;
			shiftUp(index);
			return temp;
		}

	}

	/**********************************
	 * 
	 * Name: trimeToSize
	 * Description: trim all empty space within the list
	 * to the current space used
	 * Return: None
	 *
	 ***********************************/

	@Override
	public void trimToSize() {

	}

	/**********************************
	 * 
	 * Name: printList
	 * Description: prints all element within the list
	 * Return: None
	 *
	 ***********************************/
	@Override
	public void printList() {

		System.out.print("Note Set:");
		for (int i = 0; i < size; i++) {
			System.out.print(noteSet[i] + ' ');
		}

	}

	/******************************
	 * 
	 * Name:shiftUp
	 * Description: shifts all elements
	 * up to cover empty spaces
	 * Return: None
	 * 
	 ******************************/
	@Override
	public void shiftUp(int index) {
		try {

			for (int i = index; i < size; ++i)
				noteSet[i] = noteSet[i + 1];

		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	private void reSize() {
		String[] newList = new String[2 * noteSet.length];

		for (int i = 0; i < noteSet.length; i++) {
			newList[i] = noteSet[i];
		}
		noteSet = newList;
	}

	/******************************
	 * 
	 * Name:shiftDown
	 * Description: shifts all elements
	 * down in case a new element is
	 * inserted above
	 * Return: None
	 * 
	 ******************************/
	@Override
	public void shiftDown(int index) {

		try {
			for (int i = size; i > index - 1; --i)
				noteSet[i + 1] = noteSet[i];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getNoteLetter(int index) {
		try {

			for (int i = 0; i < noteSet[index].length(); i++) {
				if (noteSet[index].charAt(i) >= 'A' && noteSet[index].charAt(i) <= 'G')
					return "" + noteSet[index].charAt(i);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getNotePitch(int index) {
		try {

			for (int i = 0; i < noteSet[index].length(); i++) {
				if (noteSet[index].charAt(i) == '#' || noteSet[index].charAt(i) == 'b')
					return "" + noteSet[index].charAt(i);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String getNoteType(int index) {
		try {

			for (int i = 0; i < noteSet[index].length(); i++) {
				if (isNoteType(noteSet[index].charAt(i)))
					return "" + noteSet[index].charAt(i);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getNoteOctave(int index) {
		try {

			for (int i = 0; i < noteSet[index].length(); i++) {
				if (noteSet[index].charAt(i) >= '0' && noteSet[index].charAt(i) <= '9')
					return "" + noteSet[index].charAt(i);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getIndex(int index) {

		return noteSet[index];
	}

	@Override
	public void clearList() {

		while (size > 0) {
			System.out.println(removeAtFront());
		}
		System.out.println("done");
	}

}
