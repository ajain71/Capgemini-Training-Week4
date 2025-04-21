import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ImageConverter {

    public static byte[] imgToByteArray(String imagePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(imagePath);
            ByteArrayOutputStream bs = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int br;
            while ((br = fis.read(buf)) != -1) {
                bs.write(buf, 0, br);
            }
            return bs.toByteArray();
        }
    }

    public static void byteArrayToImage(byte[] data, String outputPath) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
             FileOutputStream fos = new FileOutputStream(outputPath)) {
            byte[] buf = new byte[1024];
            int br;
            while ((br = bais.read(buf)) != -1) {
                fos.write(buf, 0, br);
            }
        }
    }

    public static void main(String[] args) {
        String inputImagePath = "input.jpg";
        String outputImagePath = "output.jpg";

        try {
            byte[] imageData = imgToByteArray(inputImagePath);
            byteArrayToImage(imageData, outputImagePath);

            byte[] originalData = imgToByteArray(inputImagePath);
            byte[] newData = imgToByteArray(outputImagePath);

            if (Arrays.equals(originalData, newData)) {
                System.out.println("Success: The output image is identical to the original.");
            } else {
                System.out.println("Warning: The output image differs from the original.");
            }
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        }
    }
}
