package tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Ignore;
import org.junit.Test;

import exception.IllegalCommandException;

import backEnd.Managers.LanguageManager;

import parser.AbstractParser;
import parser.TextParser;
import parser.tree.StringNode;

public class ParserTest {
	

	
	@Test
	public void testBuildTree() throws IllegalCommandException{
		TextParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("fd :hello");
		assertEquals("FORWARD", root.get(0).getCommandString());
		assertEquals(":HELLO", root.get(0).getChildren().get(0).getCommandString());
	}
	
	@Test
	public void testRepeat() throws IllegalCommandException{
		TextParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("Repeat 9 [FD 50]");
		assertEquals("REPEAT", root.get(0).getCommandString());
		assertEquals(1, root.size());
	}
	
	@Test
	public void testIf() throws IllegalCommandException{
		TextParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("If less? 1 2 [fd 50]");
		assertEquals("IF", root.get(0).getCommandString());
		assertEquals(1, root.size());

	}
	@Test
	public void testIfElse() throws IllegalCommandException{
		TextParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("ifelse less? 1 2 [fd 50] [bk 50]");
		assertEquals("IFELSE", root.get(0).getCommandString());
		assertEquals(1, root.size());
	}
	@Test
	public void testDoTimes() throws IllegalCommandException{
		TextParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("Dotimes [:a 10] [fd 50]");
		assertEquals("DOTIMES", root.get(0).getCommandString());
		assertEquals(1, root.size());
	}
	@Test
	public void testFor() throws IllegalCommandException {
		TextParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("for [ :a 0 40 10 ] [ fd :a ]");
		assertEquals("FOR", root.get(0).getCommandString());
		assertEquals(1, root.size());
		root.get(0).printParameters();
	}
	
	@Test
	public void testVariables() throws IllegalCommandException{
		TextParser parser = new TextParser();
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
	public void testVarz() throws IllegalCommandException {
		TextParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("[ :a 0 40 10 ]");
		for (StringNode child : root) {
			System.out.println("sdfd" + child.getCommandString());
		}
				
	}
	@Test
	public void testFour() throws IllegalCommandException{
		TextParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("4");

		assertEquals("4", root.get(0).getCommandString());
		
	}
	
	@Test
	public void testLanguage() throws IllegalCommandException{
		TextParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		lang.setLanguage("French");
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("devant 50");
		for (StringNode node : root) {
			System.out.println(node.getCommandString());
		}
		assertEquals("FORWARD", root.get(0).getCommandString());
	}
	
	@Test
	public void testTranslate() throws IllegalCommandException{
		TextParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		lang.setLanguage("French");
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("devant 50");
		System.out.println(lang.translateNode(root.get(0)).getCommandString());
		assertEquals("FORWARD", lang.translateNode(root.get(0)).getCommandString());
	}
	
	@Test
	public void testErrorCheck() throws IllegalCommandException {
		TextParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("fd 50 fd 50");
		//System.out.println("testErrorCheck" + " has errors:" + (parser).hasErrors(root));
		assertFalse(parser.hasErrors(root));
	}
	
	@Ignore
	public void testPalette() throws IllegalCommandException {
		TextParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> root = parser.parse("SetPalette 2 255 255 255 fd 50");
		assertEquals("SETPALETTE", root.get(0).getCommandString());
		assertEquals("2", root.get(0).getChildren().get(0).getCommandString());
		for (int i = 1; i < root.get(0).getChildren().size(); i++) {
			assertEquals("255", root.get(0).getChildren().get(i).getCommandString());
		}
		assertEquals("FD", root.get(1).getCommandString());

	}
	
	@Test
	public void testControls() throws IllegalCommandException {
		TextParser parser = new TextParser();
		LanguageManager lang = new LanguageManager();
		parser.setLanguageManager(lang);
		List<StringNode> forcommand = parser.parse("for [:a 0 40 10] [ fd :a ]");
		forcommand.get(0).printParameters();
		List<StringNode> ifcommand = parser.parse("if equalp 50 50 [ fd 50 ]");
		ifcommand.get(0).printParameters();
		List<StringNode> dotimes = parser.parse("Dotimes [:a 10] [fd 50]");
		dotimes.get(0).printParameters();
		List<StringNode> tell = parser.parse("tell [1 2 3]");
		tell.get(0).printParameters();
		List<StringNode> ask = parser.parse("ask [1 2 3] [fd 50]");
		ask.get(0).printParameters();
		List<StringNode> askwith = parser.parse("askwith [equalp pc 3] [fd 50]");
		askwith.get(0).printParameters();
		List<StringNode> repeat = parser.parse("repeat 4 [fd 50 rt 90]");
		repeat.get(0).printParameters();

	}
}
