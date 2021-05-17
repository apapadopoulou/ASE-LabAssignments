# Unit Testing Practice Repository

The purpose of this project is to practice the unit testing concepts and complete the third lab assignment
of the Applied Software Engineering course in the Department of Management Science and Technology.

## <a name="table-of-contents"></a>Table of contents
* [ArithmeticOperations](#arithmetic-operations)
* [MyMath](#my-math)

---

## <a name="arithmetic-operations"></a>Arithmetic Operations

This class contains methods for multiplying and dividing numbers. 
The objective of the lab assignment was to provide test cases for each one of the methods.

* The `divide` method is tested to throw an `ArithmeticException` whenever a division by zero is attempted.
* The `multiply` method is tested to throw an `IllegalArgumentException` whenever a negative number is used as an input.

---

## <a name="my-math"></a>MyMath

MyMath is a new class that was created for the purposes of this assignment.
The method's objective is to calculate the factorial of any given number in the range of [0, 12].

The class only contains a `factorial` method, which is tested using a Testing Suite.

* `MyMathParametrizedTest` class contains the parameterized test of boundary and valid input values.
* `MyMathTest` class contains tests regarding out-of-range values that should raise exceptions.