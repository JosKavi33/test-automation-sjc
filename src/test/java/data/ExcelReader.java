package data;

import com.poiji.bind.Poiji;
import models.ItemDetails;

import java.io.File;
import java.util.List;

public class ExcelReader {

    private static final String Excel_Path = "src/test/resources/data/products/ItemDetails.xlsx";

    public static List<ItemDetails> readDetailsItemsExcel() {
        return Poiji.fromExcel(new File(Excel_Path), ItemDetails.class);
    }
}
