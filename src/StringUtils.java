import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringUtils {

    /**
     * Находит индекс начала искомой подстроки в строке для поиска
     *
     * @param haystack строка для поиска
     * @param needle   искомая подстрок
     * @return индекс, либо -1, если строка не найдена
     */
    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null)
            return 0;
        if (needle.length() == 0)
            return 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (i + needle.length() > haystack.length()) {
                return -1;
            }
            int m = i;
            for (int j = 0; j < needle.length(); j++) {
                if (needle.charAt(j) == haystack.charAt(m)) {
                    if (j == needle.length() - 1)
                        return i;
                    m++;
                } else {
                    break;
                }

            }
        }
        return -1;
    }

    /**
     * Возвращает мапу строка - сколько раз встречается в тексте каждая подстрока этого текста
     *
     * @param haystack текст
     * @return мапа
     */
    public static Map<String, Integer> substringsFrequencies(String haystack) {
        Map<String, Integer> stringOccurrence = new HashMap<>();
        for (int substringLength = 1; substringLength < haystack.length(); substringLength++) {
            for (int offset = 0; offset < haystack.length() - substringLength; offset++) {
                String needle = haystack.substring(offset, offset + substringLength);
                int startingIndex = strStr(haystack, needle);
                if (startingIndex != -1) {
                    if (!stringOccurrence.containsKey(needle)) {
                        stringOccurrence.put(needle, 0);
                    } else {
                        int newFrequency = stringOccurrence.get(needle);
                        stringOccurrence.replace(needle, ++newFrequency);
                    }
                }
            }
        }
        return stringOccurrence;
    }

    /**
     * Возвращает отсортированный по длине список всех подстрок текста, которые встречались в нем
     * не меньше указанного значения, а также не меньше указанной длины
     *
     * @param haystack  текст
     * @param frequency минимальная частота, при которой строка будет вноситься в результирующий список
     * @param length    минимальная длина, при которой строка будет вноситься в результирующий список
     * @return отсортированный список строк, удовлетворяющих условиям, описанным выше
     */
    public static List<String> mostOftenStrings(String haystack, int frequency, int length) {
        List<String> resultedList = new ArrayList<>();
        Map<String, Integer> substringsFrequencies = substringsFrequencies(haystack);
        for (Map.Entry<String, Integer> entry : substringsFrequencies.entrySet()) {
            if (entry.getValue() >= frequency && entry.getKey().length() >= length) {
                resultedList.add(entry.getKey());
            }
        }
        resultedList.sort(Comparator.comparingInt(String::length));
        return resultedList;
    }

    public static char[] getCharsArrayFromBytesString(String bytesSource) {
        byte[] bytes = bytesSource.getBytes();
        char[] result = new char[bytesSource.length() / 2];
        for (int i = 0, j = 0; i < result.length; i++, j += 2) {
            result[i] = (char) ((bytes[j] << 8) + (bytes[j + 1]));
        }
        return result;
    }

    public static String getBytesStringFromCharsArray(char[] chars) {
        byte[] result = new byte[chars.length * 2];
        for (int i = 0, j = 0; i < result.length; i++, j += 2) {
            result[j] = (byte) ((chars[i] ^ 0xF0) >> 8);
            result[j + 1] = (byte) (chars[i] ^ 0x0F);
        }
        return IntStream.range(0, result.length).mapToObj(Integer::toHexString).collect(Collectors.joining());
    }
}
