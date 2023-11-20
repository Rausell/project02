//Angel Lopez Project02 
//Importing needed modules
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.*;

/*
Class floor demonstrates the process of multiple floors. Including handling
arrival.
*/

class Passenger{
	//Track passenger existence/dereference
	private int id;
	//Track passenger starting floor entered
	private int sf;
	//Track passenger ending floor (destination)
	private int ef;
	//Tracking ticks it took to get passenger to ef
	private int timeTick;

	//Constructors
	public Passenger(int id, int sf, int ef, int timeTick){
		this.id = id;
		this.sf = sf;
		this.ef = ef;
		this.timeTick = timeTick;
	}

	//Getter methods for Passenger
	/*
	This is 'artificial functions' are created to access the information 
	from this class to other classes not located within (similar to becoming global variables)
	*/
	public int getId(){
		return id;
	}
	public int getsf(){
		return sf;
	}
	public int getef(){
		return ef;
	}
	public int getTimeTick(){
		return timeTick;
	}
}//End of Passenger class

/*
class Elevator{

}

class Floor{
	private int floors;
	private List<Elevator> elevators;
	private List<Queue<Passenger>> floorQueue;

	//Constructor for floor
	public Floor(int floors, int elevators, int elevatorCap){
		this.floors = floors;
		this.elevators = new ArrayList<>();
		this.floorQueue = new ArrayList<>();
	}
}
*/

public class elevatorProgram{
	public static void main(String[] args) {
		System.out.println("Testing program runs successfully.");

		//Establishing default values
		int floors = 32;
		double passengers = 0.03;
		int elevators = 1;
		int elevatorCap = 10;
		int ticks = 500;

		/*
		Following block checks for existence of a given file
		& reads/updates values as listed in file.
		It also deals with any error that might arise from the file
		*/

		if(args.length > 0){
			try{
				FileInputStream file = new FileInputStream(args[0]);
				Properties properties = new Properties();
				properties.load(file);

				//Assigning given 'Key' names to appropriate variable
				//Default values still at play if no matching 'Key' exists				
				floors = Integer.parseInt(properties.getProperty("floors", "32"));
				passengers = Double.parseDouble(properties.getProperty("passengers", "0.03"));
				elevators = Integer.parseInt(properties.getProperty("elevators", "1"));
				elevatorCap = Integer.parseInt(properties.getProperty("elevatorCapacity", "10"));
				ticks = Integer.parseInt(properties.getProperty("duration", "500"));

				file.close();
			//Dealing with file error
			} catch (IOException e){
				e.printStackTrace();
			}
		}

		//Printing read values from file to test program correctness DEL
		System.out.println(floors);
		System.out.println(passengers);
		System.out.println(elevators);
		System.out.println(elevatorCap);
		System.out.println(ticks);
		//DEL
	}
}