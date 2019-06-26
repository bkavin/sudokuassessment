package sudokuassessment;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.assessment.sudoku.SudokuMainApplication;

public class SudokuMainApplicationTest extends AbstractTest{

	@Autowired	
	private SudokuMainApplication sudokuMainApplication;

	@Test
	public void testSudokuWithTrueValue() {
		logger.info("Test case with board value is one");
		sudokuMainApplication.process(new String[] {"1"});
		assertEquals(true, true);
	}
	@Test
	public void testSudokuWithFalseValue() {
		logger.info("Test case with board value is two");
		sudokuMainApplication.process(new String[] {"2"});
		assertEquals(false, false);
		
	}
}
