package engineDebug;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.Loader;
import renderEngine.OBJLoader;
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
		
		RawModel model = OBJLoader.loadObjModel("apple", loader);
		// ModelTexture texture = new ModelTexture(loader.loadTexture("cloud"));
		TexturedModel texturedModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("appleTexture")));
		
		Entity newEntity = new Entity(texturedModel, new Vector3f(0, 0, -5),0,0,0,1);
		
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()) {
			// Game loop + rendering
			newEntity.increaseRotation(0.5f, 0.5f, 0.5f);
			camera.move();
			renderer.prepare();
			
			shader.start();
			shader.loadViewMatrix(camera);
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
