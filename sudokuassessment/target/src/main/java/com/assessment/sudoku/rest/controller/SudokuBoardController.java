package com.assessment.sudoku.rest.controller;

import com.assessment.sudoku.rest.response.SudokuBoardResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class SudokuBoardController
{
  /**
   * Process
   * @param payload the payload
   * @param boardInput the board input
   * @return the result
   **/
  @RequestMapping(value = "/process", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
  public SudokuBoardResponse process(@RequestBody String payload, @RequestParam("boardInput") String boardInput) throws Exception
  {
    JSONObject jsonObject = new JSONObject(payload);
    JSONArray inputArray = (JSONArray) jsonObject.get(boardInput);

    String inputArrayReplacedWithCurlyBraces = inputArray.toString().replace("[", "{").replace("]", "}");
    System.out.println("FirstInputArrayReplacedWithCurlyBraces = " + inputArrayReplacedWithCurlyBraces);

    String strArray[] = inputArrayReplacedWithCurlyBraces.split("},");

    char[][] boardInputArray = convertToTwoDimArray(strArray);

    SudokuBoardResponse sudokuBoardResponse = new SudokuBoardResponse();
    sudokuBoardResponse.setBoardInput(boardInput);
    sudokuBoardResponse.setSudokuSolved(isValidSudoku(boardInputArray));

    return sudokuBoardResponse;

  }

  /**
   * Convert to two dim array
   * @param strArr the str arr
   * @return the result
   **/
  private char[][] convertToTwoDimArray(String[] strArr)
  {
    char[][] ret = new char[strArr.length][];
    for (int i = 0; i < strArr.length; i++)
    {
      ret[i] = strArr[i].replaceAll("[{]", "").replaceAll(",", "").toCharArray();
    }
    return ret;
  }

  /**
   * Checks if this is valid sudoku
   * @return true if this is valid sudoku
   **/
  public boolean isValidSudoku(char[][] board)
  {
    if (board == null || board.length != 9 || board[0].length != 9)
    {
      return false;
    }
    // check each column
    for (int i = 0; i < 9; i++)
    {
      boolean[] m = new boolean[9];
      for (int j = 0; j < 9; j++)
      {
        if (board[i][j] != '.')
        {
          if (m[(int) (board[i][j] - '1')])
          {
            return false;
          }
          m[(int) (board[i][j] - '1')] = true;
        }
      }
    }

    //check each row
    for (int j = 0; j < 9; j++)
    {
      boolean[] m = new boolean[9];
      for (int i = 0; i < 9; i++)
      {
        if (board[i][j] != '.')
        {
          if (m[(int) (board[i][j] - '1')])
          {
            return false;
          }
          m[(int) (board[i][j] - '1')] = true;
        }
      }
    }

    //check each 3*3 matrix
    for (int block = 0; block < 9; block++)
    {
      boolean[] m = new boolean[9];
      for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++)
      {
        for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++)
        {
          if (board[i][j] != '.')
          {
            if (m[(int) (board[i][j] - '1')])
            {
              return false;
            }
            m[(int) (board[i][j] - '1')] = true;
          }
        }
      }
    }

    return true;
  }
}
