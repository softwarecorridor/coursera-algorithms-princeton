import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

class TestKdTree {

	/**
	 * Test the case where there are no items in the set. #isEmpty() should return True.
	 */
	@Test
	void test_isEmptyNoItems() {
		KdTree kdTree = new KdTree();
		assertTrue(kdTree.isEmpty());
	
	}

	
	/**
	 * Test case where #insert is given a null param.
	 */
	@Test
	void test_insertNullArgument() {
		KdTree kdTree = new KdTree();
		assertThrows(IllegalArgumentException.class, () -> kdTree.insert(null));
	}
	
	/**
	 * Test case where #contains is given a null param.
	 */
	@Test
	void test_containsNullArgument() {
		KdTree kdTree = new KdTree();
		assertThrows(IllegalArgumentException.class, () -> kdTree.contains(null));
	}
	
	/**
	 * Test case where #nearest is given a null param.
	 */
	@Test
	void test_nearestNullArgument() {
		KdTree kdTree = new KdTree();
		assertThrows(IllegalArgumentException.class, () -> kdTree.nearest(null));
	}
	
	@Test
	void test_rangeNullArgument() {
		KdTree kdTree = new KdTree();
		assertThrows(IllegalArgumentException.class, () -> kdTree.range(null));
	}
	
	
	/**
	 * Test the case where there is one item in the set. #isEmpty() should return False.
	 */
	@Test
	void test_isEmptyOneItem() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(1,2));
		assertFalse(kdTree.isEmpty());
	}
	
	/**
	 * Test the case where there are many items in the set. #isEmpty() should return False.
	 */
	@Test
	void test_isEmptyManyItems() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(1,2));
		kdTree.insert(new Point2D(2,2));
		kdTree.insert(new Point2D(3,2));
		kdTree.insert(new Point2D(1,7));
		kdTree.insert(new Point2D(9,2));
		kdTree.insert(new Point2D(1,32));
		assertFalse(kdTree.isEmpty());
	}
	
	
	/**
	 * Test the case where there are no items in the set. #size() should return 0.
	 */
	@Test
	void test_sizeNoItems() {
		KdTree kdTree = new KdTree();
		int result = kdTree.size();
		int expected = 0;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there is one item in the set. #size() should return 1.
	 */
	@Test
	void test_sizeOneItem() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(1,7));
		int result = kdTree.size();
		int expected = 1;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there are many items in the set. #size() should return 5.
	 */
	@Test
	void test_sizeManyItems() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(43,2));
		kdTree.insert(new Point2D(9,7));
		kdTree.insert(new Point2D(25,7));
		kdTree.insert(new Point2D(91,3));
		kdTree.insert(new Point2D(12,89));
		
		int result = kdTree.size();
		int expected = 5;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there are no items in the set. #contains(..) should return false.
	 */
	@Test
	void test_containsNoItems() {
		KdTree kdTree = new KdTree();
		boolean result = kdTree.contains(new Point2D(12, 34));
		boolean expected = false;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there is one item in the set and the point is in the set. #contains(..) should return true.
	 */
	@Test
	void test_containsOneItemTrue() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(12, 34));
		boolean result = kdTree.contains(new Point2D(12, 34));
		boolean expected = true;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there is one item in the set and the point is not in the set. #contains(..) should return false.
	 */
	@Test
	void test_containsOneItemFalse() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(12, 34));
		boolean result = kdTree.contains(new Point2D(5, 9));
		boolean expected = false;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there are many items in the set and the point is not in the set. #contains(..) should return false.
	 */
	@Test
	void test_containsManyItemsNotFound() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(2, 0));
		kdTree.insert(new Point2D(1, 5));
		kdTree.insert(new Point2D(8, 5));
		kdTree.insert(new Point2D(55, 99));
		kdTree.insert(new Point2D(27, 34));
		boolean result = kdTree.contains(new Point2D(12, 34));
		boolean expected = false;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there are many items in the set and the point is in the set. #contains(..) should return true.
	 */
	@Test
	void test_containsManyItemFound() {
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(6, 2));
		kdTree.insert(new Point2D(76, 2));
		kdTree.insert(new Point2D(1, 94));
		kdTree.insert(new Point2D(7, 7));
		kdTree.insert(new Point2D(2, 68));
		boolean result = kdTree.contains(new Point2D(7,7));
		boolean expected = true;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there are no items in the set.
	 */
	@Test
	void test_rangeNoItems()
	{
		KdTree kdTree = new KdTree();
		Iterable<Point2D> result = kdTree.range(new RectHV(0, 0, 14, 14));
		Iterable<Point2D> expected = new SET<Point2D>();
		
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there is one item and it is in the range
	 */
	@Test
	void test_rangeOneItemInRange()
	{
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(11, 10));
		Iterable<Point2D> result = kdTree.range(new RectHV(0, 0, 14, 14));
		
		SET<Point2D> expectedSet = new SET<Point2D>();
		expectedSet.add(new Point2D(11, 10));
		Iterable<Point2D> expected = expectedSet;
		
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there is one item and it is not in the range
	 */
	@Test
	void test_rangeOneItemNotInRange()
	{
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(22, 134));
		Iterable<Point2D> result = kdTree.range(new RectHV(11, 11, 12, 12));
		SET<Point2D> expectedSet = new SET<Point2D>();
		Iterable<Point2D> expected = expectedSet;
		
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there is one item and it is on the edge on the x Axis
	 */
	@Test
	void test_rangeOneItemOnTheXEdge()
	{
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(11, 12));
		Iterable<Point2D> result = kdTree.range(new RectHV(11, 11, 14, 14));
		SET<Point2D> expectedSet = new SET<Point2D>();
		expectedSet.add(new Point2D(11, 12));
		Iterable<Point2D> expected = expectedSet;
		assertEquals(expected, result);
	}	
	
	
	/**
	 * Test the case where there is one item and it is on the edge on the y Axis
	 */
	@Test
	void test_rangeOneItemOnTheYEdge()
	{
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(12, 14));
		Iterable<Point2D> result = kdTree.range(new RectHV(11, 11, 14, 14));
		SET<Point2D> expectedSet = new SET<Point2D>();
		expectedSet.add(new Point2D(12, 14));
		Iterable<Point2D> expected = expectedSet;
		assertEquals(expected, result);
	}	
	
	
	
	/**
	 * Test the case where there are many items and some are in the range
	 */
	@Test
	void test_rangeManyItemsSomeInRange()
	{
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(12, 14));
		kdTree.insert(new Point2D(0, 1));
		kdTree.insert(new Point2D(32, 11));
		kdTree.insert(new Point2D(13, 13));
		kdTree.insert(new Point2D(20, 6));
		Iterable<Point2D> result = kdTree.range(new RectHV(11, 11, 14, 14));
		SET<Point2D> expectedSet = new SET<Point2D>();
		expectedSet.add(new Point2D(12, 14));
		expectedSet.add(new Point2D(13, 13));
		Iterable<Point2D> expected = expectedSet;
		assertEquals(expected, result);
	}	
	
	
	
	/**
	 * Test the case where there are many items and none are in the range
	 */
	@Test
	void test_rangeManyItemsNoneInRange()
	{
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(0, 0));
		kdTree.insert(new Point2D(99, 99));
		kdTree.insert(new Point2D(22, 56));
		kdTree.insert(new Point2D(16, 85));
		kdTree.insert(new Point2D(68, 99));
		kdTree.insert(new Point2D(66, 45));
		Iterable<Point2D> result = kdTree.range(new RectHV(11, 11, 14, 14));
		SET<Point2D> expectedSet = new SET<Point2D>();
		Iterable<Point2D> expected = expectedSet;
		assertEquals(expected, result);
	}	
	
	/**
	 * Test the case where there are many items and they are all on the x Axis
	 */
	@Test
	void test_rangeManyItemsAllOnXAxis()
	{
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(11, 11));
		kdTree.insert(new Point2D(11, 12));
		kdTree.insert(new Point2D(11, 13));
		kdTree.insert(new Point2D(11, 14));
		Iterable<Point2D> result = kdTree.range(new RectHV(11, 11, 14, 14));
		SET<Point2D> expectedSet = new SET<Point2D>();
		expectedSet.add(new Point2D(11, 11));
		expectedSet.add(new Point2D(11, 12));
		expectedSet.add(new Point2D(11, 13));
		expectedSet.add(new Point2D(11, 14));
		Iterable<Point2D> expected = expectedSet;
		assertEquals(expected, result);
	}	
	
	/**
	 * Test the case where there are many items and they are all on the y Axis
	 */
	@Test
	void test_rangeManyItemsAllOnYAxis()
	{
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(11, 14));
		kdTree.insert(new Point2D(12, 14));
		kdTree.insert(new Point2D(13, 14));
		kdTree.insert(new Point2D(14, 14));
		RectHV searchRange = new RectHV(11.0, 11.0, 14.0, 14.0);
		Iterable<Point2D> result = kdTree.range(searchRange);
		SET<Point2D> expectedSet = new SET<Point2D>();
		expectedSet.add(new Point2D(11, 14));
		expectedSet.add(new Point2D(12, 14));
		expectedSet.add(new Point2D(13, 14));
		expectedSet.add(new Point2D(14, 14));
		Iterable<Point2D> expected = expectedSet;
		assertEquals(expected, result);
	}	
	
	/**
	 * Test the case where there are no points in the set.
	 */
	@Test
	void test_nearestNoEntries()
	{
		KdTree kdTree = new KdTree();
		Point2D result = kdTree.nearest(new Point2D(0, 0));
		assertEquals(null, result);
	}
	
	/**
	 * Test the case where there is only one point in the set.
	 */
	@Test
	void test_nearestOneEntry()
	{
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(1, 2));
		Point2D result = kdTree.nearest(new Point2D(0, 0));
		Point2D expected = new Point2D(1,2);
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there are many points in the set.
	 */
	@Test
	void test_nearestManyEntries()
	{
		KdTree kdTree = new KdTree();
		kdTree.insert(new Point2D(3, 7));
		kdTree.insert(new Point2D(2, 8));
		kdTree.insert(new Point2D(10, 1));
		kdTree.insert(new Point2D(6, 6));
		kdTree.insert(new Point2D(3, 1));
		
		Point2D result = kdTree.nearest(new Point2D(10, 10));
		Point2D expected = new Point2D(6,6);
		assertEquals(expected, result);
	}
	
	
}
