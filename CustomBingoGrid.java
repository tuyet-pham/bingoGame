/*
 * Tuyet Pham
 * Java Programming
 * CustomBingoGrid$Cell.java
 * Program set 3, #3
 * Reference : Chapter 15 and 16.
 * 			 : http://www.dreamincode.net/forums/topic/141310-bingo-project/
 * 			 : https://stackoverflow.com/questions/18387760/bingo-card-game-in-java
 */
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**A class for my custom bingo grid*/
class CustomBingoGrid extends GridPane {

	public char player = ' ';														//To hold token char
	private GridPane pane = new GridPane();											//To create gridPane
	final int ROW = 6;																//Max numbers of row
	final int COL = 5;																//Max numbers of column
	private Cell[][] cell = new Cell[ROW][COL];										//Create A 2D array to hold Cell Objects
	private Label[] title = new Label[COL];											//An array to hold 5 label
	private String[] titleStrings = new String[] { "B", "I", "N", "G", "O" };		//Strings for the "BINGO" title, of each Bingo grid.
	private Label free = new Label("FREE");											//Middle free label

	/**Constructor that takes in a char and use it as a token below in Cell class*/
	public CustomBingoGrid(char player) {
		this.player = player;				//Set player char

		free.setFont(Font.font("Arial", FontWeight.BOLD, 15));	//Format free Label

		for(int i = 0; i < ROW; i++)							//Creating grid of Cell objects side by side.
			for(int j = 0; j < COL; j++)
				pane.add(cell[i][j] = new Cell(), j, i);

		for(int i = 0; i < COL; i++) {					//Place label title in First row of cells in
														//grid by passing contents of titleStrings to lableMaker
			title[i] = labelMaker(titleStrings[i]);		//Storing newly made label in the title Array
			cell[0][i].getChildren().add(title[i]);		//To display the new label.
		}

		getChildren().add(pane);			//display
	}

	/**A Simple method to turn my bingo title strings to labels.*/
	public Label labelMaker(String string) {
		Label newLabel = new Label(string);
		newLabel.setFont(Font.font("HelveticaNeue", FontWeight.EXTRA_BOLD, 30));
		return newLabel;
	}

	/**Method to clear the cells in grid*/
	public void clear(){

		int row = 0;
		while(row < COL ){
			if(!cell[row+1][0].getChildren().isEmpty()){
				cell[row+1][0].getChildren().clear();
			}
			row++;
		}

		int row1 = 0;
		while(row1 < COL ){
			if(!cell[row1+1][1].getChildren().isEmpty()){
				cell[row1+1][1].getChildren().clear();
			}
			row1++;
		}

		int row2 = 0;
		while(row2 < COL ){
			if(!cell[row2+1][2].getChildren().isEmpty()){
				cell[row2+1][2].getChildren().clear();
			}
			row2++;
		}

		int row3 = 0;
		while(row3 < COL ){
			if(!cell[row3+1][3].getChildren().isEmpty()){
				cell[row3+1][3].getChildren().clear();
			}
			row3++;
		}
		int row4 = 0;
		while(row4 < COL ){
			if(!cell[row4+1][4].getChildren().isEmpty()){
				cell[row4+1][4].getChildren().clear();
			}
			row4++;
		}

	}

	/**A method to generate random numbers for bingo grid*/
	public void generateNumbers() {
		ArrayList<Integer> usedNum = new ArrayList<Integer>();				//A list for already used numbers

		/*
		 * All of these while loops create a random number, then
		 * check the usedNum list to see if the number was already been created/ used.
		 * If so the loop will continue until it creates a number that hasn't been used.
		 * Only then will the counter variable increment.
		 *
		 */

		int row = 0;
		while(row < COL ){
			Integer num = 1 + (int)(Math.random()*15);
			if(!usedNum.contains(num)){
				cell[row+1][0].getChildren().add(new Label(num.toString()));
				usedNum.add(num);
				row++;
			}
		}

		int row1 = 0;
		while(row1 < COL ){
			Integer num = 16 + (int)(Math.random()*15);
			if(!usedNum.contains(num)){
				cell[row1+1][1].getChildren().add(new Label(num.toString()));
				usedNum.add(num);
				row1++;
			}
		}

		int row2 = 0;
		while(row2 < COL ){
			Integer num = 31 + (int)(Math.random()*15);
			if(!usedNum.contains(num)){
				if(cell[row2+1][2]==cell[3][2]){								//Test to see if it's the free space
					cell[3][2].getChildren().addAll(free);
					if (player == '1'){
						cell[3][2].setToken(player);				//To display circle.
					}
					if (player == '2'){
						cell[3][2].setToken(player);			//To display circle.
					}
				}
				else
					cell[row2+1][2].getChildren().add(new Label(num.toString()));
				usedNum.add(num);
				row2++;
			}
		}

		int row3 = 0;
		while(row3 < COL ){
			Integer num = 47 + (int)(Math.random()*15);
			if(!usedNum.contains(num)){
				cell[row3+1][3].getChildren().add(new Label(num.toString()));
				usedNum.add(num);
				row3++;
			}
		}

		int row4 = 0;
		while(row4 < COL ){
			Integer num = 61 + (int)(Math.random()*15);
			if(!usedNum.contains(num)){
				cell[row4+1][4].getChildren().add(new Label(num.toString()));
				usedNum.add(num);
				row4++;
			}
		}
	}

	/**A Method to check if there are tokens within selected cells*/
	public boolean status(char token) {
		for(int i = 0; i < 5; i++)						//Checking rows
			if(cell[i+1][0].getToken() == token
				&& cell[i+1][1].getToken() == token
				&& cell[i+1][2].getToken() == token
				&& cell[i+1][3].getToken() == token
				&& cell[i+1][4].getToken() == token){
				return true;
			}


		for(int j = 0; j < 5; j++)						//Checking Columns
			if(cell[1][j].getToken() == token
				&& cell[2][j].getToken() == token
				&& cell[3][j].getToken() == token
				&& cell[4][j].getToken() == token
				&& cell[5][j].getToken() == token){
				return true;
		}



		if(cell[1][0].getToken() == token				//Checking DiagonalMajor
			&& cell[2][1].getToken() == token
			&& cell[3][2].getToken() == token
			&& cell[4][3].getToken() == token
			&& cell[5][4].getToken() == token){
				return true;
		}


		if(cell[1][4].getToken() == token				//Checking DiagonalMinor
			&& cell[2][3].getToken() == token
			&& cell[3][2].getToken() == token
			&& cell[4][1].getToken() == token
			&& cell[5][0].getToken() == token){
				return true;
		}
		else
			return false;
	}

	/**Class to make custom Cells, extended from stackPanes*/
	class Cell extends StackPane {
		private char token = ' ';
		boolean gameStarted = false;

		/**constructor*/
		public Cell() {
			setStyle("-fx-border-color:black; -fx-background-color: white");	//Formating each Cells
			this.setPrefSize(50,50);
			this.setOnMouseClicked(e -> handleMouseClick());					//To handle what happens with you click on cell.
		}

		/**Get token. this depends on which player*/
		public char getToken(){
			return token;
		}

		/**Setting token depending on which player*/
		public void setToken(char player){
			token = player;								//Assigning token the player char

			if (token == '1'){
				Circle dot = new Circle();
				dot.setRadius(20);						//Settings for circle
				dot.setFill(Color.GOLD);
				dot.setBlendMode(BlendMode.MULTIPLY);
				getChildren().add(dot);					//To display circle.
			}
			if (token == '2'){
				Circle dot = new Circle();
				dot.setRadius(20);						//Settings for circle
				dot.setFill(Color.DARKSEAGREEN);
				dot.setBlendMode(BlendMode.MULTIPLY);
				getChildren().add(dot);					//To display circle.
			}

		}

		/**Method to handle mouse click*/
		private void handleMouseClick() {
			gameStarted = true;					//False until someone clicks on the cell
			if(gameStarted == true ){			//Once the game is started look at the status of the game when each token is set.
				setToken(player);
				if(status(player)) {			//If status is true pass player to winningPane
					winningPane(player);
				}
			}
		}

		/**Method for when a player wins*/
		public void winningPane(char player) {
			Label winner = new Label("Congradulation Player " + player + ", You Won!"
					+ "\nPress Reset Game to start a new game.");			//Formating label
			winner.setFont(Font.font("Helvetica Neue", FontWeight.LIGHT, 15));
			StackPane pane = new StackPane(winner);
			pane.setPadding(new Insets(20));
			if(player == '1')
				winner.setTextFill(Color.DARKGOLDENROD);
			else
				winner.setTextFill(Color.DARKCYAN);

			Stage winningStage = new Stage();			//Putting Label in scene in stage.
			winningStage.setTitle("Bingo Game");
			Scene scene = new Scene(pane, 400, 100);
			winningStage.setScene(scene);
			winningStage.show();
			token = ' ';								//Set token to ' ' again to reset game.
		}
	}
}
