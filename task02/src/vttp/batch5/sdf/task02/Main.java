package vttp.batch5.sdf.task02;

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
		
		Function function = new Function();
		function.readFile(fileName);
		
	}
}
