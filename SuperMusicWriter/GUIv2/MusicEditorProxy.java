public class MusicEditorProxy implements MusicEditorIF {

    private MusicEditorIF musicEditorService;

    public MusicEditorProxy() {
        this.musicEditorService = new MusicEditorUtility();
    }

    @Override
    public void playAll() {

        musicEditorService.playAll();
    }

}
