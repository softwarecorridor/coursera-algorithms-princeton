import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BruteCollinearPoints {

	private ArrayList<LineSegment> segments = new ArrayList<>();
	
	
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
		
		Arrays.sort(points);
		
		
		for (int p = 0; p<points.length-3;p++)
		{
			
			Point pPoint = points[p];
			System.out.println(pPoint.toString());
			for (int q = 1; q<points.length-2;q++)
			{
				
				Point qPoint = points[q];
				double p_q = pPoint.slopeTo(qPoint);
				
				for (int r = 2; r<points.length-1;r++)
				{
					Point rPoint = points[r];
					double p_r = pPoint.slopeTo(rPoint);	
					if(p_r == p_q)
					{
						for (int s = 3; s<points.length;s++)
						{
							
							Point sPoint = points[s];
							double p_s = pPoint.slopeTo(sPoint);
							
							if (p_r == p_s)
							{
								segments.add(new LineSegment(pPoint, sPoint));
							}
						}
					}
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
