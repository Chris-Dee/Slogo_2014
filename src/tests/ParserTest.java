package tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import parser.AbstractParser;
import parser.TextParser;
import parser.tree.StringNode;

public class ParserTest {
	
	@Test
	public void testBuildTree() {
		AbstractParser parser = new TextParser();
		StringNode root = parser.parse("fd 50 fd 50");
		assertEquals("FD", root.getCommandString());
		assertEquals("50", root.getChildren().get(0).getCommandString());
		assertEquals("FD", root.getChildren().get(0).getChildren().get(0).getCommandString());
		assertEquals("50", root.getChildren().get(0).getChildren().get(0).getChildren().get(0).getCommandString());
	}
	
	@Test
	public void testTwoParameters() {
		AbstractParser parser = new TextParser();
		StringNode root = parser.parse("sum fd 50 bk 30");
		//parser.printTree(root);
		assertEquals("SUM", root.getCommandString());
		assertEquals("FD", root.getChildren().get(0).getCommandString());
		assertEquals("50", root.getChildren().get(0).getChildren().get(0).getCommandString());
		assertEquals("BK", root.getChildren().get(1).getCommandString());
		assertEquals("30", root.getChildren().get(1).getChildren().get(0).getCommandString());

	}
	
	@Test
	public void testBrackets() {
		AbstractParser parser = new TextParser();
		StringNode root = parser.parse("repeat 3 [fd 50 repeat 5 [fd 50]] fd 50");
		parser.printTree(root);
		assertEquals("REPEAT", root.getCommandString());
		assertEquals("3", root.getChildren().get(0).getCommandString());
		//assertEquals("[FD 50 REPEAT 5 [FD 50]]", root.getChildren().get(1).getCommandString());
		//assertEquals("FD", root.getChildren().get(1).getChildren().get(0).getCommandString());
	}
	
}
