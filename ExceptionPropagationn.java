
public class ExceptionPropagationn {
 	static double calculateInterest(double amt, double rate, int yrs) throws IllegalArgumentException {
     	if (amt < 0 || rate < 0) {
         	throw new IllegalArgumentException();
     	}
     	return amt * rate * yrs / 100;
 	}

 	public static void main(String[] args) {
     	try {
         	double result = calculateInterest(1000000, 15, 1);
         	System.out.println("Interest: " + result);
     	} catch (IllegalArgumentException e) {
         	System.out.println("Invalid input: Amount and rate must be positive");
     	}
 	}
 }

