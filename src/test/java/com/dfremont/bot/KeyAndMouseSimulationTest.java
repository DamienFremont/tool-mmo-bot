package com.dfremont.bot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.junit.Test;

public class KeyAndMouseSimulationTest {

	@Test
	public void test() throws AWTException {
		Robot robot = new Robot();

		// Simulate a mouse click
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

		// Simulate a key press
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);

		// Simulate a window key press
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
	}

}
