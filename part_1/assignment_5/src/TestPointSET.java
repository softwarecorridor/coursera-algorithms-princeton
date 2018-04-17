import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

class TestPointSET {

	/**
	 * Test the case where there are no items in the set. #isEmpty() should return True.
	 */
	@Test
	void test_isEmptyNoItems() {
		PointSET pSet = new PointSET();
		assertTrue(pSet.isEmpty());
	
	}

	
	/**
	 * Test case where #insert is given a null param.
	 */
	@Test
	void test_insertNullArgument() {
		PointSET pSet = new PointSET();
		assertThrows(IllegalArgumentException.class, () -> pSet.insert(null));
	}
	
	/**
	 * Test case where #contains is given a null param.
	 */
	@Test
	void test_containsNullArgument() {
		PointSET pSet = new PointSET();
		assertThrows(IllegalArgumentException.class, () -> pSet.contains(null));
	}
	
	/**
	 * Test case where #nearest is given a null param.
	 */
	@Test
	void test_nearestNullArgument() {
		PointSET pSet = new PointSET();
		assertThrows(IllegalArgumentException.class, () -> pSet.nearest(null));
	}
	
	@Test
	void test_rangeNullArgument() {
		PointSET pSet = new PointSET();
		assertThrows(IllegalArgumentException.class, () -> pSet.range(null));
	}
	
	
	/**
	 * Test the case where there is one item in the set. #isEmpty() should return False.
	 */
	@Test
	void test_isEmptyOneItem() {
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(1,2));
		assertFalse(pSet.isEmpty());
	}
	
	/**
	 * Test the case where there are many items in the set. #isEmpty() should return False.
	 */
	@Test
	void test_isEmptyManyItems() {
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(1,2));
		pSet.insert(new Point2D(2,2));
		pSet.insert(new Point2D(3,2));
		pSet.insert(new Point2D(1,7));
		pSet.insert(new Point2D(9,2));
		pSet.insert(new Point2D(1,32));
		assertFalse(pSet.isEmpty());
	}
	
	
	/**
	 * Test the case where there are no items in the set. #size() should return 0.
	 */
	@Test
	void test_sizeNoItems() {
		PointSET pSet = new PointSET();
		int result = pSet.size();
		int expected = 0;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there is one item in the set. #size() should return 1.
	 */
	@Test
	void test_sizeOneItem() {
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(1,7));
		int result = pSet.size();
		int expected = 1;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there are many items in the set. #size() should return 5.
	 */
	@Test
	void test_sizeManyItems() {
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(43,2));
		pSet.insert(new Point2D(9,7));
		pSet.insert(new Point2D(25,7));
		pSet.insert(new Point2D(91,3));
		pSet.insert(new Point2D(12,89));
		
		int result = pSet.size();
		int expected = 5;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there are no items in the set. #contains(..) should return false.
	 */
	@Test
	void test_containsNoItems() {
		PointSET pSet = new PointSET();
		boolean result = pSet.contains(new Point2D(12, 34));
		boolean expected = false;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there is one item in the set and the point is in the set. #contains(..) should return true.
	 */
	@Test
	void test_containsOneItemTrue() {
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(12, 34));
		boolean result = pSet.contains(new Point2D(12, 34));
		boolean expected = true;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there is one item in the set and the point is not in the set. #contains(..) should return false.
	 */
	@Test
	void test_containsOneItemFalse() {
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(12, 34));
		boolean result = pSet.contains(new Point2D(5, 9));
		boolean expected = false;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there are many items in the set and the point is not in the set. #contains(..) should return false.
	 */
	@Test
	void test_containsManyItemTrue() {
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(2, 0));
		pSet.insert(new Point2D(1, 5));
		pSet.insert(new Point2D(8, 5));
		pSet.insert(new Point2D(55, 99));
		pSet.insert(new Point2D(27, 34));
		boolean result = pSet.contains(new Point2D(12, 34));
		boolean expected = false;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there are many items in the set and the point is in the set. #contains(..) should return true.
	 */
	@Test
	void test_containsManyItemFalse() {
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(6, 2));
		pSet.insert(new Point2D(76, 2));
		pSet.insert(new Point2D(1, 94));
		pSet.insert(new Point2D(7, 7));
		pSet.insert(new Point2D(2, 68));
		boolean result = pSet.contains(new Point2D(7,7));
		boolean expected = true;
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there are no items in the set.
	 */
	@Test
	void test_rangeNoItems()
	{
		PointSET pSet = new PointSET();
		Iterable<Point2D> result = pSet.range(new RectHV(0, 0, 14, 14));
		Iterable<Point2D> expected = new SET<Point2D>();
		
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there is one item and it is in the range
	 */
	@Test
	void test_rangeOneItemInRange()
	{
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(11, 10));
		Iterable<Point2D> result = pSet.range(new RectHV(0, 0, 14, 14));
		
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
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(22, 134));
		Iterable<Point2D> result = pSet.range(new RectHV(11, 11, 12, 12));
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
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(11, 12));
		Iterable<Point2D> result = pSet.range(new RectHV(11, 11, 14, 14));
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
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(12, 14));
		Iterable<Point2D> result = pSet.range(new RectHV(11, 11, 14, 14));
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
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(12, 14));
		pSet.insert(new Point2D(0, 1));
		pSet.insert(new Point2D(32, 11));
		pSet.insert(new Point2D(13, 13));
		pSet.insert(new Point2D(20, 6));
		Iterable<Point2D> result = pSet.range(new RectHV(11, 11, 14, 14));
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
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(0, 0));
		pSet.insert(new Point2D(99, 99));
		pSet.insert(new Point2D(22, 56));
		pSet.insert(new Point2D(16, 85));
		pSet.insert(new Point2D(68, 99));
		pSet.insert(new Point2D(66, 45));
		Iterable<Point2D> result = pSet.range(new RectHV(11, 11, 14, 14));
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
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(11, 11));
		pSet.insert(new Point2D(11, 12));
		pSet.insert(new Point2D(11, 13));
		pSet.insert(new Point2D(11, 14));
		Iterable<Point2D> result = pSet.range(new RectHV(11, 11, 14, 14));
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
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(11, 14));
		pSet.insert(new Point2D(12, 14));
		pSet.insert(new Point2D(13, 14));
		pSet.insert(new Point2D(14, 14));
		Iterable<Point2D> result = pSet.range(new RectHV(11, 11, 14, 14));
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
		PointSET pSet = new PointSET();
		Point2D result = pSet.nearest(new Point2D(0, 0));
		assertEquals(null, result);
	}
	
	/**
	 * Test the case where there is only one point in the set.
	 */
	@Test
	void test_nearestOneEntry()
	{
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(1, 2));
		Point2D result = pSet.nearest(new Point2D(0, 0));
		Point2D expected = new Point2D(1,2);
		assertEquals(expected, result);
	}
	
	/**
	 * Test the case where there are many points in the set.
	 */
	@Test
	void test_nearestManyEntries()
	{
		PointSET pSet = new PointSET();
		pSet.insert(new Point2D(3, 7));
		pSet.insert(new Point2D(2, 8));
		pSet.insert(new Point2D(10, 1));
		pSet.insert(new Point2D(6, 6));
		pSet.insert(new Point2D(3, 1));
		
		Point2D result = pSet.nearest(new Point2D(10, 10));
		Point2D expected = new Point2D(6,6);
		assertEquals(expected, result);
	}
	
	
}
