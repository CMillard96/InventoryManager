import java.util.Date;

public class ItemSold extends Item {
	
	private float salePrice;
	//private float profit;
	private Date dateSold;

	public ItemSold(int itemID, String itemBrand, String itemSize, float itemCost, int itemCategory, float salePrice, Date dateSold) {
		
		super(itemID, itemBrand, itemSize, itemCost, itemCategory);
		this.salePrice = salePrice;
		this.dateSold = dateSold;
		
		// TODO Auto-generated constructor stub
	}

}
