package utilities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVUtility {
    /*public static List<String[]> readCSVData(String filePath) throws IOException, CsvValidationException {
        List<String[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        }
        return data;
    }*/
    @DataProvider(name = "userData")
    public static Iterator<Object[]> getUserData() throws IOException, CsvValidationException {
        List<Object[]> testData = new ArrayList<>();

        // Define the path to your CSV file
        String csvFilePath = "testdata/userdata.csv";

        // Read data from the CSV file
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] rowData;
            boolean firstLineSkipped = false; // Flag to skip the first line
            while ((rowData = reader.readNext()) != null) {
                if (!firstLineSkipped) {
                    firstLineSkipped = true;
                    continue; // Skip the first line
                }
                // Add the CSV row data as an object array to the testData list
                testData.add(new Object[]{rowData});
            }
        }

        // Return the iterator over the test data list
        return testData.iterator();
    }
}
