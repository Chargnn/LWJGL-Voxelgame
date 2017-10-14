package shader;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileReader;

import utils.maths.Vector3f;

public class Shader {
	
	public int programID;
	
	public Shader(String vertexShader, String fragmentShader){
		programID = glCreateProgram();
		
		if(programID == GL_FALSE)
		{
			System.err.println("Shader program error!");
			System.exit(1);
		}
		
		createShader(loadFile(vertexShader), GL_VERTEX_SHADER);
		createShader(loadFile(fragmentShader), GL_FRAGMENT_SHADER);
		
		glLinkProgram(programID);
		glValidateProgram(programID);
	}
	
	public void start()
	{
		glUseProgram(programID);
	}
	
	public void stop()
	{
		glUseProgram(0);
	}
	
	public void setUniform(String name, float value)
	{
		glUniform1f(glGetUniformLocation(programID, name), value);
	}
	
	public void setUniform(String name, Vector3f value)
	{
		glUniform3f(glGetUniformLocation(programID, name), value.getX(), value.getY(), value.getZ());
	}
	
	private void createShader(String filename, int type)
	{
		int shader = glCreateShader(type);
		
		if(shader == GL_FALSE)
		{
			System.err.println("Shader error: " + shader);
			System.exit(1);
		}
		
		glShaderSource(shader, filename);
		glCompileShader(shader);
		
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE)
		{
			System.err.println(glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}
		
		glAttachShader(programID, shader);
	}
	
	private String loadFile(String filename)
	{
		StringBuilder vertexCode = new StringBuilder();
		String line = null ;
		try
		{
		    BufferedReader reader = new BufferedReader(new FileReader(filename));
		    while( (line = reader.readLine()) !=null )
		    	vertexCode.append(line + "\n");
		    
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("unable to load shader from file ["+filename+"]", e);
		}
 
		return vertexCode.toString();
	}
}
