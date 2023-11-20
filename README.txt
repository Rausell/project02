Angel Lopez
Project02 README

For this project I decided to follow the data structures of Queues & ArrayLists. For a more close step by step execution of the program, comments are added to blocks of code detailing what each section is meant to do. In essence, upon reading from a file the values to run the simulation (or using the default values if needed or if file misses some parts), the program then creates instances of people, elevators, & floors as requested or given. The code also deals with any errors the file may provide when reading by implementing a try & catch statement.

The program then passes these given values to the simulation to fill in/create the amount of elevators & floor limits needed to run its program.

For class Passenger, we create details that the program will need to keep track of the person's initial floor (where the person got inside the elvator), the person's destination (where they will leave the elevator), & a tick counter (how long it took the person to reach their destination). We incorporated an id to keep track of when they've left & delete them, but did not add it into the simulation itself as its a bit tricky to keep tabs on all passengers (as later explained in the simulation process). For class passenger we also included getter functions to call on these stats during the simulation as they are 'created' during every tick.

For class Elevator we incorporated a Queue structure that will add passengers if the probability causes the person to be created at a certain tick. Since the passengers will be randomly assigned to a destination floor, a queue optimizes the ability to unload passengers as they get to their destination & get in new passengers that might spawn based on given probability (this does vary on probability of a passenger appearing & the capacity of the elevator).

In class Elevator we also have the action to move the elevator up or down (depending on its current trajectory) as a function nextFloor which is updated in the run portion of the Simulation class. This ensures that the action to move the elevator is not disrupted by any unrelated factors such as loops associated to the tick counter as well as loops to check max capacity on the elevators.

Within class Floors we have the ArrayLists floor & elevator to keep track of the location the elevator is in as well as using the floor to match the passenger's final destination. These are both equipped with getter methods that do certain actions such as obtaining the elevator's current position, any entering passengers (labeled as arrivals), any departing passengers, as well as initializing both ArrayLists to be used in the execution process.

In the simulation class we initialize & keep track of the ongoing increasing tick as well as the original tick we were given. This also includes the given variables we were provided (or that the program's default option provided) for passenger, floors, elevators, passenger probability, elevator max capacity & the duration/ticks the simulation will take.

For every tick we randomize a value based on the min of 0 & max assigned by passenger. For every randomized number that is less than given passenger probability we create a passenger assigning it to a randomized starting point in the amount of floors given as well as assigning them with a randomized floor for which the elevator should head towards. 

Alongside the randomization we also update the elvator & passenger ArrayList/Queue at each tick to continue the timely manner of running the simulation. This of course then leads to the waitTime being updated that is used to estimate the average, long, & short time highlight that the simulation provded after the tick timer ends. Which is printed back to the user.

When running the program you will see first the settings of the simulation (if read from a file the udpated values should be given otherwise the default values will be given). After the simulation you should also see a printed statement detailing the average time, long time, & short time that occurred during that iteration of the simulation.

Some testing cases I implemented was including multiple variable lines (such as have three ticks in the file), to determine which one is taking priority (the last one is). I also attempted to use spaces betweek key & value terms as well as skip lines which the Properties module detected & removed them from causing any errors.

Overall the program should succeed in create instances of floors, elevators, passengers, & create passengers based on the given probabilities. The only issues I've faced was in the simulation process -- essentially how to tie it all together at every tick & ensure the the waitTime could keep track of each individual passenger to keep track of the long & short time. I do hope that my program can at least show the endgoal I am aiming at. 

It would be fun to add some graphical image to it (which java can provide through one of the modules), just to see the elevators going up & down & some people appearing/disappearing as they move around. That might be a personal project for later, but in the meantime the program should contain more line by line explanation as to what each part does.

The resources used for this program are listed below:

Constructor overview/refresher:
(https://www.youtube.com/watch?v=HnQs4K1NRmU&t=243s)
(https://www.youtube.com/watch?v=pgBk8HC7jbU)

Reading files overview/refresher:
(https://www.youtube.com/watch?v=lHFlAYaNfdo&t=300s)

Java documentation Constructors:
(https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Constructor.html)

Java documentation Iterator:
(https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html)

Class provided geeksForGeeks resource:
(https://www.geeksforgeeks.org/java-util-properties-class-java/)


