package code.objects;


import framework.engine.DisplayableObject;
import framework.engine.Scene;

/**
 * Creates the table using Material class
 * @author itxsa27
 *
 */
public class Table extends DisplayableObject {
	//constructors 
	public Table(Scene parent) {
		super(parent);
	}
	public Table(Scene parent, int dim) {
		super(parent);
		size(dim);
	}
	public Table(Scene parent, int l, int h, int w) {
		super(parent);
		size(l, w, h);
	}


	@Override
	public void display() {
		// Save status of seen before starting
		parent.pushMatrix();
		parent.pushStyle();
		
        parent.translate(pos.x,pos.y,pos.z);    // move to position
        parent.scale(scale.x,scale.y,scale.z);  // scale to size
        parent.strokeWeight(2/scale.x);
        // Table top
		Material t = new Material(parent);
		t.position(-0.45f, -0.40f, 0.45f);
		t.size(1, 0.1f , 1);
		t.display();
		t.display();
		//First leg
		Material l1 = new Material(parent);
		l1.position(0.45f, 0.5f, 0.45f);
		l1.size(0.1f, 0.9f, 0.1f);
		l1.display();
		//Second leg
		Material l2 = new Material(parent);
		l2.position(-0.45f, 0.5f, 0.45f);
		l2.size(0.1f, 0.9f, 0.1f);
		l2.display();
		//Third leg
		Material l3 = new Material(parent);
		l3.position(0.45f, 0.5f, -0.45f);
		l3.size(0.1f, 0.9f, 0.1f );
		l3.display();
		//Fourth leg
		Material l4 = new Material(parent);
		l4.position(-0.45f, 0.5f, -0.45f);
		l4.size(0.1f, 0.9f ,0.1f);
		l4.display();
		//return the state of the seen back
		parent.popStyle();
		parent.popMatrix();
		
	}

}
