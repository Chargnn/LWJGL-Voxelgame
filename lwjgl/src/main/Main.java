package main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import utils.Time;

import static org.lwjgl.opengl.GL11.*;

public class Main {

	private Screen screen;
	
	public Main()
	{
		Window.createWindow(1200, 800, "game");
		
		screen = new Screen();
	}
	
	public static void main(String args[])
	{
		Main main = new Main();
	
		main.run();
	}
	
	public void init()
	{
		screen.initGL();
		
		run();
	}
	
	public void run()
	{
		int frames = 0;

		double unprocessedSeconds = 0;
		long lastTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;
		
		while(!Display.isCloseRequested())
		{
			long now = System.nanoTime();
			long passedTime = now - lastTime;
			lastTime = now;
			if (passedTime < 0) passedTime = 0;
			if (passedTime > 100000000) passedTime = 100000000;

			unprocessedSeconds += passedTime / 1000000000.0;
			
			boolean ticked = false;
			while (unprocessedSeconds > secondsPerTick) {
				update();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;

				tickCount++;
				if (tickCount % 60 == 0) {
					System.out.println(frames + " fps");
					lastTime += 1000;
					frames = 0;
				}
			}

			if (ticked) {
				render();
				frames++;
			}
		}
	}
	
	public void update()
	{
		Time.update();
		Window.update(60);
		
		if(Mouse.isButtonDown(0) && !Mouse.isGrabbed()) Mouse.setGrabbed(true);
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && Mouse.isGrabbed()) Mouse.setGrabbed(false);
		if(!Mouse.isGrabbed()) return;
		
		screen.update();
	}
	
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(0, 0.5f, 0.8f, 1);
		
		screen.render();
	}
	
	public void stop()
	{
		Window.destroy();
		Mouse.destroy();
		Keyboard.destroy();
	}
	
}
