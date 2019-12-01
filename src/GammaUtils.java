public class GammaUtils {
    public static char[] getXoredChars(char[] text, char[] gammaPart, final int offset, final int gammaLength) {
        if (gammaPart.length + offset > gammaLength) {
            throw new IllegalArgumentException("Указанная длина для гаммы меньше суммы смещения и длины предпологаемой гаммы:\n " +
                    gammaLength + " < " + (gammaPart.length + offset));
        }
        char[] pseudoGamma = new char[gammaLength];
        for (int i = 0; i < offset; i++) {
            pseudoGamma[i] = 0;
        }
        for (int i = offset, j = 0; i < gammaPart.length + offset; i++, j++) {
            pseudoGamma[i] = gammaPart[j];
        }
        for (int i = gammaPart.length + offset; i < gammaLength; i++) {
            pseudoGamma[i] = 0;
        }

        char[] resultedText = new char[text.length];
        System.arraycopy(text, 0, resultedText, 0, offset);

        for (int i = offset; i < text.length; i++) {
            resultedText[i] = (char) (text[i] ^ pseudoGamma[i % gammaLength]);
        }
        return resultedText;
    }
}
