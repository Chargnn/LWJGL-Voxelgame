package world;

import java.util.Random;

import entity.Entity;
import world.chunk.Chunk;
import world.generator.Noise;

public class World {

	private static final int CHUNK_COUNT = 5;
	
	private Chunk[][] chunks;
	private Noise noise;
	private EntityManager entityManager;
	
	public World()
	{
		chunks = new Chunk[CHUNK_COUNT][CHUNK_COUNT];
		noise = new Noise(new Random().nextLong(), 20, 5);
		entityManager = new EntityManager();
		
		for (int x = 0; x < CHUNK_COUNT; x++) {
			for (int z = 0; z < CHUNK_COUNT; z++) {
				chunks[x][z] = new Chunk(x, 0, z, noise, this);
			}
		}
		
		for (int x = 0; x < CHUNK_COUNT; x++) {
			for (int z = 0; z < CHUNK_COUNT; z++) {
				chunks[x][z].addFoliage(0.02f);
			}
		}
		
		for (int x = 0; x < CHUNK_COUNT; x++) {
			for (int z = 0; z < CHUNK_COUNT; z++) {
				chunks[x][z].createChunk();
			}
		}
	}
	
	public void update()
	{
		entityManager.update();
	}
	
	public void render()
	{
		for (int x = 0; x < CHUNK_COUNT; x++) {
			for (int z = 0; z < CHUNK_COUNT; z++) {
				chunks[x][z].render();
			}
		}
		
		entityManager.render();
	}
	
	public Block getBlock(int x, int y, int z)
	{
		int xc = x / Chunk.SIZE;
		int zc = z / Chunk.SIZE;
		
		if(xc < 0 || zc < 0 || xc >= CHUNK_COUNT || zc >= CHUNK_COUNT) return null;
		
		Chunk chunk = chunks[xc][zc];
		
		int xb = x % Chunk.SIZE;
		int yb = y;
		int zb = z % Chunk.SIZE;
		
		return chunk.getBlock(xb, yb, zb);
	}
	
	public void addBlock(Block block, int x, int y, int z)
	{
		int xc = x / Chunk.SIZE;
		int zc = z / Chunk.SIZE;
		
		if(xc < 0 || zc < 0 || xc >= CHUNK_COUNT || zc >= CHUNK_COUNT) return;
		
		Chunk chunk = chunks[xc][zc];
		
		int xb = x % Chunk.SIZE;
		int yb = y;
		int zb = z % Chunk.SIZE;
		
		chunk.addBlock(block, xb, yb, zb);
	}
	
	public void addEntity(Entity e)
	{
		entityManager.add(e, this);
	}
}
