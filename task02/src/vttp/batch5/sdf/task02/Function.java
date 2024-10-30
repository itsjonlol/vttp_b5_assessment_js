package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Function {
    private String[][] board;
    public void readFile (String fileName) {
        try (Reader reader = new FileReader("task02/"+ fileName)) {
			BufferedReader br = new BufferedReader(reader);
			int dataRead =0;
			int count = 0;

            board = new String[3][3];
            for (int row = 0; row<3; row++) {
                for (int col = 0; col <3; col++) {
                    board[row][col] = ".";
                }
            }
            
			// while (dataRead!=-1) {
			// 	count++;
			// 	dataRead = br.read();
            //     char data = (char) dataRead;
            //     if (data == '.'|| data == 'O' || data == 'X') {
            //         for (int i = 0; i<3; i++) {
            //             for (int j = 0;j<3; j++) {
            //                 if (char[i][j] == '') {
            //                     char[i][j] = data;
            //                 }
            //             }
            //         }
            //         System.out.println("hi");
            //     }
                
			// 	System.out.println(data); 
       
			// }

            String line = "";
            while ((line = br.readLine())!= null) {
                //System.out.println(line.trim());
                String[] tokens = line.trim().split("");
                //create a string[][] array filled with values corresponding with the text file
                
                if (count == 0) {
                    board[0][0] = tokens[0];
                    board[0][1] = tokens[1];
                    board[0][2] = tokens[2];

                }
                if (count == 1) {
                    board[1][0] = tokens[0];
                    board[1][1] = tokens[1];
                    board[1][2] = tokens[2];

                }
                if (count == 2) {
                    board[2][0] = tokens[0];
                    board[2][1] = tokens[1];
                    board[2][2] = tokens[2];

                }

                count++;
                

            }
			this.showBoard(board);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
    public void showBoard(String[][] board) {
        System.out.println( board[0][0] + board[0][1] + board[0][2]);
        System.out.println( board[1][0] + board[1][1] + board[1][2]);
        System.out.println( board[2][0] + board[2][1] + board[2][2]);
    }

    public void findEmptyPos(String[][] board) {
        String x = "";
        String y = "";
    }
}
