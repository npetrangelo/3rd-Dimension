import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;

public class Face {
	public ArrayList<CubePoint> points;
	public ArrayList<Line> edges;
	public ArrayList<CubePoint> intersections = new ArrayList<CubePoint>();
	public Polygon projection = new Polygon();

	public Face(ArrayList<CubePoint> points) {
		this.points = points;
		edges = new ArrayList<Line>();

		for (int i = 0; i < points.size() - 1; i++) {
			edges.add(new Line(points.get(i), points.get(i + 1)));
		}
		edges.add(new Line(points.get(points.size() - 1), points.get(0)));
	}

	public static Face makeFace(CubePoint[] pts) {
		return new Face(new ArrayList<CubePoint>(Arrays.asList(pts)));
	}

	// /**
	// * Makes a face using the lines as edges, assuming each pair shares a
	// common point
	// * @param lines ArrayList of lines
	// * @return A face using the lines as edges
	// */
	// public static Face makeFace(ArrayList<Line> lines)
	// {
	// if (lines.isEmpty())
	// {
	// return null;
	// }
	// ArrayList<CubePoint> pts = new ArrayList<CubePoint>();
	// pts.add(lines.get(0).p1);
	// for (Line line : lines)
	// {
	// pts.add(line.p2);
	// }
	// return new Face(pts);
	// }

	public void Draw(Graphics2D g2d, Camera cam) {
		for (CubePoint pt : points) {
			Point projected = cam.project(pt);
			projection.addPoint(projected.x, projected.y);
		}
		g2d.drawPolygon(projection);
		// for (Line edge : edges)
		// {
		// edge.Draw(g2d);
		// }
	}

	public void updateIntersections(double zPlane) {
		intersections.clear();
		for (Line edge : edges) {
			CubePoint intersection = edge.makeIntersection(zPlane);
			if (intersection != null) {
				// System.out.println("Face.updateIntersections intersection=" +
				// intersection);
				intersections.add(intersection);
			}
		}
		intersections.sort(CubePoint::compareByX);
		// System.out.println("Face.updateIntersections intersections=" +
		// intersections);
	}

	public Line getIntersection(double zPlane) {
		ArrayList<CubePoint> intersections = new ArrayList<CubePoint>();
		for (Line edge : edges) {
			CubePoint intersection = edge.makeIntersection(zPlane);
			if (intersection != null) {
				// System.out.println("Face.getIntersection intersection=" +
				// intersection);
				intersections.add(intersection);
			}
		}
		intersections.sort(CubePoint::compareByX);
		if (intersections.isEmpty()) {
			return null;
		}
		return new Line(intersections.get(0), intersections.get(1));
	}

	public void drawIntersection(Graphics2D g2d, Camera p) {
		// System.out.println(intersections.size());
		if (!intersections.isEmpty()) {
			for (int i = 0; i <= intersections.size() - 2; i += 2) {
				Line intersection = new Line(intersections.get(i), intersections.get(i + 1));
				intersection.Draw(g2d, p);
			}
		}
	}

	@Override
	public String toString() {
		return "Face [points=" + points + ", edges=" + edges + ", intersections=" + intersections + ", projection="
				+ projection + "]";
	}
}
