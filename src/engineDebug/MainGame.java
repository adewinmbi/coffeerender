package engineDebug;

import org.lwjgl.opengl.Display;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

import renderEngine.DisplayManager;

public class MainGame {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
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
		
		RawModel model = loader.loadToVAO(vertices, indices);
		
		while(!Display.isCloseRequested()) {
			// Game loop + rendering
			renderer.prepare();
			renderer.render(model);
			DisplayManager.updateDisplay();
		}
		
		loader.removeVOs();
		DisplayManager.closeDisplay();
		System.exit(0);
	}

}
