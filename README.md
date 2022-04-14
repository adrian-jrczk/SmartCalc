# SmartCalc

## Table of contents
* [About](#about)
* [Features](#features)
* [Usage](#usage)
* [Installation](#installation)
* [Technologies used](#technnologies-used)
* [Screenshots](#screenshots)


## About

SmartCalc is a command line calculator with ability to do 3 types of calculations:
- solving mathematical expressions
- solving linear equation systems
- performing matrix operations

## Features

- calculate results of basic operations
- assign and use variables
- solve mathematical expressions
- solve linear equation systems
- perform different matrix operations

## Usage

SmartCalc can be used directly with command line or through program arguments.

### CLI

#### EXPRESSIONS

This module calculates simple mathematical expressions.
You can use numbers, operators(+ - \* \/ ^), variables and brackets.
Before using variable you need to assign it like this: name = value.

#### LINEAR EQUATIONS

This module calculates value of variables in linear equations systems.
Example of correct equations system form and results:

|input:          | description                                 |
|----------------|---------------------------------------------|
|3 3             | number of variables and number of equations |
|1 1 2 9         | equation: 1x + 1y + 2z = 9                  |
|2 4 -3 1        | equation: 2x + 4y - 3z = 1                  |
|3 6 -5 0        | equation: 3x + 6y - 5z = 0                  |


|results:        | description          |
|----------------|----------------------|
|1.0             | value of variable x  |
|2.0             | value of variable y  |
|3.0             | value of variable z  |

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


Example of correct matrix format:

|input:   |description                           |
|---------|--------------------------------------|
|3 3      | number of rows and number of columns |
|1 2 2    | matrix row 1                         |
|2 4 -3   | matrix row 2                         |
|3 6 -5   | matrix row 3                         |


#### Available commands:
`/variables`                - shows all defined variables
`/equation`                 - marks next input as equations
`/matrix add`               - type to add matrices
`/matrix multiple`          - type to multiply matrices
`/matrix multiple by scalar`- type to multiply matrix by scalar
`/matrix determinant`       - type to calculate matrix determinant
`/matrix inverse`           - type to inverse matrix
`/matrix transpose`         - type to transpose matrix
`/matrix flip vertical`     - type to flip matrix vertically
`/matrix flip horizontal`   - type to flip matrix horizontally
`/help`                     - type to display this message
`/exit`                     - type to exit program

### Arguments

Data in files should respect forms specified in CLI.
To multiply matrix by scalar, it's value should be put before matrix.
When multiplying or adding matrices, they should be put one after another.


#### Arguments:

`-t, --type CALCULATION_TYPE`			calculation type
`-m, --matrix-operation`				matrix operation type
`-i, --input-file INPUTFILENAME`		name of the file with input data
`-o, --output-file OUTPUTFILENAME`		name of the file with output data (default: INPUTFILENAME)

## Installation

1. Import this repository to some folder with `git clone repourl`
2. Open this folder and install with `mvn clean install`
3. In `target` folder there will be executable jar file `smartcalc.jar` which you can move freely and run with `java -jar smartcalc.jar`

## Technologies used

- Java 17

## Screenshots

![screenshot 1](images/screenshot01.png?raw=true "Expression calculation example")
![screenshot 2](images/screenshot02.png?raw=true "Matrix operations example")
![screenshot 3](images/screenshot03.png?raw=true "Solving linear equations example")