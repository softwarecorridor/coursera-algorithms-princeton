import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

	private ArrayList<LineSegment> segments;

	/**
	 * finds all line segments containing 4 or more points
	 * 
	 * @param points
	 */
	public FastCollinearPoints(Point[] points) {

		validateInput(points);

		Point[] copy = points.clone();
		Arrays.sort(copy);

		segments = new ArrayList<>();

		for (int p = 0; p < copy.length - 3; p++) {
			// Think of p as the origin.

			Point origin = points[p];
			Point[] internalCopy = points.clone();

			// Sort the points according to the slopes they makes with p.
			Arrays.sort(internalCopy, origin.slopeOrder());

			// Check if any 3 (or more) adjacent points in the sorted order have
			// equal slopes with respect to p. If so, these points, together
			// with p, are collinear.
			for (int j = 1; j < internalCopy.length - 3; j++) {
				Point pointJ = internalCopy[j];
				Point pointK = internalCopy[j + 1];
				Point pointL = internalCopy[j + 2];

				double slopeJ = origin.slopeTo(pointJ);
				double slopeK = origin.slopeTo(pointK);
				double slopeL = origin.slopeTo(pointL);

				if (slopeJ == slopeK && slopeK == slopeL) {
					addSegment(origin, pointL);
				}

			}

		}

	}

	private void validateInput(Point[] points) {

		if (points == null) {
			throw new IllegalArgumentException();
		}

		for (Point p : points) {
			if (p == null) {
				throw new IllegalArgumentException();
			}
		}
		// TODO Auto-generated method stub
		detectDuplicatedPoints(points);
	}

	private void detectDuplicatedPoints(Point[] points) {
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				Point p = points[i];
				Point q = points[j];

				if (p.compareTo(q) == 0) {
					throw new IllegalArgumentException();
				}
			}
		}
	}

	private void addSegment(Point start, Point end) {

		segments.add(new LineSegment(start, end));
	}

	/**
	 * the number of line segments
	 * 
	 * @return
	 */
	public int numberOfSegments() {
		return segments.size();
	}

	/**
	 * the line segments
	 * 
	 * @return
	 */
	public LineSegment[] segments() {
		return segments.toArray(new LineSegment[0]);
	};

}
