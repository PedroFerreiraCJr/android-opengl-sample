package br.com.dotofcodex.opengl_sample.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import br.com.dotofcodex.opengl_sample.model.Circle;

public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private Circle circle;
    public boolean objectsReady;

    public OpenGLRenderer() {
        super();
    }

    public Circle getCircle() {
        return circle;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(.9f, .9f, .9f, 1f);
        circle = new Circle(0,0, 0.1f, 55);
        objectsReady = true;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        this.circle.draw();
    }

    public static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
