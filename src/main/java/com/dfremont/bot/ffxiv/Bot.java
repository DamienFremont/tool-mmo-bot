package com.dfremont.bot.ffxiv;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class Bot {

	private static final long COMBO_COOLDOWN = 2700L;

	Robot robot;

	private Thread effectTask = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				cast();
			}
		}
	});
	private Thread killTask = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				searchAndLock();
				kill();
			}
		}
	});

	public Bot() throws AWTException {
		robot = new Robot();
	}

	public void farm() {
		initRobot();
		effectTask.start();
		killTask.start();
		while (true) {
			// nothing end here!
		}
	}

	private void cast() {
		System.out.println("cast blood");
		robotKey(KeyEvent.VK_5);
		robotSleep(90000L);
	}

	private void initRobot() {
		HWND hwnd = User32.INSTANCE.FindWindow(null,
				"FINAL FANTASY XIV: A Realm Reborn");
		if (hwnd == null) {
			throw new IllegalStateException("FF14 is not running");
		} else {
			User32.INSTANCE.ShowWindow(hwnd, 9);
			User32.INSTANCE.SetForegroundWindow(hwnd);
		}
	}

	private void robotSleep(long timeInMs) {
		try {
			Thread.sleep(timeInMs);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void robotKey(int k, long time) {
		robot.keyPress(k);
		robotSleep(time);
		robot.keyRelease(k);
	}

	private void robotKey(int k) {
		robot.keyPress(k);
		robotSleep(10);
		robot.keyRelease(k);
	}

	void searchAndLock() {
		System.out.println("lock");
		robotSleep(2000);
		robotKey(KeyEvent.VK_TAB, 500L);

	}

	void kill() {
		System.out.println("attack!");
		doWeak();
		doCombo1();
		doCombo2();
		doCombo1();
		doCombo2();
	}

	private void doWeak() {
		robotKey(KeyEvent.VK_4);
		robotSleep(4000L);
	}

	void doCombo1() {
		System.out.println("attack 1 then 3");
		robotKey(KeyEvent.VK_1);
		robotSleep(COMBO_COOLDOWN);
		robotKey(KeyEvent.VK_3);
		robotSleep(COMBO_COOLDOWN);
	}

	void doCombo2() {
		System.out.println("attack 1 then 8");
		robotKey(KeyEvent.VK_1);
		robotSleep(COMBO_COOLDOWN);
		robotKey(KeyEvent.VK_8);
		robotSleep(COMBO_COOLDOWN);
	}
}
