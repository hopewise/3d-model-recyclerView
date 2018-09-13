package customized;

import android.content.Context;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;

import com.example.android.recyclerview.MainActivity;

import org.andresoviedo.app.model3D.controller.TouchController;

/**
 * This is the actual opengl view. From here we can detect touch gestures for example
 * 
 * @author andresoviedo
 *
 */
public class ModelSurfaceView extends GLSurfaceView {


	private ModelRenderer mRenderer;
	private TouchController touchHandler;

	private static final int REQUEST_CODE_LOAD_TEXTURE = 1000;

	/**
	 * Type of model if file name has no extension (provided though content provider)
	 */
	private int paramType;
	/**
	 * The file to load. Passed as input parameter
	 */
	private Uri paramUri;
	/**
	 * Enter into Android Immersive mode so the renderer is full screen or not
	 */
	private boolean immersiveMode = true;
	/**
	 * Background GL clear color. Default is light gray
	 */
	private float[] backgroundColor = new float[]{0.2f, 0.2f, 0.2f, 1.0f};

	private SceneLoader scene;

	private Handler handler;



	public ModelSurfaceView(Context context) {
		super(context);
		//init();
	}
	public ModelSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init(){

		this.paramUri = Uri.parse("assets://"+"com.example.android.recyclerview"+"/models/cowboy.dae");
		//this.paramUri = Uri.parse("assets:///assets/models/cowboy.dae");
		this.paramType =-1;
		backgroundColor= new float[]{0.2f, 0.2f, 0.2f, 1.0f};

		Log.i("Renderer", "Params: uri '" + paramUri + "'");



		handler = new Handler(MainActivity.instance.getMainLooper());

		//setupOnSystemVisibilityChangeListener();

		// Create an OpenGL ES 2.0 context.
		setEGLContextClientVersion(2);

		// This is the actual renderer of the 3D space
		mRenderer = new ModelRenderer(this);


		// Render the view only when there is a change in the drawing data
		// TODO: enable this?
		// setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

		scene = new SceneLoader(this);
		setRenderer(mRenderer);
		scene.init();
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		return touchHandler.onTouchEvent(event);
//	}



	public ModelRenderer getModelRenderer(){
		return mRenderer;
	}

	public Uri getParamUri() {
		return paramUri;
	}

	public int getParamType() {
		return paramType;
	}

	public float[] getBackgroundColor() {
		return backgroundColor;
	}

	public SceneLoader getScene() {
		return scene;
	}

	public ModelSurfaceView getGLView() {
		return this;
	}

	public void iniBinding(String s) {


	}
}