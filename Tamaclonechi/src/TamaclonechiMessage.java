/**
 * File:TamaclonechiMessage.java
 * * purpose: to pass information from model to view and save pet
 * @author Austin Connick 
 */

import java.io.Serializable;

public class TamaclonechiMessage implements Serializable {

	private int age;
	private int health;
	private int weight;
	private int happiness;
	private int lastAte;
	private boolean isSick;
	private int sickFor;
	private boolean isAlive;
	private long time;
	private boolean defecate;
	private String name;
	private String background;

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for TamaclonechiMessage
	 * 
	 * @param age       the age of pet
	 * @param health    the health of pet
	 * @param weight    the weight of pet
	 * @param happiness the happiness of pet
	 * @param lastAte   increments since the pet ate last
	 * @param isSick    is the pet sick
	 * @param sickFor   amount of increments the pet has been sick
	 * @param isAlive   is the pet alive
	 */
	public TamaclonechiMessage(int age, int health, int weight, int happiness, int lastAte, boolean isSick, int sickFor,
			boolean isAlive,long time ,String name, boolean defecate, String background) {
		this.age = age;
		this.health = health;
		this.weight = weight;
		this.happiness = happiness;
		this.lastAte = lastAte;
		this.isSick = isSick;
		this.sickFor = sickFor;
		this.isAlive = isAlive;
		this.time = time;
		this.name = name;
		this.defecate = defecate;
		this.background = background;
	}

	/**
	 * getAge return the age of the pet
	 * 
	 * @return age the age of pet
	 */
	public int getAge() {
		return age;
	}

	/**
	 * getHealth returns age of pet
	 * 
	 * @return health the health of pet
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * getWeight returns the weight of the pet
	 * 
	 * @return weight the weight of pet
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * getHappiness returns the weight of the pet
	 * 
	 * @return happiness the happiness of the pet
	 */
	public int getHappiness() {
		return happiness;
	}

	/**
	 * getLastAte returns the amount of increments since the pet last ate
	 * 
	 * @return lastAte the number of increments since the pet last ate
	 */
	public int getLastAte() {
		return lastAte;
	}

	/**
	 * getIsSick returns if the pet is sick
	 * 
	 * @return isSick the sick status of the pet
	 */
	public boolean getIsSick() {
		return isSick;
	}

	/**
	 * getSickFor returns if the time the pet has been sick
	 * 
	 * @return sickFor the amount of increments the pet has been sick
	 */
	public int getSickFor() {
		return sickFor;
	}

	/**
	 * getIsAlive returns is the pet is still alive
	 * 
	 * @return isAlive is the pet is still alive
	 */
	public boolean getIsAlive() {
		return isAlive;
	}
	
	/**
	 * getName returns is the name of the pet
	 * 
	 * @return name of the pet
	 */
	public String getName() {
		return name;
	}
    /**
     * returns time stamp of save
     * @return
     */
	public long getTime() {
		// TODO Auto-generated method stub
		return time;
	}
	/**
	 * returns if pet has defecated
	 * @return defecate if pet has defecated
	 */
	public boolean getDefecate() {
		return defecate;
	}
	/**
	 * returns the back ground of pet
	 * @return background used by the pet
	 */
	public String getBackground() {
		return background;
	}

}
