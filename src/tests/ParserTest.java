package tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import parser.AbstractParser;
import parser.TextParser;

public class ParserTest {

	@Test
	public void testListReturn() {
		AbstractParser parser = new TextParser();
		List<String> listString = parser.parse("hello this is a line");
		
		for (int i = 0; i < listString.size(); i++) {
				assertEquals(determineWord(i), listString.get(i));
		}
		
		assertEquals(parser.parse("hello\n this is \n multiple lines"), parser.parse("hello this is multiple lines"));
	}
	
	private String determineWord(int i) {
		// TODO Auto-generated method stub
		if (i == 0)
			return "HELLO";
		if (i == 1)
			return "THIS";
		if (i == 2)
			return "IS";
		if (i == 3)
			return "A";
		if (i == 4)
			return "LINE";
		return null;
	}

	@Test 
	public void testLowerToUpperCase() {
		AbstractParser parser = new TextParser();
		assertEquals(parser.parse("heLLO THis wAs not UPpERcaSE"), parser.parse("hElLo thiS WaS NOT upPerCAse"));
	}
	
	@Test
	public void testNumbersInText() {
		AbstractParser parser = new TextParser();
		List<String> commands = parser.parse("fd 10 fd 20 fd 30");
		for (int i = 0; i < commands.size(); i++) {
			String command = commands.get(i);
			double parameter = 0;
			if (parser.isParameter(command))
				parameter = parser.convertToDouble(command);
			if(i % 2 == 1)
				assertEquals("fd", command);
			else
				assertEquals(5, parameter/i);
		}
	}

}