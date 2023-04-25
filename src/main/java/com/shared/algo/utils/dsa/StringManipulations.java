package com.shared.algo.utils.dsa;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("prototype")
public class StringManipulations {

    public int stringLength(String content) {
        int count = 0;
        char[] charArray = content.toCharArray();
        for (char c : charArray) count++;

        return count;
    }


    public int totalWords(String content) {
        int wordCount = 1;
        char[] charArray = content.toCharArray();

        for (char c : charArray) if (c == 32) wordCount++;
        return wordCount;
    }

    public int vowels(String content) {
        int vowels = 0;
        char[] charArray = content.toCharArray();

        for (char c : charArray)
            if ((c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ||
                    (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')) vowels++;

        return vowels;
    }


    public int consonents(String content) {
        int consonents = 0;
        char[] charArray = content.toCharArray();

        for (char c : charArray)
            if ((c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') &&
                    (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U')) consonents++;

        return consonents;
    }


    public int upperCaseCount(String content) {
        int upperCaseCount = 0;
        char[] charArray = content.toCharArray();

        for (char c : charArray)
            if (c >= 65 && c <= 90) upperCaseCount++;

        return upperCaseCount;
    }

    public int lowerCaseCount(String content) {
        int lowerCaseCount = 0;
        char[] charArray = content.toCharArray();

        for (char c : charArray)
            if (c >= 97 && c <= 122) lowerCaseCount++;

        return lowerCaseCount;
    }

    public String reverse(String content) {
        char[] charArray = content.toCharArray();

        int i = 0;
        int j = charArray.length - 1;

        while (i < j) {
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;

            i++;
            j--;
        }

        return String.valueOf(charArray);
    }

    public String upperCaseConversion(String content) {
        char[] charArray = content.toCharArray();

        for (int i = 0; i < charArray.length; i++)
            if (charArray[i] >= 97 && charArray[i] <= 122)
                charArray[i] = (char) (charArray[i] - 32);

        return String.valueOf(charArray);
    }

    public String lowerCaseConversion(String content) {
        char[] charArray = content.toCharArray();

        for (int i = 0; i < charArray.length; i++)
            if (charArray[i] >= 65 && charArray[i] <= 90)
                charArray[i] = (char) (charArray[i] + 32);

        return String.valueOf(charArray);
    }

    public boolean checkStringEquality(String a, String b) {
        char[] arrayA = lowerCaseConversion(a).toCharArray();
        char[] arrayB = lowerCaseConversion(b).toCharArray();

        if (stringLength(a) != stringLength(b)) return false;

        for (int i = 0; i < arrayA.length; i++) {
            if (arrayA[i] != arrayB[i]) {
                return false;
            }
        }

        return true;
    }

    public int searchIndexOfCharacter(String content, char character) {
        char[] charArray = lowerCaseConversion(content).toCharArray();
        //Sorting necessary for binary search
        Arrays.sort(charArray);

        int left = 0;
        int right = charArray.length - 1;
        int first = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (charArray[mid] == character) {
                first = mid;
                right = mid - 1;
            } else if (charArray[mid] < character) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return first;
    }

    public char caseConversionLower(char character) {
        if (character >= 65 && character <= 90) {
            character = (char) (character + 32);
        }

        return character;
    }

    public char caseConversionUpper(char character) {
        if (character >= 97 && character <= 122) {
            character = (char) (character - 32);
        }

        return character;
    }

    public String replaceCharacter(String content, char givenCharacter, char replaceWith) {
        char[] charArray = lowerCaseConversion(content).toCharArray();

        for (int i = 0; i < charArray.length; i++)
            if (caseConversionLower(charArray[i]) == caseConversionLower(givenCharacter)) {
                charArray[i] = caseConversionLower(replaceWith);
            }

        return String.valueOf(charArray);
    }

    public String removeSpaces(String content) {
        char[] charArray = content.toCharArray();
        List<Character> list = new ArrayList<>();

        for (char c : charArray) {
            if (c != 32) {
                list.add(c);
            }
        }
        charArray = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            charArray[i] = list.get(i);
        }

        return String.valueOf(charArray);
    }

    public String removeVowels(String content) {
        char[] charArray = content.toCharArray();
        List<Character> list = new ArrayList<>();

        for (char c : charArray) {
            if ((c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') &&
                    (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U')) {
                list.add(c);
            }
        }
        charArray = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            charArray[i] = list.get(i);
        }

        return String.valueOf(charArray);
    }

    public String removeConsonents(String content) {
        char[] charArray = content.toCharArray();
        List<Character> list = new ArrayList<>();

        for (char c : charArray) {
            if ((c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 32) ||
                    (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')) {
                list.add(c);
            }
        }
        charArray = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            charArray[i] = list.get(i);
        }

        return String.valueOf(charArray);
    }

    public String removeSpecialCharacters(String content) {
        char[] charArray = content.toCharArray();
        List<Character> list = new ArrayList<>();

        for (char c : charArray) {
            if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
                list.add(c);
            }
        }
        charArray = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            charArray[i] = list.get(i);
        }

        return String.valueOf(charArray);
    }

    public String removeDigits(String content) {
        char[] charArray = content.toCharArray();
        List<Character> list = new ArrayList<>();

        for (char c : charArray) {
            if (c >= 48 && c <= 57) {
            } else {
                list.add(c);
            }
        }
        charArray = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            charArray[i] = list.get(i);
        }

        return String.valueOf(charArray);
    }


    public String checkLongestWord(String content) {
        List<String> list = Arrays.stream(content.split(" ")).toList();
        int longestWord = list.get(0).length();
        String actualWord = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).length() > longestWord) {
                longestWord = list.get(i).length();
                actualWord = list.get(i);
            }
        }

        return actualWord;
    }

    public String checkShortestWord(String content) {
        List<String> list = Arrays.stream(content.split(" ")).toList();
        int shortestWord = list.get(0).length();
        String actualWord = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).length() < shortestWord) {
                shortestWord = list.get(i).length();
                actualWord = list.get(i);
            }
        }

        return actualWord;
    }

    public Integer frequencyOfCharacters(String content, char character) {
        char[] charArray = content.toCharArray();
        Map<Character, Integer> map = new LinkedHashMap<>();

        for (char c : charArray) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        return map.get(character);
    }
}
