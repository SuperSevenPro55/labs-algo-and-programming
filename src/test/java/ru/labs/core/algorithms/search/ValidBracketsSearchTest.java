package ru.labs.core.algorithms.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidBracketsSearchTest {
    @Test
    public void isValid_correctInputSingleBrackets_true() {
        String singleBrackets1 = "()";
        String singleBrackets2 = "{}";
        String singleBrackets3 = "[]";
        assertTrue(ValidBracketsSearch.isValid(singleBrackets1));
        assertTrue(ValidBracketsSearch.isValid(singleBrackets2));
        assertTrue(ValidBracketsSearch.isValid(singleBrackets3));
    }

    @Test
    public void isValid_incorrectInput_illegalArgumentException() {
        try {
            ValidBracketsSearch.isValid("Test string");
        } catch (IllegalArgumentException e) {
            fail();
        }
    }
}
