/** 
* FILE: TamaclonechiModel.java
* ASSIGNMENT: Final Project - Tamaclonechi
* COURSE: CSc 335; Fall 2020;
* PURPOSE: The following class is used To represent the model of the tamaclonechi game. It handles all of
* private variables that are associated with a tamaclonechi pet, such as the age, health, happiness, weight,
* the last time that the pet ate, if the pet is sick or not, how long its been sick, if its alive, its
* bowel movement status, its location, and whether or not its alive or not. 
* 
* @author Fernando Ruiz
* @author Austin Connick
* @author David Izmailov
* @author Noah SilverMan
* @see TamaclonechiGUI
*/

import java.util.Observable;

public class TamaclonechiModel extends Observable {
	private int age;
	private int health;
	private int weight;
	private int happiness;
	private int lastAte;
	private boolean isSick;
	private int sickFor;
	private boolean isAlive;
	private long time;
	private String name;
	private boolean defecate;
	private String background;
	
	/**
	 * Creates a new pet with base stats
	 */
	public TamaclonechiModel() {
		name = "Kuchipatchi";
		age = 0;
		health = 75;
		weight = 50;
		happiness = 50;
		lastAte = 0;
		isSick = false;
		sickFor = 0;
		time = 0;
		isAlive = true;
		defecate = false;
		background = "Default";
	}

	/**
	 * When the age method is called the pet's age is incremented by 1.
	 * 
	 */
	public void age() {
		age++;
		alert();
	}

	/**
	 * Can add or subtract health from the pet (to subtract make change negative).
	 * Health will not drop below 0 or go above 100
	 * 
	 * @param change amount to add or subtract (use negative) to health
	 */
	public void changeHealth(int change) {
		if (health == 0) {
			isAlive = false;
			alert();
			return;
		}
		health += change;
		health = Math.min(health, 100);
		health = Math.max(health, 0);
		alert();
	}

	/**
	 * Can add or subtract weight from the pet (to subtract make change negative).
	 * Weight will not drop below 0 or go above 100
	 * 
	 * @param change amount to add or subtract (use negative) to weight
	 */
	public void changeWeight(int change) {
		weight += change;
		weight = Math.min(weight, 100);
		weight = Math.max(weight, 0);
		alert();
	}

	/**
	 * Can add or subtract happiness from the pet (to subtract make change
	 * negative). Happiness will not drop below 0 or go above 100
	 * 
	 * @param change amount to add or subtract (use negative) to happiness
	 */
	public void changeHappiness(int change) {
		happiness += change;
		happiness = Math.min(happiness, 100);
		happiness = Math.max(happiness, 0);
		alert();
	}

	/**
	 * Makes the pet sick
	 */
	public void getSick() {
		isSick = true;
		alert();
	}

	/**
	 * If the pet is still sick the sickFor is incremented
	 */
	public void stillSick() {
		isSick = true;
		sickFor++;
		changeHappiness(-10);
		alert();
	}

	/**
	 * Makes the pet not sick Also resets the sick counter
	 */
	public void getWell() {
		isSick = false;
		sickFor = 0;
		alert();
	}

	/**
	 * Makes the pet not alive
	 */
	public void kill() {
		isAlive = false;
		alert();
	}
	
	/**
	* Sends message with all instance fields to the observers of this class.
    *
    */
	public void alert() {
		TamaclonechiMessage msg = new TamaclonechiMessage(this.age, this.health, this.weight, this.happiness,
				this.lastAte, this.isSick, this.sickFor, this.isAlive,this.time, this.name,this.defecate,this.background);
		setChanged();
		notifyObservers(msg);
	}
	
    /**
     * Returns the name of the tamaclonechi instance.
     * 
     * @return name is a string to identify the pet.
     */
	public String getName() {
		return this.name;
	}
	
    /**
     * Returns the age of the tamaclonechi instance.
     * 
     * @return age is an integer to represent time since birth of the pet.
     */
	public int getAge() {
		return this.age;
	}
	
    /**
     * Returns the weight of the tamaclonechi instance.
     * 
     * @return weight is an integer to represent body mass of pet.
     */
	public int getWeight() {
		return this.weight;
	}
	
    /**
     * Returns the health of the tamaclonechi instance.
     * 
     * @return health is an integer to represent the pet's wellbeing.
     */
	public int getHealth() {
		return this.health;
	}
	
    /**
     * Returns the happiness of the tamaclonechi instance.
     * 
     * @return happiness is an integer to represent satisfaction of the pet.
     */
	public int getHappiness() {
		return this.happiness;
	}
	
    /**
     * Returns the whether or not the pet needs to use the restroom.
     * 
     * @return age is an integer to represent time since birth of the pet.
     */
	public boolean getBowelMovement() {
		return this.defecate;
	}
	
	/**
	 * Returns the current time of the function.
	 * 
	 * @return time is a long to represent the time the pet has been active.
	 */
	public long getTime() {
		return this.time;
	}
	
    /**
     * sets the status of the pet's bowel movements
     * 
     * @param flag is a boolean
     */
	public void setBowelMovement(boolean flag) {
		this.defecate = flag;
	}

    /**
     * set age of pet using when loading a save game
     * @param age of pet 
     */
	public void setAge(int age) {
		this.age = age;
	}
	
    /**
     * set happiness of pet using when loading a save game
     * @param happiness of pet
     */
	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}
	
    /**
     * set health of pet using when loading a save game
     * @param health of pet
     */
	public void setHealth(int health) {
		this.health = health;
		if (health == 0) {
			isAlive = false;
		}
	}
	
    /**
     * set lastAte of pet using when loading a save game
     * @param lastAte of pet
     */
	public void setLastAte(int lastAte) {
		this.lastAte = lastAte;
	}
	
    /**
     * set sickFor of pet using when loading a save game
     * @param sickFor of pet
     */
	public void setSickFor(int sickFor) {
		this.sickFor = sickFor;
	}
	
    /**
     * set isAlive of pet using when loading a save game
     * @param isAlive of pet
     */
	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
    /**
     * set isSick of pet using when loading a save game
     * @param isSick of pet
     */
	public void setIsSick(boolean isSick) {
		this.isSick = isSick;
	}
	
    /**
     * set weight of pet using when loading a save game
     * @param weight of pet
     */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
     * set name of pet
     * 
     * @param name of pet
     */
	public void setName(String name) {
		this.name = name;
		alert();
	}
	
	/**
     * Sets the current time using the passed in time
     * 
     * @param curr is a long to rep. time.
     */
	public void setTime(long curr) {
		this.time = curr;
	}
	
	/**
	* Returns when the tamagotchi last ate.
    *
    * @param lastAte is an integer rep. for when the pet last ate.
    */
	public int getLastAte() {
		return lastAte;
	}

	/**
	* Returns the amount of time the tamagotchi has been sick.
    *
    * @param sickFor is the number of times the pet has gotten sick.
    */
	public int getSickFor() {
		return sickFor;
	}

	/**
	* Returns a boolean of whether or not the tamagotchi is Alive.
    *
    * @param isAlive is boolean flag based on whether the pet is Alive.
    */
	public boolean getIsAlive() {
		return isAlive;
	}
	
	/**
	* Returns a boolean of whether or not the tamagotchi is sick.
    *
    * @param isSick is boolean flag based on whether the pet is sick.
    */
	public boolean getIsSick() {
		return isSick;
	}
	
	 /**
	 * Returns the background/location of the tamagotchi.
     *
     * @param background is a string rep. of the tamagotchi's location/background
     */
	public String getBackground() {
		return background;
	}
	
	 /**
     * Sets the location.background of the tamagotchi
     *
     * @param newBackground is a String name for an image
     */
	public void setBackground(String newBackground) {
		this.background = newBackground;
		alert();
	}
	
	/**
	* Creates a new game by resting the fields of the instance.
    *
    * @param newName is the name set by the user for the tamagatchi.
    * @param newBackground is the name of the background/location set by the user.
    */
	public void newGame(String newName, String newBackground) {
		name = newName;
		age = 0;
		health = 75;
		weight = 50;
		happiness = 50;
		lastAte = 0;
		isSick = false;
		sickFor = 0;
		time = 0;
		isAlive = true;
		defecate = false;
		background = newBackground;
		alert();
	}
}
