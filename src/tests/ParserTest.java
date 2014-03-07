package tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import exception.IllegalCommandException;

import backEnd.Managers.LanguageManager;

import parser.AbstractParser;
import parser.TextParser;
import parser.tree.StringNode;

public class ParserTest {
	

	
	@Test
	public void testBuildTree() throws IllegalCommandException{
		AbstractParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("fd :hello");
		assertEquals("FORWARD", root.get(0).getCommandString());
		assertEquals(":HELLO", root.get(0).getChildren().get(0).getCommandString());
	}
	
	@Test
	public void testRepeat() throws IllegalCommandException{
		AbstractParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("Repeat 9 [FD 50]");
		assertEquals("REPEAT", root.get(0).getCommandString());
		assertEquals(1, root.size());
	}
	
	@Test
	public void testIf() throws IllegalCommandException{
		AbstractParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("If less? 1 2 [fd 50]");
		assertEquals("IF", root.get(0).getCommandString());
		assertEquals(1, root.size());

	}
	@Test
	public void testIfElse() throws IllegalCommandException{
		AbstractParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("ifelse less? 1 2 [fd 50] [bk 50]");
		assertEquals("IFELSE", root.get(0).getCommandString());
		assertEquals(1, root.size());
	}
	@Test
	public void testDoTimes() throws IllegalCommandException{
		AbstractParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("Dotimes [:a 10]");
		assertEquals("DOTIMES", root.get(0).getCommandString());
		assertEquals(1, root.size());
	}
	@Test
	public void testFor() throws IllegalCommandException {
		AbstractParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("for [:a 0 10 2] [fd 50]");
		assertEquals("FOR", root.get(0).getCommandString());
		assertEquals(1, root.size());
	}
	
	@Test
	public void testVariables() throws IllegalCommandException{
		AbstractParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("1 2 3 4 5 6");
		assertEquals("1", root.get(0).getCommandString());
		assertEquals("2", root.get(1).getCommandString());
		assertEquals("3", root.get(2).getCommandString());
		assertEquals("4", root.get(3).getCommandString());
		assertEquals("5", root.get(4).getCommandString());
		assertEquals("6", root.get(5).getCommandString());
	}
	@Test
	public void testFour() throws IllegalCommandException{
		AbstractParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("4");

		assertEquals("4", root.get(0).getCommandString());
		
	}
	
	@Test
	public void testLanguage() throws IllegalCommandException{
		AbstractParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		parser.setLanguage("French");
		List<StringNode> root = parser.parse("devant 50");
		for (StringNode node : root) {
			System.out.println(node.getCommandString());
		}
		assertEquals("FORWARD", root.get(0).getCommandString());
	}
	
	@Test
	public void testTranslate() throws IllegalCommandException{
		AbstractParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		parser.setLanguage("French");
		List<StringNode> root = parser.parse("devant 50");
		System.out.println(lang.translateNode(root.get(0)).getCommandString());
		assertEquals("FORWARD", lang.translateNode(root.get(0)).getCommandString());
	}
	
	@Test
	public void testErrorCheck() throws IllegalCommandException {
		AbstractParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("fd 50 fd 50");
		System.out.println("testErrorCheck" + " has errors:" + ((TextParser) parser).hasErrors(root));
		assertTrue(((TextParser) parser).hasErrors(root));
	}
	
}
