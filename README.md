<div align="center" style="text-align: center;">
<h1>Assignment 6</h1>
</div>

Overview
------
Assignment 6 focuses on integrating static analysis and automated testing using **GitHub Actions** to enhance workflow
implementation and maintain code quality.


### PART 1 -- Practice & Project Setup
1. __Inputs__: ISBN, Quantity
    * ISBN -- null, empty, valid, invalid
    * Quantity -- 0, >0, <0, more than available, less than available
2. __Output__: NullPointerException, total >= 0

Input Combinations (ISBN, Quantity):  (null, 0), (empty, 0), (invalid, >0), (valid, less), (valid, more), (valid, 0)

__Structural-based testing__ ==> Added test cases to call the equals method from the Book class




