package shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;

public abstract class ShaderProgram {

	private int programID;
	private int vertexID;
	private int fragmentID;
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public ShaderProgram(String vertexFile, String fragmentFile) {
		vertexID = loadShader(vertexFile, GL_VERTEX_SHADER);
		fragmentID = loadShader(fragmentFile, GL_FRAGMENT_SHADER);
		programID = glCreateProgram();
		glAttachShader(programID, vertexID);
		glAttachShader(programID, fragmentID);
		bindAttributes(); // Must call before linking program
		glLinkProgram(programID);
		glValidateProgram(programID);
		getAllUniformLocations();
	}
	
	protected abstract void getAllUniformLocations();
	
	protected int getUniformLocation(String uniformName) {
		return glGetUniformLocation(programID, uniformName);
	}
	
	public void start() {
		glUseProgram(programID);
	}
	
	public void stop() {
		glUseProgram(0);
	}
	
	public void cleanUp() {
		stop();
		glDetachShader(programID, vertexID);
		glDetachShader(programID, fragmentID);
		glDeleteShader(vertexID);
		glDeleteShader(fragmentID);
		glDeleteProgram(programID);
	}
	
	protected abstract void bindAttributes();
	protected void bindAttribute(int attribute, String variableName) {
		glBindAttribLocation(programID, attribute, variableName);
	}
	
	protected void loadFloat(int location, float value) {
		glUniform1f(location, value);
	}
	
	protected void loadVector(int location, Vector3f vec) {
		glUniform3f(location, vec.x, vec.y, vec.z);
	}
	
	protected void loadBool(int location, boolean value) {
		float toLoad = 0;
		if (value)
			toLoad = 1;
		glUniform1f(location, toLoad);
	}
	
	protected void loadMatrix(int location, Matrix4f matrix) {
		matrix.store(matrixBuffer); // Store matrix into float buffer
		matrixBuffer.flip(); // Flip so it can be read from correctly
		glUniformMatrix4(location, false, matrixBuffer);
	}
	
	@SuppressWarnings("deprecation")
	private static int loadShader(String file, int type) {
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				shaderSource.append(line).append("\n");
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Could not read file.");
			e.printStackTrace();
			System.exit(-1);
		}
		
		int shaderID = glCreateShader(type);
		glShaderSource(shaderID, shaderSource);
		glCompileShader(shaderID);
		
		if (glGetShader(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.out.println(glGetShaderInfoLog(shaderID, 500));
			System.err.println("Could not compile shader.");
			System.exit(-1);
		}
		return shaderID;
	}
	
}
