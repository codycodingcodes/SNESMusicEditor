
/******************************************************************************************
 * 
 * Programmer Name: Team 5
 * 
 * Class Name: InstrumentEnum
 *
 ******************************************************************************************
 * 
 * Description: This class sets sets instruments to an enum value for accessing each type
 * 
 * ****************************************************************************************
 * 
 * Custom Defined Methods
 * ______________________
 * 
 * +getInstrument():String
 * 
 ******************************************************************************************/


public enum InstrumentEnum {
	
	
	FLUTE("0"),
	MARIMBA("3"),
	CELLO("4"),
	STEEL_STRING_GUITAR("5"),
	TRUMPET("6"),
	STEEL_DRUMS("7"),
	ACOUSTIC_BASS("8"),
	PIANO("9"),
	SYNTH_DRUM("10"),
	TAIKO_DRUM("12"), //BONGO DRUM
	ELECTRIC_PIANO("13"),
	SLAP_BASS_1("14"),
	ORCHESTRA_HIT("15"),
	DISTORTION_GUITAR("17"),
	GLOCKENSPEIL("2"),//not working atm
	BASS_DRUM("21"),//not working atm
	MARACAS("23"), //not working atm
	HI_WOOD_BLOCK("25");//not working atm

	
	public final String VALUE;
	
	
	private InstrumentEnum(String value)
	{
	  VALUE = value;	
	}
	
	/*************************************
	 * 
	 * Name: InstrumentEnum
	 * Description: Returns the instrument
	 * @param instrument
	 * 
	 ************************************/
	
	public static InstrumentEnum getInstrument(String instrumentCode)
	{
		switch (instrumentCode) {
			case "0":
			case "1":
			case "2":
				return FLUTE;

			//	return GLOCKENSPEIL;
			case "3":
				return MARIMBA;
			case "4":
				return	CELLO;
			case "5":
				return STEEL_STRING_GUITAR;
			case "6":
				return TRUMPET;
			case "7":
				return STEEL_DRUMS;
			case "8":
				return ACOUSTIC_BASS;
			case "9":
				return PIANO;
			case "10":
			case "11":
				return SYNTH_DRUM;
			case "12":
				return TAIKO_DRUM;
			case "13":
				return ELECTRIC_PIANO;
			case "14":
				return SLAP_BASS_1;
			case "15":
				return ORCHESTRA_HIT;
			case "17":
				return DISTORTION_GUITAR;
			default:
			return PIANO;
		}
	}

}
