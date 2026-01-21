/*
 * TIC-TAC-TOE GAME
 * ================
 * A classic two-player game where players take turns marking spaces in a 3×3 grid.
 * 
 * HOW TO PLAY:
 * -----------
 * 1. Two players are required - Player 1 (X) and Player 2 (O)
 * 2. Players take turns placing their symbol on the board
 * 3. On each turn, enter the row number (0-2) and column number (0-2)
 *    - Row 0 is the top row, Row 2 is the bottom row
 *    - Column 0 is the leftmost column, Column 2 is the rightmost column
 * 4. The first player to get three of their symbols in a row wins!
 *    - Winning combinations: horizontal, vertical, or diagonal
 * 5. If all 9 spaces are filled with no winner, the game is a draw
 * 6. After each game, you can choose to play again or exit
 * 
 * BOARD POSITIONS:
 * ---------------
 *   Col:  0   1   2
 * Row 0:  _  |  _  |  _
 * Row 1:  _  |  _  |  _
 * Row 2:  _  |  _  |  _
 * 
 * EXAMPLE: To place your symbol in the center, enter Row: 1, Column: 1
 */

import java.util.*;

/**
 * TicTacToe class - Main game implementation
 * Contains all game logic, board management, and player interaction methods
 */
class TicTacToe{
        /**
         * 3x3 game board represented as a 2D character array
         * ' ' represents an empty cell
         * 'X' represents Player 1's mark
         * 'O' represents Player 2's mark
         */
        static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        
        /**
         * Displays the current state of the game board
         * Shows a formatted 3x3 grid with vertical and horizontal separators
         */
        static void print_board(){
            System.out.println("Current Board:");
            // Loop through each row of the board
            for(int i=0;i<3;i++){
                // Loop through each column in the current row
                for(int j=0;j<3;j++){
                    System.out.print(" "+board[i][j]+" ");
                    if(j<2) System.out.print(" | "); // Add vertical separator between columns
                }
                System.out.println();
                if(i<2) System.out.println("----------------"); // Add horizontal separator between rows
            }
        }
        
        /**
         * Main game loop that alternates turns between two players
         * @param player1 Name of the first player (plays with 'X')
         * @param player2 Name of the second player (plays with 'O')
         */
        static void play(String player1, String player2){
            // Maximum 9 turns possible in a 3x3 board
            for(int t=0;t<9;t++){
                // Even turns (0,2,4,6,8) are for Player 1 (X)
                if(t%2==0){
                    System.out.println(player1+"'s turn");
                    place('X');
                    // Check if Player 1 has won after their move
                    if(won()){
                        System.out.println(player1+" has won the game!");
                        return;
                    }
                } else {
                    // Odd turns (1,3,5,7) are for Player 2 (O)
                    System.out.println(player2+"'s turn");
                    place('O');
                    // Check if Player 2 has won after their move
                    if(won()){
                        System.out.println(player2+" has won the game!");
                        return;
                    }
                }
            }
            // If all 9 turns complete with no winner, it's a draw
            System.out.println("The game is a draw!");
        }
        
        /**
         * Prompts the current player to place their symbol on the board
         * Validates input and ensures the selected cell is empty
         * @param symbol The player's symbol ('X' or 'O')
         */
        static void place(char symbol){
            Scanner sc = new Scanner(System.in);
            int row, col;
            // Keep asking for input until a valid move is made
            while(true){
                System.out.print("Enter row (0-2): ");
                row = sc.nextInt();
                System.out.print("Enter column (0-2): ");
                col = sc.nextInt();
                
                // Validate that row and column are within bounds (0-2)
                if(row<0 || row>2 || col<0 || col>2){
                    System.out.println("Invalid input. Please enter values between 0 and 2.");
                } 
                // Check if the selected cell is already occupied
                else if(board[row][col]!=' '){
                    System.out.println("Cell already occupied. Choose another cell.");
                } 
                // Valid move - place the symbol and update the board
                else {
                    board[row][col] = symbol;
                    print_board();
                    break; // Exit the loop after successful placement
                }
            }
        }
        
        /**
         * Checks if the current player has won the game
         * @return true if there's a winning combination, false otherwise
         */
        static boolean won(){
            return check_rows() || check_columns() || check_diagonals();
        }
        
        /**
         * Checks all horizontal rows for three matching symbols
         * @return true if any row has three identical non-empty symbols
         */
        static boolean check_rows(){
            // Check each of the 3 rows
            for(int i=0;i<3;i++){
                // Check if all three cells in the row match and are not empty
                if(board[i][0]==board[i][1] && board[i][1]==board[i][2] && board[i][0]!=' '){
                    return true; // Found a winning row
                }
            }
            return false; // No winning row found
        }
        
        /**
         * Checks all vertical columns for three matching symbols
         * @return true if any column has three identical non-empty symbols
         */
        static boolean check_columns(){
            // Check each of the 3 columns
            for(int i=0;i<3;i++){
                // Check if all three cells in the column match and are not empty
                if(board[0][i]==board[1][i] && board[1][i]==board[2][i] && board[0][i]!=' '){
                    return true; // Found a winning column
                }
            }
            return false; // No winning column found
        }
        
        /**
         * Checks both diagonal lines for three matching symbols
         * Checks top-left to bottom-right and top-right to bottom-left
         * @return true if either diagonal has three identical non-empty symbols
         */
        static boolean check_diagonals(){
            // Check top-left to bottom-right diagonal (0,0 -> 1,1 -> 2,2)
            if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]!=' ')
                return true;
            // Check top-right to bottom-left diagonal (0,2 -> 1,1 -> 2,0)
            else if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]!=' ')
                return true;
            return false; // No winning diagonal found
        }
        
        /**
         * Main method - Entry point of the Tic-Tac-Toe game
         * Handles player name input, game initialization, and replay functionality
         */
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            
            // Welcome message and player name input
            System.out.println("\n========== WELCOME TO TIC-TAC-TOE ==========\n");
            System.out.print("Enter name of Player 1: ");   
            String player1 = sc.nextLine();
            System.out.print("Enter name of Player 2: ");
            String player2 = sc.nextLine();
            
            System.out.println("\n" + player1 + " will play as 'X'");
            System.out.println(player2 + " will play as 'O'\n");
            
            boolean want = true;
            // Game loop - allows multiple games to be played
            while(want){
                System.out.println("Game has started between "+player1+" and "+player2);
                print_board(); // Display the initial empty board
                play(player1, player2); // Start the game
                
                // Ask if players want to play again
                System.out.print("Do you want to play again? (yes/no): ");
                if(sc.nextLine().toLowerCase().equals("yes"))
                    // Reset the board for a new game
                    board = new char[][] {
                            {' ', ' ', ' '},
                            {' ', ' ', ' '},
                            {' ', ' ', ' '}
                        };
                else want = false; // Exit the game loop
            }
            
            System.out.println("\nThank you for playing Tic-Tac-Toe! Goodbye!");
            sc.close(); // Close the scanner to prevent resource leaks
        }
}