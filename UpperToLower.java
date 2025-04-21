import java.io.*;

public class UpperToLower {
    public static void main(String[] args) {
        String input = "long-doc.txt";
        String output = "output.txt";

        try (
            BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(input), "UTF-8"));
            BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(output), "UTF-8"))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line.toLowerCase());
                bw.newLine();
            }
            System.out.println("Conversion completed. Output written to " + output);
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }
}
