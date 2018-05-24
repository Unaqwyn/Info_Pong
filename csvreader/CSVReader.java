import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CSVReader {
    private BufferedReader br;
    private final String separator;
    
    public CSVReader(String filename, String separator) throws FileNotFoundException {
        this.separator=separator;
        br = new BufferedReader(new FileReader(new File(filename)));
    } 
    
    public CSVReader(String filename) throws FileNotFoundException
    {
        this(filename, ",");
    }
    
    public CSVReader() throws FileNotFoundException
    {
        this("Raw Data.csv", ",");
    }
    
    public String[] getNextData() throws IOException {
        String line = br.readLine();
        if (line != null) {
            return line.split(separator);
        } else {
            br.close();
            return null;
        } 
    } 
} 
