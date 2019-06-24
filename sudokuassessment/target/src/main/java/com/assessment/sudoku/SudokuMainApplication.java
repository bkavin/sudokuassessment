package com.assessment.sudoku;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class SudokuMainApplication
{
  /**
   * Main
   * @param args the args
   **/
  final static String FILE_NAME="board.json";
  public static void main(String args[])
  {
    SudokuMainApplication sudokuMainApplication = new SudokuMainApplication();
    sudokuMainApplication.process(args);
  }
  /**
   * Process
   * @param input the args
   * @return the result
   **/
  private boolean process(String input[])
  {
    InputStream inputStream = null;
    JSONObject jsonObject = null;
    boolean sudokuSolved = false;
    try
    {
      inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);

      if (inputStream != null)
      {
        BufferedReader streamReader = new BufferedReader(
                new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
          responseStrBuilder.append(inputStr);
        jsonObject = new JSONObject(responseStrBuilder.toString());

        JSONArray inputArray = (JSONArray) jsonObject.get(input[0]);

        String inputArrayReplacedWithCurlyBraces = inputArray.toString().replace("[", "{").replace("]", "}");

        String strArray[] = inputArrayReplacedWithCurlyBraces.split("},");

        char[][] boardInputArray = convertToTwoDimArray(strArray);
        sudokuSolved = isValidSudoku(boardInputArray);

        System.out.println("BoardInput = "+input[0]+ " | SudokuBoardInputValid? :"+ sudokuSolved);

      }
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (Exception e)
    {
      System.out.println("Exception: " + e);
    }

    return sudokuSolved;

  }

  /**
   * Convert to two dim array
   *
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
   *
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

