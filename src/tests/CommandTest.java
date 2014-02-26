package tests;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

import commands.AbstractCommand;
import commands.FDBKCommand;
import frontEnd.Turtle;

public class CommandTest {
	
	List<Turtle> myTurtles = new ArrayList<Turtle>();

	@SuppressWarnings("deprecation")
	@Test
	public void testFDBKCommand() {
		AbstractCommand fd = new FDBKCommand("FD", 50, myTurtles);
		AbstractCommand bk = new FDBKCommand("BK", 50, myTurtles);
		assertEquals(50, fd.execute());
		assertEquals(50, bk.execute());
		
	}

}
