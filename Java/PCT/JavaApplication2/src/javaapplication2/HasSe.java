package javaapplication2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class HasSe {

	String name;
	long population;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HasSe other = (HasSe) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static void main(String[] args) {
		HashMap<Integer, String> employeeHashmap = new HashMap<Integer, String>();
		employeeHashmap.put(1, "Arpit");
		employeeHashmap.put(2, "John");
		employeeHashmap.put(3, "John");

		for (int i : employeeHashmap.keySet())
			System.out.println(i);

		HashSet employeeSet = new HashSet();
		employeeSet.add("Arpit");
		employeeSet.add("Arpit");
		employeeSet.add("john");

		  Iterator<String> it = employeeSet.iterator();
		     while(it.hasNext()){
		        System.out.println(it.next());
		     }

	}
}
