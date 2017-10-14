package world;

import utils.Color4f;
import world.blocks.GrassBlock;
import world.blocks.LogBlock;
import world.blocks.SandBlock;
import world.blocks.WaterBlock;

public abstract class Block {

	public static final Block GRASS = new GrassBlock();
	public static final Block SAND = new SandBlock();
	public static final Block WATER = new WaterBlock();
	public static final Block LOG = new LogBlock();
	
	private Color4f color;
	
	public Block(Color4f color)
	{
		this.color = color;
	}
	
	public float[] createBlockFront(float x, float y, float z, float s)
	{
		return new float[] {
			x, y, z, 			color.getR() * 0.9f, color.getG() * 0.9f, color.getB() * 0.9f, color.getA(),
			x + s, y, z, 		color.getR() * 0.9f, color.getG() * 0.9f, color.getB() * 0.9f, color.getA(),
			x + s, y + s, z, 	color.getR() * 0.9f, color.getG() * 0.9f, color.getB() * 0.9f, color.getA(),
			x, y + s, z, 		color.getR() * 0.9f, color.getG() * 0.9f, color.getB() * 0.9f, color.getA(),	
		};
	}
	
	public float[] createBlockBack(float x, float y, float z, float s)
	{
		return new float[] {
			x + s, y, z + s, 	color.getR() * 0.9f, color.getG() * 0.9f, color.getB() * 0.9f, color.getA(),
			x , y, z + s, 		color.getR() * 0.9f, color.getG() * 0.9f, color.getB() * 0.9f, color.getA(),
			x, y + s, z + s,color.getR() * 0.9f, color.getG() * 0.9f, color.getB() * 0.9f, color.getA(),
			x + s, y + s, z + s,color.getR() * 0.9f, color.getG() * 0.9f, color.getB() * 0.9f, color.getA(),
				
		};
	}
	
	public float[] createBlockLeft(float x, float y, float z, float s)
	{
		return new float[] {
			x, y, z, 			color.getR() * 0.8f, color.getG() * 0.8f, color.getB() * 0.8f, color.getA(),
			x, y + s, z, 		color.getR() * 0.8f, color.getG() * 0.8f, color.getB() * 0.8f, color.getA(),
			x, y + s, z + s, 	color.getR() * 0.8f, color.getG() * 0.8f, color.getB() * 0.8f, color.getA(),
			x, y, z + s, 		color.getR() * 0.8f, color.getG() * 0.8f, color.getB() * 0.8f, color.getA(),	
		};
	}
	
	public float[] createBlockRight(float x, float y, float z, float s)
	{
		return new float[] {
			x + s, y + s, z, 		color.getR() * 0.8f, color.getG() * 0.8f, color.getB() * 0.8f, color.getA(),
			x + s, y, z, 	color.getR() * 0.8f, color.getG() * 0.8f, color.getB() * 0.8f, color.getA(),
			x + s, y, z + s,color.getR() * 0.8f, color.getG() * 0.8f, color.getB() * 0.8f, color.getA(),
			x + s, y + s, z + s, 	color.getR() * 0.8f, color.getG() * 0.8f, color.getB() * 0.8f, color.getA(),	
		};
	}
	
	public float[] createBlockBottom(float x, float y, float z, float s)
	{
		return new float[] {
			x, y, z, 			color.getR() * 0.7f, color.getG() * 0.7f, color.getB() * 0.7f, color.getA(),
			x + s, y, z, 		color.getR() * 0.7f, color.getG() * 0.7f, color.getB() * 0.7f, color.getA(),
			x + s, y, z + s, 	color.getR() * 0.7f, color.getG() * 0.7f, color.getB() * 0.7f, color.getA(),
			x, y, z + s, 		color.getR() * 0.7f, color.getG() * 0.7f, color.getB() * 0.7f, color.getA(),		
		};
	}
	
	public float[] createBlockTop(float x, float y, float z, float s)
	{
		return new float[] {
			x, y + s, z, 		color.getR() * 1.0f, color.getG() * 1.0f, color.getB() * 1.0f, color.getA(),
			x + s, y + s, z, 	color.getR() * 1.0f, color.getG() * 1.0f, color.getB() * 1.0f, color.getA(),
			x + s, y + s, z + s,color.getR() * 1.0f, color.getG() * 1.0f, color.getB() * 1.0f, color.getA(),
			x, y + s, z + s, 	color.getR() * 1.0f, color.getG() * 1.0f, color.getB() * 1.0f, color.getA(),		
		};
	}
	
}
