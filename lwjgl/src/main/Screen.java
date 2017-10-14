package main;

import static org.lwjgl.opengl.GL11.glLoadIdentity;

import renderer.Camera;
import shader.Shader;
import utils.maths.Vector3f;
import world.World;

public class Screen {

	private Camera camera;
	private World world;
	private Shader shader;
	
	public Screen()
	{
		camera = new Camera(new Vector3f(0,0,0));
		world = new World();
		shader = new Shader("res/shaders/main.vert", "res/shaders/main.frag");
	}
	
	public void initGL()
	{
	}
	
	public void update()
	{
		world.update();
	}
	
	public void render()
	{
		camera.getPerspectiveProjection();
		camera.update();
		
		shader.start();
		world.render();
		shader.stop();
		
		glLoadIdentity();
	}
	
}
