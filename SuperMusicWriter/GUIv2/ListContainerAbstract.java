
/******************************************************************************************
 * 
 * Programmer Name: Team 5
 * 
 * Class Name: ListContainerAbstract
 *
 ******************************************************************************************
 * 
 * Description: This class provides abstract methods for getting size and
 * checking if empty
 * 
 * ****************************************************************************************
 * 
 * Custom Defined Methods
 * ______________________
 * 
 * +getSize():int +isEmpty():boolean
 * 
 ******************************************************************************************/

public abstract class ListContainerAbstract implements ListContainerInterface {

	protected int size = 0;

	/*********************************
	 * 
	 * Name: getSize
	 * Description: returns the size
	 * 
	 * @return size
	 * 
	 ********************************/
	public int getSize() {
		return this.size;
	}

	/*********************************************
	 * 
	 * Name: isEmpty
	 * Description: Checks to see if empty or full
	 * 
	 * @return size
	 * 
	 *********************************************/
	public boolean isEmpty() {
		return this.size == 0;
	}

}
