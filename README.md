# Boolean Expression Minimizer using Quine-McCluskey Method
## Introduction
The Boolean Expression Minimizer is a Java application that automates the Quine-McCluskey method to simplify and minimize Boolean expressions. This method is a widely used technique in digital logic design and optimization. This mini-project aims to provide an easy way for inputting Boolean expressions and obtaining their simplified forms using the Quine-McCluskey algorithm.

## Features
**Input**: Users can input Boolean expressions using standard logical operators (AND, OR, NOT) along with custom variable names. <br/> <br/>
**Quine-McCluskey Algorithm**: The core of the project is the implementation of the Quine-McCluskey algorithm, which is used to find prime implicants and generate essential prime implicants to minimize the expressions.<br/> <br/>
**Simplification**: The algorithm takes the input expression and reduces it to its simplest form, using the fewest possible terms.<br/> <br/>
**User Interface**: The project runs in any terminal that allows users to input Boolean expressions, triggers the minimization process and the simplified output is displayed!<br/>

## How to Use
**Input Expression**: Complie and run the Driver file and input your Boolean expression using standard logical operators and variable names. For example: AB + C'.<br/>
**Minimize**: After giving the inputs, the Quine-McCluskey algorithm is initiated and thus performs the minimization process.<br/>
**View Result**: The simplified Boolean expression(terms) will be displayed in the Terminal once the minimization is complete.<br/>

## Installation
Clone Repository: Clone this repository to your local machine using git clone <repository-url>.<br/>
Compile: Navigate to the project directory and compile the Java source files using a Java compiler (e.g., javac Driver.java).<br/>
Run: Run the compiled Java program using java BooleanMinimizer.<br/>

## Conclusion
The Boolean Expression Minimizer using the Quine-McCluskey Method is a valuable tool for anyone working in digital logic design, circuit optimization, or Boolean algebra. It provides a convenient way to simplify complex Boolean expressions, leading to more efficient logic design than Karnaugh maps(K-map).
