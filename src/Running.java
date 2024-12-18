
public class Running extends Shoe {
	
	private double weight;
	
	public Running(String type, String brand, double price, double weight) {
		super(type, brand, price);
		this.weight = weight;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public boolean isOnSale(int size) {
		return (getQuantity(size) > 3 && !(getBrand().equals("Saucony") || getBrand().equals("Asics")));
	}

	public double getPrice(int size) {
		if (!isOnSale(size)) {
			return getBasePrice();
		}else {
			return (Math.round(getBasePrice()*0.70*100)/100.00);
		}
	}
	
	public String getSpecial() {
		return "Weight: " + getWeight();
	}
}
