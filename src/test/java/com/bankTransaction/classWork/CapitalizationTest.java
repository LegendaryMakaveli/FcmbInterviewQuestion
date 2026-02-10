package com.bankTransaction.classWork;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CapitalizationTest {

    @Test
    void capitalize() {
        String sentence = "this is a sentence";
        String expected = "This Is A Sentence";
        assertEquals(expected, Capitalization.capitalize(sentence));
    }
}