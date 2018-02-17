package dynamix;

public class scm {
	int add(int i, int j) {
		return i + j;
	}
}

 class B extends scm{
	public static void main(String argv[]) {
		short s = 9;
		System.out.println(add(s,6));
	}
}
