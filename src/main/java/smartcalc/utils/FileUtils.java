package smartcalc.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static String loadData(String fileName) throws FileOperationException {
        try {
            return Files.readString(Path.of(fileName));
        } catch (Exception exception) {
            throw new FileOperationException("Could not load data from file: " + fileName);
        }
    }

    public static void saveResults(String results, String fileName) throws FileOperationException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(results);
        } catch (Exception exception) {
            throw new FileOperationException("Could not save results to file: " + fileName);
        }
    }
}
