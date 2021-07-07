package shaders;

public class StaticShader extends ShaderProgram{
	
	private static final String vertexFile = "src/shaders/vertex.glsl";
	private static final String fragmentFile = "src/shaders/fragment.glsl";

	public StaticShader() {
		super(vertexFile, fragmentFile);
	}
	
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

}
