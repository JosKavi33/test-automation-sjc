package utilities;

import com.poiji.bind.Poiji;
import models.ItemDetails;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FeatureGeneratorFromExcel {

    public static void main(String[] args) {
        String excelPath = "src/test/resources/data/products/ItemDetails.xlsx";
        String featurePath = "src/test/resources/features/generated/productDetails.feature";

        File excelFile = new File(excelPath);
        if (!excelFile.exists()) {
            Logs.debug("❌ Excel file not found: " + excelPath);
            System.err.println("❌ Excel file not found: " + excelPath);
            return;
        }

        List<ItemDetails> items = Poiji.fromExcel(excelFile, ItemDetails.class);
        if (items.isEmpty()) {
            Logs.debug("❌ No data found in Excel file.");
            System.err.println("❌ No data found in Excel file.");
            return;
        }

        generateFeatureFile(items, featurePath);
    }

    private static void generateFeatureFile(List<ItemDetails> items, String featureFilePath) {
        StringBuilder feature = new StringBuilder();

        feature.append("Feature: Verify the product page and its details\n\n");
        feature.append("  Background: Precondition Feature\n");
        feature.append("    Given The user navigates to the Products page\n");
        feature.append("    Then The Products page loads correctly\n\n");
        feature.append("  Scenario Outline: Verify that a single product's details are correct\n");
        feature.append("    When I check the details of \"<productName>\" with price <productPrice>\n\n");
        feature.append("    Examples:\n");
        feature.append("      | productName          | productPrice |\n");

        for (ItemDetails item : items) {
            feature.append(String.format("      | %-20s | %-12d |\n", item.getItemName(), item.getItemPrice()));
        }

        try {
            File featureFile = new File(featureFilePath);
            File parentDir = featureFile.getParentFile();

            if (!parentDir.exists()) {
                boolean created = parentDir.mkdirs();
                if (!created) {
                    Logs.debug("❌ Could not create directory: " + parentDir.getAbsolutePath());
                    System.err.println("❌ Could not create directory: " + parentDir.getAbsolutePath());
                    return;
                }
            }

            try (FileWriter writer = new FileWriter(featureFile)) {
                writer.write(feature.toString());
                System.out.println("✅ Feature file generated at: " + featureFilePath);
            }
        } catch (IOException e) {
            Logs.debug("❌ Failed to write feature file: " + e.getMessage());
            System.err.println("❌ Failed to write feature file: " + e.getMessage());
        }
    }
}
