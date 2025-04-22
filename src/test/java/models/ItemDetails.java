package models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("Hoja1")
public class ItemDetails {

    @ExcelCellName("Item Name")
    private String itemName;
    @ExcelCellName("Price")
    private int itemPrice;

    public ItemDetails(String itemName, int itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public ItemDetails() {
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }


    /*@Override
    public String toString() {
        final var multilinea = """
                itemName: %s
                itemPrice: %d
                """;
        return String.format(
                multilinea,
                itemName,
                itemPrice
        );
    }*/
}
