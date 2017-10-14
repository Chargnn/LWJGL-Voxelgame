#version 330

in vec3 in_position;
in vec4 in_color;

out vec4 color;

void main(void)
{
	color = in_color;

	gl_Position = ftransform();
}