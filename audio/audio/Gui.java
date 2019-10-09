package audio;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.collections.ObservableList; 

public class Gui extends Application {
	
	private Text create_text(int fsize, double xloc, double yloc, String text){
		Text t = new Text(xloc, yloc, text);
		t.setFont(new Font(fsize));
		return t;
	}

	@Override
	public void start(Stage main){
		
		// Create Text
		// Text text = new Text();
		// text.setFont(new Font(45));
		// text.setX(50);
		// text.setY(150);
		// text.setText("testing");

		Text text = this.create_text(45, 50, 150, "test1");
		Text text2 = this.create_text(13, 100, 200, "test2");

		ComboBox drop_down = new ComboBox();

		// add Text to Group and create scene
		Group root = new Group();
		ObservableList list = root.getChildren();
		list.add(text);
		list.add(text2);
		Scene scene = new Scene(root, 900, 600);
		main.setTitle("Music Application");
		main.setScene(scene);
		main.show();

	}

	public static void main(String[] args){
		launch();
	}
}