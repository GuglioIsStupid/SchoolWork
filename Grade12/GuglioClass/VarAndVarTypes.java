package GuglioClass;

public class VarAndVarTypes {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int Radius = 5;
		double Pi = 3.141;
		boolean Finished = false;
		String InfoStr = "";
		
		InfoStr += "The Radius of the circle is " + String.valueOf(Radius) + "\n";
		InfoStr += "PI equals to " + String.valueOf(Pi) + "\n\n";
		
		InfoStr += "The Perimeter of the circle is " + String.valueOf(2 * Pi * Radius) + "\n";
		InfoStr += "Finished Calculating: " + String.valueOf(Finished);
		
		Finished = true;
		
		System.out.println(InfoStr);
	}
}
