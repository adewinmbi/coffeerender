package renderEngine;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	private static final int width = 1280;
	private static final int height = 720;
	private static final int fpsCap = 120;
	
	public static void createDisplay() {
		
		ContextAttribs attribs = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle("Voxel Space Demo");
			Display.create(new PixelFormat(), attribs);
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		GL11.glViewport(0, 0, width, height);
	}
	
	public static void updateDisplay() {
		Display.sync(fpsCap);
		Display.update();
	}
	
	public static void closeDisplay() {
		Display.destroy();
	}

}
