import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BruteCollinearPoints {

	private ArrayList<Node> nodes = new ArrayList<>();
	
	
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
			
			Node currentLineSegment = new Node(p);
			boolean isNewLine = true;
			
			for (int q = p+ 1; q<points.length-2;q++)
			{
				
				Point qPoint = points[q];
				double p_q = pPoint.slopeTo(qPoint);
				
				
				for (int r = q + 1; r<points.length-1;r++)
				{
					Point rPoint = points[r];
					double p_r = pPoint.slopeTo(rPoint);
					
					
					if(p_r == p_q)
					{
						for (int s =r + 1; s<points.length;s++)
						{
							
							Point sPoint = points[s];
							double p_s = pPoint.slopeTo(sPoint);
							
							if (p_r == p_s)
							{
								if(isNewLine)
								{
									nodes.add(currentLineSegment);
									
									currentLineSegment.next = new Node(q);
									currentLineSegment = currentLineSegment.next;
									currentLineSegment.next = new Node(r);
									currentLineSegment = currentLineSegment.next;
									
									
									isNewLine = false;
									
								}else
								{
									if(!Node.containsNode(nodes.get(p), s))
									{
										currentLineSegment.next = new Node(s);
										currentLineSegment = currentLineSegment.next;
									}
								}
								
								
								
								
							}
						}
					}
				}
			}
		}
		
		
		
		if(nodes.size()>1)
		{
			for (int i = 1; i<nodes.size(); i++)
			{
				if(Node.collinear(nodes.get(i-1), nodes.get(i)))
				{
					nodes.remove(nodes.get(i));
				}
			}
		}
		
		debug();
	}
	

	/**
	 * 
	 * the number of line segments
	 * 
	 * @return
	 */
	public int numberOfSegments() {
		return nodes.size();
	}

	/**
	 * 
	 * the line segments
	 * 
	 * @return
	 */
	public LineSegment[] segments() {
		return  null;
	};
	
	private static class Node
	{
		public int position;
		public Node next;
		
		
		public Node(int pos)
		{
			position = pos;
			next = null;
		}
		
		
		public static Node searchNode(Node n, int pos)
		{
			Node current = n;
			while(current.next != null)
			{
				if(current.position == pos)
				{
					return current;
				}
				current = current.next;
			}
			return null;
		}
		
		public static boolean containsNode(Node n, int pos)
		{
			Node current = n;
			while(current.next!= null)
			{
				if(current.position == pos)
				{
					return true;
				}
				current = current.next;
			}
			return false;
		}
		
		public static int size(Node n)
		{
			int count = 0;
			Node current = n;
			while(current.next!= null)
			{
				count++;
				current = current.next;
			}
			return count;
		}
		
		public static boolean collinear(Node outer, Node inner)
		{
			Node coNode = Node.searchNode(outer, inner.position);
			
			if(coNode!=null)
			{
				if(coNode.next != null && inner.next != null)
				{
					return coNode.next.position == inner.next.position;
				}
			}
			
			return false;
		}
			
	}
	
	private void debug()
	{
		for(Node n: nodes)
		{
			Node current = n;
			StringBuilder sb = new StringBuilder();
			while(current.next!= null)
			{
				sb.append(current.position);
				sb.append("=>");
				current = current.next;
			}
			System.out.println(sb.toString());
		}
	}
	
	

}
