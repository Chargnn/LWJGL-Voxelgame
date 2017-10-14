package world.chunk;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import world.Block;
import world.World;
import world.foliages.Tree;
import world.generator.Noise;

public class Chunk {
	
	public static final int SIZE = 16;
	public static final int HEIGHT = 64;
	
	private int x, y, z;
	
	private int vbo;
	private FloatBuffer buffer;
	private int bufferSize;
	
	private Block[][][] blocks;
	private Noise noise;
	private World world;
	
	public Chunk(int x, int y, int z, Noise noise, World world)
	{
		this.x = x * SIZE;
		this.y = y * HEIGHT;
		this.z = z * SIZE;
		this.noise = noise;
		this.world = world;
		
		buffer = BufferUtils.createFloatBuffer(SIZE * HEIGHT * SIZE * 6 * 4 * (3+4));
		blocks = new Block[SIZE][HEIGHT][SIZE];
		
		generateChunk();
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 7 * 4, 0);
		glVertexAttribPointer(1, 4, GL_FLOAT, false, 7 * 4, 12);
		glDrawArrays(GL_QUADS, 0, bufferSize);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);		
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(0);
	}
	
	private void generateChunk()
	{	
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				for (int z = 0; z < SIZE; z++) {
					int xx = this.x + x;
					int yy = this.y + y;
					int zz = this.z + z;
					
					if(noise.getNoise(xx, zz) > yy - 1)
					{						
						Block block =  Block.GRASS;						
						addBlock(block, x, y, z);
					}
				}
			}
		}
	}
	
	public Block getBlock(int x, int y, int z)
	{
		if(x < 0 || x >= SIZE || y < 0 || y >= HEIGHT || z < 0 || z >= SIZE)	return null;
			
		return blocks[x][y][z];
	}
	
	
	public void addBlock(Block block, int x, int y, int z)
	{
		if(x < 0 || x >= SIZE || y < 0 || y >= HEIGHT || z < 0 || z >= SIZE) return;
		
		blocks[x][y][z] = block;
	}
	
	public void addFoliage(float density)
	{
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				for (int z = 0; z < SIZE; z++) {
					int xx = this.x + x;
					int yy = this.y + y;
					int zz = this.z + z;
					
					boolean grounded = noise.getNoise(xx, zz) > yy - 1 && noise.getNoise(xx, zz) < yy;
					
					if(Math.random() < density && grounded)
					{
						Tree.addTree(world, xx, yy, zz);
					}
				}
			}
		}
	}
	
	public void createChunk()
	{
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				for (int z = 0; z < SIZE; z++) {
					
					int xx = this.x + x;
					int yy = this.y + y;
					int zz = this.z + z;
					
					boolean up = world.getBlock(xx, yy + 1, zz) == null;
					boolean down = world.getBlock(xx, yy - 1, zz) == null;
					boolean left = world.getBlock(xx - 1, yy, zz) == null;
					boolean right = world.getBlock(xx + 1, yy, zz) == null;
					boolean front = world.getBlock(xx, yy, zz - 1) == null;
					boolean back = world.getBlock(xx, yy, zz + 1) == null;
					
					if(!up && !down && !left && !right && !front && !back) continue;
					if(y == 0 && !up) continue;
					if(blocks[x][y][z] == null) continue;
					Block block = blocks[x][y][z];
					
					int totalFace = 0;
					if(up) 
					{
						buffer.put(block.createBlockTop(xx, yy, zz, 1));
						totalFace ++;
					}
					if(down) 
					{
						buffer.put(block.createBlockBottom(xx, yy, zz, 1));
						totalFace ++;
					}
					if(left) 
					{
						buffer.put(block.createBlockLeft(xx, yy, zz, 1));
						totalFace ++;
					}
					if(right) 
					{
						buffer.put(block.createBlockRight(xx, yy, zz, 1));
						totalFace ++;
					}
					if(front) 
					{
						buffer.put(block.createBlockFront(xx, yy, zz, 1));
						totalFace ++;
					}
					if(back) 
					{
						buffer.put(block.createBlockBack(xx, yy, zz, 1));
						totalFace ++;
					}
					
					bufferSize += totalFace * 4;
				}
			}
		}
		
		buffer.flip();
		createBuffer();
	}
	
	private void createBuffer()
	{
		vbo = glGenBuffers();
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
}
