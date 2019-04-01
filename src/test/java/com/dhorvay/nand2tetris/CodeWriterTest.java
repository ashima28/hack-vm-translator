package com.dhorvay.nand2tetris;

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class CodeWriterTest {

    private final String filename = "src/test/resources/StackArithmetic/SimpleAdd/SimpleAdd.vm";
    private CodeWriter codeWriter;

    @BeforeEach
    void setup() {
        codeWriter = new CodeWriter(filename);
    }

    @Test
    void testSetFileName() {
        assertEquals("src/test/resources/StackArithmetic/SimpleAdd/SimpleAdd.asm", codeWriter.setFileName(filename));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TestCases/arithmetic.csv")
    void testWriteArithmetic(String command, String expected) {
        codeWriter.writeArithmetic(command);
        assertEquals(expected, codeWriter.output.toString());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/TestCases/pushpop.csv")
    void testWritePushPop(CommandType commandType, String segment, int index, String expected) {
        codeWriter.writePushPop(commandType, segment, index);
        assertEquals(expected, codeWriter.output.toString());
    }

    @Test
    @ExpectSystemExitWithStatus(1)
    void testClose() {
        codeWriter = new CodeWriter("/UNDEFINED/UNDEFINED");
        codeWriter.close();
    }
}