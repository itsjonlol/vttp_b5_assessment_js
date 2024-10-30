package vttp.batch5.sdf.task02;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("Please provide 1 argument only. FileName should begin with 'TTT/'.");
			System.exit(-1);
		}
		String fileName = args[0];
		if (!fileName.startsWith("TTT/")) {
			System.err.println("FileName should begin with 'TTT/'");
			System.exit(-1);
		}
		System.out.println("Processing: " + fileName);
		

		String[][] board = new String[3][3];

		for (int row = 0; row<3; row++) {
			for (int col = 0; col <3; col++) {
				board[row][col] = ".";
			}
		}

		Function function = new Function();
		String[][] fileBoard = function.readFile(fileName,board);
		function.showBoard(fileBoard);
		List<List<Integer>> coordinatesList = function.findEmptyPos(fileBoard);

		for (List<Integer> coordinates : coordinatesList) {
			for (Integer coordinate : coordinates) {
				System.out.println(coordinate);
			}
		}

		
	}
}
