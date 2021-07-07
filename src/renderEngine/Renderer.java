package renderEngine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL13.*;

import models.RawModel;
import models.TexturedModel;

import static org.lwjgl.opengl.GL20.*;

public class Renderer {

	public void prepare() { // Call once every frame
		glClearColor(0.2f, 0.2f, 0.7f, 1);
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public void render(TexturedModel texturedModel) {
		RawModel model = texturedModel.getRawModel();
		glBindVertexArray(model.getVaoID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, texturedModel.getTexture().getID());
		glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
	}
	
}
