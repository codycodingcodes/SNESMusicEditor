public class MusicNoteSetsAdaptor implements MusicNoteSetsAdaptor_IF{
    private String mmlNoteSet;
    private MusicNotesSets jfugueNoteSet;
    private ChannelValues channel; 
    
    public MusicNoteSetsAdaptor() {

    }
    
    //fluent setters
    public MusicNoteSetsAdaptor mmlNoteSet(String noteSet) {
        setNoteSet(noteSet);
        return this;
    }
    private void setNoteSet(String noteSet) {
        this.mmlNoteSet = noteSet;
    }

    public MusicNoteSetsAdaptor jfugueNoteSet(MusicNotesSets noteSet) {
        setJfugueNoteSet(noteSet);
        return this;
    }
    private void setJfugueNoteSet(MusicNotesSets noteSet) {
        this.jfugueNoteSet = noteSet;
    }

    public MusicNoteSetsAdaptor channel(ChannelValues channel) {
        setChannel(channel);
        return this;
    }
    private void setChannel(ChannelValues channel) {
        this.channel = channel;
    }

    @Override
    public String getJFugueFormat() {
        return ToJFugue(mmlNoteSet, channel);
    }

    @Override
    public String getMMLFormat() {
        return ToMML(jfugueNoteSet);
    }

    private String ToJFugue(String mmlFormat, ChannelValues channel) {
        //////////////////////////////

		// LOCAL VARIABLES //

		///////////////////////////////

		String fugueFormat = "";
		String noteOctave = channel.getOctaveLevel();
		String noteType = "";
		MusicNotesConvertor noteConverter = new MusicNotesConvertor();

		/////////////////////////////////

		// loop through the string to extract the proper data
		for (int i = 1; i < mmlFormat.length(); i++) {

			// if this will check if the sequence is a super loop
			if (mmlFormat.charAt(i) == '[') {
				String newFugueFormat = new String();
				fugueFormat = mmlFormat.substring(i + 1, mmlFormat.indexOf(']'));
				newFugueFormat = fugueFormat.toUpperCase().charAt(0) + noteConverter.convertNoteToJFugue("" + fugueFormat.charAt(1));
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
					fugueFormat += (noteOctave + noteConverter.convertNoteToJFugue(noteType) + " ");
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
				fugueFormat += (noteOctave + noteConverter.convertNoteToJFugue(noteType) + " ");

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
				fugueFormat += (noteOctave + "" + noteConverter.convertNoteToJFugue(noteType) + " ");

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
				fugueFormat += (noteOctave + noteConverter.convertNoteToJFugue(noteType) + " ");
				noteType = "";
				break;

			}

		} // end of for loop

		return fugueFormat;
    }

    private String ToMML(MusicNotesSets musicNotes) {
        String mmlFormat = new String();
		MusicNotesConvertor noteConverter = new MusicNotesConvertor();

		for (int i = 0; i < musicNotes.getSize(); i++) {

			if (i == 0 || i == musicNotes.getSize()) {
				mmlFormat += (musicNotes.getNoteLetter(i).toLowerCase() + noteConverter.convertPitchToMML(musicNotes.getNotePitch(i))
						+ noteConverter.convertNoteTypeToMML(musicNotes.getNoteType(i)));
			} else {
				// checks for if the octave level is lower for the next note
				if (Integer.parseInt(musicNotes.getNoteOctave(i - 1)) > Integer.parseInt(musicNotes.getNoteOctave(i))) {
					for (int j = 0; j < Math.abs(Integer.parseInt(musicNotes.getNoteOctave(i - 1))
							- Integer.parseInt(musicNotes.getNoteOctave(i))); j++)
						mmlFormat += '<';
					mmlFormat += (musicNotes.getNoteLetter(i).toLowerCase()
							+ noteConverter.convertPitchToMML(musicNotes.getNotePitch(i))
							+ noteConverter.convertNoteTypeToMML(musicNotes.getNoteType(i)));
				}
				// checks if the octave level is higher for the next note
				else if (Integer.parseInt(musicNotes.getNoteOctave(i - 1)) < Integer
						.parseInt(musicNotes.getNoteOctave(i))) {
					for (int j = 0; j < Math.abs(Integer.parseInt(musicNotes.getNoteOctave(i - 1))
							- Integer.parseInt(musicNotes.getNoteOctave(i))); j++)
						mmlFormat += '>';
					mmlFormat += (mmlFormat += (musicNotes.getNoteLetter(i).toLowerCase()
							+ noteConverter.convertPitchToMML(musicNotes.getNotePitch(i))
							+ noteConverter.convertNoteTypeToMML(musicNotes.getNoteType(i))));
				}
				// if there is no change to the octave level
				else {
					mmlFormat += (musicNotes.getNoteLetter(i).toLowerCase()
							+ noteConverter.convertPitchToMML(musicNotes.getNotePitch(i))
							+ noteConverter.convertNoteTypeToMML(musicNotes.getNoteType(i)));
				}
			}
		}
		return mmlFormat;
    }

}