package desktop.baseForTests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public interface ParsingFile {
    ArrayList<String> ar = new ArrayList<>();

    default ArrayList<String> getArrayLine(File file) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            int lines = 0;
            String  line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }
                lines++;
                ar.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ar;
    }

}
