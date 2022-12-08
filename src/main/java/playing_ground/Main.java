package playing_ground;

import com.naamad.csvparser.utils.CsvReader;
import com.naamad.csvparser.utils.CsvWriter;

public class Main {
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader();
        csvReader.parseFile();
//        CsvWriter csvWriter = new CsvWriter();
//        csvWriter.createFile();
    }
}
