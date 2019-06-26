package com.assessessment.sudoku.util;

public class SudokuUtil {

	/**
	 * Checks if this is valid sudoku
	 *
	 * @return true if this is valid sudoku
	 **/
	public static boolean isValidSudoku(char[][] board)
	{
		if (board == null || board.length != 9 || board[0].length != 9)
		{
			return false;
		}
		// check each column whether same number is there or not
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

		//check each row contains same number there or not
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

		//check each 3*3 matrix contains same value or not
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
	public static char[][] convertToTwoDimArray(String[] strArr)
	{
		char[][] ret = new char[strArr.length][];
		for (int i = 0; i < strArr.length; i++)
		{
			ret[i] = strArr[i].replaceAll("[{]", "").replaceAll(",", "").toCharArray();
		}
		return ret;
	}
}
