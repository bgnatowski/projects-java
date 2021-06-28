import java.util.Random;

public class RandomLiczba {
	public static void main(String[] args)
	{
		for(int i = 0; i<100;i++)
		{
			double start = 1.45;
			double end = 1.80;
			double random = new Random().nextDouble();
			double result = start + (random * (end - start));
			System.out.format("%.2f%n", result);
		}
	}

}
