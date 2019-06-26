package com.assessment.sudoku.rest.controller;

import com.assessessment.sudoku.util.SudokuUtil;
import com.assessment.sudoku.rest.response.SudokuBoardResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class SudokuBoardController
{
	Logger log =LoggerFactory.getLogger(this.getClass());
	/**
	 * Process
	 * @param payload the payload
	 * @param boardInput the board input
	 * @return the result
	 **/
	@RequestMapping(value = "/process", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public SudokuBoardResponse process(@RequestBody String payload, @RequestParam("boardInput") String boardInput) throws Exception
	{
		log.info("Started process method for input ::"+boardInput);
		String[] strArray = processJsonFile(payload, boardInput);

		char[][] boardInputArray = SudokuUtil.convertToTwoDimArray(strArray);

		SudokuBoardResponse sudokuBoardResponse = new SudokuBoardResponse();
		sudokuBoardResponse.setBoardInput(boardInput);
		sudokuBoardResponse.setSudokuSolved(SudokuUtil.isValidSudoku(boardInputArray));

		return sudokuBoardResponse;

	}

	private String[] processJsonFile(String payload, String boardInput) {
		JSONObject jsonObject = new JSONObject(payload);
		JSONArray inputArray = (JSONArray) jsonObject.get(boardInput);

		String inputArrayReplacedWithCurlyBraces = inputArray.toString().replace("[", "{").replace("]", "}");
		log.info("FirstInputArrayReplacedWithCurlyBraces = " + inputArrayReplacedWithCurlyBraces);
		String strArray[] = inputArrayReplacedWithCurlyBraces.split("},");
		return strArray;
	}

	

}
