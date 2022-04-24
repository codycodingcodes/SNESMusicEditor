
/******************************************************************************************
 * 
 * Programmer Name: Team 5
 * 
 * Class Name: ListContainerInterface
 *
 ******************************************************************************************
 * 
 * Description: This class provides methods used to make and modify musical notes
 * 
 * ****************************************************************************************
 * 
 * Custom Defined Methods
 * ______________________
 * 
 * +add(String):boolean +removeAtFront():String +removeAtEnd():String
 * +removeAtIndex(int):String +printList():Void +trimToSize():Void
 * +shiftUp(int):Void +isEmpty():boolean +getNoteLetter(int):String
 * +getNotePitch(int):String +getNoteType(int):String +getNoteOctave(int):String
 * +isNoteType(char):boolean +noteSequence():String +getIndex(int):String
 * +clearList():Void
 * 
 ******************************************************************************************/


public interface ListContainerInterface 
{
	/*************************************
	 * 
	 * Name: add
	 * Description: Adds note as a string
	 * @param text
	 * 
	 ************************************/
	public boolean add(String text);
	
	/****************************************
	 * 
	 * Name: removeAtFront
	 * Description: remove element from front
	 * @return String
	 * 
	 ***************************************/
	public String removeAtFront();
	
	/*******************************************
	 * 
	 * Name: removeAtEnd
	 * Description: removes element from the end
	 * @return String
	 * 
	 *******************************************/
	public String removeAtEnd();
	
	/**********************************************
	 * 
	 * Name: removeAtIndex
	 * Description: Remove element from given index
	 * @param instrument
	 * @return String
	 * 
	 **********************************************/
	public String removeAtIndex(int index);
	
	/****************************************
	 * 
	 * Name: printList
	 * Description: prints the list of notes
	 * 
	 ***************************************/
	public void printList();
	
	/****************************************
	 * 
	 * Name: trimtoSize
	 * Description: Method to resize elements
	 * 
	 ****************************************/
	public void trimToSize();
	
	/*************************************
	 * 
	 * Name: shiftUp
	 * Description: Shifts up the note
	 * @param index
	 * 
	 ************************************/
	public void shiftUp(int index);
	
	/*************************************
	 * 
	 * Name: shiftDown
	 * Description: Shifts down the note
	 * @param index
	 * 
	 ************************************/
	public void shiftDown(int index);
	
	/*************************************
	 * 
	 * Name: isEmpty
	 * Description: Checks to see if empty
	 * @return boolean
	 * 
	 ************************************/
	public boolean isEmpty();
	
	/**********************************************
	 * 
	 * Name: getNoteLetter
	 * Description: Gets the value of note selected
	 * @param index
	 * @return String
	 * 
	 *********************************************/
	public String getNoteLetter(int index);
	
	/*************************************
	 * 
	 * Name: getNotePitch
	 * Description: Returns the note pitch
	 * @param index
	 * @return String
	 * 
	 ************************************/
	public String getNotePitch(int index);
	
	/*************************************
	 * 
	 * Name: getNoteType
	 * Description: Returns the note type
	 * @param index
	 * @return String
	 * 
	 ************************************/
	public String getNoteType(int index);
	
	/*********************************************
	 * 
	 * Name: getNoteOctave
	 * Description: Returns the octave of the note
	 * @param indes
	 * @return String
	 * 
	 ********************************************/
	public String getNoteOctave(int index);
	
	/*************************************
	 * 
	 * Name: isNoteType
	 * Description: Checks for note type
	 * @param noteType
	 * @return boolean
	 * 
	 ************************************/
	public boolean isNoteType(char noteType);
	
	/**********************************************************
	 * 
	 * Name: noteSequence
	 * Description: gives the progression of the notes selected
	 * @return String
	 * 
	 **********************************************************/
	public String noteSequence();
	
	/*******************************************
	 * 
	 * Name: getIndex
	 * Description: Returns index value of note
	 * @param index
	 * @return String
	 * 
	 ******************************************/
	public String getIndex(int index);
	
	/*************************************
	 * 
	 * Name: clearList
	 * Description: Creates a blank list
	 * 
	 ************************************/
	public void clearList();
	

}
