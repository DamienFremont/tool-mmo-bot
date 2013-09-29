package com.dfremont.bot;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.junit.Ignore;
import org.junit.Test;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

@Ignore
public class SystemTest {

	@Test
	public void test_key_actions() throws Exception {
		Robot robot = new Robot();

		// Simulate a mouse click
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
		// Simulate a key press
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
	}

	@Test
	public void test_key_actions_loop() throws Exception {

		Robot robot = new Robot();
		for (int i = 0; i < 5; i++) {
			System.out.println("click key");
			robot.keyPress(KeyEvent.VK_WINDOWS);
			robot.keyRelease(KeyEvent.VK_WINDOWS);
			System.out.println("wait 1sec");
			Thread.sleep(1000);
		}
	}

	@Test
	public void test_windows_focus() throws Exception {
		HWND hwnd = User32.INSTANCE.FindWindow(null,
				"Ordinateur"); // window title
		if (hwnd == null) {
			System.out.println("Explorer is not running");
		} else {
			User32.INSTANCE.ShowWindow(hwnd, 9); // SW_RESTORE
			User32.INSTANCE.SetForegroundWindow(hwnd); // bring to front
		}
	}

}
