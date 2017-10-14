package world;

import java.util.Random;

import world.chunk.Chunk;
import world.generator.Noise;

public class World {

	private static final int CHUNK_COUNT = 8;
	
	private Chunk[][] chunks;
	private Noise noise;
	
	public World()
	{
		noise = new Noise(new Random().nextLong(), 10, 4.0f);
		
		chunks = new Chunk[CHUNK_COUNT][CHUNK_COUNT];
		
		for (int x = 0; x < CHUNK_COUNT; x++) {
			for (int z = 0; z < CHUNK_COUNT; z++) {
				chunks[x][z] = new Chunk(x, 0, z, noise);
			}
		}
		
		for (int x = 0; x < CHUNK_COUNT; x++) {
			for (int z = 0; z < CHUNK_COUNT; z++) {
				chunks[x][z].createChunk(this);
			}
		}
	}
	
	public void update()
	{
		/*for (int x = 0; x < CHUNK_COUNT; x++) {
			for (int z = 0; z < CHUNK_COUNT; z++) {
				chunks[x][z].update();
			}
		}*/
	}
	
	public void render()
	{
		for (int x = 0; x < CHUNK_COUNT; x++) {
			for (int z = 0; z < CHUNK_COUNT; z++) {
				chunks[x][z].render();
			}
		}
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
}
