import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {
    public static void main(String[] args) {
        BufferedReader br = null;
        FileWriter fw = null;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter your name: ");
            String name = br.readLine();

            System.out.print("Enter your age: ");
            String age = br.readLine();

            System.out.print("Enter your favorite programming language: ");
            String language = br.readLine();

            String data = "Name: " + name + "\nAge: " + age + "\nFavorite Language: " + language + "\n";

            fw = new FileWriter("userinfo.txt");
            fw.write(data);

            System.out.println("Information saved to userinfo.txt");

        } 
        catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        } 
        finally {
            try {
                if (br != null) br.close();
                if (fw != null) fw.close();
            } 
            catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
