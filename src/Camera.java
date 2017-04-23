import java.awt.Color;
import java.awt.Point;

public class Camera {
	private double x = 0.0;
	private double y = 0.0;
	private double z = -150.0;
	private double phi = 0.0;

	// Intersection is 150 pixels away from camera
	private static final int focalLength = 450;
	private static final int orthoZoom = 3;
	private double factor = 1.0;

	public Point project(CubePoint pt) {
		CubePoint image = transform(pt);
		// Mix perspective and orthographic projections
		double projectionConstant = mix(focalLength / image.z, orthoZoom, factor);
		return new Point((int) (image.x * projectionConstant), (int) (image.y * projectionConstant));
	}

	public Color getCubeColor() {
		return Color.BLUE;
	}

	public Color getIntersectionColor() {
		return Color.CYAN;
	}

	public CubePoint transform(CubePoint point) {
		CubePoint image = new CubePoint(point.x, point.y, point.z);
		CubePoint axis = new CubePoint(0.0, 0.0, 0.0);
		image.rotateX(phi, axis);
		image.translate(-x, -y, -z);
		return image;
	}

	public void rotate(double dPhi) {
		phi += dPhi;
	}

	public void setRotation(double phi) {
		// double diff = this.phi - phi;
		// while (this.phi != phi) {
		// this.rotate(diff/10);
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		this.phi = phi;
	}

	public void translate(double dx, double dy, double dz) {
		x += dx;
		y += dy;
		z += dz;
	}

	public void setPosition(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setProjectionFactor(double factor) {
		this.factor = factor;
	}

	/**
	 * @param value1
	 *            The first value to mix
	 * @param value2
	 *            The second value to mix
	 * @param factor
	 *            0 = completely value2, 1 is completely value1
	 * @return The mixed value
	 */
	private double mix(double value1, double value2, double factor) {
		return (value1 * factor) + (value2 * (1 - factor));
	}
}
