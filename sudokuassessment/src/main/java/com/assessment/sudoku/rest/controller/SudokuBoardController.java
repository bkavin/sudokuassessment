package com.assessment.sudoku.rest.controller;

import com.assessessment.sudoku.util.SudokuUtil;
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
		sudokuBoardResponse.setSudokuSolved(SudokuUtil.isValidSudoku(boardInputArray));

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

}
