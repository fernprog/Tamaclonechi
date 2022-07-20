/** 
* FILE: TamaclonechiTests.java
* ASSIGNMENT: Final Project Tamaclonechi
* COURSE: CSc 335; Fall 2020;
* PURPOSE: The following class is used to test the controller, model, message,
* and sprite animation. These test cases achieve a >90% code coverage for all
* non-GUI, and non-TimeRelated code.
* 
* @author David Izmailov
*/

import static org.junit.jupiter.api.Assertions.*;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.Test;

public class TamaclonechiTests {
	
	private TamaclonechiModel model;
	private TamaclonechiController controller;
	
	// ----- MODEL TEST CASES -----
	
	/**
	 * Test the model constructor
	 */
	@Test
	public void testModelDefaultConstructor() {
		model = new TamaclonechiModel();
		assertEquals(model.getName(), "Kuchipatchi");
		assertEquals(model.getAge(), 0);
		assertEquals(model.getHealth(), 75);
		assertEquals(model.getWeight(), 50);
		assertEquals(model.getHappiness(), 50);
		assertEquals(model.getLastAte(), 0);
		assertFalse(model.getIsSick());
		assertEquals(model.getSickFor(), 0);
		assertTrue(model.getIsAlive());
		assertFalse(model.getBowelMovement());
		assertEquals(model.getBackground(), "Default");
	}
	
	/**
	 * Test the age pet method in the model
	 */
	@Test
	public void testModelAgePet() {
		model = new TamaclonechiModel();
		assertEquals(model.getAge(), 0);
		model.age();
		assertEquals(model.getAge(), 1);
	}
	
	/**
	 * Test the change health method in the model
	 */
	@Test
	public void testModelChangeHealth() {
		model = new TamaclonechiModel();
		assertEquals(model.getHealth(), 75);
		model.changeHealth(65);
		assertEquals(model.getHealth(), 100);
		model.changeHealth(-200);
		assertEquals(model.getHealth(), 0);
	}
	
	/**
	 * Test the change weight method in the model
	 */
	@Test
	public void testModelChangeWeight() {
		model = new TamaclonechiModel();
		assertEquals(model.getWeight(), 50);
		model.changeWeight(65);
		assertEquals(model.getWeight(), 100);
		model.changeWeight(-200);
		assertEquals(model.getWeight(), 0);
	}
	
	/**
	 * Test the change happiness method in the model
	 */
	@Test
	public void testModelChangeHappiness() {
		model = new TamaclonechiModel();
		assertEquals(model.getHappiness(), 50);
		model.changeHappiness(65);
		assertEquals(model.getHappiness(), 100);
		model.changeHappiness(-200);
		assertEquals(model.getHappiness(), 0);
	}
	
	/**
	 * Test the get sick method in the model
	 */
	@Test
	public void testModelGetSick() {
		model = new TamaclonechiModel();
		assertFalse(model.getIsSick());
		model.getSick();
		assertTrue(model.getIsSick());
	}
	
	/**
	 * Test the still sick method in the model
	 */
	@Test
	public void testModelStillSick() {
		model = new TamaclonechiModel();
		assertEquals(model.getSickFor(), 0);
		assertFalse(model.getIsSick());
		assertEquals(model.getHappiness(), 50);
		model.getSick();
		model.stillSick();
		assertEquals(model.getSickFor(), 1);
		assertTrue(model.getIsSick());
		assertEquals(model.getHappiness(), 40);
	}
	
	/**
	 * Test the get well method in the model
	 */
	@Test
	public void testModelGetWell() {
		model = new TamaclonechiModel();
		assertEquals(model.getSickFor(), 0);
		assertFalse(model.getIsSick());
		assertEquals(model.getHappiness(), 50);
		model.getSick();
		model.stillSick();
		assertEquals(model.getSickFor(), 1);
		assertTrue(model.getIsSick());
		assertEquals(model.getHappiness(), 40);
		model.getWell();
		assertEquals(model.getSickFor(), 0);
		assertFalse(model.getIsSick());
	}
	
	/**
	 * Test the kill method in the model
	 */
	@Test
	public void testModelKill() {
		model = new TamaclonechiModel();
		assertTrue(model.getIsAlive());
		model.kill();
		assertFalse(model.getIsAlive());
	}
	
	/**
	 * Test the set bowel movement method in the model
	 */
	@Test
	public void testModelSetBowelMovement() {
		model = new TamaclonechiModel();
		assertFalse(model.getBowelMovement());
		model.setBowelMovement(true);
		assertTrue(model.getBowelMovement());
	}
	
	/**
	 * Test the set age method in the model
	 */
	@Test
	public void testModelSetAge() {
		model = new TamaclonechiModel();
		assertEquals(model.getAge(), 0);
		model.setAge(10);
		assertEquals(model.getAge(), 10);
	}
	
	/**
	 * Test the set bowel movement method in the model
	 */
	@Test
	public void testModelSetHappiness() {
		model = new TamaclonechiModel();
		assertEquals(model.getHappiness(), 50);
		model.setHappiness(10);
		assertEquals(model.getHappiness(), 10);
	}
	
	/**
	 * Test the set health method in the model
	 */
	@Test
	public void testModelSetHealth() {
		model = new TamaclonechiModel();
		assertEquals(model.getHealth(), 75);
		model.setHealth(10);
		assertEquals(model.getHealth(), 10);
	}
	
	/**
	 * Test the set last ate method in the model
	 */
	@Test
	public void testModelSetLastAte() {
		model = new TamaclonechiModel();
		assertEquals(model.getLastAte(), 0);
		model.setLastAte(2);
		assertEquals(model.getLastAte(), 2);
	}
	
	/**
	 * Test the set sick for method in the model
	 */
	@Test
	public void testModelSetSickFor() {
		model = new TamaclonechiModel();
		assertEquals(model.getSickFor(), 0);
		model.setSickFor(5);
		assertEquals(model.getSickFor(), 5);
	}
	
	/**
	 * Test the set is alive method in the model
	 */
	@Test
	public void testModelSetIsAlive() {
		model = new TamaclonechiModel();
		assertTrue(model.getIsAlive());
		model.setIsAlive(false);
		assertFalse(model.getIsAlive());
	}
	
	/**
	 * Test the set is sick method in the model
	 */
	@Test
	public void testModelSetIsSick() {
		model = new TamaclonechiModel();
		assertFalse(model.getIsSick());
		model.setIsSick(true);
		assertTrue(model.getIsAlive());
	}
	
	/**
	 * Test the set weight method in the model
	 */
	@Test
	public void testModelSetWeight() {
		model = new TamaclonechiModel();
		assertEquals(model.getWeight(), 50);
		model.setWeight(80);
		assertEquals(model.getWeight(), 80);
	}
	
	/**
	 * Test the set name method in the model
	 */
	@Test
	public void testModelSetName() {
		model = new TamaclonechiModel();
		assertEquals(model.getName(), "Kuchipatchi");
		model.setName("David");
		assertEquals(model.getName(), "David");
	}
	
	/**
	 * Test the set time method in the model
	 */
	@Test
	public void testModelSetTime() {
		model = new TamaclonechiModel();
		assertEquals(model.getTime(), 0);
		model.setTime(4);
		assertTrue(model.getTime() != 0);
	}
	
	/**
	 * Test the set background method in the model
	 */
	@Test
	public void testModelSetBackground() {
		model = new TamaclonechiModel();
		assertEquals(model.getBackground(), "Default");
		model.setBackground("Night");
		assertEquals(model.getBackground(), "Night");
	}
	
	/**
	 * Test the new game method in the model
	 */
	@Test
	public void testModelNewGame() {
		model = new TamaclonechiModel();
		assertEquals(model.getName(), "Kuchipatchi");
		assertEquals(model.getAge(), 0);
		assertEquals(model.getHealth(), 75);
		assertEquals(model.getWeight(), 50);
		assertEquals(model.getHappiness(), 50);
		assertEquals(model.getLastAte(), 0);
		assertFalse(model.getIsSick());
		assertEquals(model.getSickFor(), 0);
		assertTrue(model.getIsAlive());
		assertFalse(model.getBowelMovement());
		assertEquals(model.getBackground(), "Default");
		model.newGame("David", "Night");
		assertEquals(model.getName(), "David");
		assertEquals(model.getAge(), 0);
		assertEquals(model.getHealth(), 75);
		assertEquals(model.getWeight(), 50);
		assertEquals(model.getHappiness(), 50);
		assertEquals(model.getLastAte(), 0);
		assertFalse(model.getIsSick());
		assertEquals(model.getSickFor(), 0);
		assertTrue(model.getIsAlive());
		assertFalse(model.getBowelMovement());
		assertEquals(model.getBackground(), "Night");
	}
	
	// ----- SPRITE ANIMATION TEST CASES -----
	
	/**
	 * Test the Sprite Animation constructor
	 */
	@Test
	public void testSpriteAnimationConstructor() {
		ImageView newImageView = new ImageView();
		SpriteAnimation spriteAnimation = null;
		spriteAnimation = new SpriteAnimation(newImageView, Duration.millis(3000), 0, 0, 0, 0, 0, 0);
		assertFalse(spriteAnimation == null);
	}
	
	// ----- CONTROLLER TEST CASES -----
	
	/**
	 * Test the controller constructor and getters in the controller
	 */
	@Test
	public void testControllerConstructorAndFieldAccessors() {
		model = new TamaclonechiModel();
		controller = new TamaclonechiController(model);
		assertEquals(controller.getName(), "Kuchipatchi");
		assertEquals(controller.getAge(), 0);
		assertEquals(controller.getHealth(), 75);
		assertEquals(controller.getWeight(), 50);
		assertEquals(controller.getHappiness(), 50);
		assertFalse(controller.getIsSick());
		assertTrue(controller.getIsAlive());
		assertEquals(controller.getBackground(), "Default");	
	}
	
	/**
	 * Test the age pet method in the controller
	 */
	@Test
	public void testControllerAgePet() {
		model = new TamaclonechiModel();
		controller = new TamaclonechiController(model);
		assertEquals(controller.getAge(), 0);
		controller.age();
		assertEquals(controller.getAge(), 1);
	}
	
	/**
	 * Test the check health method in the controller
	 */
	@Test
	public void testControllerCheckHealth() {
		model = new TamaclonechiModel();
		controller = new TamaclonechiController(model);
		assertEquals(controller.getHealth(), 75);
		model.setWeight(90);
		controller.checkHealth();
		assertEquals(controller.getHealth(), 65);
	}
	
	/**
	 * Test the save and load game methods in the controller
	 */
	@Test
	public void testControllerSaveAndLoadGame() {
		model = new TamaclonechiModel();
		controller = new TamaclonechiController(model);
		assertEquals(controller.getHealth(), 75);
		model.setWeight(90);
		controller.checkHealth();
		controller.saveGame();
		controller.loadSave("Day");
	}
	
	/**
	 * Test the update time method in the controller
	 */
	@Test
	public void testControllerUpdateTime() {
		model = new TamaclonechiModel();
		controller = new TamaclonechiController(model);
		assertEquals(controller.getHealth(), 75);
		model.setWeight(90);
		controller.startClock();
		controller.updateTime(9001323);
		assertEquals(controller.getHealth(), 65);
	}
	
	/**
	 * Test the new game method in the controller
	 */
	@Test
	public void testControllernewGame() {
		model = new TamaclonechiModel();
		controller = new TamaclonechiController(model);
		assertEquals(controller.getHealth(), 75);
		model.setWeight(90);
		controller.newGame("David", "Night");
		assertEquals(model.getName(), "David");
		assertEquals(model.getAge(), 0);
		assertEquals(model.getHealth(), 75);
		assertEquals(model.getWeight(), 50);
		assertEquals(model.getHappiness(), 50);
		assertEquals(model.getLastAte(), 0);
		assertFalse(model.getIsSick());
		assertEquals(model.getSickFor(), 0);
		assertTrue(model.getIsAlive());
		assertFalse(model.getBowelMovement());
		assertEquals(model.getBackground(), "Night");
	}
	
	/**
	 * Test the dance method in the controller
	 */
	@Test
	public void testControllerDance() {
		model = new TamaclonechiModel();
		controller = new TamaclonechiController(model);
		controller.newGame("David", "Night");
		assertEquals(model.getName(), "David");
		assertEquals(model.getAge(), 0);
		assertEquals(model.getHealth(), 75);
		assertEquals(model.getWeight(), 50);
		assertEquals(model.getHappiness(), 50);
		assertEquals(model.getLastAte(), 0);
		assertFalse(model.getIsSick());
		assertEquals(model.getSickFor(), 0);
		assertTrue(model.getIsAlive());
		assertFalse(model.getBowelMovement());
		assertEquals(model.getBackground(), "Night");
		controller.dance();
		assertEquals(model.getName(), "David");
		assertEquals(model.getAge(), 0);
		assertEquals(model.getHealth(), 90);
		assertEquals(model.getWeight(), 40);
		assertEquals(model.getHappiness(), 65);
		assertEquals(model.getLastAte(), 0);
		assertFalse(model.getIsSick());
		assertEquals(model.getSickFor(), 0);
		assertTrue(model.getIsAlive());
		assertFalse(model.getBowelMovement());
		assertEquals(model.getBackground(), "Night");
	}
	
	/**
	 * Test the clean method in the controller
	 */
	@Test
	public void testControllerClean() {
		model = new TamaclonechiModel();
		controller = new TamaclonechiController(model);
		controller.newGame("David", "Night");
		assertEquals(model.getName(), "David");
		assertEquals(model.getAge(), 0);
		assertEquals(model.getHealth(), 75);
		assertEquals(model.getWeight(), 50);
		assertEquals(model.getHappiness(), 50);
		assertEquals(model.getLastAte(), 0);
		assertFalse(model.getIsSick());
		assertEquals(model.getSickFor(), 0);
		assertTrue(model.getIsAlive());
		assertFalse(model.getBowelMovement());
		assertEquals(model.getBackground(), "Night");
		model.setBowelMovement(true);
		controller.clean();
		assertEquals(model.getName(), "David");
		assertEquals(model.getAge(), 0);
		assertEquals(model.getHealth(), 85);
		assertEquals(model.getWeight(), 50);
		assertEquals(model.getHappiness(), 55);
		assertEquals(model.getLastAte(), 0);
		assertFalse(model.getIsSick());
		assertEquals(model.getSickFor(), 0);
		assertTrue(model.getIsAlive());
		assertFalse(model.getBowelMovement());
		assertEquals(model.getBackground(), "Night");
	}
	
	/**
	 * Test the sleep method in the controller
	 */
	@Test
	public void testControllerSleep() {
		model = new TamaclonechiModel();
		controller = new TamaclonechiController(model);
		controller.newGame("David", "Night");
		controller.sleep();
		assertEquals(model.getName(), "David");
		assertEquals(model.getAge(), 0);
		assertEquals(model.getHealth(), 85);
		assertEquals(model.getWeight(), 45);
		assertEquals(model.getHappiness(), 55);
		assertEquals(model.getLastAte(), 0);
		assertFalse(model.getIsSick());
		assertEquals(model.getSickFor(), 0);
		assertTrue(model.getIsAlive());
		assertFalse(model.getBowelMovement());
		assertEquals(model.getBackground(), "Night");
	}
	
	/**
	 * Test the medicine method in the controller
	 */
	@Test
	public void testControllerMedicine() {
		model = new TamaclonechiModel();
		controller = new TamaclonechiController(model);
		controller.newGame("David", "Night");
		model.setIsSick(true);
		assertEquals(model.getName(), "David");
		assertEquals(model.getAge(), 0);
		assertEquals(model.getHealth(), 75);
		assertEquals(model.getWeight(), 50);
		assertEquals(model.getHappiness(), 50);
		assertEquals(model.getLastAte(), 0);
		assertTrue(model.getIsSick());
		assertEquals(model.getSickFor(), 0);
		assertTrue(model.getIsAlive());
		assertFalse(model.getBowelMovement());
		assertEquals(model.getBackground(), "Night");
		controller.medicine();
		assertEquals(model.getName(), "David");
		assertEquals(model.getAge(), 0);
		assertTrue(model.getHealth() != 50);
		assertEquals(model.getWeight(), 50);
		assertEquals(model.getHappiness(), 50);
		assertEquals(model.getLastAte(), 0);
		assertEquals(model.getSickFor(), 0);
		assertTrue(model.getIsAlive());
		assertFalse(model.getBowelMovement());
		assertEquals(model.getBackground(), "Night");
	}
	
	/**
	 * Test the eat method in the controller
	 */
	@Test
	public void testControllerEat() {
		model = new TamaclonechiModel();
		controller = new TamaclonechiController(model);
		controller.newGame("David", "Night");
		assertEquals(model.getName(), "David");
		assertEquals(model.getAge(), 0);
		assertEquals(model.getHealth(), 75);
		assertEquals(model.getWeight(), 50);
		assertEquals(model.getHappiness(), 50);
		assertEquals(model.getLastAte(), 0);
		assertFalse(model.getIsSick());
		assertEquals(model.getSickFor(), 0);
		assertTrue(model.getIsAlive());
		assertFalse(model.getBowelMovement());
		assertEquals(model.getBackground(), "Night");
		controller.eat();
		assertEquals(model.getName(), "David");
		assertEquals(model.getAge(), 0);
		assertEquals(model.getHealth(), 75);
		assertEquals(model.getWeight(), 55);
		assertEquals(model.getHappiness(), 55);
		assertEquals(model.getLastAte(), 0);
		assertFalse(model.getIsSick());
		assertEquals(model.getSickFor(), 0);
		assertTrue(model.getIsAlive());
		assertFalse(model.getBowelMovement());
		assertEquals(model.getBackground(), "Night");
	}
	
	// ----- MESSAGE TEST CASES -----
	
	/**
	 * Test the constructor and getters in the message class
	 */
	@Test
	public void testMessageConstructorAndAccessors() {
		TamaclonechiMessage message = new TamaclonechiMessage(0, 0, 0, 0, 0, false, 0, false, 0, "David", false, "Night");
		assertEquals(message.getName(), "David");
		assertEquals(message.getAge(), 0);
		assertEquals(message.getHealth(), 0);
		assertEquals(message.getWeight(), 0);
		assertEquals(message.getHappiness(), 0);
		assertEquals(message.getLastAte(), 0);
		assertFalse(message.getIsSick());
		assertEquals(message.getSickFor(), 0);
		assertFalse(message.getIsAlive());
		assertFalse(message.getDefecate());
		assertEquals(message.getBackground(), "Night");
	}
}