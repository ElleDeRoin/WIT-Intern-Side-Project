import java.lang.Math;
import java.util.Scanner;
public class TicTacToe
{
    //PIVs
    private String[][] board;//creates the board basically just a matrix which is why you see the [][]
    private final String X = "X";//the X and O that will appear on the board when player inputs choice
    private final String O = "O";
    private final String BLANK = " ";//an empty space with neither X or O
    private boolean roundOver;//true or false if round is over or not
    private int numRounds;//keeps track of the amount of rounds played
    private int numPlayers;//keep track of players
    private int[] wins;//score, keeps track of wins for one player
    private boolean player1Turn;//keeps track of which players turn it is. if true player 1, else either player 2 or computer's turn
    private Scanner kybd;//scanner to get input from players

    //constructor, default values for each game, takes in 1 or 2 players to decide whether computer will play, as well as how many rounds will be played before final results posted
    public TicTacToe(int players, int rounds)
    {
        numPlayers = players;
        numRounds = rounds;
        board = new String[3][3];//creates the board of 3 rows and 3 columns
        resetBoard();
        roundOver = false;//in order to start game, all other methods will depend on this boolean to see if round is over
        wins = new int[2];//an array of ints, keeps track of the 2 player's(may be player and computer or 2 seperate players) wins per game
        player1Turn = true;//player 1 will start automatically, after first loss switches to computer/player2
        kybd = new Scanner(System.in);//instantiates scanner
    }
    //this method should start a new game for the player. Will create as many rounds as inputed until finished and then display results
   
    public void play()
    {
        startUpScreen();
        displayBoard();

        //simulate rounds would recommend for loop dependent on numRounds, then play rounds method below. we can also print out what round player is in and score so far
        
        for(int i= 1; i<= numRounds; i++) {
        	System.out.print("Round #" + i );
        	playRound();
        }
        displayResults();
    }
//this method should start a new round(take advantage of roundOver variable). Would recommend while loop, should allow player to choose next location
    //display board each round, check if there is a winner, and make it the turn of the next player. when round over reset board
    private void playRound()
    {
    	roundOver= false;
    		while(!roundOver) {
    			chooseLocation();
    			displayBoard();
    			checkForWinner();
    			
    			player1Turn = !player1Turn;
    		}
    	resetBoard();
    }
//this method checks for winner, player 1 always starts off unless lost one round therefore should be X(i recommend if statement for this). Remember start counting at 0
    private void checkForWinner()
    {

        int player= 0;
    	String let= "";
    	
    	if(player1Turn) {
    		player= 0;
    		let= X;
    	}
    	else {
    		player= 1;
    		let= O;
    	}
        
        //check rows for a winner, use for loop start at 0 til board length. when checking is 3 in a row remember board is 3 by 3
        //if winner is found set roundOver to true and add wins to the player's score (no need for return value)

        for(int r= 0; r< board.length; r++)
    		if(board[r][0].equals(let) && board[r][1].equals(let) && board[r][2].equals(let)) {
    			roundOver= true;
    			wins[player]++;
    			return;
    		}
        
        //check cols for a winner, use for loop start at 0 til board length. when checking is 3 in a row remember board is 3 by 3
        //if winner is found set roundOver to true and add wins to the player's score (no need for return value)

        for(int c= 0; c< board[0].length; c++)
    		if(board[0][c].equals(let) && board[1][c].equals(let) && board[2][c].equals(let)) {
    			roundOver= true;
    			wins[player]++;
    			return;
    		}
        
        //check diagonal for a winner. there are only 2 options for diagonal really, here we can use if statement should be similar to horizontal and vertical

        if(board[2][0].equals(let) && board[1][1].equals(let) && board[0][2].equals(let)) {
				roundOver= true;
				wins[player]++;
				return;
    	}
    	
    	if(board[0][0].equals(let) && board[1][1].equals(let) && board[2][2].equals(let)) {
    			roundOver= true;
    			wins[player]++;
    			return;
    	}
        
        //check if tie, should check if there is a blank on the board bcs if not then there is tie(we could detect if there is no chance of a win after a couple round but i dunno how and i suck at tic tac toes tbh)
            
    	for(int r= 0; r< board.length; r++)
    	    for(int c= 0; c< board[r].length; c++)
    	        if(board[r][c].equals(BLANK))
                {
    			    roundOver= false;
    			    return;
    	        }
        roundOver=true;
        
    }

    private void chooseLocation()
    {
        displayChoices();
        //ask for choice and determine if valid choice
        //we can print out whose choice it is with an if statement
        //left if statement here in case not familiar with scanner class, ask me if you want me to explain this!
                
	    if(player1Turn)
        	System.out.print("Player 1's Choice");
        else
        	if(numPlayers == 2)
        		System.out.println("Player 2's Choice");
        	else
        		System.out.println("Computer's Choice");
        
	    if(numPlayers == 2 || (player1Turn && numPlayers == 1))
        {
            int square = kybd.nextInt();
            placeSymbol(square);
        }
        else
        {
            int square = computerChoice();
            System.out.println("*** Computer Choice: " + square + " ***");
            placeSymbol(square);
        }
    }
//this method is for computer generate choice, the 2 if's are to see if we are X or O
    //checks if the spot is available and honestly loophole is that the computer will always choose the same spots in order for each round, just  a  place holder for now
    private int computerChoice()
    {
        int rand;
      do {
          rand = (int) (Math.random() * 9) + 1;
      } while(!board[(rand-1)/3][(rand-1)%3].equals(BLANK));
      return rand;
    }
//this method just places the symbol in whatever part of the matrix chosen. Kinda not intuitive so left here to explain if ya'll want me to
    private void placeSymbol(int square)
    {
        int row = (square-1) / 3;
        int col = (square-1) % 3;
        if(board[row][col].equals(BLANK))
        {
            if(player1Turn)
                board[row][col] = X;
            else
                board[row][col] = O;
        }
        else{
            System.out.println("Invalid, please choose different spot!");
            square= kybd.nextInt();
            placeSymbol(square);
        }

    }
//Displays a matrix with the square numbered so player can choose which square to input their letter, use a nested for loop!
 
	private void displayChoices()
    {
    	int choice= 1;
    	System.out.println("\n");
    	for(int r= 0; r< board.length; r++) {
    		for(int c=0; c< board[r].length; c++) {
    			System.out.print(choice);
    			if(c< board[r].length-1)
    				System.out.print("|");
    			choice++;
    		}
    		if(r< board.length-1)
    			System.out.println("\n-----");
    		}
    		System.out.println("\n");
	}
	
//displays results, just use System.out.println with wins and ties. use if and else statement if wanting to separate computer and player 2 wins
    private void displayResults()
    {
        System.out.println("\nFinal Results");
        System.out.println("Player 1 wins: " + wins[0]);
        if(numPlayers == 1)
            System.out.println("Computer wins: " + wins[1]);
        else
            System.out.println("Player 2 wins: " + wins[1]);
        System.out.println("Ties: " +
                (numRounds - wins[0] - wins[1]));
    }
//displays board. use nested for loop with | and -- to seperate matrix, just looks pretty
    private void displayBoard()
    {
    	System.out.print("\n");
    	for(int r= 0; r< board.length; r++) {
    		for(int c= 0; c< board[r].length; c++) {
    			System.out.print(board[r][c]);
    			if(c< board[r].length-1)
    				System.out.print("|");
    		}
    		if(r< board.length-1)
    			System.out.println("\n-----");
    	}
    	System.out.println("\n");
    }
//Starts up screen lmk if you need me to explain this , but it's just sopln
    private void startUpScreen()
    {
        System.out.println("Tic-Tac-Toe\n");
        System.out.println("Player 1 is X");
        if(numPlayers == 1)
            System.out.println("Computer is O");
        else
            System.out.println("Player 2 is O");
        System.out.println("Game will be " + numRounds + " rounds");
        System.out.println("Enjoy the game and good luck.");
    }
//resets board, set every spot on board to BLANK. use nested for loop to get each spot on board
private void resetBoard()
    {
    	for(int r= 0; r< board.length; r++)
    		for(int c= 0; c< board[r].length; c++)
    			board[r][c] = BLANK;
    }
}
