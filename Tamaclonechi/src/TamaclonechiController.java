/**
 * FILE: TamaclonechiController.java
 * ASSIGNMENT: Final Project - Tamaclonechi
 * COURSE: CSc 335; Fall 2020;
 * PURPOSE: The  following class controls the TamaclonechiModel instance. 
 * This class is used by the TamaclonechiGUI to modify the stats of the
 * tamaclonechi displayed in its interface. 
 * 
 * @author Austin Connick
 * @author Noah Silverman
 * @see TamaclonechiGUI
 * @see TamaclonechiModel
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Random;

import javafx.application.Platform;

public class TamaclonechiController implements Runnable {
	private TamaclonechiModel pet;
	
    /**
     * Constructor used to fill model used by controller
     * @param model of tama pet
     */
	public TamaclonechiController(TamaclonechiModel model) {
		pet = model;
	}

	
	/**
	 * ages the pet
	 */
	public void age() {
		pet.age();
	}

	/**
	 * Feeds the tamaclonechi by increasing it weight and happiness.
	 */
	public void eat() {
		pet.changeWeight(5);
		pet.changeHappiness(5);
		pet.setLastAte(0);
	}

	/**
	 * Tries to give the pet medicine. Has a chance to fail.
	 * 
	 * @return if the medicine successfully healed the pet or not
	 */
	public boolean medicine() {
		// You can only use medicine if the pet is sick.
		if (pet.getIsSick()) {
			Random rand = new Random();
			int healNum = rand.nextInt(100);
			// Tests a random number to see if the medicine was successful. Medicine has an
			// 80% success rate. If it is successful the pet's health jumps up and the pet
			// is no longer sick.
			if (healNum < 80) {
				pet.changeHealth(50);
				pet.getWell();
				return true;
			} // If the medicine is not successful the pet still gets a small boost to health.
			else {
				pet.changeHealth(10);
				return false;
			}
		}
		return false;
	}
	
    /**
     * Calls change methods to alter pet status in terms of 
     * factors related to sleeping.
     */
	public void sleep() {
		//fasting during sleep duration
		pet.changeWeight(-5);
		//overall increase to health and happiness
		pet.changeHappiness(5);
		pet.changeHealth(10);
	}
	
    /**
     * Calls change methods to alter pet status in terms of 
     * factors related to being in a clean environment.
     */
	public void clean() {
		if(pet.getBowelMovement()) {
			//overall increase to health and happiness
			pet.changeHappiness(5);
			pet.changeHealth(10);
			//reset
			pet.setBowelMovement(false);
		} 
	}
	
    /**
     * Calls change methods to alter pet status in terms of 
     * factors related to dancing.
     */
	public void dance() {
		//overall increase to health and happiness
		pet.changeHappiness(15);
		pet.changeHealth(15);
		//physically activity decrease to weight
		pet.changeWeight(-10);
	}

	/**
	 * Does various checks on the pets health to determine if the pet is sick or
	 * dead
	 */
	public void checkHealth() {
		if (pet.getHealth() == 0) {
			pet.kill();
		}
		pet.alert();
		// Checks the pet's weight. 81/100 and over is considered over weight, 19/100
		// and under is under weight. If the pet is not a healthy weight, the health
		// will drop by 10 points
		if (pet.getWeight() > 80 || pet.getWeight() < 20) {
			pet.changeHealth(-10);
		}
		// Check to see if the pet is hungry. If the pet's last meal was 5 or more
		// increments ago, the pet loses 5 health
		if (pet.getLastAte() > 5) {
			pet.changeHealth(-5);
		}
		// Checks the health of the pet. If the health falls below 30/100 there is a
		// random chance the pet will become sick.
		if (pet.getHealth() < 30) {
			// Determines the chance of the pet becoming sick. As the pet's health and happiness get's
			// lower, the chance for getting sick becomes higher.
			int chance = ((100 - pet.getHealth()) + (100 - pet.getHappiness()) / 2);
			Random rand = new Random();

			int sickNum = rand.nextInt(100);
			// Tests a random number against the chance of the pet getting sick. If the
			// chance of getting sick is 75%, then there is a 75% chance that the random
			// number chosen will be less than 75
			if (sickNum < chance) {
				pet.getSick();
			}
		}
		// If the pet is healthy it becomes happier.
		if (pet.getHealth() > 60) {
			pet.changeHappiness(15);
		}
		// Checks if the pet is sick, if it is increments the counter of sickness.
		if (pet.getIsSick()) {
			pet.stillSick();
		}
		// If the pet has been sick for 5 increments the pet dies
		if (pet.getSickFor() >= 5) {
			pet.kill();
		}
	}
	
	/**
     * Saves current model in object at default location of project
     * 
     */
    public void saveGame() {
    	try {
    		Date tmp2 = new Date();
    		TamaclonechiMessage tmp = new TamaclonechiMessage(pet.getAge(), pet.getHealth(), pet.getWeight(), pet.getHappiness(), pet.getLastAte(), pet.getIsSick(), pet.getSickFor(),
    				pet.getIsAlive() , tmp2.getTime(), pet.getName(), pet.getBowelMovement(),pet.getBackground());
    		FileOutputStream fs = new FileOutputStream("GameSave");
    		ObjectOutputStream os = new ObjectOutputStream(fs);
    		os.writeObject(tmp);
    		os.close();
    		fs.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * loads save game into current model
     */
    public void loadSave(String background) {
    	try {
    		
			FileInputStream fs = new FileInputStream("GameSave");
			ObjectInputStream os = new ObjectInputStream(fs);
			TamaclonechiMessage tmp2 = new TamaclonechiMessage(0, 0, 0, 0, 0, true,0, true , 0,"",false,"");
			tmp2 = (TamaclonechiMessage) os.readObject();
			os.close();
			fs.close();
			pet.setAge(tmp2.getAge());
			pet.setHappiness(tmp2.getHappiness());
			pet.setHealth(tmp2.getHealth());
			pet.setLastAte(tmp2.getLastAte());
			pet.setSickFor(tmp2.getSickFor());
			pet.setIsAlive(tmp2.getIsAlive());
			pet.setIsSick(tmp2.getIsSick());
			pet.setWeight(tmp2.getWeight());
			pet.setName(tmp2.getName());
			pet.setBackground(background);

			Date tmp3 = new Date();
			long curr = tmp3.getTime();
			long last = tmp2.getTime();
			
			long past = curr - last;

			updateTime(past);
			pet.setTime(curr);
			pet.alert();
		} catch (Exception e) {
			System.out.println("Save not found");
			saveGame();
			return;
		}
    }
    
    /**
     * updates time progress of pet when reading in game save
     * @param past the time in milliseconds that have past
     */
    public void updateTime(long past) {
    	int hours = (int) (past / (1000*60*60));
    	for(int i = 1; i <= hours; i++) {
    		if(i % 5 == 0) {
    			int age = pet.getAge();
    			age++;
    			pet.setAge(age);
    		}
    		if(i % 3 == 0) {
    			int happeness = pet.getHappiness();
    			happeness = happeness - 10;
               pet.setHappiness(happeness);
    		}
    		if(i % 3 == 0) {
    			int health = pet.getHealth();
    			health = health - 10;
               pet.setHealth(health);
    		}
    		if(i % 3 == 0) {
    			int weight = pet.getWeight();
    			if(weight > 10) {
    				weight = weight - 10;
               pet.setWeight(weight);
    			}
    		}
    		
    	}
    	
    	if(hours > 1) {
	    	pet.setBowelMovement(true);
	    	checkHealth();
    	}
    	pet.alert();
    }
    
    /**
     * Returns if the pet is alive
     * @return isAlive if pet is alive
     */
	public boolean getIsAlive() {
		return pet.getIsAlive();
	}
	
    /**
     * Returns if pet is sick
     * @return isSick if pet is sick
     */
	public boolean getIsSick() {
		return pet.getIsSick();
	}

    /**
     * Returns the name of the tamaclonechi instance.
     * 
     * @return name is a string to identify the pet.
     */
	public String getName() {
		return pet.getName();
	}
	
    /**
     * Returns the age of the tamaclonechi instance.
     * 
     * @return age is an integer to represent time since birth of the pet.
     */
	public int getAge() {
		return pet.getAge();
	}
	
    /**
     * Returns the weight of the tamaclonechi instance.
     * 
     * @return weight is an integer to represent body mass of pet.
     */
	public int getWeight() {
		return pet.getWeight();
	}
	
    /**
     * Returns the health of the tamaclonechi instance.
     * 
     * @return health is an integer to represent the pet's wellbeing.
     */
	public int getHealth() {
		return pet.getHealth();
	}
	
    /**
     * Returns the happiness of the tamaclonechi instance.
     * 
     * @return happiness is an integer to represent satisfaction of the pet.
     */
	public int getHappiness() {
		return pet.getHappiness();
	}
	
	/**
	 * Returns the background of pet
	 * @return pets background
	 */
	public String getBackground() {
		return pet.getBackground();
	}

	/**
	 * Creates a new game, by re-initializing the model.
	 * 
	 * @param name is the name of the pet.
	 */
	public void newGame(String name, String background) {
		pet.newGame(name,background);
	}
	
	/**
	 * Starts time progress when game is running
	 */
	public void startClock() {
		Thread time = new Thread(this);
		time.setDaemon(true);
		time.start();
	}

	/**
	 * Runnable for time progression.
	 */
    @Override
	public void run() {
			try {
				int years = pet.getAge();
				int min = 0;
				while(true) {
					if(pet.getIsAlive()) {
					min++;
					// every 5 hours pet ages one year
					if((min * 4) % 5 == 0 && min != 0) {
					years++; 
					pet.setAge(years);
					saveGame();
					
					}	
					//update happeness ever 3 hours
					if((min * 4) % 3 == 0 && min != 0) {
		    			int happeness = pet.getHappiness();
		    			if(happeness > 10) {
		    			happeness = happeness - 10;
		               pet.setHappiness(happeness);
		    			}
		    		}
					//update health every 3 hours
		    		if((min * 4) % 3 == 0 && min != 0) {
		    			int health = pet.getHealth();
		    			if(health > 20) {
		    			health = health - 10;
		               pet.setHealth(health);
		    			}
		    		}
		    		//update weight every 3 hours
		    		if((min * 4) % 3 == 0 && min != 0) {
		    			int weight = pet.getWeight();
		    			if(weight > 10) {
		    				weight = weight - 10;
		               pet.setWeight(weight);
		    			}
		    		}
		    		   
				    //lifespan
				    if(years == 80){
				    	 Platform.runLater(() -> {
							pet.setIsAlive(false);
							pet.alert();
				    	 });
				    }
					}
				    Thread.sleep(50 * 1000);
				    
		    			Platform.runLater(() -> {
		    				if(getIsAlive()) {
			    				pet.setBowelMovement(true);
			    				checkHealth();
							    pet.alert();
		    				}
					 });
		    			

				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
