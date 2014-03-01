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
	
}
