//Angel Lopez Project02 
//Importing needed modules
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Iterator;
import java.util.*;

/*
Class Passenger holds the information for the requested amount of 
Passengers. This will later help us keep track of the passengers 
loading/unloading to their desired location & keep tabs on the time it
takes to get the passengers around.
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

class Elevator{
	//Track set/given max capacity
	private int capacity;
	//Track elevator location on given amount of floors
	private int currentFloor;
	/*
	Using Queues as the data structure to keep track of the Passengers that are 
	coming in & out of the elevator. This gives us flexibility to deal with situations
	where user1 wants floor 9, but user2 (who entered recently) needs floor 7.
	Will allow stops before it reaches 9 (if going/meeting in that direction).
	*/
	private Queue<Passenger> passengers;

	//Constructors
	public Elevator(int capacity){
		this.capacity = capacity;
		//Starting point for the elevators
		this.currentFloor = 0;
		this.passengers = new LinkedList<>();
	}

	//Loading passengers into elevator
	public void load(Passenger passenger){
		passengers.add(passenger);
	}
	//Unloading passengers from elevator if elevator is in destination floor of the passenger
	public void unload(){
		//Allows us to remove passengers that meet destination floor criteria
		Iterator<Passenger> iterator = passengers.iterator();
		while(iterator.hasNext()){
			Passenger passenger = iterator.next();
			if(passenger.getef() == currentFloor){
				iterator.remove();
			}
		}
	}

	//Moving elevator to next floor
	public void nextFloor(int floor){
		currentFloor = floor;
	}
	//Checking if elevator has max passenger capacity
	public boolean maxCap(){
		return passengers.size() >= capacity;
	}
}//End of Elevator class


class Floor{
	private int totalFloors;
	//The following Lists allows each elevator keep track of its own floor progress
	private List<Elevator> elevators;
	private List<Queue<Passenger>> floorQueue;

	//Constructor for floor
	public Floor(int totalFloors, int totalElevators, int elevatorCap){
		this.totalFloors = totalFloors;
		this.elevators = new ArrayList<>();
		this.floorQueue = new ArrayList<>();

		//Initializing elevator movement & amount based on file request
		for(int i = 0; i < totalElevators; i++){
			elevators.add(new Elevator(elevatorCap));
		}
		//Initializing floor queues/requests 
		for(int i = 0; i < totalFloors; i++){
			floorQueue.add(new LinkedList<>());
		}
	}

	//Dealing with individual passenger arrivals for each floor
	public void arrivals(Passenger passenger){
		//Adding passenger intial floor & adding to queue as request
		floorQueue.get(passenger.getsf()).add(passenger);
	}
	//Getter methods for Floor
	public int getFloor(){
		return totalFloors;
	}
	public List<Elevator> getElevator(){
		return elevators;
	}
	public List<Queue<Passenger>> getFQ(){
		return floorQueue;
	}
}//End of Floor class

class Simulation{
	private Floor floor;
	//Track the amount of time left
	private int concurringTicks;
	//Track total initial time given
	private int ticks;
	private double passengers;

	//Constructors
	public Simulation(int totalFloors, int totalElevators, int elevatorCap, int ticks, double passengers){
		this.floor = new Floor(totalFloors, totalElevators, elevatorCap);
		this.concurringTicks = 0;
		this.ticks = ticks;
		this.passengers = passengers;
	}

	/*
	The following process incorporates creating randomized passengers as given in the file which will
	have a destination set in mind & might appear based on given file probabilies
	*/
	private Passenger newPassenger(){
		Random rand = new Random();
		int sf = rand.nextInt(floor.getFloor());
		int ef;
		do{
			//Continue in elevator trajectory
			ef = rand.nextInt(floor.getFloor());
		} while (ef == sf);//Until destination is met

		//Tracking total time passenger lasted in elevator
		return new Passenger(ticks, sf, ef, ticks);
	} 

	public void execute(){
		//While tick does not reach given amount
		for(int tick = 0; tick < ticks; tick++){
			if(Math.random() < passengers){
				//Creating passenger based on probability given
				Passenger passenger = newPassenger();
				floor.arrivals(passenger);
			}

			//Moving elevators & passengers loading/unloading process per tick
			List<Elevator> elevators = floor.getElevator();
			List<Queue<Passenger>> floorQueue = floor.getFQ();
		}
	}

	//Printing simulation results
	public void results(){
		double average = 0.0;
		double longTime = 0.0;
		double shortTime = 0.0;

		System.out.println("Average wait time: " + average);
		System.out.println("Longest wait time" + longTime);
		System.out.println("Shortest wait time" + shortTime);
	}

}

public class elevatorProgram{
	public static void main(String[] args) {

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

		//Creating instance of simulation & executing it
		Simulation run = new Simulation(floors, elevators, elevatorCap, ticks, passengers);
		run.execute();
		run.results();
	}
}