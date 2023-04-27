package com.shared.algo.utils.dsa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class StringManipulationsTest {

    @InjectMocks
    private StringManipulations stringManipulations;

    @Test
    @DisplayName("Calculates string length")
    void stringLength() {
        int response = stringManipulations.stringLength("developer");
        assertEquals(9, response);
    }

    @Test
    @DisplayName("Calculates total words inside a string")
    void totalWords() {
        int response = stringManipulations.totalWords("Engineering is more than a job, it's a mindset");
        assertEquals(9, response);
    }

    @Test
    @DisplayName("Calculates total vowels inside a string")
    void totalVowels() {
        int response = stringManipulations.vowels("mindset");
        assertEquals(2, response);
    }

    @Test
    @DisplayName("Calculates total consonants inside a string")
    void totalConsonants() {
        int response = stringManipulations.consonants("mindset");
        assertEquals(5, response);
    }

    @Test
    @DisplayName("Total uppercase case characters in a string")
    void upperCaseCount() {
        int response = stringManipulations.upperCaseCharacterCount("Engineering");
        assertEquals(1, response);
    }

    @Test
    @DisplayName("Total lowercase case characters in a string")
    void lowerCaseCount() {
        int response = stringManipulations.lowerCaseCharacterCount("Engineering");
        assertEquals(10, response);
    }

    @Test
    @DisplayName("Reverse a string")
    void reverse() {
        String response = stringManipulations.reverse("mindset");
        assertEquals("tesdnim", response);
    }

    @Test
    @DisplayName("Convert String to Upper case")
    void upperCaseConversion() {
        String response = stringManipulations.upperCaseStringConversion("mindSet");
        assertEquals("MINDSET", response);
    }

    @Test
    @DisplayName("Convert String to Lower case")
    void lowerCaseConversion() {
        String response = stringManipulations.lowerCaseStringConversion("MIndSET");
        assertEquals("mindset", response);
    }

    @Test
    @DisplayName("Check string equality")
    void checkStringEquality() {
        boolean response = stringManipulations.checkStringEquality("MIndSET", "mindset");
        assertTrue(response);
    }

    @Test
    @DisplayName("Search index of a character")
    void characterIndex() {
        int response = stringManipulations.searchIndexOfCharacter("mindset", 'm');
        assertEquals(3, response);
    }

    @Test
    @DisplayName("Convert character to lowercase")
    void lowerCaseConversionCheck() {
        char response = stringManipulations.caseConversionLower('M');
        assertEquals('m', response);
    }

    @Test
    @DisplayName("Convert character to uppercase")
    void upperCaseConversionCheck() {
        char response = stringManipulations.caseConversionUpper('m');
        assertEquals('M', response);
    }

    @Test
    @DisplayName("Replace character occurrences with given character")
    void replaceOccurrences() {
        String response = stringManipulations.replaceCharacter("boat", 'b', 'g');
        assertEquals("goat", response);
    }

    @Test
    @DisplayName("Removes spaces from string")
    void removeSpaces() {
        String response = stringManipulations.removeSpaces("boat is floating");
        assertEquals("boatisfloating", response);
    }

    @Test
    @DisplayName("Removes vowels from string")
    void removeVowels() {
        String response = stringManipulations.removeVowels("boat is floating");
        assertEquals("bt s fltng", response);
    }

    @Test
    @DisplayName("Removes consonants from string")
    void removeConsonants() {
        String response = stringManipulations.removeConsonants("boat is floating");
        assertEquals("oa i oai", response);
    }

    @Test
    @DisplayName("Removes special from string")
    void removeSpecialCharacter() {
        String response = stringManipulations.removeSpecialCharacters("b@at i$ f%oat*ng");
        assertEquals("batifoatng", response);
    }

    @Test
    @DisplayName("Removes digits from string")
    void removeDigitsFromString() {
        String response = stringManipulations.removeDigits("There are 15 boats floating new 7 harbours");
        assertEquals("There are  boats floating new  harbours", response);
    }

    @Test
    @DisplayName("Check for longest word in a string")
    void checkLongestWord() {
        String response = stringManipulations.checkLongestWord("There are 15 boats floating");
        assertEquals("floating", response);
    }

    @Test
    @DisplayName("Check for shortest word in a string")
    void checkShortestWord() {
        String response = stringManipulations.checkShortestWord("Hi there!");
        assertEquals("Hi", response);
    }

    @Test
    @DisplayName("Check frequency of given character")
    void checkFrequencyOfCharacter() {
        Integer response = stringManipulations.frequencyOfCharacters("Hi there!", 'e');
        assertEquals(2, response);
    }

    @Test
    @DisplayName("Split the given string at whitespace")
    void splitString() {
        List<String> response = stringManipulations.splitString("Hi there!");
        assertEquals("Hithere!", response.get(0).concat(response.get(1)));
    }

    @Test
    @DisplayName("Remove given character from a string")
    void removeCharacter() {
        String response = stringManipulations.removeGivenCharacter("Hi there!", 'e');
        assertEquals("hi thr!", response);
    }

    @Test
    @DisplayName("Remove given word from a string")
    void removeWord() {
        String response = stringManipulations.removeGivenWord("Hi there!", "there!");
        assertEquals("Hi", response.trim());
    }
}