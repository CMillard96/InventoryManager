import java.util.Date;

public class Item {
	
	private int itemID;
	private String itemBrand;
	private String itemSize;
	private float itemCost;
	private int itemCategory;
	private float price;
	private Date dateSold;
	
	
	
	public Item(int itemID, String itemBrand, String itemSize, float itemPrice, int itemCategory, float price, Date dateSold) {
		this.itemID = itemID;
		this.itemBrand = itemBrand;
		this.itemSize = itemSize;
		this.itemCost = itemPrice;
		this.itemCategory = itemCategory;
		this.price = price;
		this.dateSold = dateSold;
		
	}
	
	public int getItemID() {
		return itemID;
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public String getItemBrand() {
		return itemBrand;
	}
	
	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}
	
	public String getItemSize() {
		return itemSize;
	}
	
	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}
	
	public float getItemCost() {
		return itemCost;
	}
	
	public void setItemCost(float itemPrice) {
		this.itemCost = itemPrice;
	}
	
	public int getItemCategory() {
		return itemCategory;
	}
	
	public void setItemCategory(int itemCategory) {
		this.itemCategory = itemCategory;
	}
	
	public float getItemPrice() {
		return price;
	}
	
	public void setItemPrice(float price) {
		this.price = price;
	}
	
	public Date getDateSold() {
		return dateSold;
	}
	
	public void setDateSold(Date dateSold) {
		this.dateSold = dateSold;
	}
	
	
}
