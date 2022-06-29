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

    //constructor, default values for each game, takes in 1 oe 2 players to decide whether computer will play, as well as how many rounds will be played before final results posted
    public TicTacToe(int players, int rounds)
    {
        numPlayers = players;
        numRounds = rounds;
        board = new String[3][3];//creates the board of 3 rows and 3 columns
        resetBoard();
        roundOver = false;//in order to start game, all other methods will depend on this boolean to see if round is over
        wins = new int[2];//an array of ints, keeps track of the 2 player's(may be player and computer or 2 seperate players) wins per game
        player1Turn = true;//player 1 will start automatically, after first loss switches to computer
        kybd = new Scanner(System.in);//instantiates scanner
    }
    //this method should start a new game for the player. Will create as many rounds as inputed until finished and then display results
    public void play()
    {
        //startUpScreen();
        //displayBoard();

        //simulate rounds would recommend for loop dependent on numRounds, then play rounds method below. we can also print out what round player is in and score so far
        //displayResults();
    }
//this method should start a new round(take advantage of roundOver variable). Would recommend while loop, should allow player to choose next location
    //display board each round, check if there is a winner, and make it the turn of the next player. when round over reset board
    private void playRound()
    {

    }
//this method checks for winner, player 1 always starts off unless lost one round therefore should be X(i recommend if statement for this). Remember start counting at 0
    private void checkForWinner()
    {

        //check rows for a winner, use for loop start at 0 til board length. when checking is 3 in a row remember board is 3 by 3
        //if winner is found set roundOver to true and add wins to the player's score (no need for return value)

        //check cols for a winner, use for loop start at 0 til board length. when checking is 3 in a row remember board is 3 by 3
        //if winner is found set roundOver to true and add wins to the player's score (no need for return value)

        //check diagonal for a winner. there are only 2 options for diagonal really, her we can use if statement should be similar to horizontal and vertical

        //check if tie, should check if there is a blank on the bpard and set round over to false, else round over is set to true
    }

    private void chooseLocation()
    {
        //displayChoices();
        //ask for choice and determine if valid choice
        //we can print out whose choice it is with an if statement
        //left if statement here in case not familiar with scanner class, ask me if you want me to explain this!
        if(numPlayers == 2 ||
                (player1Turn && numPlayers == 1))
        {
            int square = kybd.nextInt();
            placeSymbol(square);
        }
        else
        {
            int square = computerChoice();
            placeSymbol(square);
        }
    }
//this method is for the "AI" we can implement this later, or improve mine. Kinda forgot how i did this but if you need help I can def explain
    private int computerChoice()
    {
        int sentinel = 0;
        while (sentinel != 0){

        }
    int x=0;
    if(numRounds%2==0)
    {
      if(5+x<=9 && !(board[(5+x)/3][(5+x)%3]==O))
      {
        return 5+x;
      }
      else if(5-x>0 && !(board[(5+x)/3][(5+x)%3]==O))
      {
        return 5-x;
      }
    }
    else 
    {
      if(5+x<=9 && !(board[(5+x)/3][(5+x)%3]==X))
      {
        return 5+x;
      }
      else if(5-x>0 && !(board[(5+x)/3][(5+x)%3]==X))
      {
        return 5-x;
      }
    }
    x++;
    return 0;
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
            //player1Turn = !player1Turn;
        }
    }
//Displays a matrix with the square numbered so player can choose which square to input their letter, use a nested for loop!
    private void displayChoices()
    {

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

    }
//Starts up screen left in bcs why not
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
//resets board, set every spot on board to BLANK. use nested for loop
    private void resetBoard()
    {

    }
}