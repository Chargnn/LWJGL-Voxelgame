package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {

	public static void createWindow(int width, int height, String title) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setResizable(false);
			Display.setTitle(title);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	public static boolean isWindowCloseRequested() {
		return Display.isCloseRequested();
	}

	public static void update(int fps) {
		Display.update();
		//Display.sync(fps);
	}

	public static void destroy() {
		Display.destroy();
	}

}
