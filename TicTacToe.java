/*
 * Tic-Tac-Toe Game
 * 
 * You know the game - X's and O's on a 3x3 grid. Get three in a row and you win.
 * Made this as a quick practice project, nothing fancy.
 * 
 * How to play:
 * 
 * Grab a friend because you need two people. First player gets X, second gets O.
 * You just take turns picking where to put your mark.
 * 
 * When it's your turn, type in a row number and a column number (both 0-2).
 * Think of it like coordinates:
 *    Rows go 0 at the top, 1 in the middle, 2 at the bottom
 *    Columns go 0 on the left, 1 middle, 2 right
 * 
 * Get three of yours in a line (horizontal, vertical, or diagonal) and you win.
 * Fill up the whole board with no winner? That's a draw.
 * 
 * Here's how the board positions work:
 *       Col 0  Col 1  Col 2
 *   0:   _   |   _   |   _
 *   1:   _   |   _   |   _
 *   2:   _   |   _   |   _
 * 
 * Example: Want the middle spot? Type row 1, column 1.
 */

import java.util.*;

// Main game class - all the logic lives here
class TicTacToe{
        // The game board - 3x3 grid stored as a 2D char array
        // Blank spaces are ' ', X for player 1, O for player 2
        static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        
        // Shows the current board state
        static void print_board(){
            System.out.println("Current Board:");
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    System.out.print(" "+board[i][j]+" ");
                    if(j<2) System.out.print(" | ");
                }
                System.out.println();
                if(i<2) System.out.println("----------------");
            }
        }
        
        // Main game loop - alternates between players until someone wins or board fills up
        static void play(String player1, String player2){
            // Max 9 moves possible
            for(int t=0;t<9;t++){
                if(t%2==0){  // player 1's turn
                    System.out.println(player1+"'s turn");
                    place('X');
                    if(won()){
                        System.out.println(player1+" has won the game!");
                        return;
                    }
                } else {  // player 2's turn
                    System.out.println(player2+"'s turn");
                    place('O');
                    if(won()){
                        System.out.println(player2+" has won the game!");
                        return;
                    }
                }
            }
            // Made it through all 9 moves without a winner
            System.out.println("The game is a draw!");
        }
        
        // Handles player input and places their symbol on the board
        static void place(char symbol){
            Scanner sc = new Scanner(System.in);
            int row, col;
            while(true){
                System.out.print("Enter row (0-2): ");
                row = sc.nextInt();
                System.out.print("Enter column (0-2): ");
                col = sc.nextInt();
                
                if(row<0 || row>2 || col<0 || col>2){
                    System.out.println("Invalid input. Please enter values between 0 and 2.");
                } 
                else if(board[row][col]!=' '){
                    System.out.println("Cell already occupied. Choose another cell.");
                } 
                else {
                    board[row][col] = symbol;
                    print_board();
                    break;
                }
            }
        }
        
        // Checks if someone won
        static boolean won(){
            return check_rows() || check_columns() || check_diagonals();
        }
        
        // Check rows for a win
        static boolean check_rows(){
            for(int i=0;i<3;i++){
                if(board[i][0]==board[i][1] && board[i][1]==board[i][2] && board[i][0]!=' '){
                    return true;
                }
            }
            return false;
        }
        
        // Check columns for a win
        static boolean check_columns(){
            for(int i=0;i<3;i++){
                if(board[0][i]==board[1][i] && board[1][i]==board[2][i] && board[0][i]!=' '){
                    return true;
                }
            }
            return false;
        }
        
        // Check both diagonals
        static boolean check_diagonals(){
            // top-left to bottom-right
            if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]!=' ')
                return true;
            // top-right to bottom-left
            if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]!=' ')
                return true;
            return false;
        }
        
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            
            // Get player names
            System.out.println("\n========== WELCOME TO TIC-TAC-TOE ==========\n");
            System.out.print("Enter name of Player 1: ");   
            String player1 = sc.nextLine();
            System.out.print("Enter name of Player 2: ");
            String player2 = sc.nextLine();
            
            System.out.println("\n" + player1 + " will play as 'X'");
            System.out.println(player2 + " will play as 'O'\n");
            
            boolean want = true;
            while(want){
                System.out.println("Game has started between "+player1+" and "+player2);
                print_board();
                play(player1, player2);
                
                System.out.print("Do you want to play again? (yes/no): ");
                if(sc.nextLine().toLowerCase().equals("yes"))
                    board = new char[][] {  // reset board
                            {' ', ' ', ' '},
                            {' ', ' ', ' '},
                            {' ', ' ', ' '}
                        };
                else want = false;
            }
            
            System.out.println("\nThank you for playing Tic-Tac-Toe! Goodbye!");
            sc.close();
        }
}
