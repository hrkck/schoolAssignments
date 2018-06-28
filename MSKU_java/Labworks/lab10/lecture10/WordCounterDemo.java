package lecture10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class WordCounterDemo {

	public static void main(String[] args) {

		boolean exit = false;
		while (!exit) {
			Scanner scan = new Scanner(System.in);
			System.out.print("Input filename:");
			String filename = scan.nextLine();

			WordCounter wCounter = new WordCounter();
			System.out.println(filename);
			try {
				wCounter.countWords(filename);
				exit = true;
				System.out.println(wCounter.getWordMap());
			} catch (IOException e) {
				System.out.println("File does not exist");
			}
		}



	}
}