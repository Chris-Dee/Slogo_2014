package tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import backEnd.Managers.LanguageManager;

import parser.AbstractParser;
import parser.TextParser;
import parser.tree.StringNode;

public class ParserTest {
	

	
	@Test
	public void testBuildTree() {
		AbstractParser parser = new TextParser();
		List<StringNode> root = parser.parse("fd :hello");
		assertEquals("FD", root.get(0).getCommandString());
		assertEquals(":HELLO", root.get(0).getChildren().get(0).getCommandString());
	}
	
	@Test
	public void testRepeat() {
		AbstractParser parser = new TextParser();
		List<StringNode> root = parser.parse("Repeat 9 [FD 50]");
		assertEquals("REPEAT", root.get(0).getCommandString());
		assertEquals(1, root.size());
	}
	
	@Test
	public void testIf() {
		AbstractParser parser = new TextParser();
		List<StringNode> root = parser.parse("If less? 1 2 [fd 50]");
		assertEquals("IF", root.get(0).getCommandString());
		assertEquals(1, root.size());

	}
	@Test
	public void testIfElse() {
		AbstractParser parser = new TextParser();
		List<StringNode> root = parser.parse("ifelse less? 1 2 [fd 50] [bk 50]");
		assertEquals("IFELSE", root.get(0).getCommandString());
		assertEquals(1, root.size());
	}
	@Test
	public void testDoTimes() {
		AbstractParser parser = new TextParser();
		List<StringNode> root = parser.parse("Dotimes [:a 10]");
		assertEquals("DOTIMES", root.get(0).getCommandString());
		assertEquals(1, root.size());
	}
	@Test
	public void testFor() {
		AbstractParser parser = new TextParser();
		List<StringNode> root = parser.parse("for [:a 0 10 2] [fd 50]");
		assertEquals("FOR", root.get(0).getCommandString());
		assertEquals(1, root.size());
	}
	
	@Test
	public void testVariables() {
		AbstractParser parser = new TextParser();
		List<StringNode> root = parser.parse(":a 2 :b 3 :c 5");
		assertEquals(":A", root.get(0).getCommandString());
		assertEquals("2", root.get(1).getCommandString());
		assertEquals(":B", root.get(2).getCommandString());
		assertEquals("3", root.get(3).getCommandString());
		assertEquals(":C", root.get(4).getCommandString());
		assertEquals("5", root.get(5).getCommandString());
	}
	
	@Test
	public void testLanguage() {
		AbstractParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		parser.setLanguage("French");
		List<StringNode> root = parser.parse("devant 50");
		for (StringNode node : root) {
			System.out.println(node.getCommandString());
		}
		assertEquals("DEVANT", root.get(0).getCommandString());
	
	}
	
}
