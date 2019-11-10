import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LRS {
    public static char[] getText(String bytesSource) {
        byte[] bytes = bytesSource.getBytes();
        char[] result = new char[bytesSource.length() / 2];
        for (int i = 0, j = 0; i < result.length; i++, j+=2) {
            result[i] = (char) ((bytes[j] << 8) + (bytes[j+1]));
        }
        return result;
    }

    public static String getTextString(char[] text) {
        return String.valueOf(text);
    }

    public static void main(String[] args) {
        String bytes = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("txt.txt"))) {
            bytes = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        if (bytes.length() % 2 != 0) {
            throw new IllegalArgumentException("Неверная длина текста");
        }
        char[] text = getText(bytes);
        System.out.printf("");
    }
}
