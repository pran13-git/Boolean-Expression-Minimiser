# Boolean Expression Minimizer using Quine-McCluskey Method
## Introduction
The Boolean Expression Minimizer is a Java CLI tool that automates the Quine-McCluskey method to simplify and minimize Boolean expressions. This method is a widely used technique in digital logic design and optimization. This mini-project aims to provide an easy way for inputting Boolean expressions and obtaining their simplified forms using the Quine-McCluskey algorithm.

## Features
**Input**: Users can input Boolean expressions using standard logical operators (AND, OR, NOT) along with custom variable names. <br/> <br/>
**Quine-McCluskey Algorithm**: The core of the project is the implementation of the Quine-McCluskey algorithm, which is used to find prime implicants and generate essential prime implicants to minimize the expressions.<br/> <br/>
**Simplification**: The algorithm takes the input expression and reduces it to its simplest form, using the fewest possible terms.<br/> <br/>
**User Interface**: The project runs in any terminal that allows users to input Boolean expressions, triggers the minimization process and the simplified output is displayed!<br/>

<img width="315" alt="image" src="https://github.com/pran13-git/Boolean-Expression-Minimiser/assets/72128521/cdeba857-0bb8-4d57-9464-311250ecd3d3">


## How to Use
**Input Expression**: Complie and run the Driver file and input your Boolean expression using standard logical operators and variable names. <br/>
For example: AB + C'.<br/>
**Minimize**: After giving the inputs, the Quine-McCluskey algorithm is initiated and thus performs the minimization process.<br/>
**View Result**: The simplified Boolean expression(terms) will be displayed in the Terminal once the minimization is complete.<br/>

#### Output Example:
<img width="377" alt="image" src="https://github.com/pran13-git/Boolean-Expression-Minimiser/assets/72128521/4d07a813-7fe4-4f41-9708-2cc2c234f07d">


## Installation
Clone Repository: Clone this repository to your local machine using git clone <repository-url>.<br/>
Compile: Navigate to the project directory and compile the Java source files using a Java compiler (e.g., javac Driver.java).<br/>
Run: Run the compiled Java program using java BooleanMinimizer.<br/>

## Conclusion
The Boolean Expression Minimizer using the Quine-McCluskey Method is a valuable tool for anyone working in digital logic design, circuit optimization, or Boolean algebra. It provides a convenient way to simplify complex Boolean expressions, leading to more efficient logic design than Karnaugh maps(K-map).
