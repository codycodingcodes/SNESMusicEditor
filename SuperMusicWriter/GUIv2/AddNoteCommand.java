public class AddNoteCommand implements Command_IF {

    MusicNotesSets mns;

    public AddNoteCommand() {
        mns = new MusicNotesSets();
    }
    // public boolean add(String text) {

    // if (size <= noteSet.length) {
    // // reSize();
    // noteSet[size] = text;
    // size++;
    // return true;
    // }

    // return false;
    // }

    // public String removeAtEnd() {

    // size--;
    // String temp = noteSet[size];
    // noteSet[size] = null;

    // return temp;
    // }

    @Override
    public MusicNotesSets doIt(String text, MusicNotesSets notes) {
        mns = notes;
        mns.add(text);
        return mns;
    }

    @Override
    public MusicNotesSets undo(MusicNotesSets notes) {
        mns = notes;
        mns.removeAtEnd();
        return mns;
    }

}
