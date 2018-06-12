package hw9;

import java.util.Comparator;

public class CountryComparator implements Comparator<Country> {

	@Override
	public int compare(Country o1, Country o2) {
		if ((o1.density() - o2.density())>0){
			return -1;
		}else if(o2.density() - o1.density()>0){
			return 1;
		}else{
			return o1.getName().compareTo(o2.getName());
		}
	}

}
