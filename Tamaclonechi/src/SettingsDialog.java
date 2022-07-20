/** 
* FILE: SettingsDialog.java
* ASSIGNMENT: Final Project Tamaclonechi
* COURSE: CSc 335; Fall 2020;
* PURPOSE: The following class is a dialog box for the TamaclonechiView. 
* It is initModality(Modality.APPLICATION_MODAL) so that when doing showAndWait() in the view,
* it acts as a modal dialog. It prompts the user to select game options.
* 
* @author David Izmailov
* @see TamaclonechiGUI
*/

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class SettingsDialog extends Stage{
	
		/**
		   * Creates the settings dialog.
		   * 
		   * The following function is used to create the settings dialog and to
		   * handle the new game and load game input from the user.
		   * 
		   * @param controller The controller that we use in the game.
		   */
	      public SettingsDialog(TamaclonechiController controller) {
	  		this.setTitle("Settings");
	  		this.initModality(Modality.APPLICATION_MODAL);
			Group root = new Group();
			
			//radio 
			ToggleGroup tg1 = new ToggleGroup();
			RadioButton newGame = new RadioButton("New Game");
			RadioButton loadGame = new RadioButton("Load Last Game");
			newGame.setSelected(true);
			newGame.setToggleGroup(tg1);
			loadGame.setToggleGroup(tg1);
			
			ToggleGroup tg2 = new ToggleGroup();
			RadioButton default1 = new RadioButton("Default");
			RadioButton day = new RadioButton("Day");
			RadioButton night = new RadioButton("Night");
			RadioButton az = new RadioButton("AZ");
			RadioButton oldMain = new RadioButton("Old Main");
			RadioButton windows = new RadioButton("Windows");
			RadioButton roof = new RadioButton("Roof Top");
			RadioButton tokyo = new RadioButton("Tokyo");
			RadioButton underWater = new RadioButton("Under Water");
			RadioButton officeHours = new RadioButton("Office Hours");
			default1.setSelected(true);
			default1.setToggleGroup(tg2);
			day.setToggleGroup(tg2);
			night.setToggleGroup(tg2);
			az.setToggleGroup(tg2);
			oldMain.setToggleGroup(tg2);
			windows.setToggleGroup(tg2);
			roof.setToggleGroup(tg2);
			tokyo.setToggleGroup(tg2);
			underWater.setToggleGroup(tg2);
			officeHours.setToggleGroup(tg2);
			VBox backgroundSelect1 = new VBox(10, default1, day, night, az, oldMain);
			VBox backgroundSelect2 = new VBox(10, windows, roof, tokyo, underWater, officeHours);
			HBox backgroundSelectGroup = new HBox(10, new Label("Location: "), backgroundSelect1,backgroundSelect2);
			//text fields and button
			TextField name = new TextField("");
			Button ok = new Button("Ok");
			Button cancel = new Button("Cancel");
			
			//formating
			VBox hBoxes = new VBox(25);
			HBox hBox1 = new HBox(10, newGame, loadGame);
			HBox hBox2 = new HBox(10, new Label("Name: "), name);
			HBox hBox3 = new HBox(10, ok, cancel);
			hBoxes.setPadding(new Insets(25, 25, 25, 25));
			hBoxes.getChildren().addAll(hBox1, hBox2, backgroundSelectGroup, hBox3);
			root.getChildren().add(hBoxes);
			
			//setting handlers
			Stage temp = this;
			
			newGame.setOnAction(event -> {
				name.setDisable(false);
        	});
			
			loadGame.setOnAction(event -> {
				name.clear();
				name.setDisable(true);
        	});
			
			ok.setOnAction(event -> {
				if(tg1.getSelectedToggle() == newGame) {
					controller.newGame(name.getText(),((Labeled) tg2.getSelectedToggle()).getText());
					
				} else {
					controller.loadSave(((Labeled) tg2.getSelectedToggle()).getText());
				}
				temp.close();
        	});
			
			cancel.setOnAction(event -> {
				temp.close();
			});
			
			Scene scene = new Scene(root, 280, 315);
			this.setScene(scene);
	      }
	}