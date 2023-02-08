package com.TETOSOFT.input;


import java.awt.Robot;
import java.awt.AWTException;

public class InputManagerProduct {
	private Robot robot;

	public Robot getRobot() {
		return robot;
	}

	public void setRelativeMouseMode(boolean mode) {
		if (mode == isRelativeMouseMode()) {
			return;
		}
		robot(mode);
		if (mode) {
		} else {
			robot = null;
		}
	}

	public void robot(boolean mode) {
		if (mode) {
			try {
				robot = new Robot();
			} catch (AWTException ex) {
				robot = null;
			}
		} else {
		}
	}

	public boolean isRelativeMouseMode() {
		return (robot != null);
	}
}