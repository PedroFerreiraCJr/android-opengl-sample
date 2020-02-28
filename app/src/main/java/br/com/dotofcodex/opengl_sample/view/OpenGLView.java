package br.com.dotofcodex.opengl_sample.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import br.com.dotofcodex.opengl_sample.renderer.OpenGLRenderer;

public class OpenGLView extends GLSurfaceView {

    public OpenGLRenderer renderer;

    public OpenGLView(Context context) {
        super(context);
        init();
    }

    public OpenGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        this.renderer = new OpenGLRenderer();
        setRenderer(this.renderer);
    }
}
