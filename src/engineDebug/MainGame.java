package engineDebug;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.EntityRenderer;
import shaders.StaticShader;
import textures.ModelTexture;
import renderEngine.DisplayManager;

public class MainGame {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		TexturedModel texturedModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("pink")));
		ModelTexture texture = texturedModel.getTexture();
		texture.setShineDamper(10f);
		texture.setReflectivity(1f);
		
		List<Entity> dragons = new ArrayList<Entity>();
		for (int i = 0; i < 3; i++) {
			Entity dragon = new Entity(texturedModel, new Vector3f(0, -5, -20),0,0,0,1);
			dragon.increasePosition((float)(i * 10 - 9), 0, 0);
			dragons.add(dragon);
		}
		
		Light light = new Light(new Vector3f(0, 20, -15), new Vector3f(1, 1, 1));		
		Camera camera = new Camera();
		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested()) {
			// Game loop + rendering
			camera.move();

			for (Entity dragon : dragons) {
				renderer.processEntity(dragon);
				dragon.increaseRotation(0f, 0.5f, 0f);
			}
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		
		renderer.cleanUp();
		loader.removeVOs();
		DisplayManager.closeDisplay();
		System.exit(0);
	}

}
