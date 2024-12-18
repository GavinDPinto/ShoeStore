import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoeStore {

	private static ArrayList<Shoe> inventory = new ArrayList<>();
	
	public static void main(String[] args) {
		ShoeStore store = new ShoeStore();
		store.loadShoes("src/ShoeInventory1.csv");
		store.printPricing();
		store.printInventory();
	}
	
	public void loadShoes(String filePath) {
		String line = "";
		String[] values;
		String shoeType;
		String shoeBrand;
		int shoeSize;
		double shoePrice;
		int shoeQty;
		boolean shoeGoreTex;
		double shoeWeight;
		int shoeTraction;
		
		
		try {
			Scanner input = new Scanner(new File(filePath));
			boolean isFirstLine = true;
			
			while (input.hasNext()) {
				boolean shoeExists = false;
				line = input.nextLine();
				
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				values = line.split(",");
				
				shoeType = values[0];
				shoeBrand = values[1];
				shoeSize = Integer.parseInt(values[2]);
				shoePrice = Double.parseDouble(values[3]);
				shoeQty = Integer.parseInt(values[4]);
				
				
				for (int i = 0; i < inventory.size(); i++) {
					if (inventory.get(i).getBrand().equals(shoeBrand) && inventory.get(i).getType().equals(shoeType)) {
						inventory.get(i).setQuantity(shoeSize, shoeQty);
						shoeExists = true;
						break;
					}
				}
				
				if (!shoeExists) {
					switch (shoeType) {
						case "Walking":
							shoeGoreTex = Boolean.parseBoolean(values[5]);
							inventory.add(new Walking(shoeType, shoeBrand, shoePrice, shoeGoreTex));
							inventory.get(inventory.size() - 1).setQuantity(shoeSize, shoeQty);
							break;
						case "Hiking":
							shoeTraction = Integer.parseInt(values[5]);
							inventory.add(new Hiking(shoeType, shoeBrand, shoePrice, shoeTraction));
							inventory.get(inventory.size() - 1).setQuantity(shoeSize, shoeQty);
							break;
						case "Running":
							shoeWeight = Double.parseDouble(values[5]);
							inventory.add(new Running(shoeType, shoeBrand, shoePrice, shoeWeight));
							inventory.get(inventory.size() - 1).setQuantity(shoeSize, shoeQty);
							break;
					}
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String mostExpensiveShoe() {
		String mostExpensiveShoeBrand = "";
		double highestCost = 0.0;
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getBasePrice() > highestCost) {
				highestCost = inventory.get(i).getBasePrice();
				mostExpensiveShoeBrand = inventory.get(i).getBrand();
			}
		}
		
		return mostExpensiveShoeBrand + ": $" + highestCost;
	}
	
	public String highestStockedShoe() {
		String mostStockedType = "";
		String mostStockedBrand = "";
		int highestStock = 0;
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getTotalQuantity() > highestStock) {
				highestStock = inventory.get(i).getTotalQuantity();
				mostStockedType = inventory.get(i).getType();
				mostStockedBrand = inventory.get(i).getBrand();
			}
		}
		
		return mostStockedType + " - " + mostStockedBrand;
	}
	
	public String mostSizes() {
		String mostSizesType = "";
		String mostSizesBrand = "";
		int mostSizes = 0;
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getTotalSizes() > mostSizes) {
				mostSizes = inventory.get(i).getTotalSizes();
				mostSizesType = inventory.get(i).getType();
				mostSizesBrand = inventory.get(i).getBrand();
			}
		}
		
		return mostSizesType + " - " + mostSizesBrand;
	}
	
	public String mostOnSale() {
		String mostOnSaleType = "";
		String mostOnSaleBrand = "";
		int mostOnSale = 0;
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).numOnSale() > mostOnSale) {
				mostOnSale = inventory.get(i).numOnSale();
				mostOnSaleType = inventory.get(i).getType();
				mostOnSaleBrand = inventory.get(i).getBrand();
			}
		}
		
		return mostOnSaleType + " - " + mostOnSaleBrand;
	}
	
	public void printPricing() {
		
		System.out.println("Feet Locker Database");
		System.out.println("--------------------\n");
		
		System.out.printf("%-30s%-20s\n", "Most Expensive Shoe:", mostExpensiveShoe());
		System.out.printf("%-30s%-20s\n", "Most Stocked Shoe:", highestStockedShoe());
		System.out.printf("%-30s%-20s\n", "Most Shoe Sizes:", mostSizes());
		System.out.printf("%-30s%-20s\n\n", "Most On Sale:", mostOnSale());
		
		System.out.printf("%60s\n", "Size");
		System.out.printf("%65s\n", "--------------");
		System.out.printf("%-15s%-15s%-10d%-10d%-10d%-10d%-10d%-10d%-20s\n", 
				"Type", "Shoe", 7, 8, 9, 10, 11, 12, "Special");
		System.out.println("---");
		
		for (int i = 0; i < inventory.size(); i++) {
			Shoe curShoe = inventory.get(i);
			System.out.printf("%-15s%-15s%-10s%-10s%-10s%-10s%-10s%-10s%-20s\n",
					curShoe.getType(), 
					curShoe.getBrand(), 
					(curShoe.getQuantity(7) > 0 ? "$" + curShoe.getPrice(7) + (curShoe.isOnSale(7) ? "*" : "") : ""),
					(curShoe.getQuantity(8) > 0 ? "$" + curShoe.getPrice(8) + (curShoe.isOnSale(8) ? "*" : "") : ""),
					(curShoe.getQuantity(9) > 0 ? "$" + curShoe.getPrice(9) + (curShoe.isOnSale(9) ? "*" : "") : ""),
					(curShoe.getQuantity(10) > 0 ? "$" + curShoe.getPrice(10) + (curShoe.isOnSale(10) ? "*" : "") : ""),
					(curShoe.getQuantity(11) > 0 ? "$" + curShoe.getPrice(11) + (curShoe.isOnSale(11) ? "*" : "") : ""),
					(curShoe.getQuantity(12) > 0 ? "$" + curShoe.getPrice(12) + (curShoe.isOnSale(12) ? "*" : "") : ""),
					curShoe.getSpecial());
		}
		
		System.out.println("\nShoes with * are on sale\n");
		
	}
	
	public void printInventory() {
		
		System.out.printf("%60s\n", "Size");
		System.out.printf("%65s\n", "--------------");
		System.out.printf("%-15s%-15s%-10d%-10d%-10d%-10d%-10d%-10d\n", 
				"Type", "Shoe", 7, 8, 9, 10, 11, 12);
		System.out.println("---");
		
		for (int i = 0; i < inventory.size(); i++) {
			Shoe curShoe = inventory.get(i);
			System.out.printf("%-15s%-15s%-10d%-10d%-10d%-10d%-10d%-10d\n",
					curShoe.getType(),
					curShoe.getBrand(),
					curShoe.getQuantity(7),
					curShoe.getQuantity(8),
					curShoe.getQuantity(9),
					curShoe.getQuantity(10),
					curShoe.getQuantity(11),
					curShoe.getQuantity(12));
		}
	}
	
	

}
