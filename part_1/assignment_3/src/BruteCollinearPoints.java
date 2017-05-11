import java.util.ArrayList;

public class BruteCollinearPoints {

	ArrayList<LineSegment> segments = new ArrayList<>();
	
	
	/**
	 * finds all line segments containing 4 points.
	 * 4 points at a time and checks whether they all lie on the same line segment, 
	 * returning all such line segments. 
	 * To check whether the 4 points p, q, r, and s are collinear, 
	 * check whether the three slopes between p and q, between p and r, 
	 * and between p and s are all equal. 
	 * 
	 * @param points
	 */
	public BruteCollinearPoints(Point[] points) {

		if(points == null)
		{
			throw new NullPointerException();
		}
		
		if(points.length>3)
		{
			segments.add(new LineSegment(points[0], points[1]));
			
			for(int i = 2; i<points.length; i++)
			{
				Point p = points[i];
				for(int j = 0; j< segments.size(); j++)
				{
					if
				}
			}
			
		}
		
	}

	/**
	 * 
	 * the number of line segments
	 * 
	 * @return
	 */
	public int numberOfSegments() {
		return segments.size();
	}

	/**
	 * 
	 * the line segments
	 * 
	 * @return
	 */
	public LineSegment[] segments() {
		return segments.toArray(new LineSegment[0]);
	};

}
