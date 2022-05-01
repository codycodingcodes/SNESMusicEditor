public class AddNoteCommand implements Command_IF {

    MusicNotesSets mns;

    public AddNoteCommand() {
        mns = new MusicNotesSets();
    }

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
