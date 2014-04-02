//
//  openGLMain.java
//  
//
//  Main class for tessellation assignment.
//
//  Students should not be modifying this file.
//

import java.awt.*;
import java.nio.*;

import java.awt.event.*;
import javax.media.opengl.*;
import java.io.*;
import javax.media.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

public class FinalMain implements GLEventListener, KeyListener
{
    /**
     * static values
     */
    public final static int CONE = 0;
   
    private int division1 = 4;
    private int division2 = 4;
    private int currentShape = FinalMain.CONE;
    static int o=0;
    private boolean bufferInit = false;
    private int vbuffer,ebuffer;
    public int theta,scale,trans,eye,lookAt,up,fov,aspect,near,far,lightP;
    public int shine,ambient,diffuse,specular;
    
    float SHINE_ALL_DIRECTIONS = 1;
    float[] lightPos = {2, 1, 5, SHINE_ALL_DIRECTIONS};
    float[] ColorAmbient = {0.9f, 0.0f, 0.1f, 1.0f};
    float[] ColorDiffuse = {0.4f, 1.0f, 0.1f, 1.0f};
    float[] ColorSpecular = {0.3f, 0.5f, 0.2f, 1.0f};
    public float fovData=60.0f;
    public float aspectData=1.0f;
    public float nearData= 1.0f;
    public float farData = 10.0f;
    
    public float angles[];
    public float angles1[];
    public float transData[];
    public float transData1[];
    public float transData2[];
    public float transData3[];
    public float scaleData[];
    private float angleInc = 5.0f;
    private float transInc = 0.5f;
    private float scaleInc = 0.5f;
    private float eyeData[];
    private float lookAtData[];
    private float upData[];
    private float shininess;

    
	
	/**
	 * shader info
	 */
	private shaderProgram myShaders;
	private int shaderProgID = 0;
    /**
     * shape info
     */
    cg1Shape Shape;
    cg1Shape myShape1;
    
    /**
     * my canvas
     */
    GLCanvas myCanvas;
	
	/**
	 * constructor
	 */
	public FinalMain(GLCanvas G)
 	{
        //keeping angles same for both the objects
		angles = new float[3];
        angles[0] = 0.0f;
        angles[1] = 90.0f;
        angles[2] = 90.0f;
        
        //translating the first egg 
        transData = new float[3];
        transData[0] =  0.0f;
        transData[1] = 1.5f;
        transData[2] =  0.0f;
        
    // translating the second egg
       transData1 = new float[3];
        transData1[0] = 0.0f;
        transData1[1] = -1.5f;
        transData1[2] = 0.0f;
        
        // translating the third egg
        transData2 = new float[3];
        transData2[0] = 0.0f;
        transData2[1] = 0.0f;
        transData2[2] = 0.0f;
        
        // translating the fourth egg
        transData3 = new float[3];
        transData3[0] = -0.5f;
        transData3[1] = 0.0f;
        transData3[2] = 0.0f;
        
        //keeping angles same for both the objects
		angles1 = new float[3];
        angles1[0] = 0.0f;
        angles1[1] = 0.0f;
        angles1[2] = 0.0f;
        
        //keeping the scale same for both the objects
        scaleData = new float[3];
        scaleData[0] = 1.0f;
        scaleData[1] = 1.0f;
        scaleData[2] = 1.0f;
        
        eyeData=new float[3];
        eyeData[0]= 0.0f;
        eyeData[1]= 0.0f;
        eyeData[2]= 4.0f;
        
        lookAtData= new float[3];
        lookAtData[0]=0.0f;
        lookAtData[1]=0.0f;
        lookAtData[2]=0.0f;
        
        upData = new float[3];
        upData[0]= 0.0f;
        upData[1]= 1.0f;
        upData[2]= 0.0f;
        
        shininess =  10.0f;
    
		myShaders = new shaderProgram();
        Shape = new cg1Shape();
        myCanvas = G;
        
        G.addGLEventListener (this);
        G.addKeyListener (this);
        return;
	}
    
    /**
	 * Called by the drawable to initiate OpenGL rendering by the client. 
	 */
	public void display(GLAutoDrawable drawable)
	{
     
        
        // get GL
		GL2 gl2 = (drawable.getGL()).getGL2();
            
		// clear and draw parameters
		gl2.glClear ( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );
		gl2.glCullFace ( GL.GL_BACK );
        gl2.glUseProgram (shaderProgID);  
        //sending texture data
            Texture texture;
        
        try {
            InputStream stream = getClass().getResourceAsStream("texture.jpg");
            
            TextureData data = TextureIO.newTextureData(gl2.getGLProfile(),stream, false, "jpg");
            texture = TextureIO.newTexture(data);
        }
        catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }

            Buffer points   = Shape.getVerticies();
            Buffer normal   = Shape.getNormals();
            Buffer texture1  = Shape.getTextureFloat();
            Buffer elements = Shape.getElements();
            
            long vertBsize = Shape.getNVerts() * 4l * 4l; 
            long normBsize = Shape.getNVerts() * 3l * 4l; 
            long tBuffSize = Shape.getNVerts() * 2l * 4l;
            long eBuffSize = Shape.getNVerts() * 2l;
         
            int bf[]= new int[1];
            
            for(int i=0;i<3;i++) {

            if (bufferInit) {
                bf[0] = vbuffer;
                gl2.glDeleteBuffers(1, bf, 0);
            }
            gl2.glGenBuffers (1, bf, 0);
            vbuffer = bf[0];
                
            gl2.glBindBuffer ( GL.GL_ARRAY_BUFFER, vbuffer);
            gl2.glBufferData ( GL.GL_ARRAY_BUFFER, vertBsize + normBsize + tBuffSize , null, GL.GL_STATIC_DRAW);
                gl2.glBufferSubData(GL.GL_ARRAY_BUFFER,0,vertBsize, points);
                gl2.glBufferSubData(GL.GL_ARRAY_BUFFER,vertBsize,normBsize, normal);
                gl2.glBufferSubData(GL.GL_ARRAY_BUFFER,vertBsize + normBsize, tBuffSize, texture1);
                
                // set up vertex arrays in shader
                int  vPosition = gl2.glGetAttribLocation (shaderProgID, "vPosition");
                gl2.glUseProgram (shaderProgID);  
                gl2.glEnableVertexAttribArray ( vPosition );
                
                
                gl2.glVertexAttribPointer (vPosition, 4, GL.GL_FLOAT, false,
                                           0, 0l);
        
                bufferInit = true;
        
                //ataching normals                
                int  vNormal = gl2.glGetAttribLocation (shaderProgID, "vNormal");
                gl2.glEnableVertexAttribArray ( vNormal );
                gl2.glVertexAttribPointer (vNormal, 3, GL.GL_FLOAT, false,
                                           0, vertBsize); 
            
                
               //Texture 
               int vTexCoord = gl2.glGetAttribLocation (shaderProgID, "vTexCoord");
                gl2.glEnableVertexAttribArray ( vTexCoord );
                gl2.glVertexAttribPointer (vTexCoord, 2, GL.GL_FLOAT, false,
                                           0, normBsize); 
                
                
                bufferInit = true;
            // set up elements buffer.
            if (bufferInit) {
                bf[0] = ebuffer;
                gl2.glDeleteBuffers(1, bf, 0);
            }
            gl2.glGenBuffers (1, bf, 0);
            ebuffer = bf[0];
                               
            gl2.glBindBuffer ( GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer);
            gl2.glBufferData ( GL.GL_ELEMENT_ARRAY_BUFFER, eBuffSize,elements, GL.GL_STATIC_DRAW);   
                
               
                
                if(o==0) {     
                //rotation
                
                theta = gl2.glGetUniformLocation (shaderProgID, "theta");
                gl2.glUniform3fv (theta, 1, angles, 0);
                
                
                // pass in your translation     
                
                trans = gl2.glGetUniformLocation (shaderProgID, "trans");
                gl2.glUniform3fv (trans, 1, transData, 0);
                
                
                // pass in your scale values
                
                scale = gl2.glGetUniformLocation (shaderProgID, "scale");
                gl2.glUniform3fv (scale, 1, scaleData, 0);
                    
                    
                }
                
                if(o==1) {     
                    //rotation
                    
                    theta = gl2.glGetUniformLocation (shaderProgID, "theta");
                    gl2.glUniform3fv (theta, 1, angles, 0);
                    
                    
                    // pass in your translation     
                    
                    trans = gl2.glGetUniformLocation (shaderProgID, "trans");
                    gl2.glUniform3fv (trans, 1, transData1, 0);
                    
                    
                    // pass in your scale values
                    
                    scale = gl2.glGetUniformLocation (shaderProgID, "scale");
                    gl2.glUniform3fv (scale, 1, scaleData, 0);
                 
                }
                
                if(o==2) {     
                    //rotation
                    
                    theta = gl2.glGetUniformLocation (shaderProgID, "theta");
                    gl2.glUniform3fv (theta, 1, angles, 0);
                    
                    
                    // pass in your translation     
                    
                    trans = gl2.glGetUniformLocation (shaderProgID, "trans");
                    gl2.glUniform3fv (trans, 1, transData2, 0);
                    
                    
                    // pass in your scale values
                    
                    scale = gl2.glGetUniformLocation (shaderProgID, "scale");
                    gl2.glUniform3fv (scale, 1, scaleData, 0);
                
                }

                if(o==3) {     
                    //rotation
                    
                    theta = gl2.glGetUniformLocation (shaderProgID, "theta");
                    gl2.glUniform3fv (theta, 1, angles1, 0);
                    
                    
                    // pass in your translation     
                    
                    trans = gl2.glGetUniformLocation (shaderProgID, "trans");
                    gl2.glUniform3fv (trans, 1, transData3, 0);
                    
                    
                    // pass in your scale values
                    
                    scale = gl2.glGetUniformLocation (shaderProgID, "scale");
                    gl2.glUniform3fv (scale, 1, scaleData, 0);
                    

                }                
                o++;
                
                eye = gl2.glGetUniformLocation (shaderProgID, "eye");
                gl2.glUniform3fv (eye, 1, eyeData, 0);
                
                lookAt = gl2.glGetUniformLocation (shaderProgID, "lookAt");
                gl2.glUniform3fv (lookAt, 1, lookAtData, 0);
                
                up = gl2.glGetUniformLocation (shaderProgID, "up");
                gl2.glUniform3fv (up, 1, upData, 0);
                
                //setting light
                
                lightP =gl2.glGetUniformLocation (shaderProgID, "lightPosition");
                gl2.glUniform4fv (lightP, 1, lightPos, 0);    
                
                
                shine =gl2.glGetUniformLocation (shaderProgID, "shininess");
                gl2.glUniform1f(shine, shininess);  
                
                ambient = gl2.glGetUniformLocation (shaderProgID, "ambientProduct");
                gl2.glUniform4fv (ambient, 1, ColorAmbient, 0);
                
                diffuse = gl2.glGetUniformLocation (shaderProgID, "diffuseProduct");
                gl2.glUniform4fv (diffuse, 1, ColorDiffuse, 0);
                
                specular = gl2.glGetUniformLocation (shaderProgID,"specularProduct");
                gl2.glUniform4fv (specular, 1, ColorSpecular, 0); 
                
                           
		
            int nElems = Shape.getNVerts();
        gl2.glDrawElements ( GL.GL_TRIANGLES, nElems,  GL.GL_UNSIGNED_SHORT, 0l);
            }
	}
        
	
	/**
	 * Notifies the listener to perform the release of all OpenGL 
	 * resources per GLContext, such as memory buffers and GLSL 
	 * programs.
	 */
	public void dispose(GLAutoDrawable drawable)
	{
            
	}
        
	
	 /* Called by the drawable immediately after the OpenGL context is
	 * initialized. 
	 */
	public void init(GLAutoDrawable drawable)
	{
		// get the gl object
		GL2 gl2 = drawable.getGL().getGL2();
			
		// Load shaders
		shaderProgID = myShaders.readAndCompile (gl2, "vshader.glsl", "fshader.glsl");
		if (shaderProgID == 0) {
			System.err.println ("Error setting up shaders");
			System.exit (1);
		}
			
		// Other GL initialization
		gl2.glEnable (GL.GL_DEPTH_TEST);
		gl2.glEnable (GL.GL_CULL_FACE);
        gl2.glFrontFace(GL.GL_CCW);
		gl2.glClearColor (0.9f, 0.9f, 0.9f, 1.0f);
        gl2.glDepthFunc (GL.GL_LEQUAL);
        gl2.glClearDepth (1.0f);
			
		// initially create a new Shape
		createNewShape();
			
	}
        
	/**
	 * Called by the drawable during the first repaint after the component
	 * has been resized. 
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                     int height)
	{
            
	}
	

	
	/**
	 * creates a new shape
	 */
	public void createNewShape()
	{
        // clear the old shape
        Shape.clear();
      //  myShape1.clear();
        
        // create the new shape...should be a switch here
        switch (currentShape)
        {
            case CONE: 
            
                Shape.makeCone(4,4,4);
                break;
                
          
                
          
            
        }

	}
    
    /**
     * Because I am a Key Listener...we'll only respond to key presses
     */
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

    /** 
     * Invoked when a key has been pressed.
     */
    public void keyPressed(KeyEvent e)
    {
        // Get the key that was pressed
        char key = e.getKeyChar();
      //  System.out.println("key ========="+key);
        // Respond appropriately
        switch( key ) {
                
            case 'x': angles[0] -= angleInc;
			break;
            case 'y': angles[1] -= angleInc;
			break;
            case 'z': angles[2] -= angleInc;
			break;  
            case 'X': angles[0] += angleInc;
			break;
            case 'Y': angles[1] += angleInc;
			break;
            case 'Z': angles[2] += angleInc;
			break;
                
       
            case 'c' : currentShape = CONE; createNewShape(); break;
        
            case 's' : currentShape = CONE; createNewShape(); break;
            
            case '+': division1++; createNewShape(); break;
            case '=': division2++; createNewShape(); break;
            case '-': if (division1 > 1 ) {division1--; createNewShape();}break;
            case '_': if (division2 > 1)  {
                division2--; 
                if (currentShape != CONE) createNewShape();
            }
            break;

            case 'q': case 'Q':
                System.exit( 0 );
                break;
        }
        
        // do a redraw
        myCanvas.display();
    }
    
        
    /**
     * main program
     */
    public static void main(String [] args)
    {
        // GL setup
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);
		
		// create your tessMain
		FinalMain myMain = new FinalMain(canvas);
        
        
        Frame frame = new Frame("CG1 - OpenGL scene");
        frame.setSize(512, 512);
        frame.add(canvas);
        frame.setVisible(true);
        
        // by default, an AWT Frame doesn't do anything when you click
        // the close button; this bit of code will terminate the program when
        // the window is asked to close
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
