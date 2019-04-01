package com.dhorvay.nand2tetris;

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.dhorvay.nand2tetris.CommandType.C_ARITHMETIC;
import static com.dhorvay.nand2tetris.CommandType.C_PUSH;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ParserTest {

    private Parser parser;

    @BeforeEach
    void setup() {
        final String filename = "src/test/resources/StackArithmetic/SimpleAdd/SimpleAdd.vm";
        parser = new Parser(filename);
    }

    // Given a command type advance until the next
    // line we expect that command
    private void advanceUntilCommand(CommandType commandType) {
        int gotoLine = 0;
        switch(commandType) {
            case C_ARITHMETIC:
                // 'add'
                gotoLine = 8;
                break;
            case C_PUSH:
                // 'push constant 7'
                gotoLine = 6;
                break;
            default:
                gotoLine = 6;
                break;
        }
        for(int i = 0; i <= gotoLine; i++){
            parser.advance();
            parser.commandType();
        }
    }

    @Test
    @ExpectSystemExitWithStatus(1)
    void testParser_whenFileNotFound() {
        parser = new Parser("UNDEFINED");
    }

    @Test
    void testHasMoreCommands() {
        while(parser.hasMoreCommands()) {
            assertTrue(parser.hasMoreCommands());
            parser.advance();
        }
        assertFalse(parser.hasMoreCommands());
    }

    @ParameterizedTest
    @EnumSource(value = CommandType.class, names = {"C_ARITHMETIC", "C_PUSH"})
    void testCommandType(CommandType commandType) {
        advanceUntilCommand(commandType);
        assertEquals(commandType, parser.commandType());
    }

    @ParameterizedTest
    @MethodSource("testArg1Provider")
    void testArg1(String expected, CommandType commandType) {
        advanceUntilCommand(commandType);
        assertEquals(expected, parser.arg1());
    }

    static Stream<Arguments> testArg1Provider() {
        return Stream.of(
                arguments("add", C_ARITHMETIC),
                arguments("constant", C_PUSH)
        );
    }

    @ParameterizedTest
    @MethodSource("testArg2Provider")
    void testArg2(Integer expected, CommandType commandType) {
        advanceUntilCommand(commandType);
        assertEquals(expected, parser.arg2());
    }

    static Stream<Arguments> testArg2Provider() {
        return Stream.of(
                arguments(null, C_ARITHMETIC),
                arguments(7, C_PUSH)
        );
    }
}