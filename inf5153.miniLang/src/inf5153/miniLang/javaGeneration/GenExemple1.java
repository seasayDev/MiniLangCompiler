package inf5153.miniLang.javaGeneration;
public class GenExemple1 {
	public static void main(String[] args) {
		int a;
		int b;
		int c;
		int e;
		int x;

		a = 15;
		b = 4;

		if (a != 2) {
			c = 15 + a;
			e = b - c;
		} else {
			a = 15 + 18;
		}

		java.util.Scanner scanner = new java.util.Scanner(System.in);
		System.out.print("La taille : ");
		x = scanner.nextInt();

		while (a == 0) {
		System.out.println("Resultat : " + a);
		}
	}
}
