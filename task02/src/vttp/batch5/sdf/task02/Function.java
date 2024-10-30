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
    // get a list containing a list of empty coordinates
    // public List<List<Integer>>  findEmptyPos(String[][] board) {
    //     List<List<Integer>> coordinatesList = new ArrayList<>();
        
    //     for (int row = 0;row<3; row++) {
    //         for (int col = 0; col<3; col++) {
    //             List<Integer> coordinates = new ArrayList<>();
    //             if (board[row][col].equals(".")) {
    //                 coordinates.add(row);
    //                 coordinates.add(col);
    //                 coordinatesList.add(coordinates);
    //             }
                
    //         }
    //     }
    //     //System.out.println(coordinates.size());
    //     System.out.println(coordinatesList.size());
        
    //     return coordinatesList;
        
    // }

    public int utilityEvaluation( String[][] board) {
        int utility = 1000;

        
        //check if each row and each column contains player
        for (int i = 0; i<3; i++) {
            if ((board[i][0].equals(board[i][1])) && (board[i][1].equals(board[i][2])) && board[i][1].equals(player)) {
                utility = 1;
            } else if((board[0][i].equals(board[1][i])) && (board[1][i].equals(board[2][i])) && board[i][1].equals(computer)) {
                utility = -1;   
            }  

            if ((board[0][i].equals(board[1][i])) && (board[1][i].equals(board[2][i])) && board[1][i].equals(player)) {
                utility = 1;
            } else if ((board[0][i].equals(board[1][i])) && (board[1][i].equals(board[2][i])) && board[1][i].equals(computer)) {
                utility = -1;
            }
        }
        
        //check both diagonals if each diagonal contains player
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[1][1].equals(player)) {
            utility =1;
        } else if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[1][1].equals(computer)){
            utility = -1;
        } 

        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && board[1][1].equals(player)) {
            utility =1;
        } else if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && board[1][1].equals(computer)) {
                utility = -1;
            }
        //check if each row and column has 2 "O" and "."
        
       

        //loop through row 0, if row 0, count the number of 0, and the number of 
        //board[0][0], board[0][1],board[0][2]
        
        

        return utility;
    }

    public int utilityEvaluate(String[][] board) {
        int utility = 0;
        int countO = 0;
        int countEmpty = 0;
        int countOrow = 0;
        int countEmptyrow = 0;
        for (int k = 0; k<3; k++) {
            
            if (board[k][0].equals("O")) {
                countO++;
            } else if (board[k][0].equals(".")) {
                countEmpty++;
            }
            if (countO == 2 && countEmpty == 1) {
                return -1;
            }
        }
        for (int j = 0; j<3; j++) {
            
            if (board[0][j].equals("O")) {
                countOrow++;
            } else if (board[0][j].equals(".")) {
                countEmptyrow++;
            }
            if (countOrow == 2 && countEmptyrow == 1) {
                return -1;
            }
        }

        for (int i = 0; i<3; i++) {
            if ((board[i][0].equals(board[i][1])) && (board[i][1].equals(board[i][2])) && board[i][1].equals(player)) {
                return 1;
            }
            if ((board[0][i].equals(board[1][i])) && (board[1][i].equals(board[2][i])) && board[1][i].equals(player)) {
                return 1;
            } 
            
        }
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[1][1].equals(player)) {
            return 1;
        } 
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && board[1][1].equals(player)) {
            return 1;
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
                    int utility = utilityEvaluate(board); // check the utility
                    this.showBoard(board);
                    coordinates.add(utility);
                    board[row][col] = "."; // i want to undo back the move for the next turn
                    this.showBoard(board);
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
  
}
        

