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
		List<StringNode> root = parser.parse("fd 50 fd 50");
		assertEquals("FD", root.get(0).getCommandString());
		System.out.println("Start: ");
		parser.printTree(root.get(0));
		System.out.println("F:SDKLJFS:DLFK");
		for (StringNode s : root.get(0).getChildren()) {
			System.out.println(s.getCommandString() + " ");
		}
		System.out.println("F:SDKLJFS:DLFK");

		for (StringNode s : root.get(1).getChildren()) {
			System.out.println(s.getCommandString() + " ");
		}
//		assertEquals("FD", root.get(1).getCommandString());
//		System.out.println("F:SDKLJFS:DLFK");
//		parser.printTree(root.get(1));
	}
	
}
