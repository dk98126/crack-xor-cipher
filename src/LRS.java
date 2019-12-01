import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LRS {
    public static final int OFFSET = 26; //сдвиг для гаммы
    public static final int GAMMA_LENGTH = 31; //длина гаммы в 31 символ (31 байт!)

    public static final char[] GAMMA = new char[]
            {       0x88, 0x77, 0x98, 0xA1, 0x1B,
                    0xE5, 0x27, 0x75, 0xA7, 0x12,
                    0x34, 0x27, 0xD5, 0x82, 0xA7,
                       0,    0,    0,    0,    0,
                       0,    0,    0,    0,    0xAE,
                       0xED, 0xA6, 0x13, 0x06, 0x44,
                    0xC2
            };

    public static void main(String[] args) {
        String charsInBytes = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("txt.txt"))) {
            charsInBytes = reader.readLine().toLowerCase();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        if (charsInBytes.length() % 2 != 0) {
            throw new IllegalArgumentException("Неверная длина текста");
        }
        char[] realChars = StringUtils.getCharsArrayFromBytesString(charsInBytes);
        String realCharsString = String.valueOf(realChars);
        List<String> repeatedStrings = StringUtils.mostOftenStrings(realCharsString, 2, 3);
        List<String> repeatedStringsBytes = repeatedStrings.stream().map(s -> StringUtils.getBytesStringFromCharsArray(s.toCharArray())).collect(Collectors.toList());
        String gammaPart = Collections.max(repeatedStrings);
        String gammaPartInBytes = StringUtils.getBytesStringFromCharsArray(gammaPart.toCharArray());
        char[] gammaPartChars = StringUtils.getCharsArrayFromBytesString(gammaPartInBytes);
        String decipheredText = String.valueOf(GammaUtils.getXoredChars(realChars, GAMMA, 0, 31));
        String decipheredTextBytes = StringUtils.getBytesStringFromCharsArray(decipheredText.toCharArray());
        if (decipheredTextBytes.substring(114, 124).equals("0000000000") &&
                decipheredTextBytes.substring(300, 310).equals("0000000000") &&
                decipheredTextBytes.substring(424, 434).equals("0000000000")) {
            System.out.printf("");
        }
    }
}
