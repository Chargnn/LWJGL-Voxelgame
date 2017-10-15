package renderer;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector2f;

import main.Screen;
import utils.maths.Vector3f;

public class Camera {

	public Camera()
	{
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
		glRotatef(Screen.getPlayer().rot.x, 1, 0, 0);
		glRotatef(Screen.getPlayer().rot.y, 0, 1, 0);
		glTranslatef(Screen.getPlayer().pos.getX(), Screen.getPlayer().pos.getY(), Screen.getPlayer().pos.getZ());
		glPopMatrix();
	}
}
