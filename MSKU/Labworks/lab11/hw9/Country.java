package hw9;

public class Country {
	private String name;
	private int area;
	private int population;
	
	public Country(String name, int area, int population) {
		super();
		this.name = name;
		this.area = area;
		this.population = population;
	}

	public String getName() {
		return name;
	}

	public int getArea() {
		return area;
	}

	public int getPopulation() {
		return population;
	}

	public double density() {
		return population/(double)area;
	}
	
	public String toString(){
		return name + "\t" + area + "\t" + population + "\t" + density();
	}
}
