package engineDebug;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;
import renderEngine.DisplayManager;

public class MainGame {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		float[] vertices = {
			  -0.5f,  0.5f, 0f,
			  -0.5f, -0.5f, 0f,
			   0.5f, -0.5f, 0f, 
			   0.5f,  0.5f, 0f
		};
				  
		int[] indices = {
			  0, 1, 3,
			  3, 1, 2
		};
		
		float[] textureCoords = {
				0, 0,
				0, 1,
				1, 1,
				1, 0
		};
		
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("cloud"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		Entity newEntity = new Entity(texturedModel, new Vector3f(0, 0, -1),0,0,0,1);
		
		while(!Display.isCloseRequested()) {
			// Game loop + rendering
			newEntity.increaseRotation(0.5f, 0.5f, 0.5f);
			renderer.prepare();
			shader.start();
			renderer.render(newEntity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.removeVOs();
		DisplayManager.closeDisplay();
		System.exit(0);
	}

}
