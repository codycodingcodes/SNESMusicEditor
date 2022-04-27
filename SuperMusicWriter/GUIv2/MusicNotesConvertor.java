
/****************************************************************************
 * 			
 * 				Programmer Name: Kevin Woo
 *				Class Name:	ConvertData
 *				Day Start: 10/14/2021
 *				Day Finished : 10/22/2021
 *
 ****************************************************************************
 * 
 * 
 * 							Description
 * 			This class will handle all of data from JFugue to
 * 			MML vise verse MLL to JFugue.
 * 
 * **************************************************************************
 * 
 * 
 * 						Custom Defined Methods
 * 
 * 			+getNotes():ArrayList<String>
 * 			+readMMLFile(ArrayList<ChannelValues>, String):void
 * 			+convertMML_To_JFugue(String, ChannelValues):String
 * 			+convert_JFugue_To_MMLFormat(ArrayList<MusicNotesSets>):String
 * 			+convertPitchToMML(String s):String
 * 			+convertNoteToJFugue(String noteCode):String
 * 			+convertNoteTypeToMML(String noteCode):String
 * 			+extract(ArrayList<MusicNotesSets>, String,ChannelValues) :void
 * 			+saveFile(String fileName,ArrayList<ChannelValues>,ArrayList<ArrayList<MusicNotesSets>> ):void
 * 
 * 

 *****************************************************************************/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MusicNotesConvertor {

	///////////////////////////////////////

	// PRIVATE GLOBAL VARIABLES //

	/////////////////////////////////////

	private ArrayList<String> notes;

	////////////////////////////////////

	///////////////////////////

	// CONSTRUCTOR //

	/////////////////////////

	public MusicNotesConvertor() {

		notes = new ArrayList<String>();
	}

	///////////////////////////

	///////////////////////////////////////

	// STAR OF CUSTOM DEFINED METHODS //

	/////////////////////////////////////

	public ArrayList<String> getNotes() {
		return notes;
	}

	/******************************
	 * 
	 * Name: readMMLFile Description: reads the .txt file in mml format
	 * 
	 * @param ch
	 **************************************/
	public ArrayList<ChannelValues> readMMLChannelValues(File f)
	// public void readMMLChannelValues(String fileName,ArrayList<MusicNotesSets>
	// noteSets,ArrayList<ChannelValues> channel)
	{

		///////////////////////

		// LOCAL VARIABLES //

		//////////////////////
		ArrayList<ChannelValues> channel;
		BufferedReader out;
		String s;
		String channelSlot = "";
		String masterVol = "";
		String tempo = "";
		String instrumentCode = "";
		String vol = "";
		String octLevel = "";
		boolean superLoop = false;

		/////////////////////////

		try {

			channel = new ArrayList<ChannelValues>();
			// newNoteSets = new ArrayList<MusicNotesSets>();
			//out = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("Music/" + fileName))); //old
			out = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			while ((s = out.readLine()) != null) {

				if (s.equals("#amk 2")) {
					s = out.readLine();
				}
				// System.out.println(s);
				// loop through to get the content
				for (int i = 0; i < s.length(); i++) {
					// System.out.println(i);
					// gets the channel
					if (s.charAt(i) == '#') {
						// System.out.println("#");
						channelSlot = "";
						channelSlot = "" + s.charAt(i + 1);

					} else if (s.charAt(i) == 'w') {
						// System.out.println("w");
						int index = i + 1;
						while (s.charAt(index) != ' ') {

							masterVol += s.charAt(index);
							index++;
						}
						// System.out.println(masterVol);
					}
					// gets the tempo
					else if (s.charAt(i) == 't') {
						// System.out.println("t");
						int index = i + 1;
						while (s.charAt(index) != ' ') {

							tempo += s.charAt(index);
							// System.out.println(tempo);
							index++;

							if (index == s.length()) {
								i = index;
								break;
							}
						}

					}
					// gets the instrument
					else if (s.charAt(i) == '@') {
						// System.out.println("@");
						instrumentCode += s.charAt(i + 1);
						// System.out.println(instrumentCode);
					} else if (s.charAt(i) == 'v') {
						// System.out.println("v");
						int index = i + 1;
						while (s.charAt(index) != ' ') {

							vol += s.charAt(index);

							index++;
						}
						//// System.out.println(vol);
					}
					// gets the octave level
					else if (s.charAt(i) == 'o') {
						// System.out.println("0");
						octLevel = "" + s.charAt(i + 1);

						// System.out.println(octLevel);
					}
					// find the note sequence
					else if (s.charAt(i) == '[') {
						if (s.charAt(i + 1) == '[') {
							superLoop = true;
						}

						// store the data of the channel
						if (channelSlot != "")
							channel.add(new ChannelValues(channelSlot, masterVol, tempo, instrumentCode, vol, octLevel,
									s.substring(s.lastIndexOf(']') + 1), superLoop));

						// clears the data for the next loop cycle
						channelSlot = "";
						masterVol = "";
						tempo = "";
						instrumentCode = "";
						vol = "";
						octLevel = "";
						s = "";

					}

				}

			}
			//// noteStrings = notes;
			// System.out.println("Outside of try"+noteStrings.get(0).toString());

			return channel;
		} catch (IOException e) {
			System.out.println("Sorry no file");
		}

		// channel = newChannel;
		return null;

	}

	public ArrayList<MusicNotesSets> returnNoteSets(String fileName, ArrayList<ChannelValues> channel)
	// public void readMMLChannelValues(String fileName,ArrayList<MusicNotesSets>
	// noteSets,ArrayList<ChannelValues> channel)
	{

		///////////////////////

		// LOCAL VARIABLES //

		//////////////////////
		// ArrayList<ChannelValues> channel;
		BufferedReader out;
		String s;
		int currentChannelIndex = 0;
		String currentNoteSets = new String();
		char[] noteSetsArray = null;
		ArrayList<MusicNotesSets> newNoteSets = null;
		/////////////////////////

		try {

			newNoteSets = new ArrayList<MusicNotesSets>();
			out = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("Music/" + fileName)));
			while ((s = out.readLine()) != null) {

				if (s.equals("#amk 2")) {
					s = out.readLine();
				}
				// System.out.println(s);
				// loop through to get the content
				for (int i = 0; i < s.length(); i++) {

					// find the note sequence
					if (s.charAt(i) == '[') {

						System.out.println("MML2:" + s.toString());

						notes.add(convertMML_To_JFugue(s.toString(), channel.get(currentChannelIndex)));
						System.out.println("qwqw");
						System.out.println(notes.get(currentChannelIndex).toString());
						newNoteSets.add(new MusicNotesSets());
						;
						noteSetsArray = notes.get(currentChannelIndex).toString().toCharArray();
						for (int j = 0; j < noteSetsArray.length; j++) {
							if (noteSetsArray[j] == ' ') {
								newNoteSets.get(currentChannelIndex).add(currentNoteSets);
								currentNoteSets = "";
							} else {
								currentNoteSets += noteSetsArray[j];
							}
						}

						s = "";
						currentChannelIndex++;
						System.out.println("Index" + currentChannelIndex);
					}

				}

			}

			// System.out.println("Outside of try"+newNoteSets.get(1).noteSequence());

			return newNoteSets;
		} catch (IOException e) {
			System.out.println("Sorry no file");
		}

		// channel = newChannel;
		return null;

	}

	/******************************************************
	 * 
	 * Name: convertMML_To_JFugue Description: convert the MML format to a format
	 * that JFugue can read
	 * 
	 * @param mmlFormat
	 * @param channel
	 * @return String
	 ******************************************************/

	public String convertMML_To_JFugue(String mmlFormat, ChannelValues channel) {

		//////////////////////////////

		// LOCAL VARIABLES //

		///////////////////////////////

		String fugueFormat = "";
		String noteOctave = channel.getOctaveLevel();
		String noteType = "";

		/////////////////////////////////

		// loop through the string to extract the proper data
		for (int i = 1; i < mmlFormat.length(); i++) {

			// if this will check if the sequence is a super loop
			if (mmlFormat.charAt(i) == '[') {
				String newFugueFormat = new String();
				fugueFormat = mmlFormat.substring(i + 1, mmlFormat.indexOf(']'));
				newFugueFormat = fugueFormat.toUpperCase().charAt(0) + convertNoteToJFugue("" + fugueFormat.charAt(1));
				return newFugueFormat;

			}
			// this will extract the note letter
			else if (mmlFormat.charAt(i) >= 'a' && mmlFormat.charAt(i) <= 'g') {
				fugueFormat += mmlFormat.toUpperCase().charAt(i);
			}
			// this will convert the pitch type to #
			else if (mmlFormat.charAt(i) == '+') {
				fugueFormat += '#';

			}
			// this will convert the pitch type to b
			else if (mmlFormat.charAt(i) == '-') {
				fugueFormat += 'b';

			}
			// this will get the note type
			else if (mmlFormat.charAt(i) >= '0' && mmlFormat.charAt(i) <= '9') {
				// if the next note is a letter goes here and add the to the string the note
				// sequence
				if (mmlFormat.charAt(i + 1) >= 'a' && mmlFormat.charAt(i + 1) <= 'g') {

					noteType += mmlFormat.charAt(i);
					fugueFormat += (noteOctave + convertNoteToJFugue(noteType) + " ");
					noteType = "";
				}
				// normal condition if the next character is not a letter
				else {
					noteType += mmlFormat.charAt(i);
				}

			}
			// this will lower the octave level
			else if (mmlFormat.charAt(i) == '<') {
				// adding the the string
				fugueFormat += (noteOctave + convertNoteToJFugue(noteType) + " ");

				// this loop will loop through til '<' is not found within the set sequence
				for (int j = i; j < mmlFormat.length(); j++) {
					// checks if the char is '<' and lower the octave
					if (mmlFormat.charAt(j) == '<')
						noteOctave = String.valueOf(Integer.parseInt(noteOctave) - 1);
					// end the loop and reassign i
					else {
						i = j - 1;
						break;
					}
				} // end for loop
				noteType = "";

			}
			// this will raise the octave level
			else if (mmlFormat.charAt(i) == '>') {
				fugueFormat += (noteOctave + "" + convertNoteToJFugue(noteType) + " ");

				// this loop will loop through til '>' is not found within the set sequence
				for (int j = i; j < mmlFormat.length(); j++) {
					// checks if the char is '>' and raise the octave
					if (mmlFormat.charAt(j) == '>') {

						noteOctave = String.valueOf(Integer.parseInt(noteOctave) + 1);
					}
					// end the loop and reassign i
					else {
						i = j - 1;
						break;
					}
				}
				noteType = "";

			}
			// this is the end of the current sequence
			else if (mmlFormat.charAt(i) == ']') {
				fugueFormat += (noteOctave + convertNoteToJFugue(noteType) + " ");
				noteType = "";
				break;

			}

		} // end of for loop

		return fugueFormat;

	}

	/*******************************************************
	 * 
	 * Name: convert_JFugue_To_MMLFormat Description: convert JFugue format to a
	 * format that MML can read
	 * 
	 * @param musicNotes
	 * @return String
	 ********************************************************/
	public String convert_JFugue_To_MMLFormat(MusicNotesSets musicNotes) {
		String mmlFormat = new String();

		for (int i = 0; i < musicNotes.getSize(); i++) {

			if (i == 0 || i == musicNotes.getSize()) {
				mmlFormat += (musicNotes.getNoteLetter(i).toLowerCase() + convertPitchToMML(musicNotes.getNotePitch(i))
						+ convertNoteTypeToMML(musicNotes.getNoteType(i)));
			} else {
				// checks for if the octave level is lower for the next note
				if (Integer.parseInt(musicNotes.getNoteOctave(i - 1)) > Integer.parseInt(musicNotes.getNoteOctave(i))) {
					for (int j = 0; j < Math.abs(Integer.parseInt(musicNotes.getNoteOctave(i - 1))
							- Integer.parseInt(musicNotes.getNoteOctave(i))); j++)
						mmlFormat += '<';
					mmlFormat += (musicNotes.getNoteLetter(i).toLowerCase()
							+ convertPitchToMML(musicNotes.getNotePitch(i))
							+ convertNoteTypeToMML(musicNotes.getNoteType(i)));
				}
				// checks if the octave level is higher for the next note
				else if (Integer.parseInt(musicNotes.getNoteOctave(i - 1)) < Integer
						.parseInt(musicNotes.getNoteOctave(i))) {
					for (int j = 0; j < Math.abs(Integer.parseInt(musicNotes.getNoteOctave(i - 1))
							- Integer.parseInt(musicNotes.getNoteOctave(i))); j++)
						mmlFormat += '>';
					mmlFormat += (mmlFormat += (musicNotes.getNoteLetter(i).toLowerCase()
							+ convertPitchToMML(musicNotes.getNotePitch(i))
							+ convertNoteTypeToMML(musicNotes.getNoteType(i))));
				}
				// if there is no change to the octave level
				else {
					mmlFormat += (musicNotes.getNoteLetter(i).toLowerCase()
							+ convertPitchToMML(musicNotes.getNotePitch(i))
							+ convertNoteTypeToMML(musicNotes.getNoteType(i)));
				}
			}
		}
		return mmlFormat;
	}

	public String convertPitchToMML(String s) {
		if (s.equals("#")) {
			return "+";
		} else if (s.equals("b")) {
			return "-";
		}
		return "";
	}

	/***************************
	 * 
	 * Name: convertNoteToJFugue Description: convert the mml note to JFugue
	 * 
	 * @param noteCode
	 * @return
	 ****************************/

	public String convertNoteToJFugue(String noteCode) {

		switch (noteCode) {
			case "1":
				return ("W");
			case "2":
				return ("H");
			case "4":
				return ("Q");
			case "8":
				return ("I");
			case "16":
				return ("S");
			case "32":
				return ("T");
			case "64":
				return ("X");
			case "128":
				return ("O");
			default:
				return ("");
		}

	}

	/***********************************
	 * 
	 * Name: convertNoteTypeToMML Description: Converts the JFugue format to MML
	 * format
	 * 
	 * @param noteCode
	 * @return
	 ***********************************/

	public String convertNoteTypeToMML(String noteCode) {
		switch (noteCode) {
			case "W":
				return ("1");
			case "H":
				return ("2");
			case "Q":
				return ("4");
			case "I":
				return ("8");
			case "S":
				return ("16");
			case "T":
				return ("32");
			case "X":
				return ("64");
			case "O":
				return ("128");
			default:
				return ("");
		}
	}

	/*******************************
	 * 
	 * Name:saveFile Description: saves the note sequence to a .txt file
	 * 
	 * @param fileName
	 * @param channel
	 * @param music
	 */

	public void saveFile(File file, ArrayList<ChannelValues> channel, ArrayList<MusicNotesSets> music) {
		FileWriter saveToFile = null;

		try {

			saveToFile = new FileWriter(file);

			saveToFile.write("#amk 2\n\n");
			for (int i = 0; i < channel.size(); i++) {
				// used for the super loop condition
				if (channel.get(i).getSuperLoop()) {

					saveToFile
							.write(channel.get(i).getChannelData() + "\n[[" + convert_JFugue_To_MMLFormat(music.get(i))
									+ "]]" + channel.get(i).getRepeatValue() + "\n\n");

				} else {
					saveToFile.write(
							channel.get(i).getChannelData() + "\n" + "[" + convert_JFugue_To_MMLFormat(music.get(i))
									+ "]" + channel.get(i).getRepeatValue() + "\n\n");

				}

			}
			saveToFile.close();
		} catch (IOException e) {
			System.out.println("error");
		}
	}

}
