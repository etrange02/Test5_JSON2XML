package tp5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONReader {

	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("You must put a filename in parameter");
			return;
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
