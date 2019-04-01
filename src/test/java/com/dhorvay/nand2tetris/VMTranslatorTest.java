package com.dhorvay.nand2tetris;

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.dhorvay.nand2tetris.VMTranslator.main;

class VMTranslatorTest {

    private VMTranslator translator;

    @BeforeEach
    void setup() {
        translator = new VMTranslator();
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    void testMain() {
        String filename = "src/test/resources/StackArithmetic/SimpleAdd/SimpleAdd.vm";
        String[] args = {filename};
        main(args);
    }

    @Test
    @ExpectSystemExitWithStatus(1)
    void testRun_whenNoArguments() {
        String[] args = {};
        translator.run(args);
    }
}