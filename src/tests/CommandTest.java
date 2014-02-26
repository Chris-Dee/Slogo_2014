package tests;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

import commands.AbstractCommand;
import commands.CORQuery;
import commands.CSCommand;
import commands.FDBKCommand;
import commands.GOTOCommand;
import commands.HEADINGCommand;
import commands.HomeCommand;
import commands.LTRTCommand;
import commands.PENCommand;
import commands.PENDOWNPCommand;
import commands.SETHCommand;
import commands.SHOWINGPCommand;
import commands.STHTCommand;
import frontEnd.Turtle;

public class CommandTest {
	
	List<Turtle> myTurtles = new ArrayList<Turtle>();
	Turtle myTurtle = new Turtle();

	@org.junit.Test
	public void testFDBKCommand() {
		AbstractCommand fd = new FDBKCommand("FD", 50, myTurtle);
		AbstractCommand bk = new FDBKCommand("BK", 50, myTurtle);
		assertEquals(50, fd.execute(), .001);
		assertEquals(50, bk.execute(), .001);
	}
	
	@org.junit.Test
	public void testCorQuery() {
		AbstractCommand cor = new CORQuery("XCOR", myTurtle);
		//not sure how this is exactly implemented
		assertEquals(0, cor.execute(), .001);
	}
	
	@org.junit.Test
	public void testCSCommand() {
		AbstractCommand cs = new CSCommand("CS", myTurtle);
		assertEquals(0, cs.execute(), .001);
	}
	
	@Test
	public void testGOTOCommand() {
		AbstractCommand GOTO = new GOTOCommand("GOTO", 0, 0, myTurtle);
		assertEquals(1, GOTO.execute(), .001);
	}
	
	@Test
	public void testHEADINGCommand() {
		AbstractCommand heading = new HEADINGCommand("HEADING", myTurtle);
		assertEquals(0, heading.execute(), .001);
	}
	
	@Test
	public void testHomeCommand() {
		AbstractCommand home = new HomeCommand("HOME", myTurtle);
		assertEquals(0, home.execute(), .001);
	}
	
	@Test
	public void testLTRTCommand() {
		AbstractCommand lt = new LTRTCommand("FD", 50, myTurtle);
		AbstractCommand rt = new LTRTCommand("BK", 50, myTurtle);
		assertEquals(50, lt.execute(), .001);
		assertEquals(50, rt.execute(), .001);
	}
	
	@Test
	public void testPENCommand() {
		AbstractCommand pd = new PENCommand("PD", myTurtle);
		assertEquals(0, pd.execute(), .001);
	}
	
	@Test
	public void testPENDOWNPCommand() {
		AbstractCommand pendownp = new PENDOWNPCommand("PENDOWNP", myTurtle);
		assertEquals(0, pendownp.execute(), .001);
	}
	
	@Test 
	public void testSETHCommand() {
		AbstractCommand seth = new SETHCommand("SETH", 90, myTurtle);
		assertEquals(0, seth.execute(), .001);
	}
	
	@Test
	public void testSHOWINGPCommand() {
		AbstractCommand showingp = new SHOWINGPCommand("SHOWINGP", myTurtle);
		assertEquals(0, showingp.execute(), .001);
	}
	
	@Test
	public void testSTHTCommand() {
		AbstractCommand stht = new STHTCommand("ST", myTurtle);
		assertEquals(0, stht.execute(), .001);
	}

}
