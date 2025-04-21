import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StudentDataBinary {
    static final String file = "students.dat";

    public static void main(String[] args) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(51);
            dos.writeUTF("Kshitij");
            dos.writeDouble(8.6);

            dos.writeInt(40);
            dos.writeUTF("Manoj");
            dos.writeDouble(8.4);

            dos.writeInt(15);
            dos.writeUTF("Aman");
            dos.writeDouble(8.2);

            System.out.println("Student data has been written to " + file);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            System.out.println("\nRetrieved Student Data:");
            while (dis.available() > 0) {
                int roll = dis.readInt();
                String name = dis.readUTF();
                double gpa = dis.readDouble();
                System.out.println("Roll No: " + roll + ", Name: " + name + ", GPA: " + gpa);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}
