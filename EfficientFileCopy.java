import java.io.*;

public class EfficientFileCopy {
    public static void main(String[] args) {
        String sourceFile = "100mb-examplefile-com.txt";
        String Buffered = "Buffered.txt";
        String Unbuffered = "Unbuffered.txt";

        try {
            long bufferedTime = BufferedStreams(sourceFile, Buffered);
            System.out.println("Time taken with Buffered Streams: " + bufferedTime + " nanoseconds");

            long unbufferedTime = UnbufferedStreams(sourceFile, Unbuffered);
            System.out.println("Time taken with Unbuffered Streams: " + unbufferedTime + " nanoseconds");

        } 
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    static long BufferedStreams(String source, String destination) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destination))) {

            byte[] buffer = new byte[4096];
            int n;
            long st = System.nanoTime();

            while ((n = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, n);
            }

            long et = System.nanoTime();
            return et - st;
        }
    }

    static long UnbufferedStreams(String source, String destination) throws IOException {
        try (FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(destination)) {

            byte[] buffer = new byte[4096];
            int n;
            long st = System.nanoTime();

            while ((n = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, n);
            }

            long et = System.nanoTime();
            return et - st;
        }
    }
}
