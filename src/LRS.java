import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LRS {

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
        List<String> strings = StringUtils.mostOftenStrings(bytes, 2, 4);
        System.out.printf("");
    }
}
