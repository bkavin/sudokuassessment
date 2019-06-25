The sudokuassessment porject can run as spring boot rest service and standalone program as well.
Rest url is http://localhost:8080/process?boardInput=? (the boardInput parameter is input we can pass 1 to 7 as per our json file, 
if give out of range then arrayindex exception will throw.
The rest method is POST we need to pass whole json as input, when tesing in postman or other tool, here i avoided reading input file in 
program since we can use this rest service for common.
Run Standalon  program
There is file name called SudokuMainApplication.java invoke this file from commad line as bellow

mvn exec:java -Dexec.mainClass="com.assessment.sudoku.SudokuMainApplication" -Dexec.args="1" (args is input we can pass 1- 7 as per 
json file.

