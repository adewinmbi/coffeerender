#version 400 core

in vec3 position;
in vec2 textureCoords;

out vec2 passTextureCoords;

uniform mat4 transformationMatrix;

void main(void) {

	gl_Position = vec4(position.x, position.y, position.z, 1.0);
	passTextureCoords = textureCoords;

}