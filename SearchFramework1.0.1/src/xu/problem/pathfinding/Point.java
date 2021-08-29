package xu.problem.pathfinding;

import java.util.ArrayList;
import java.util.Comparator;

import static java.util.Comparator.*;

public class Point implements Comparable<Point>{

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	private int x;
	private int y;
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public static final Comparator<Point> RowColComparator = naturalOrder();
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj instanceof Point){
			Point p = (Point) obj;
			return this.x == p.x && this.y == p.y;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return x << 3 | y;
	}

	//根据x坐标比较
	public static final Comparator<Point> TestComparator = comparingInt(arg0 -> arg0.x);

	//根据到原点的曼哈顿距离进行比较
	public static final Comparator<Point> ManhattanComparator = comparingInt(arg0 -> arg0.manhattan(new Point(0, 0)));

	/**
	 * 当前点对象与参数p之间的曼哈顿距离
	 * @param p	另一个点p
	 * @return	当前点对象与p的曼哈顿距离
	 */
	public int manhattan(Point p){
		return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
	}

	/**
	 * 当前点对象与参数p之间的欧几里得距离，取整
	 * @param p	另一个点p
	 * @return	当前点对象与p的欧几里得距离
	 */
	public double euclid(Point p){
		int x = this.x - p.x;
		int y = this.y - p.y;
		return Math.sqrt(x * x + y * y);
	}

	/**
	 * 自然序，先比较横坐标，再比较纵坐标
	 */
	@Override
	public int compareTo(Point p) {
		if (this.x == p.x) 
			return this.y - p.y;
		
		return this.x - p.x;
	}
	
	public static void main(String[] args) {
		ArrayList<Point> treeset = new ArrayList<>();
		Point p = new Point(3, 5);
		treeset.add(p);
		p = new Point(4, 5);
		treeset.add(p);
		p = new Point(2, 7);
		treeset.add(p);
		p = new Point(3, 7);
		treeset.add(p);
		p = new Point(3, 1);
		treeset.add(p);
		treeset.sort(ManhattanComparator);
		
		for (Point point : treeset) {
			System.out.println(point);
		}
	}
}
