// import drivers
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


class Bottle {

    // Number of pills
    private int Number = 0;
    // number of days 
    private int Days = 0;  
    //Array fot w/h pills
    private ArrayList<Pill> pills;
  
  
    public int[] Whole; //whole pills in bottle
    public int[] Half; //half pills in bottle
    private Random rand;  
    
    public Bottle(int Number){
      this.Number = Number; //set number of pills
      this.Days = 0;
  
      this.Whole = new int[2*Number];
      this.Half = new int[2*Number];
      this.pills = new ArrayList<Pill>();
  
      for(int i=0; i < Number; i++){
        pills.add(Pill.Whole); //add one whole pill
      }
      this.Whole[0] = Number;  //first day
  
      this.rand = new Random();
    }
  
    public int numDoses(){
      return 2*Number;
    }
  
    public boolean isEmpty(){
      return (pills.size() == 0);
    }
  
    // take a pill
    public Pill take(){
  
      if(isEmpty()){
        return Pill.None;
      }
  
      // random pill 
      int r = rand.nextInt() % pills.size();
      if(r < 0){  
        r *= -1;  //make it random number to positive
      }
  
      Pill p = pills.remove(r);
      if(p == Pill.Whole){ 
        this.Whole[Days]--; //take one whole pill 
        this.Half[Days]++; //take one half pill 
        pills.add(Pill.Half); //add one half pill to the bottle
      }else{
        this.Half[Days]--; //take one whole pill away
      }
  
      this.Days++; //increase the days
      if(this.Days < numDoses()){
        this.Whole[this.Days] = this.Whole[this.Days - 1];  //whole pills for tomorrow
        this.Half[this.Days] = this.Half[this.Days - 1];  //half pills for tomorrow
      }
  
      return p;
    }
  }

  enum Pill {
	Half("H"), //whole pill
	Whole("W"),  //half pill
	None("empty");  //in case if the bottle is empty
  
	public final String label;
  
	private Pill(String label) {
		  this.label = label;
	  }
  }
  

class Main {

  // Maximum number of Pills for every bottles
  private static int N = 0; 
  // Maximum number of bottles
  private static int S = 0; 
  // Maximum pills and bottles limit given by the client for their user
  private static int maximumPills = 1000;
  private static int maximumBottles = 1000;

  //Main driver
  public static void main(String[] args) {
	userDescription();
	try { //wait till the user reads the description
		Thread.sleep(10000);
	} catch (InterruptedException ex){
		ex.printStackTrace();
	}
    Scanner input = new Scanner(System.in);
    // UI get the number of pills
    while(true){
      System.out.printf("\nEnter the number of fixed Pills N [1 - %d]:", maximumPills);
      N = input.nextInt();
      if((N > 0) && ( N < maximumPills)){
        break;
      }
	  else{
      System.out.println("Error: N not in range!"); // Print error
	  }
    }
    // UI get number of Bottles
    while(true){
      System.out.printf("\nEnter number of bottles S [1 - %d]:", maximumBottles);
      S = input.nextInt();
      if((S > 0) && (S < maximumBottles)){
        break;
      }
	  else {
      System.out.println("Error: S not in range!"); // Print error
	  }
    }
    input.nextLine();
	// average
    Bottle average = new Bottle(N);
	try { //just wait 
		Thread.sleep(1000);
	} catch (InterruptedException ex){
		ex.printStackTrace();
	} 

	System.out.println("\nStimulating.....\n");
	try { //just wait 
		Thread.sleep(3000);
	} catch (InterruptedException ex){
		ex.printStackTrace();
	} 

    //simulation loop
    for(int s = 0; s < S; s++){
      Bottle b = new Bottle(N); 
		int axcel = s + 1;
      // start the stimulation per bottle per pill
      System.out.printf("\nStimulation number %d:   Pill taken like: ", axcel);
      while(!b.isEmpty()){
          Pill p = b.take();
          System.out.printf(p.label);
      }
      System.out.println("\n");

      // print the executed stimulation
      System.out.printf("%-20s %20s %20s %20s\n", "Day", Pill.Whole.label, Pill.Half.label, "Ratio");
      for(int i=0; i < b.numDoses(); i++){
        float rat = (b.Whole[i] == 0) ? 1.0f : (float) b.Half[i] / b.Whole[i];
        System.out.printf("%-20d %20d %20d %20.2f\n", i, b.Whole[i], b.Half[i], rat);
      }

      //udpate the average
      for(int i=0; i < b.numDoses(); i++){
        average.Whole[i] = b.Whole[i];
        average.Half[i] = b.Half[i];
      }

		try { //wait till the user reads the description
			Thread.sleep(3000);
		} catch (InterruptedException ex){
			ex.printStackTrace();
		}
    }


    // Step 4. Print average 
    System.out.println("\n## Average for all simulations\n");
    System.out.printf("%-20s %20s %20s %20s\n", "Day", Pill.Whole.label, Pill.Half.label, "Ratio");
    for(int i=0; i < average.numDoses(); i++){
      float whole = (float) average.Whole[i] / S;
      float half = (float) average.Half[i] / S;
      float rat = (whole == 0) ? 1.0f : (float) half / whole;
      System.out.printf("%-20d %20.2f %20.2f %20.2f\n", i, whole, half, rat);
    }

	try { //wait till the user reads the description
		Thread.sleep(3000);
	} catch (InterruptedException ex){
		ex.printStackTrace();
	}
	System.out.println("\n\nPress enter to exit.");
    input.nextLine();
  }

  static void userDescription(){
	System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
	System.out.println("* This ia HW3 of COMPSCI 4500 FA 2021. It is a pill bottle stimulation app  *");
	System.out.println("* designed for users to insert number of bottles and one fixed number of    *");
	System.out.println("* pills for every bottle. The stimulation will show how randomly the pills  *");
	System.out.println("* selected. The user will cut a random pill in half and eat it while the    *");
	System.out.println("* other half is placed back. Same follows around until the bottle is empty. *");
	System.out.println("* The output will show how and what pills were selected to be consumed.     *");
	System.out.println("* Enjoy.                                                                    *");
	System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
	System.out.println("\n");
	}
}
