/** 
* FILE: TamaclonechiGUI.java
* ASSIGNMENT: Final Project - Tamaclonechi
* COURSE: CSc 335; Fall 2020;
* PURPOSE: The following class creates/displays the interactive graphical interface for a
* virtual Tamaclonechi pet. It features animation on button presses, and non button presses
* based on status of pet (dead,sick,happy, mad, regular and annoyed). It includes five interactive
* buttons: fead, sleep, dance, medicine, and clean (displayed at the bottom of the screen). All of 
* these button alter the state/model (TamaclonechiModel) using a controller (TamaclonechiController). It
* also features a power off button, save button and settings menu at the top. The settings menu allows
* the user to name their pet on a new game, or load a saved file. The user is also able to select the 
* background/location of the Tamaclonechi. 
* 
* 
* @author Fernando Ruiz
* @see TamaclonechiController
* @see TamaclonechiModel
*/

import java.util.Observable;
import java.util.Observer;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TamaclonechiGUI extends Application implements Observer, Runnable {
	//MVC
	private TamaclonechiModel tamaclonechi = new TamaclonechiModel();
	private TamaclonechiController tamaclonechiController = new TamaclonechiController(tamaclonechi);
	//display
	private BorderPane window = new BorderPane();
	private BorderPane main = new BorderPane();
	//whether a aniamtion is currently active, to prevent animation overlap.
	private boolean animationActive = false;
	//current background selected by the user.
	private String background = "Default";
	//current tamaclonechi status for idle animation.
	private String status = null;
	//current tamaclonechi action for action animation.
	private String action = null;
	//for knowing if tamaclonechi poop should be displayed with animation.
	private boolean clean = true;
	//time of current action in seconds for creating time delay in thread. 
	private int actionTime = 0;


	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Displays view on start.
	 * 
	 * The following function displays the view when launched from main. It displays
	 * the Tamaclonechi GUI interface.
	 *
	 * @param stage is Stage object
	 * @throws Exception if it can't be started.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		tamaclonechi.addObserver(this);
		tamaclonechiController.startClock();
		stage.setTitle("Tamaclonechi");
		Scene scene = new Scene(window, 392.5, 400);
		
		// sets main background to default day
		setBackground(background);
		
		//intialize status and set idle to it
		status();
		idle(status);
		
		// tool bar start-up
		// default params of pet, change later when model is complete.
		ToolBar statBar = statBar();
		ToolBar actionBar = actionBar();
		window.setTop(statBar);
		window.setBottom(actionBar);
		
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Sets the background of main.
	 * 
	 * The following function displays the background image of main based on the
	 * passed in string name. Has the following backgrounds that can be set: day, night
	 * Az, old Main, windows, rooftop, Tokyo and office hours. Removes previous image from main if present.
	 *
	 * @param image is a string to indicate desired image path for background
	 */
	public void setBackground(String image) {
		window.setCenter(main);
		Image img = null;
		
		if(image.equals("Night")) {
			img = new Image("Images/sleepBackground.png", true);
		} else if(image.equals("Day")) {
			img = new Image("Images/town.png", true);
		} else if(image.equals("AZ")){
			img = new Image("Images/desert.png", true);
		}  else if(image.equals("Old Main")){
			img = new Image("Images/oldMain.jpg", true);
		}  else if(image.equals("Windows")){
			img = new Image("Images/windows.jpg", true);
		}  else if(image.equals("Roof Top")){
			img = new Image("Images/animeRoofTop.png", true);
		} else if(image.equals("Under Water")){
			img = new Image("Images/underWater.png", true);
		}  else if(image.equals("Tokyo")){
			img = new Image("Images/tokyo.jpg", true);
		}  else if(image.equals("Office Hours")){
			img = new Image("Images/officeHours.png", true);
		} else if(image.equals("Default")){
			//default
			img = new Image("Images/background.jpg", true);
		}
		
		ImageView view = new ImageView(img);
		view.setFitHeight(250);
		view.setFitWidth(392.5);
		
		//if on default avoids removing background from empty main
		if(!background.equals("Default")) {
			//removes prev background
			ObservableList<Node> list = main.getChildren();
			list.remove(0);
		}
		
		background = image;
		main.getChildren().addAll(view);
	}
	
	/**
	 * Decides the status of the tamclonechi based on its stats. 
	 * 
	 * The following function decides the status of the tamaclonchi 
	 * by using getters to see where the pet's stats place in terms
	 * of the avaible animations. Has the following available status and 
	 * animations: mad, happy, regular, sad, sick, and annoyed. 
	 *
	 */
	public void status() {
		//int health = tamaclonechiController.getHealth()/100;
		int happiness = tamaclonechiController.getHappiness();
		boolean sick = tamaclonechiController.getIsSick();
		boolean alive = tamaclonechiController.getIsAlive();

		if(!alive) {
			status = "dead";
		} else if(sick) {
			status = "sick";
		} else if (happiness <= 40) {
			status = "mad";
		} else if (happiness > 40 && happiness <= 60) {
			status = "annoyed";
		} else if (happiness > 60 && happiness <= 80) {
			status = "regular";
		} else if (happiness > 80) {
			status = "happy";
		}
	}
	
	/**
	 * The following function plays an action animation based on the passed in command.
	 * 
	 * The following function plays an action animation based on the passed in command,
	 * which includes, eat, dance, sleep, medicine, defecate, and sick.
	 * 
	 * @param cmd is a string animation command.
	 */
	public void action(String cmd){
		ImageView imageView = null;
		Animation animation = null;
		int cycles = 0;
		
		if(cmd.equals("eat")) {
			imageView = new ImageView("Images/eatAnimation.png");
			animation = new SpriteAnimation(imageView, Duration.millis(3000), 5, 5, 0, 0, 105, 125);
			cycles = 1;
		} else if (cmd.equals("dance")) {
			imageView = new ImageView("Images/danceAnimation.png");
	        animation = new SpriteAnimation(imageView, Duration.millis(1000), 3, 3, 0, 0, 109, 125);
	        cycles = 10;
		} else if (cmd.equals("sleep")) {
			imageView = new ImageView("Images/sleepAnimation.png");
	        animation = new SpriteAnimation(imageView, Duration.millis(10000), 9, 9, -27, 0, 103, 125);
	        cycles = 1;
		} else if (cmd.equals("medicine")) {
			imageView = new ImageView("Images/medicineAnimation.png");
			animation = new SpriteAnimation(imageView, Duration.millis(3000), 4, 4, 0, 0, 110, 125);
			cycles = 1;
		} else if (cmd.equals("defecate")) {
			//special case return after
			imageView = new ImageView("Images/defecateAnimation.png");
			ImageView feces = new ImageView("Images/poop.png");
			animation = new SpriteAnimation(imageView, Duration.millis(6000), 3, 3, 0, 0, 92, 125);
			cycles = 1;
			feces.setViewport(new Rectangle2D(0, 0, 75, 100));
			imageView.relocate(150,150);
	        feces.relocate(300,150);
	        animation.play();
	        Pane box = new Pane(imageView,feces);
	        main.setCenter(box);
	        clean = false;
	        return;
		} else if(cmd.equals("clean")) {
			//special case return after 
			ImageView broom = new ImageView("Images/cleanAnimation.png");
			ImageView idle = new ImageView("Images/happyAnimation.png");
			Animation sweepAnimation = new SpriteAnimation(broom, Duration.millis(1000), 4, 4, -13, 9, 90, 230);
			Animation happyAnimation = new SpriteAnimation(idle, Duration.millis(1000), 2, 2, 0, 0, 90, 125);
	        broom.setViewport(new Rectangle2D(0, 0, 75, 100));
	        sweepAnimation.setCycleCount(10);
			happyAnimation.setCycleCount(Animation.INDEFINITE);
	        broom.relocate(302,0);
	        idle.relocate(150,100);
			sweepAnimation.play();
	        happyAnimation.play();
	        Pane box = new Pane(broom,idle);
	        main.setCenter(box);
	        clean = true;
	        return;
		}
		
        imageView.setViewport(new Rectangle2D(0, 0, 75, 150));
        animation.setCycleCount(cycles);
        imageView.relocate(150,100);
        animation.play();
        
        //decides whether feces is present on the screen
		if(clean == false) {
			ImageView feces = new ImageView("Images/poop.png");
			feces.setViewport(new Rectangle2D(0, 0, 75, 100));
	        feces.relocate(300,150);
	        Pane box = new Pane(imageView,feces);
	        main.setCenter(box);
		} else {
	        Pane box = new Pane(imageView);
	        main.setCenter(box);
		}
	}

	/**
	 * The following function plays an idle animation based on the passed in command.
	 * 
	 * The following function plays an idle animation based on the passed in command,
	 * which includes, regular,annoyed, mad, happy, dead, and sick.
	 * 
	 * @param cmd is a string animation command.
	 */
	public void idle(String cmd) {
		ImageView imageView = null;
		Animation animation = null;
		int cycles = Animation.INDEFINITE;
		
		if(cmd.equals("regular")) {
			imageView = new ImageView("Images/regularAnimation.png");
			animation = new SpriteAnimation(imageView, Duration.millis(1000), 2, 2, 0, 0, 90, 125);
		} else if (cmd.equals("annoyed")) {
			imageView = new ImageView("Images/annoyedAnimation.png");
			animation = new SpriteAnimation(imageView, Duration.millis(1000), 2, 2, 0, 0, 90, 125);
		} else if (cmd.equals("mad")) {
			imageView = new ImageView("Images/madAnimation.png");
			animation = new SpriteAnimation(imageView, Duration.millis(1000), 2, 2, 0, 0, 90, 125);
		} else if (cmd.equals("happy")) {
			imageView = new ImageView("Images/happyAnimation.png");
			animation = new SpriteAnimation(imageView, Duration.millis(1000), 2, 2, 0, 0, 90, 125);
		} else if (cmd.equals("dead")) {
			imageView = new ImageView("Images/dead.png");
			animation = new SpriteAnimation(imageView, Duration.millis(1000), 1, 1, 0, 0, 90, 125);
		} else if (cmd.equals("sick")) {
			imageView = new ImageView("Images/sickAnimation.png");
			animation = new SpriteAnimation(imageView, Duration.millis(3000), 2, 2, 0, 0, 84, 125);
		}
		
        imageView.setViewport(new Rectangle2D(0, 0, 75, 100));
        animation.setCycleCount(cycles);
        imageView.relocate(150,100);
        animation.play();
		
        //decides whether feces is present on the screen
		if(clean == false) {
			ImageView feces = new ImageView("Images/poop.png");
			feces.setViewport(new Rectangle2D(0, 0, 75, 100));
	        feces.relocate(300,150);
	        Pane box = new Pane(imageView,feces);
	        main.setCenter(box);
		} else {
	        Pane box = new Pane(imageView);
	        main.setCenter(box);
		}
	}
	

	/**
	 * The following function creates the status bar to be displayed at the top of
	 * the tamaclonechi GUI view.
	 * 
	 * The following function creates the status bar for the tamaclonechi. It
	 * displays three portions the first being an avatar of the tamaclonechi, with
	 * the game control buttons below (created by calling the fcn
	 * createProfileStat()). The second being a general status of the tamaclonechi
	 * with it's name, health, age and weight displayed. The third portion being the
	 * tamaclonechi's happiness level.
	 * 
	 * @returns mainStatBar is a toolBar that displays the tamaclonechi's stats.
	 */
	public ToolBar statBar() {
		// CREATE PROFILE STATUS BAR:
		VBox profileStat = createProfileStat();

		// CREATE GENERAL STATUS BAR:
		ProgressBar healthBar = null;
		if(!tamaclonechiController.getIsAlive()) {
			healthBar = new ProgressBar(0);
			//check status
			status();
			//call idle with updated status
			idle(status);
		} else {
			healthBar = new ProgressBar( tamaclonechiController.getHealth()/100.0);
		}
		healthBar.setStyle("-fx-accent:  #39FF14;" + "-fx-background: #FB48C4;");
		HBox name = new HBox(10, new Label("Name: " + tamaclonechiController.getName()));
		HBox health = new HBox(10, new Label("Health:"), healthBar);
		// might NEED SPACING ADJUSTMENT based on digit length!
		HBox ageWeight = new HBox(10, new Label("Age: " + tamaclonechiController.getAge() + " yrs      Weight: " + tamaclonechiController.getWeight() + "lb"));
		HBox location = new HBox(10, new Label("Location: " + tamaclonechiController.getBackground()));
		VBox generalStat = new VBox(name, health, ageWeight, location);
		generalStat.setSpacing(5);
		generalStat.setPadding(new Insets(0, 0, 0, 10));

		// CREATE HAPPINESS STATUS BAR:
		ProgressIndicator happinessBar = new ProgressIndicator(tamaclonechiController.getHappiness()/100f);
		happinessBar.setStyle("-fx-accent: #FB48C4");
		VBox happyStat = new VBox(10, new Label("Happiness"), happinessBar);

		// CREATE MAIN STATUS BAR:
		HBox layout = new HBox(profileStat, generalStat, happyStat);
		layout.setSpacing(30);
		ToolBar mainStatBar = new ToolBar(layout);
		mainStatBar.setPadding(new Insets(5, 20, 5, 20));

		return mainStatBar;
	}

	/**
	 * The following function creates the profileStat portion to be displayed on the
	 * status bar. Helper fcn for statBar(..).
	 * 
	 * The following function creates a portion of the status bar, called the
	 * profileStatBar. It displays an avatar of the tamaclonechi, with the game
	 * control buttons below. The buttons include power, save and settings. This fcn
	 * is to be called by the statBar().
	 * 
	 * @returns profileStatBar is a VBox to be contained and displayed in the
	 *          statBar.
	 */
	public VBox createProfileStat() {
		// CREATE AVATAR:
		//"Images/logo.jpg"
		Image avatarImg = new Image("Images/Kuchipatchi.PNG.png");
		Circle avatar = new Circle(22, 22, 22);
		avatar.setStroke(Color.DEEPPINK);
		avatar.setFill(Color.SNOW);
		avatar.setEffect(new DropShadow(+25d, 0d, +2d, Color.LIGHTGREEN));
		avatar.setFill(new ImagePattern(avatarImg));

		// CREATE BUTTONS:
		// create images (https://icons8.com)
		ImageView powerImg = new ImageView(new Image("Images/icons8-electrical-64.png", true));
		ImageView saveImg = new ImageView(new Image("Images/icons8-save-64.png", true));
		ImageView settingImg = new ImageView(new Image("Images/icons8-settings-64.png", true));

		// set button image size
		powerImg.setFitHeight(15);
		powerImg.setFitWidth(15);
		saveImg.setFitHeight(15);
		saveImg.setFitWidth(15);
		settingImg.setFitHeight(15);
		settingImg.setFitWidth(15);
		
		// settings dialog
		Stage settingsDialog = new SettingsDialog(tamaclonechiController);

		// create buttons
		Button powerButton = new Button();
		powerButton.setOnAction((event) -> {
			System.exit(0); 
		});
		Button saveButton = new Button();
		saveButton.setOnAction((event) -> {
			tamaclonechiController.saveGame();
		});
		Button settingButton = new Button();
		settingButton.setOnAction((event) -> {
			settingsDialog.showAndWait();
		});

		// css for buttons
		String style = "-fx-background-radius: 5em; " + "-fx-min-width: 25px; " + "-fx-min-height: 25px; "
				+ "-fx-max-width: 25px; " + "-fx-max-height: 25px;";

		// set style and images to buttons
		powerButton.setStyle(style);
		powerButton.setGraphic(powerImg);
		saveButton.setStyle(style);
		saveButton.setGraphic(saveImg);
		settingButton.setStyle(style);
		settingButton.setGraphic(settingImg);
		HBox buttons = new HBox(powerButton, saveButton, settingButton);

		// Create profile status bar
		VBox profileStatBar = new VBox(avatar, buttons);
		profileStatBar.setSpacing(5);
		profileStatBar.setAlignment(Pos.CENTER);

		return profileStatBar;
	}

	/**
	 * The following function creates the action bar to be displayed at the bottom
	 * of the tamaclonechi GUI view.
	 * 
	 * The following function creates the action bar for the tamaclonechi. It has
	 * the following action buttons feed, sleep, dance, clean and heal. When a
	 * button is clicked a TamaclonechiController fcn is called to alter the status
	 * of the Tamaclonechi in the model.
	 * 
	 * @returns actionBar is a toolBar that displays tamaclonechi control buttons.
	 */
	public ToolBar actionBar() {
		// create image for buttons
		ImageView feedImg = new ImageView(new Image("Images/icons8-food-64.png"));
		ImageView sleepImg = new ImageView(new Image("Images/icons8-sleeping-in-bed-512.png"));
		ImageView danceImg = new ImageView(new Image("Images/icons8-dancing-64.png"));
		ImageView cleanImg = new ImageView(new Image("Images/icons8-housekeeping-64.png"));
		ImageView medicineImg = new ImageView(new Image("Images/icons8-doctors-bag-64.png"));

		// set image size for buttons
		int imgSize = 34;
		feedImg.setFitHeight(imgSize);
		feedImg.setFitWidth(imgSize);
		sleepImg.setFitHeight(imgSize);
		sleepImg.setFitWidth(imgSize);
		danceImg.setFitHeight(imgSize);
		danceImg.setFitWidth(imgSize);
		cleanImg.setFitHeight(imgSize);
		cleanImg.setFitWidth(imgSize);
		medicineImg.setFitHeight(imgSize);
		medicineImg.setFitWidth(imgSize);

		// create buttons
		Button feedButton = new Button();
		feedButton.setOnAction((event) -> {
			actionHandler("eat", 3);
		});
		Button sleepButton = new Button();
		sleepButton.setOnAction((event) -> {
			actionHandler("sleep", 10);
			 
		});
		Button danceButton = new Button();
		danceButton.setOnAction((event) -> {
			actionHandler("dance", 10);
		});
		Button cleanButton = new Button();
		cleanButton.setOnAction((event) -> {
			actionHandler("clean", 10);
		});
		Button medicineButton = new Button();
		medicineButton.setOnAction((event) -> {
			actionHandler("medicine", 3);
		});
		
		// set button images
		feedButton.setGraphic(feedImg);
		sleepButton.setGraphic(sleepImg);
		danceButton.setGraphic(danceImg);
		cleanButton.setGraphic(cleanImg);
		medicineButton.setGraphic(medicineImg);

		// add buttons to container called actionButtons
		HBox actionButtons = new HBox(20, feedButton, sleepButton, danceButton, medicineButton, cleanButton);
		actionButtons.setPadding(new Insets(10, 25, 10, 25));

		// for loop to set button size
		ObservableList<Node> childrens = actionButtons.getChildren();
		for (Node node : childrens) {
			((Region) node).setPrefSize(50, 50);
		}

		// return actionsButtons in a toolBar container
		ToolBar actionBar = new ToolBar(actionButtons);
		return actionBar;
	}
	
	/**
	 * The following function handles controller modifications and threading to delay
	 * idle animation from playing over action animation.
	 * 
	 * The following function calls the controller fcns to alter the model/pet's stats
	 * based on the button pressed. Fcn if only ran if pet is alive and an animation
	 * isn't currently active. The fcn also uses threading to delay idle animation from
	 * occurring over the action animation. Sets act and time to be used in the run fcn 
	 * to create time specific delay in animation.
	 * 
	 * @param act is the action to play
	 * @param actionTime is the time to factor into to delay of idle animation
	 */
	public void actionHandler(String act, int time) {
		if(animationActive == false && tamaclonechiController.getIsAlive()) {
			action = act;
			actionTime = time;
			animationActive = true;
			Thread and= new Thread(this);
			and.setDaemon(true);
			and.start();
			if(act.equals("eat")) {
				tamaclonechiController.eat();
			} else if (act.equals("dance")) {
				tamaclonechiController.dance();
			} else if (act.equals("sleep")) {
				tamaclonechiController.sleep();
			} else if (act.equals("medicine")) {
				tamaclonechiController.medicine();
			} else if (act.equals("defecate")) {
				//timer set for defecation, no need
				//to modify controller.
			} else if(act.equals("clean")) {
				tamaclonechiController.clean();
			}
		}
	}

	/**
	 * The following function updates the view when the model is changed.
	 * 
	 * The following function updates the view when the model is changed. Every time
	 * a change is made the function checks if the user selected a new background,
	 * updates the tamaclonechi's stats on the status bar, display defecate animation
	 * if needed and displays idle animation based off new status.
	 * 
	 * @param arg0 is a Observable object.
	 * @param arg1 is a object.
	 */
	@Override
	public void update(Observable arg0, Object msg) {
		//checks if tamaclonechi location has changed
		String newBackground = ((TamaclonechiMessage) msg).getBackground();
		if(!background.equals(newBackground)){
			setBackground(newBackground);
		}
		
		//check status
		status();
		//call idle with updated status
		idle(status);
		
		// update by reseting status bar
		// currently passed default params,change when model is complete.
		ToolBar statBar = statBar();
		window.setTop(statBar);
		
		//checks if the tamaclonechi has to poop
		boolean hasToDefecate = tamaclonechi.getBowelMovement();
		if(hasToDefecate) {
			if(action == null) {
				actionHandler("defecate", 6);
			}
		}
	}

	/**
	 * The following function is a runnable used by the function
	 * action handler to handle consequential animation execution and
	 * prevent animation overlap.
	 * 
	 * The following function is a runnable used by the function
	 * action handler when a new thread is created. Displays action animation
	 * by calling the fcn action() with the current action, creates a time delay
	 * for animation to finish based on actionTime which duration of the current action
	 * in seconds. After delay The action and animationActive globals are then reset
	 * for the next action.
	 * 
	 */
	@Override
	public void run() {
		try {
			if(action != null) {
				Platform.runLater(() -> {
					//play action animation, was determined by button press
					action(action);
				});
			}
			
			//create time delay given action duration in seconds.
			Thread.sleep(actionTime * 1005);
			
			//reset
			action = null;
			animationActive = false;
			Platform.runLater(() -> {
				//play idle animation since action animation is done
				status();
				idle(status);
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
