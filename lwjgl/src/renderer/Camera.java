package renderer;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector2f;

import utils.maths.Vector3f;

public class Camera {

	private Vector3f pos;
	private Vector2f rot;
	
	public Camera(Vector3f pos)
	{
		this.pos = pos;
		rot = new Vector2f();
		
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_FRONT);
	}
	
	public void getPerspectiveProjection()
	{
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		
		GLU.gluPerspective(70, Display.getWidth()/Display.getHeight(), 0.01f, 1000.0f);
		
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	public void update()
	{
		glPushAttrib(GL_TRANSFORM_BIT);
		glRotatef(rot.getX(), 1, 0, 0);
		glRotatef(rot.getY(), 0, 1, 0);
		glTranslatef(pos.getX(), pos.getY(), pos.getZ());
		glPopMatrix();
		
		move();
	}
	
	private void move()
	{	
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
			pos.add(getForward().mul(0.1f));
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
			pos.add(getForward().mul(-0.1f));
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
			pos.add(getRight().mul(0.1f));
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
			pos.add(getRight().mul(-0.1f));
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			pos.addY(-0.1f);
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			pos.addY(0.1f);
	}
	
	public Vector3f getForward()
	{
		Vector3f r = new Vector3f();
		Vector3f rot = new Vector3f(this.rot.x, this.rot.y, 0);
		
		float cosY = (float) Math.cos(Math.toRadians(rot.getY() + 90));
		float sinY = (float) Math.sin(Math.toRadians(rot.getY() + 90));
		
		r.setX(cosY);
		r.setZ(sinY);
		
		return r;
	}
	
	public Vector3f getRight()
	{
		Vector3f r = new Vector3f();
		Vector3f rot = new Vector3f(this.rot.x, this.rot.y, 0);
		
		float cosY = (float) Math.cos(Math.toRadians(rot.getY()));
		float sinY = (float) Math.sin(Math.toRadians(rot.getY()));
		
		r.setX(cosY);
		r.setZ(sinY);
		
		return r;
	}
	
}
