
public class Shoe {
	
	private String brand;
	private String type;
	private double price;
	private int[] quantity;
	private int totalQuantity = 0;
	private int totalSizes = 0;
	private int totalOnSale = 0;
	
	public Shoe(String type, String brand, double price) {
		this.type = type;
		this.brand = brand;
		this.price = price;
		quantity = new int[6]; //sizes 7 to 12
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getType() {
		return type;
	}
	
	public void setQuantity(int size, int qty) {
		quantity[size - 7] = qty;
		totalQuantity += qty;
		totalSizes++;
	}
	
	public int getQuantity(int size) {
		return quantity[size - 7];
	}
	
	public int getTotalQuantity() {
		return totalQuantity;
	}
	
	public int getTotalSizes() {
		return totalSizes;
	}
	
	public double getPrice(int size) {
		if (!isOnSale(size)) {
			return getBasePrice();
		}else {
			return (Math.round(getBasePrice()*0.80*100)/100.00);
		}
	}
	
	public double getBasePrice() {
		return price;
	}
	
	public boolean isOnSale(int size) {
		return false;
	}
	
	public int numOnSale() {
		int numOnSale = 0;
		for (int i = 0; i < quantity.length; i++) {
			if (isOnSale(i + 7)) {
				numOnSale++;
			}
		}
		return numOnSale;
	}
	
	public String getSpecial() {
		return "";
	}
	
	
}
