import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * TODO Extrely raw, needs refactoring
 */
public class LRS {
    public static final int OFFSET = 0;
    public static final int GAMMA_LENGTH = 31;

    public static final char[] GAMMA = new char[]
            {       0x88, 0x77, 0x98, 0xA1, 0x1B,
                    0xE5, 0x27, 0x75, 0xA7, 0x12,
                    0x34, 0x07, 0xBA, 0xE4, 0x87,
                    0xCC, 0x74, 0x9C, 0x01, 0x6F,
                    0xA9, 0x00, 0x78, 0xAF, 0xAE,
                    0xED, 0xA6, 0x13, 0x06, 0x44,
                    0xC2
            };

    public static void main(String[] args) {
        String charsInBytes = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("encryptedText.txt"))) {
            charsInBytes = reader.readLine().toLowerCase();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        if (charsInBytes.length() % 2 != 0) {
            throw new IllegalArgumentException("Неверная длина текста");
        }
        char[] realChars = StringUtils.getCharsArrayFromBytesString(charsInBytes);
        String decipheredText = String.valueOf(GammaUtils.getXoredChars(realChars, GAMMA, OFFSET, GAMMA_LENGTH));
        String decipheredTextBytes = StringUtils.getBytesStringFromCharsArray(decipheredText.toCharArray());
        if (decipheredTextBytes.substring(114, 124).equals("0000000000") &&
                decipheredTextBytes.substring(300, 310).equals("0000000000") &&
                decipheredTextBytes.substring(424, 434).equals("0000000000")) {
            System.out.printf("");
        }
    }
}
