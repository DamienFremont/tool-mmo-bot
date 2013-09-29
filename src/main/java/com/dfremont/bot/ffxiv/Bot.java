package com.dfremont.bot.ffxiv;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class Bot {

	private static final int COMBO_COOLDOWN = 2700;

	Robot robot;

	public Bot() throws AWTException {
		robot = new Robot();
	}

	public void farm() {
		focusOnWindow();
		effectTask.start();
		killTask.start();
		while (true) {
			// nothing end here!
		}
	}

	private void focusOnWindow() {
		HWND hwnd = User32.INSTANCE.FindWindow(null,
				"FINAL FANTASY XIV: A Realm Reborn");
		if (hwnd == null) {
			throw new IllegalStateException("FF14 is not running");
		} else {
			User32.INSTANCE.ShowWindow(hwnd, 9);
			User32.INSTANCE.SetForegroundWindow(hwnd);
		}
	}

	private Thread effectTask = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				castEffects();
			}
		}
	});
	private Thread killTask = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				searchAndLock();
				attack();
			}
		}
	});

	private void castEffects() {
		System.out.println("cast blood");
		robotKey(KeyEvent.VK_5);
		try {
			Thread.sleep(90000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	void searchAndLock() {
		System.out.println("lock target");
		robot.delay(2000);
		robotKey(KeyEvent.VK_TAB, 500);
	}

	void attack() {
		System.out.println("attack target!");
		comboFracture();
		comboHeavySwingSkullSunder();
		comboHeavySwingMaim();
		comboHeavySwingSkullSunder();
		comboHeavySwingMaim();
	}

	private void robotKey(int k, int time) {
		robot.keyPress(k);
		robot.delay(time);
		robot.keyRelease(k);
	}

	private void robotKey(int k) {
		robot.keyPress(k);
		robot.keyRelease(k);
	}

	private void comboFracture() {
		System.out.println("combo Fracture");
		robotKey(KeyEvent.VK_4);
		robot.delay(4000);
	}

	void comboHeavySwingSkullSunder() {
		System.out.println("combo HeavySwing then SkullSunder");
		robotKey(KeyEvent.VK_1);
		robot.delay(COMBO_COOLDOWN);
		robotKey(KeyEvent.VK_3);
		robot.delay(COMBO_COOLDOWN);
	}

	void comboHeavySwingMaim() {
		System.out.println("combo HeavySwing then Maim");
		robotKey(KeyEvent.VK_1);
		robot.delay(COMBO_COOLDOWN);
		robotKey(KeyEvent.VK_8);
		robot.delay(COMBO_COOLDOWN);
	}
}
