package com.assessment.sudoku;


import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assessessment.sudoku.util.SudokuUtil;

import java.io.*;

public class SudokuMainApplication
{

	final String FILE_NAME="board.json";
	Logger log =LoggerFactory.getLogger(this.getClass());
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
	public boolean process(String input[])
	{
		InputStream inputStream = null;
		boolean sudokuSolved = false;
		try
		{
			inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);

			if (inputStream != null)
			{
				JSONArray inputArray = convertToJsonArray(input, inputStream);

				String[] strArray = constructArrayObject(inputArray);

				char[][] boardInputArray = SudokuUtil.convertToTwoDimArray(strArray);

				sudokuSolved=SudokuUtil.isValidSudoku(boardInputArray);
				log.info("BoardInput = "+input[0]+ " | SudokuBoardInputValid? :"+ sudokuSolved);
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
			log.error("Exception in process method::"+e);
		}

		return sudokuSolved;

	}
	private String[] constructArrayObject(JSONArray inputArray) {
		String inputArrayReplacedWithCurlyBraces = inputArray.toString().replace("[", "{").replace("]", "}");

		String strArray[] = inputArrayReplacedWithCurlyBraces.split("},");
		return strArray;
	}
	private JSONArray convertToJsonArray(String[] input, InputStream inputStream)
			throws UnsupportedEncodingException, IOException {
		JSONObject jsonObject;
		BufferedReader streamReader = new BufferedReader(
				new InputStreamReader(inputStream, "UTF-8"));
		StringBuilder responseStrBuilder = new StringBuilder();

		String inputStr;
		while ((inputStr = streamReader.readLine()) != null)
			responseStrBuilder.append(inputStr);
		jsonObject = new JSONObject(responseStrBuilder.toString());
		JSONArray inputArray = (JSONArray) jsonObject.get(input[0]);
		return inputArray;
	}

	/**
	 * Convert to two dim array
	 *
	 * @param strArr the str arr
	 * @return the result
	 **/
	


}

