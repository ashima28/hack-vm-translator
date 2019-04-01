<div align="center">
<h1>Hack VMTranslator</h1>
<img src="https://gitlab.com/dhorvay/hack-vm-translator/raw/master/doc/img/nand2tetris.png" width="450" height="auto"/>

<div align="center">
  <strong>nand2tetris - Project 7</strong>
</div>
<div align="center">
  A Virtual Machine translator for the Hack platform implemented in Java
</div>

[![author](https://img.shields.io/badge/author-dhorvay-brightgreen.svg)](https://www.gitlab.com/dhorvay)
[![language](https://img.shields.io/badge/language-java-b07219.svg)](https://www.gitlab.com/dhorvay)
[![license: GPL v3](https://img.shields.io/badge/license-GPLv3-informational.svg)](https://gitlab.com/dhorvay/hack-vm-translator/blob/master/LICENSE)
[![pipeline status](https://gitlab.com/dhorvay/hack-vm-translator/badges/master/pipeline.svg)](https://gitlab.com/dhorvay/hack-vm-translator/commits/master)
[![coverage report](https://gitlab.com/dhorvay/hack-vm-translator/badges/master/coverage.svg)](https://gitlab.com/dhorvay/hack-vm-translator/commits/master)
</div>

## Background

This repository contains a Hack VM Translator for the Hack platform, based on the seventh chapter of the book [The Elements of Computing System: Building a Modern Computer from First Principles](https://www.amazon.com/Elements-Computing-Systems-Building-Principles/dp/0262640686),
by [Noam Nisan](http://www.cs.huji.ac.il/~noam/) and [Shimon Schocken](http://www.shimonschocken.com/).
For more information about Hack, see [nand2tetris.org](https://www.nand2tetris.org/).

## Instructions

You will need to have [Java](https://www.java.com/en/download/) and [Maven](https://maven.apache.org/) installed

### Build

```bash
$ mvn clean package
```

### Run

```bash
$ java -jar target/hack-vm-translator-1.0.jar /path/to/Foo.vm
```

## Example

The program takes a hack vm file as input and generates an output in hack assembly format

**SimpleAdd.vm**
```
// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/07/StackArithmetic/SimpleAdd/SimpleAdd.vm

// Pushes and adds two constants.
push constant 7
push constant 8
add
```

**SimpleAdd.asm**
```
// push constant 7
@7
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 8
@8
D=A
@SP
A=M
M=D
@SP
M=M+1
// add
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M+D
@SP
M=M+1
```

## License

This project is licensed under the GPL License - see the [LICENSE](LICENSE) file for details
