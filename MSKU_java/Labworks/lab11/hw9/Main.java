package hw9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws IOException {
		Set<Country> countries = new TreeSet<>(new CountryComparator());
		try(Scanner scanner = new Scanner (new BufferedReader(new FileReader("CountryInfo.txt")));
				Formatter formatter = new Formatter(new BufferedWriter(new FileWriter("out.txt")))){
			String[] columns =scanner.nextLine().split("\t");
			scanner.useDelimiter("\t|\r\n");
			scanner.useLocale(Locale.US);
			while(scanner.hasNext()){
				Country country = new Country (scanner.next(),scanner.nextInt(),scanner.nextInt());
				countries.add(country);
			}
			formatter.format("%s\t%s\t%s\tdensity%n", columns[0], columns[1], columns[2]);
			for (Country c: countries){
				formatter.format("%s\t%,d\t%,d\t%.2f%n", c.getName(),c.getArea(), c.getPopulation(), c.density());
			}
		}
	}

}
