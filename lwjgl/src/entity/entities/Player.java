package entity.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import entity.Entity;
import utils.maths.Vector3f;
import world.World;

public class Player extends Entity{

	public static final float SPEED = 0.01f;
	public static final float FLY_SPEED = 0.05f;
	
	public Player(Vector3f pos) {
		super(pos);
	}

	
	private float xDir, yDir, zDir;
	private float xa, ya, za;
	@Override
	public void update() 
	{
		xDir = 0;
		yDir = 0;
		zDir = 0;
		
		if(Mouse.isGrabbed())
		{
			rot.setX(rot.getX() - Mouse.getDY());
			rot.setY(rot.getY() + Mouse.getDX());
			
			if(rot.getX() > 85) rot.setX(85);
			if(rot.getX() < -85) rot.setX(-85);
			
			if(rot.getY() > 360) rot.setY(0);
			if(rot.getY() < 0) rot.setY(360);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			zDir = SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			zDir = -SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			xDir = SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			xDir = -SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			yDir = -FLY_SPEED * 2;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			yDir = FLY_SPEED;
		}
		
		xa += xDir * Math.cos(Math.toRadians(rot.y)) - zDir * Math.sin(Math.toRadians(rot.y));
		ya = yDir;
		za += zDir * Math.cos(Math.toRadians(rot.y)) + xDir * Math.sin(Math.toRadians(rot.y));

		
		move(xa, ya, za, this);
		
		xa *= 0.9f;
		ya *= 0.9f;
		za *= 0.9f;
	}

	@Override
	public void render() 
	{
		
	}

	@Override
	public void init(World world) {
		this.world = world;
	}
	
	

}
