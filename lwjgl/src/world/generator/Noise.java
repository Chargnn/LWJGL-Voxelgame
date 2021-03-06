package world.generator;

import java.util.Random;

import org.lwjgl.util.vector.Vector2f;

public class Noise {

	private long seed;
	private Random rand;
	private int octave;
	private float amplitude;
	
	public Noise(long seed, int octave, float amplitude)
	{
		this.seed = seed;
		this.octave = octave;
		this.amplitude = amplitude;
		
		rand = new Random();
	}
	
	public float getNoise(float x, float z)
	{
		int xmin = (int) (double) x / octave;
		int xmax = (int) xmin + 1;
		int zmin = (int) (double) z / octave;
		int zmax = (int) zmin + 1;
		
		Vector2f a = new Vector2f(xmin, zmin);
		Vector2f b = new Vector2f(xmax, zmin);
		Vector2f c = new Vector2f(xmax, zmax);
		Vector2f d = new Vector2f(xmin, zmax);
		
		float ra = (float) noise(a);
		float rb = (float) noise(b);
		float rc = (float) noise(c);
		float rd = (float) noise(d);
		
		float t1 = (x - xmin * octave) / octave;
		float t2 = (z - zmin * octave) / octave;
		float i1 = interpolate(ra, rb, t1);
		float i2 = interpolate(rd, rc, t1);
		
		float h = interpolate(i1, i2, t2);
		
		return h * amplitude;
	}
	
	private float interpolate(float a, float b, float t)
	{
		float ft = (float) (t * Math.PI);
		float f = (float) (1f - Math.cos(ft) * .5f);
		float ret = a * (1f - f) + b * f;
		
		return ret;
	}
	
	private double noise(Vector2f noise)
	{
		double var = 10000 * (Math.sin(noise.x + Math.cos(noise.y)) + Math.tan(seed));
		rand.setSeed((long)var );
		double ret = rand.nextDouble();
		
		return ret;
	}
	
}
