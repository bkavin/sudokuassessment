The sudokuassessment porject developed  as spring boot rest service and standalone program as well.
Rest url is http://localhost:8080/process?boardInput=? (the boardInput parameter is input we can pass 1 to 7 as per our json file. 
The rest method is POST we need to pass whole json as input, when tesing in postman or other tool.

How to run Standalon  program
Invoke bleow command from command line 

mvn exec:java -Dexec.mainClass="com.assessment.sudoku.SudokuMainApplication" -Dexec.args="1" (args is input we can pass 1- 7 as per 
json file.

