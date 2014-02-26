package tests;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

import commands.AbstractCommand;
import commands.FDBKCommand;
import frontEnd.Turtle;

public class CommandTest {
	
	Turtle myTurtle = new Turtle();

	@SuppressWarnings("deprecation")
	@Test
	public void testFDBKCommand() {
		AbstractCommand fd = new FDBKCommand("FD", 50, myTurtle);
		AbstractCommand bk = new FDBKCommand("BK", 50, myTurtle);
		assertEquals(50, fd.execute());
		assertEquals(50, bk.execute());
		
	}

}
