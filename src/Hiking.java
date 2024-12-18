
public class Hiking extends Shoe {
	
	private int traction;
	
	public Hiking(String type, String brand, double price, int traction) {
		super(type, brand, price);
		this.traction = traction;
	}
	
	public int getTraction() {
		return traction;
	}
	
	public String getSpecial() {
		return "Traction: " + getTraction();
	}
	
}
