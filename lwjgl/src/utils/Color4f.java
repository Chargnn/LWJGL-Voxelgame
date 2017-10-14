package utils;

public class Color4f {

	public static final Color4f BLACK = new Color4f(0, 0, 0, 1);
	public static final Color4f WHITE = new Color4f(1, 1, 1, 1);
	public static final Color4f RED = new Color4f(1, 0, 0, 1);
	public static final Color4f GREEN = new Color4f(0, 1, 0, 1);
	public static final Color4f BLUE = new Color4f(0, 0, 1, 1);
	
	private float r, g, b, a;

	public Color4f(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public Color4f getColor() {
		return this;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}
	
	

}
