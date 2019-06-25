package com.assessment.sudoku.rest.response;

public class SudokuBoardResponse
{
  private String boardInput;
  private boolean sudokuSolved;

  /**
   * Get board input
   * @return the board input
   **/
  public String getBoardInput()
  {
    return boardInput;
  }

  /**
   * Set board input
   * @param boardInput the new value for board input
   **/
  public void setBoardInput(String boardInput)
  {
    this.boardInput = boardInput;
  }

  /**
   * Checks if this is sudoku solved
   * @return true if this is sudoku solved
   **/
  public boolean isSudokuSolved()
  {
    return sudokuSolved;
  }

  /**
   * Set sudoku solved
   * @param sudokuSolved the new value for sudoku solved
   **/
  public void setSudokuSolved(boolean sudokuSolved)
  {
    this.sudokuSolved = sudokuSolved;
  }

}
