package main;

import static org.lwjgl.opengl.GL11.glLoadIdentity;

import entity.entities.Player;
import renderer.Camera;
import shader.Shader;
import utils.maths.Vector3f;
import world.World;

public class Screen {

	private Camera camera;
	private World world;
	private Shader shader;
	private static Player player;
	
	public Screen()
	{
		camera = new Camera();
		world = new World();
		shader = new Shader("res/shaders/main.vert", "res/shaders/main.frag");
		player = new Player(new Vector3f(0,-8,0));
	}
	
	public void init()
	{
		world.addEntity(player);
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
	
	public static Player getPlayer()
	{
		return player;
	}
}
