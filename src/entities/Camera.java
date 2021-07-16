package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch, yaw, roll;
	private float cameraMoveSpeed = 0.02f;
	private float cameraRotSpeed = 1f;
	
	public Camera() {}
	
	public void move() { // Called every frame, gather input.
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
			position.z -= cameraMoveSpeed;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			position.x -= cameraMoveSpeed;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
			position.z += cameraMoveSpeed;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
			position.x += cameraMoveSpeed;
		
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}	
}
