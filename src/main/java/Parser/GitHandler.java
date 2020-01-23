package Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GitHandler {
    public static String getVersion() {
        Runtime rt = Runtime.getRuntime();
        StringBuilder out = new StringBuilder();
        try {
            Process pr = rt.exec(" git log --pretty=format:\"%h");
            BufferedReader bri = new BufferedReader
                    (new InputStreamReader(pr.getInputStream()));
            BufferedReader bre = new BufferedReader
                    (new InputStreamReader(pr.getErrorStream()));
            String line = bri.readLine();
            while (line != null && line.length()>0){
                out.append(line).append("\n");
                line = bri.readLine();
            }
            line = bre.readLine();
            while (line != null && line.length()>0){
                out.append(line).append("\n");
                line = bre.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
