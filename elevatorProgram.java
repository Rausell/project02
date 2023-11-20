//Angel Lopez Project02 
//Importing needed modules
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.*;

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

				file.close();
			} catch (IOException e){

			}
		}

	}
}