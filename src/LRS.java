import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LRS {
    public static final int OFFSET = 2;
    public static final int GAMMA_LENGTH = 9;

    public static void main(String[] args) {
        String charsInBytes = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("txt.txt"))) {
            charsInBytes = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        if (charsInBytes.length() % 2 != 0) {
            throw new IllegalArgumentException("Неверная длина текста");
        }
        char[] realChars = StringUtils.getCharsArrayFromBytesString(charsInBytes);
        String realCharsString = String.valueOf(realChars);
        List<String> repeatedStrings = StringUtils.mostOftenStrings(realCharsString, 2, 4);
        String gammaPart = Collections.max(repeatedStrings);
        String gammaPartInBytes = StringUtils.getBytesStringFromCharsArray(gammaPart.toCharArray());
        char[] gammaPartChars = StringUtils.getCharsArrayFromBytesString(gammaPartInBytes);
        for (int i = 0; i < 50; i++) {
            for (int j = 0; gammaPart.length() + j <= i; j++) {
                String decipheredText = String.valueOf(GammaUtils.getXoredChars(realChars, gammaPartChars, j, i));
                String decipheredTextBytes = StringUtils.getBytesStringFromCharsArray(String.valueOf(GammaUtils.getXoredChars(realChars, gammaPartChars, j, i)).toCharArray());
                if (decipheredTextBytes.contains("0000000000")) {
                    System.out.println();
                }
            }
        }
        System.out.printf("");
    }
}
