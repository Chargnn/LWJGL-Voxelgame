package world.foliages;

import world.Block;
import world.World;

public abstract class Tree {
	
	public static final int TREE_HEIGHT = 10;
	
	public static void addTree(World world, int x, int y, int z)
	{	
		//if(x - 4 < 0 || y - 3 < 0 || z - 4 < 0 || x + 4 > Chunk.SIZE || z + 4 > Chunk.SIZE) return;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					float ii = i - 2.5f;
					float jj = j - 2.5f;
					float kk = k - 2.5f;
					
					float l = (float) Math.sqrt(ii * ii + jj * jj + kk * kk);
					
					if(l < 2.5f)
					{
						world.addBlock(Block.LEAVE, (int) (x + ii), (int) (y + jj) + TREE_HEIGHT, (int) (z + kk));
					}
				}
			}
		}
		
		for (int i = 0; i < TREE_HEIGHT; i++) {
			world.addBlock(Block.LOG, x, y + i, z);
		}
	}
	
	
	
}
