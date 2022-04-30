public interface Command_IF {

    public MusicNotesSets doIt(String text, MusicNotesSets notes);

    public MusicNotesSets undo(MusicNotesSets notes);

}
