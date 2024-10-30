package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Function {
    private String[][] fileBoard;
    public static String player = "X";
    public static String computer = "O";

    public String[][] readFile(String fileName,String[][] board) {
        try (Reader reader = new FileReader("task02/"+ fileName)) {
			BufferedReader br = new BufferedReader(reader);
			// int dataRead =0;
			int count = 0;

            fileBoard = new String[3][3];

            String line = "";
            while ((line = br.readLine())!= null) {
                //System.out.println(line.trim());
                String[] tokens = line.trim().split("");
                //create a string[][] array filled with values corresponding with the text file
                
                if (count == 0) {
                    fileBoard[0][0] = tokens[0];
                    fileBoard[0][1] = tokens[1];
                    fileBoard[0][2] = tokens[2];

                }
                if (count == 1) {
                    fileBoard[1][0] = tokens[0];
                    fileBoard[1][1] = tokens[1];
                    fileBoard[1][2] = tokens[2];

                }
                if (count == 2) {
                    fileBoard[2][0] = tokens[0];
                    fileBoard[2][1] = tokens[1];
                    fileBoard[2][2] = tokens[2];

                }

                count++;
                

            }
        
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        return fileBoard;
    }
    public void showBoard(String[][] board) {
        System.out.println( board[0][0] + board[0][1] + board[0][2]);
        System.out.println( board[1][0] + board[1][1] + board[1][2]);
        System.out.println( board[2][0] + board[2][1] + board[2][2]);
    }


    public int utilityEvaluate(String[][] board) {
        int utility = 0;
 
        
        //check if a row or columns contains 3 X
        for (int i = 0; i<3; i++) {
            if ((board[i][0].equals(board[i][1])) && (board[i][1].equals(board[i][2])) && board[i][1].equals(player)) {
                return 1;
            }
            if ((board[0][i].equals(board[1][i])) && (board[1][i].equals(board[2][i])) && board[1][i].equals(player)) {
                return 1;
            } 
            
           
        }
        //check both diagonals if they have 3 X
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[1][1].equals(player)) {
            return 1;
        } 
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && board[1][1].equals(player)) {
            return 1;
        }

        

        //pseudo code
        //loop through each row, count the number of "O" using countO and 
        //number of "." using countEmpty
        //ensure that both counts reset when going to the next row.
        //if any row consists of countO = 2 and countEmpty = 1,
        //return -1

        //do the same for looping each column. if any column has countO = 2 and countEmpty = 1;
        //return -1

        //the below 2 functions are not working as intended. please refer to the pseudo code above
        int countO0 = 0;
        int countEmpty0 = 0;
        int countO1 = 0;
        int countEmpty1 = 0;
        int countO2 = 0;
        int countEmpty2 = 0;

        for (int j = 0; j<3; j++) {
            
            if (board[j][0].equals(computer)) {
                countO0++;
            } else if (board[j][0].equals(".")){
                countEmpty0++;
            } else if (board[j][1].equals(computer)) {
                countO1++;

            } else if (board[j][1].equals(".")) {
                countEmpty1++;
            } else if (board[j][2].equals(computer)) {
                countO2++;

            } else if (board[j][2].equals(".")) {
                countEmpty2++;
            }

            if ((countO0 == 2 && countEmpty0 == 1) || (countO1 == 2 && countEmpty1 == 1) || (countO2 == 2 && countEmpty2 == 1)) {
                return -1;
            }
            
        }
        int countO0B = 0;
        int countEmpty0B= 0;
        int countO1B = 0;
        int countEmpty1B = 0;
        int countO2B = 0;
        int countEmpty2B = 0;
        for (int k = 0; k<3; k++) {
            
            if (board[0][k].equals(computer)) {
                countO0B++;
            } else if (board[0][k].equals(".")){
                countEmpty0B++;
            } else if (board[1][k].equals(computer)) {
                countO1B++;

            } else if (board[1][k].equals(".")) {
                countEmpty1B++;
            } else if (board[2][k].equals(computer)) {
                countO2B++;

            } else if (board[2][k].equals(".")) {
                countEmpty2B++;
            }

            if ((countO0B == 2 && countEmpty0B == 1) || (countO1B == 2 && countEmpty1B == 1) || (countO2B == 2 && countEmpty2B == 1)) {
                return -1;
            }
            
        }


        return utility;
    }

    public List<List<Integer>>  calculateUtility(String[][] board) {
        List<List<Integer>> coordinatesList = new ArrayList<>();
        
        for (int row = 0;row<3; row++) {
            for (int col = 0; col<3; col++) {
                List<Integer> coordinates = new ArrayList<>();
                if (board[row][col].equals(".")) {
                    coordinates.add(row);
                    coordinates.add(col);
                    board[row][col] = player; //place my player in the empty position
                   
                    int utility = utilityEvaluate(board); // check the utility for the new state
                    
                    coordinates.add(utility);
                    board[row][col] = "."; // i want to undo back the move for the next possible move
            
                    coordinatesList.add(coordinates);
                }
                
            }
        }

        return coordinatesList;

    }

    public void output(List<List<Integer>> list) {
        for (List<Integer> innerLs : list) {
            System.out.println("y = " + innerLs.get(0) + ", x = " + innerLs.get(1) + ",utility = " 
            +innerLs.get(2));
            
            

        }
    }
    //pseudocode
    //board 4 has 3 X and 2 O, indicating that our turn has passed.
    //this is an anomaly, because it is assumed it is our turn next
    //after creating the string[][] board. we loop through the whole board and check the number of X and O initially
    //if X is more than O, ourTurn = false;
    //Follow the main calculateUtility function as per usual (where ourTurn = true) 
    //evaluate the state immediately (where ourTurn = false)
    //Please note that my code did not account for confirmTurn yet. it is under the assumption that it is always our turn next

    public boolean confirmTurn(String[][] board) {
        boolean ourTurn = true;
        int countPlayer = 0;
        int countComputer = 0;
        for (int i = 0;i<3;i++) {
            for (int j = 0; j<3; j++) {
                if (board[i][j].equals(player)){
                    countPlayer++;
                } else if (board[i][j].equals(computer)){
                    countComputer++;
                }
                
            }
        }
        if (countPlayer > countComputer) {
            ourTurn = false;
        }
        return ourTurn;
    }
  
}
        

