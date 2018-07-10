/*
 * Tuyet Pham
 * Java Programming
 * GUIBingoTP$customTitlePane$ShuffingBTHandler.java
 * Program set 3, #3
 * Reference : Chapter 15 and 16.
 * 			 : http://www.dreamincode.net/forums/topic/141310-bingo-project/
 * 			 : https://stackoverflow.com/questions/18387760/bingo-card-game-in-java
 *
 *************** A lot of the stuff on the Internet didn't really help.The book was mainly helpful********************
 */
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class GUIBingoTP extends Application {

	public Label player1 = new Label("Player 1");				//Label for Players
	public Label player2 = new Label("Player 2");

	//Image for shuffle button
	ImageView cage = new ImageView("https://d30y9cdsu7xlg0.cloudfront.net/png/293771-200.png");
	Button shuffle = new Button("Shuffle!", cage );				//Button to get numbers
	Button play = new Button("Play!");							//button to start the game and generate the numbers on the grid
	Button reset = new Button("Reset Game");					//Button to reset game
	private Label SELECTED = new Label();									//Label to store Selected number when shuffle

	private CustomBingoGrid grid1 = new CustomBingoGrid('1');		//Initialize 2 CustomBingoGrids
	private CustomBingoGrid grid2 = new CustomBingoGrid('2');

	private BorderPane smallPane = new BorderPane();						//To store bingo cards
	private BorderPane bigPane = new BorderPane();							//To store smallPane & set to Scene

	ArrayList<Integer> usedNum = new ArrayList<Integer>();		//An array to hold used numbers
																//when to test if they have already been used

	@Override
	public void start(Stage primaryStage) {

		player1.setFont(Font.font("HelveticaNeue", FontWeight.LIGHT, 13));		//format player labels
		player1.setTextFill(Color.DARKGOLDENROD);
		player2.setFont(Font.font("HelveticaNeue", FontWeight.LIGHT, 13));
		player2.setTextFill(Color.DARKGOLDENROD);

		cage.setFitHeight(50);			//Fit the cage image.
		cage.setFitWidth(50);
		shuffle.setFont(Font.font("AR BONNIE", FontWeight.EXTRA_BOLD, 20));	//Format the Shuffle text

		reset.setFont(Font.font("AR BONNIE", FontWeight.EXTRA_BOLD, 15));
		play.setFont(Font.font("AR BONNIE", FontWeight.EXTRA_BOLD, 25));		//Format the play button


		bigPane.setStyle("-fx-background-color:black");		//Set background color
		bigPane.setTop(new customTitlePane());				//Make and set custom title
		bigPane.setCenter(play);							//Set play in center
		bigPane.setBottom(smallPane);						//Set smallPane on the bottom

		VBox vBox1 = new VBox(grid1, player1);				//Make 2 VBox for grids, pass grid and player labels
		VBox vBox2 = new VBox(grid2, player2);

		smallPane.setStyle("-fx-background-color:black");
		smallPane.setPadding(new Insets(15));
		smallPane.setLeft(vBox1);							//Setting player 1's grid on the left & player 2's on the right
		smallPane.setRight(vBox2);
		smallPane.setCenter(SELECTED);
		smallPane.setBottom(reset);


		play.setOnAction((e) -> {				//Event Handler for when user press play!

			bigPane.setCenter(shuffle);			//Add replace play with shuffle
			grid1.generateNumbers();			//generate numbers for grid 1 & 2
			grid2.generateNumbers();

			//To handle event when user press shuffle button.
			ShufflingBTHandler handler1 = new ShufflingBTHandler();
			shuffle.setOnAction(handler1);
		});

		reset.setOnAction((e) -> {
			grid1.clear();			//generate numbers for grid 1 & 2
			grid2.clear();
			grid1.generateNumbers();
			grid2.generateNumbers();
			SELECTED.setText(" ");
			usedNum.clear();

		});

		Scene scene = new Scene(bigPane, 650, 650);			//Add Big pane to scene
		primaryStage.setScene(scene);						//and format setting for stage.
		primaryStage.setTitle("Bingo Game");
		primaryStage.setMaxWidth(700);
		primaryStage.setMaxHeight(700);
		primaryStage.show();
	}

	/**Main method needed to start program*/
	public static void main(String[] args) {
		launch(args);
	}

	/**Inner Class to handle when user press shuffle button*/
	class ShufflingBTHandler implements EventHandler<ActionEvent> {
		private String[] letter = new String[] { "B", "I", "N", "G", "O" };		//To hold Letters.
		private String selected;					//To hold string that's selected according to number drawn
		private String numSelected;					//To hold number that was randomly made, as string.

		@Override
		public void handle(ActionEvent e) {

			int random = 1 + (int)(Math.random()*75);								//Random number generator from 1-75
			SELECTED.setFont(Font.font("AR BONNIE", FontWeight.EXTRA_BOLD, 30));	//Format Label
			SELECTED.setTextFill(Color.DARKGOLDENROD);

			/*
			 * Depending on what number is drawn the
			 * corresponding letter will be picked
			 * Then it will put the used number in the usedNum arrayList.
			 * It will check if the number already exist in the list before setting
			 * the label to it
			 */
			if(usedNum.contains(random)){
				random = 1 + (int)(Math.random()*75);
			}
			else{
				if(random >= 1 && random <= 15 ) {
					selected = letter[0];
					numSelected = Integer.toString(random);
					SELECTED.setText(selected + numSelected);
					usedNum.add(random);
				}
				else if(random >= 16 && random <= 31 ) {
					selected = letter[1];
					numSelected = Integer.toString(random);
					SELECTED.setText(selected + numSelected);
					usedNum.add(random);
				}
				else if(random >= 32 && random <= 46 ) {
					selected = letter[2];
					numSelected = Integer.toString(random);
					SELECTED.setText(selected + numSelected);
					usedNum.add(random);
				}
				else if(random >= 47 && random <= 61  ) {
					selected = letter[3];
					numSelected = Integer.toString(random);
					SELECTED.setText(selected + numSelected);
					usedNum.add(random);
				}
				else if(random >= 62 && random <= 75 ) {
					selected = letter[4];
					numSelected = Integer.toString(random);
					SELECTED.setText(selected + numSelected);
					usedNum.add(random);
				}
				else
					SELECTED.setText("Something went wrong!");

			}
		}
	}
}

/**Custom class for my title/header Pane*/
class customTitlePane extends StackPane {

	public customTitlePane() {
		ImageView imV = new ImageView(new Image("http://www.amazingslots.org"
				+ "/wp-content/uploads/2016/05/bingo-art-e1463403390152.jpg"));		//URL to image
		setStyle("-fx-background-color:black");
		imV.setFitWidth(650);
		imV.setFitHeight(180);				//Fit the size of original picture.
		getChildren().addAll(imV);			//Add image to pane
	}

}




