package entity;

import org.lwjgl.util.vector.Vector2f;

import entity.entities.Player;
import utils.maths.Vector3f;
import world.World;

public abstract class Entity {

	public Vector3f pos;
	public Vector2f rot;
	public World world;
	public boolean gravity = true;
	
	public Entity(Vector3f pos)
	{
		this.pos = pos;
		rot = new Vector2f();
	}
	
	public Entity(Vector3f pos, Vector2f rot)
	{
		this.pos = pos;
		this.rot = rot;
	}
	
	public void move(float xa, float ya, float za, Entity entity)
	{
		if(gravity)
		{
			ya += Player.FLY_SPEED;
		}
		
		if(!isColliding(xa, 0, 0)) pos.setX(pos.getX() + xa);
		if(!isColliding(0, ya, 0)) pos.setY(pos.getY() + ya);
		if(!isColliding(0, 0, za)) pos.setZ(pos.getZ() + za);
	}
	
	public boolean isColliding(float xa, float ya, float za)
	{
		float r = 0.5f;
			
		int x0 = (int) (pos.getX() + xa - r);
		int x1 = (int) (pos.getX() + xa + r);
		
		int y0 = (int) (pos.getY() + ya - r);
		int y1 = (int) (pos.getY() + ya + r);
		
		int z0 = (int) (pos.getZ() + za - r);
		int z1 = (int) (pos.getZ() + za + r);
		
		if(world.getBlock(-x0, -y0 - 2, -z0) != null) return true;
		if(world.getBlock(-x1, -y0 - 2, -z0) != null) return true;
		if(world.getBlock(-x1, -y1 - 2, -z0) != null) return true;
		if(world.getBlock(-x0, -y1 - 2, -z0) != null) return true;
		
		if(world.getBlock(-x0, -y0 - 2, -z1) != null) return true;
		if(world.getBlock(-x1, -y0 - 2, -z1) != null) return true;
		if(world.getBlock(-x1, -y1 - 2, -z1) != null) return true;
		if(world.getBlock(-x0, -y1 - 2, -z1) != null) return true;
		
		return false;
	}
	
	public abstract void init(World world);
	public abstract void update();
	public abstract void render();
	
}
