
public class Item {
	
	private int itemID;
	private String itemBrand;
	private String itemSize;
	private float itemPrice;
	private int itemCategory;
	private boolean itemSold;
	
	
	public Item(int itemID, String itemBrand, String itemSize, float itemPrice, int itemCategory) {
		this.itemID = itemID;
		this.itemBrand = itemBrand;
		this.itemSize = itemSize;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
		
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
	
	public float getItemPrice() {
		return itemPrice;
	}
	
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public int getItemCategory() {
		return itemCategory;
	}
	
	public void setItemCategory(int itemCategory) {
		this.itemCategory = itemCategory;
	}
	
	public boolean getItemSold() {
		return itemSold;
	}
	
	public void setItemSold(boolean itemSold) {
		this.itemSold = itemSold;
	}
}
