# SmartCalc

![screenshot 1](images/screenshot.png?raw=true "Calculations showcase")

## About

SmartCalc is a command line calculator with the ability to perform 3 types of calculations:
- solving mathematical expressions
- solving linear equation systems
- performing matrix operations

## Features

- Perform basic mathematical operations
- Assign and use variables
- Solve mathematical expressions
- Solve linear equation systems
- Perform different matrix operations

## Usage

SmartCalc can be used directly with command line or through program arguments.

### CLI

#### EXPRESSIONS

This module calculates simple mathematical expressions.
You can use numbers, operators(+ - \* \/ ^), variables and brackets.
Before using variable you need to assign it like this: name = value.

#### LINEAR EQUATIONS

This module calculates value of variables in linear equations systems.
Example of correct input form and results:

| input:   | description                                 |
|----------|---------------------------------------------|
| 3 3      | number of variables and number of equations |
| 1 1 2 9  | equation: 1x + 1y + 2z = 9                  |
| 2 4 -3 1 | equation: 2x + 4y - 3z = 1                  |
| 3 6 -5 0 | equation: 3x + 6y - 5z = 0                  |


| results: | description         |
|----------|---------------------|
| 1.0      | value of variable x |
| 2.0      | value of variable y |
| 3.0      | value of variable z |

#### MATRICES

This module allows you to operate on matrices.
Available operations:
- matrices addition
- matrices multiplication
- matrix multiplication by scalar
- matrix determinant calculation
- matrix inversion
- matrix transposition
- matrix vertical flip
- matrix horizontal flip


Example of correct matrix input format:

| input: | description                          |
|--------|--------------------------------------|
| 3 3    | number of rows and number of columns |
| 1 2 2  | matrix row 1                         |
| 2 4 -3 | matrix row 2                         |
| 3 6 -5 | matrix row 3                         |


#### Available commands:<br/>
`/variables` - shows all defined variables<br/>
`/equation` - marks next input as system of linear equations<br/>
`/matrix add` - type to add matrices<br/>
`/matrix multiple` - type to multiply matrices<br/>
`/matrix multiple by scalar` - type to multiply matrix by scalar<br/>
`/matrix determinant` - type to calculate matrix determinant<br/>
`/matrix inverse` - type to inverse matrix<br/>
`/matrix transpose` - type to transpose matrix<br/>
`/matrix flip vertical` - type to flip matrix vertically<br/>
`/matrix flip horizontal` - type to flip matrix horizontally<br/>
`/help` - type to display help message<br/>
`/exit` - type to exit program

### Arguments

Data in files should respect forms specified in CLI.
To multiply matrix by scalar, it's value should be put before matrix.
When multiplying or adding matrices, they should be put one after another.


#### Available arguments:

`-t, --type CALCULATION_TYPE` - calculation type<br/>
`-m, --matrix-operation` - matrix operation type<br/>
`-i, --input-file INPUT_FILENAME` - name of the file with input data<br/>
`-o, --output-file OUTPUT_FILENAME` - name of the file with output data (default: INPUT_FILENAME-results.txt)

## Installation

1. Import this repository to some folder with `git clone https://github.com/adrian-jrczk/SmartCalc.git`
2. Open this folder and install with `mvn clean package`
3. In `target` folder there will be executable jar file `smartcalc.jar` which you can move freely and run with `java -jar smartcalc.jar`

## Technologies and tools

- Java
- Maven
- JUnit
- JCommander
