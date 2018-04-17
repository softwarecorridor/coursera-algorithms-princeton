
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {

	private SET<Point2D> points = new SET<Point2D>();
	
	/**
	 * Return true if the set is empty.
	 * @return
	 */
	public boolean isEmpty() {
		return points.isEmpty();
	}

	/**
	 * Add the Point2D to the set of Point2Ds.
	 * @param point2d
	 */
	public void insert(Point2D point2d) {
		if(point2d == null)
		{
			throw new IllegalArgumentException();
		}
		points.add(point2d);
		
	}
	
	public void draw() {}

	/**
	 * @return number of items in the set
	 */
	public int size() {
		return points.size();
	}

	/**
	 * Check to see if a point exists in the set.
	 * @param point2d point to find
	 * @return true if the point is in the set.
	 */
	public boolean contains(Point2D point2d) {
		if(point2d == null)
		{
			throw new IllegalArgumentException();
		}
		return points.contains(point2d);
	}

	/**
	 * Return all points that are inside the rectangle (or on the boundary) 
	 *  
	 * @param rectHV
	 * @return 
	 */
	public Iterable<Point2D> range(RectHV rectHV) {
		if(rectHV == null)
		{
			throw new IllegalArgumentException();
		}
		
		SET<Point2D> results = new SET<>();
		
		for (Point2D point : points)
		{
			if(rectHV.contains(point)) {
				results.add(point);
			}
		}
		
		return results;
	}

	/**
	 * Return the nearest neighbor in the set to point; null if the set is empty
	 * @param point2d
	 * @return
	 */
	public Point2D nearest(Point2D point2d) {
		if(point2d == null)
		{
			throw new IllegalArgumentException();
		}
		
		Point2D closestPoint = null;
		double closestDistance = Integer.MAX_VALUE;
		
		for(Point2D point : points)
		{
			double distance = point2d.distanceTo(point);
			if(distance < closestDistance)
			{
				closestDistance = distance;
				closestPoint = point;
			}
		}
		
		return closestPoint;
	}
	
}
