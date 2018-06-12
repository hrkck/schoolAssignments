package demo;

import java.util.Comparator;

public class IncaseSensitiveComparator<T extends String> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		int res = o1.toUpperCase().compareTo(o2.toUpperCase());
		if ((res == 0) && (!(o1.equals(o2)))){
			return o1.compareTo(o2);
		}
		
		return o1.toUpperCase().compareTo(o2.toUpperCase());
	}

}
