public class Test {
    public static void main(String[] args) {
        char[] cryptedText = GammaUtils.getXoredChars("Hello".toCharArray(), "12345".toCharArray(), 0, 5);
        char[] decryptedText = GammaUtils.getXoredChars(cryptedText, "12345".toCharArray(), 0, 5);
        if (!String.valueOf(decryptedText).equals("Hello")) {
            throw new ArithmeticException("Гамма работает неверно");
        }
    }
}
