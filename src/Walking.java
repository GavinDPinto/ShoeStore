
public class Walking extends Shoe {

	private boolean goreTex;
	
	public Walking(String type, String brand, double price, boolean isGoreTex) {
		super(type, brand, price);
		goreTex = isGoreTex;
	}
	
	public boolean getGoreTex() {
		return goreTex;
	}
	
	public boolean isOnSale(int size) {
		return (getQuantity(size) > 2 && !(getBrand().equals("Puma")));
	}
	
	public String getSpecial() {
		if (getGoreTex()) {
			return "GoreTex";
		}else {
			return "Not GoreTex";
		}
	}
}
