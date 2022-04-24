
/****************************************************************************
 * 
 * Programmer Name:Team 5
 * 
 * Class Name: ChannelValues
 *
 ****************************************************************************
 * 
 * 
 * Description This class will store the data of the MML channel values
 * 
 * **************************************************************************
 * 
 * 
 * Custom Defined Methods
 * ______________________
 * 
 * +getChannel():String +getChannelData():String +getMaterVolume():String
 * +getTempo():String +getInstrumentCode:String +setInstrumentCode(String):void
 * +getVolume():String +getOctaveLevel():String +setOctaveLevel(String):void
 * +getRepeatValue():String +getSuperLoop():boolean
 * 
 * 
 * 
 * 
 * 
 *****************************************************************************/

public class ChannelValues {

	/****************************
	 * Private instance variables
	 ****************************/
	private String channel;
	private String masterVolume;
	private String tempo;
	private String instrumentCode;
	private String volume;
	private String octaveLevel;
	private String repeat;
	private boolean superLoop;

	/*********************************
	 * 
	 * Name: ChannelValues
	 * Description: Default Constructor
	 * 
	 ********************************/

	public ChannelValues() {

	}

	/*********************************
	 * 
	 * Name: ChannelValues
	 * Description: Class Constructor
	 * 
	 ********************************/
	public ChannelValues(String channel) {
		this(channel, "255", "50", "9", "255", "4", "5", false);
	}

	/*********************************
	 * 
	 * Name: ChannelValues
	 * Description: Class Constructor
	 * 
	 ********************************/
	public ChannelValues(String channel, String masterVolume, String tempo, String instrumentCode, String volume,
			String octaveLevel, String repeat) {
		this(channel, masterVolume, tempo, instrumentCode, volume, octaveLevel, repeat, false);
	}

	/*********************************
	 * 
	 * Name: ChannelValues
	 * Description: Class Constructor
	 * 
	 ********************************/
	public ChannelValues(String channel, String masterVolume, String tempo, String instrumentCode, String volume,
			String octaveLevel, String repeat, boolean superLoop) {
		this.channel = channel;
		this.masterVolume = masterVolume;
		this.tempo = tempo;
		this.instrumentCode = instrumentCode;
		this.volume = volume;
		this.octaveLevel = octaveLevel;
		this.repeat = repeat;
		this.superLoop = superLoop;
	}

	/**********************************
	 * 
	 * Name: getChannel
	 * Description: returns the channel
	 * 
	 * @return String
	 * 
	 **********************************/

	public String getChannel() {
		return channel;
	}

	/**********************************************
	 * 
	 * Name: getChannelData
	 * Description: returns the entire channel data
	 * 
	 * @return String
	 * 
	 **********************************************/
	public String getChannelData() {
		if (masterVolume.equals("") && tempo.equals("") && instrumentCode.equals("") && volume.equals("")
				&& octaveLevel.equals("")) {
			return "#" + channel + '\n';
		}
		return "#" + channel + " w" + masterVolume + " t" + tempo + "\n" + "@" + instrumentCode + " v" + volume + " o"
				+ octaveLevel;
	}

	/********************************************************
	 * 
	 * Name: setMasterVolume
	 * Description: Sets the master volume for audio playback
	 * 
	 ********************************************************/
	public void setMasterVolume(String masterVolume) {
		this.masterVolume = masterVolume;
	}

	/***************************************
	 * 
	 * Name: getMasterVolume
	 * Description: returns the masterVolume
	 * 
	 * @return String
	 * 
	 ***************************************/
	public String getMasterVolume() {
		return masterVolume;
	}

	/*********************************
	 * 
	 * Name: getTempo
	 * Description: returns the tempo
	 * 
	 * @return String
	 * 
	 ********************************/
	public String getTempo() {
		return tempo;
	}

	/*****************************
	 * 
	 * Name: setTempo
	 * Description: sets the tempo
	 * 
	 * @param tempo
	 * 
	 *****************************/
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	/******************************************
	 * 
	 * Name: getInstrumentCode
	 * Description: returns the instrument code
	 * 
	 * @return String
	 * 
	 ******************************************/
	public String getInstrumentCode() {
		return instrumentCode;
	}

	/**************************************
	 * 
	 * Name: setInstrumentCode
	 * Description: sets the instrumentCode
	 * 
	 * @param instrumentCode
	 * 
	 **************************************/
	public void setInstrumentCode(String instrumentCode) {
		this.instrumentCode = instrumentCode;
	}

	/********************************
	 * 
	 * Name: setVolume
	 * Description: sets the volume
	 * 
	 * @param volume
	 * 
	 ********************************/
	public void setVolume(String volume) {
		this.volume = volume;
	}

	/*********************************
	 * 
	 * Name: getVolume
	 * Description: returns the volume
	 * 
	 * @return String
	 * 
	 ********************************/
	public String getVolume() {
		return volume;
	}

	/*********************************
	 * 
	 * Name: getOctaveLevel
	 * Description: returns the octave
	 * 
	 * @return String
	 * 
	 ********************************/
	public String getOctaveLevel() {
		return octaveLevel;
	}

	/************************************
	 * 
	 * Name: setOctaveLevel
	 * Description: set the octave level
	 * 
	 * @param octaveLevel
	 * 
	 ************************************/
	public void setOctaveLevel(String octaveLevel) {
		this.octaveLevel = octaveLevel;
	}

	/***************************************
	 * 
	 * Name: getRepeatValue
	 * Description: Returns the repeat value
	 * 
	 * @return repeat
	 * 
	 ***************************************/
	public String getRepeatValue() {
		return repeat;
	}

	/***************************************
	 * 
	 * Name setRepeatValue
	 * Description: sets the repeat interval
	 * 
	 * @param repeat
	 * 
	 ***************************************/
	public void setRepeateValue(String repeat) {
		this.repeat = repeat;
	}

	/**************************************
	 * 
	 * Name: getSuperLoop
	 * Description: Returns the super loop
	 * 
	 * @return superLoop
	 *************************************/
	public boolean getSuperLoop() {
		return superLoop;
	}
}