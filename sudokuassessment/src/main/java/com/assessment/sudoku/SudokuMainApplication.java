package com.assessment.sudoku;

import org.json.JSONArray;
import org.json.JSONObject;

import com.assessessment.sudoku.util.SudokuUtil;

import java.io.*;

public class SudokuMainApplication
{

	final String FILE_NAME="board.json";

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
				sudokuSolved=SudokuUtil.isValidSudoku(boardInputArray);

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


}

