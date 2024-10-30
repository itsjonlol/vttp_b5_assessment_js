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
			this.showBoard(fileBoard);
        
			
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
    public List<List<Integer>>  findEmptyPos(String[][] board) {
        List<List<Integer>> coordinatesList = new ArrayList<>();
        
        for (int row = 0;row<3; row++) {
            for (int col = 0; col<3; col++) {
                List<Integer> coordinates = new ArrayList<>();
                if (board[row][col].equals(".")) {
                    coordinates.add(row);
                    coordinates.add(col);
                    coordinatesList.add(coordinates);
                }
                
            }
        }
        //System.out.println(coordinates.size());
        System.out.println(coordinatesList.size());
        
        return coordinatesList;
        
    }

    public int utilityEvaluation( String[][] board) {
        int utility = 0;

        
        //check if each row and each column contains either player or computer
        for (int i = 0; i<3; i++) {
            if ((board[i][0].equals(board[i][1])) && (board[i][1].equals(board[i][2])) && board[i][1].equals(player)) {
                utility += 1;
            } else if((board[0][i].equals(board[1][i])) && (board[1][i].equals(board[2][i])) && board[i][1].equals(computer)) {
                utility -= 1;   
            }  

            if ((board[0][i].equals(board[1][i])) && (board[1][i].equals(board[2][i])) && board[1][i].equals(player)) {
                utility += 1;
            } else if ((board[0][i].equals(board[1][i])) && (board[1][i].equals(board[2][i])) && board[1][i].equals(computer)) {
                utility -= 1;
            }
        }
        
        //check both diagonals if each diagonal contains either player or computer
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[1][1].equals(player)) {
            utility +=1;
        } else if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[1][1].equals(computer)){
            utility -=1;
        } 

        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && board[1][1].equals(player)) {
            utility +=1;
        } else if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && board[1][1].equals(computer)) {
                utility -= 1;
            }


        return utility;
    }
}
