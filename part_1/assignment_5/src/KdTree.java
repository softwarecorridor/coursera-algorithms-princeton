import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class KdTree {
		
	private Node rootNode;
	
	/**
	 * Return true if the set is empty.
	 * @return
	 */
	public boolean isEmpty() {
		return size() == 0;
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
		rootNode = put(rootNode, point2d, 0);
	}

	private Node put(Node kdNode, Point2D point2d, int depth) {
		if(kdNode == null)
		{
			
			Node n = new Node(point2d, depth);
			if(depth == 0)
			{
				n.region = new RectHV(Double.MIN_VALUE,Double.MIN_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
//				n.region = new RectHV(0, 0, 20, 20);
			}
			
			return n;
		}
		
		double nodeKey = kdNode.isVertical ? kdNode.value.x() :  kdNode.value.y();
		double pointKey = kdNode.isVertical ? point2d.x() : point2d.y();
		
		if (pointKey < nodeKey)
		{
			kdNode.left = put(kdNode.left, point2d, ++depth);
			if(kdNode.isVertical)
			{
				kdNode.left.region = new RectHV(kdNode.region.xmin(), kdNode.region.ymin(),  kdNode.getKey(),  kdNode.region.ymax());
			}else
			{
				
				kdNode.left.region = new RectHV(kdNode.region.xmin(), kdNode.region.ymin(),  kdNode.region.xmax(),  kdNode.getKey());
			}
			
			
		}else
		{
			if(!kdNode.value.equals(point2d))
			{
				kdNode.right = put(kdNode.right, point2d, ++depth);
				if(kdNode.isVertical)
				{
					kdNode.right.region = new RectHV(kdNode.getKey(), kdNode.region.ymin(),  kdNode.region.xmax(),  kdNode.region.ymax());
				}else
				{
					kdNode.right.region = new RectHV(kdNode.region.xmin(), kdNode.getKey(),  kdNode.region.xmax(),  kdNode.region.ymax());
				}
			}
			
			
		}
		
		
		return kdNode;
	}

	/**
	 * @return number of items in the set
	 */
	public int size() {
		return size(rootNode);
	}
	
	private int size(Node kdNode)
	{
		if (kdNode == null){
			return 0;
		}else
		{
			return 1 + size(kdNode.left) + size(kdNode.right);
		}
		
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
		
		Node currentNode = rootNode;
		
		while(currentNode != null)
		{
			if (point2d.equals(currentNode.value))
			{
				return true;
			}else
			{
				// compare the point we are searching for to the current node
				double currentKey = currentNode.isVertical ? point2d.x() : point2d.y();
				
				// go left if less
				if(currentKey < currentNode.getKey())
				{
					currentNode = currentNode.left;
				// go right if bigger or the same
				}else
				{
					currentNode = currentNode.right;
				}
			}
			
		}
		
		// if nothing is found return false;
		return false;
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
		
		//TODO: prune
		/*
		 * 
		 * Range search. To find all points contained in a given query rectangle, start at the root and recursively 
		 * search for points in both subtrees using the following pruning rule: , there is no need to explore that node (or its subtrees). A subtree is searched only 
		 * if it might contain a point contained in the query rectangle
		 */
		
		SET<Point2D> results = new SET<Point2D>();
		if(rootNode != null)
		{
			Node searchNode = rootNode;
			rangeSearch(searchNode, rectHV, searchNode.region, results);
		}
		
		return results;
	}
	
	private void rangeSearch(Node node, RectHV searchRange, RectHV region, SET<Point2D> results)
	{
		if(node == null)
		{
			return;
		}
		
		
		if(region.intersects(searchRange))
		{
			if(searchRange.contains(node.value))
			{
				results.add(node.value);
			}
			
			if(node.left != null)
			{
				rangeSearch(node.left, searchRange, node.left.region, results);
			}
			if(node.right != null)
			{
				rangeSearch(node.right, searchRange, node.right.region, results);
			}
			
		}
	}
	
	public void draw() {}

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
		if(rootNode != null)
		{
			Node searchNode = rootNode;
			closestPoint = findNearest(searchNode, point2d, closestPoint, searchNode.region);
		}
		return closestPoint;
	}
	
	private Point2D findNearest(Node node, Point2D targetPoint, Point2D closestPoint, RectHV region)
	{
		Point2D closest = closestPoint;
		
		if (node == null)
		{
			return closest;
		}
		
		// check to see if the current Node is closer than the old closest.
		double currentClosestDistance = Double.MAX_VALUE;
		if(closest!=null)
		{
			currentClosestDistance = closestPoint.distanceSquaredTo(targetPoint);
		}
		
		if (targetPoint.distanceSquaredTo(node.value) < currentClosestDistance)
		{
			closest = node.value;
			currentClosestDistance = targetPoint.distanceSquaredTo(node.value);
		}
		
		RectHV leftSquare = null;
		if(node.left != null)
		{
			leftSquare = node.left.region;
		}
		RectHV rightSquare = null;
		
		if(node.right != null)
		{
			rightSquare = node.right.region;
		}
		
		if(leftSquare != null)
		{
			if(leftSquare.distanceSquaredTo(closest) < currentClosestDistance)
			{
				closest = findNearest(node.left, targetPoint, closest, leftSquare);
			}
		}
		
		if(rightSquare != null)
		{
			if(rightSquare.distanceSquaredTo(closest) < currentClosestDistance)
			{
				closest = findNearest(node.right, targetPoint, closest, rightSquare);
			}
		}
		

		return closest;
						
	}
	
	

	//Have to use an inner class because the class tester only allows PointSET and KdTree files.
	private class Node{
		private double[] key; // will alternate between x and y of the point depending on the level
		private Point2D value;
		private boolean isVertical;
		private RectHV region;
		private Node left, right = null;
		int depth = 0;
		
		public Node (Point2D point2d, int depth)
		{
			setValue(point2d);
			this.depth = depth;
			this.isVertical = depth % 2 == 0;
		}
		
		public double getKey()
		{
			return key[isVertical ? 0 : 1];
		}
		
		private void setValue(Point2D point2d)
		{
			this.key = new double[]{point2d.x(), point2d.y()};
			this.value = point2d;
		}
	}
}
