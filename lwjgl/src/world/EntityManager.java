package world;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;

public class EntityManager {
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	public void add(Entity e, World world)
	{
		e.init(world);
		entities.add(e);
	}
	
	public void update()
	{
		for(Entity e : entities)
		{
			e.update();
		}
	}
	
	public void render()
	{
		for(Entity e : entities)
		{
			e.render();
		}
	}
	
}
