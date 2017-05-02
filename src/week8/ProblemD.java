package week8;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// code inspired from stackoverflow and princeton uni introduction to rationals

class Rational implements Comparable<Rational> {
	private static Rational zero = new Rational(0, 1);
	private long num;
	private long den;

	public Rational(long numerator, long denominator) {
		long g = gcd(numerator, denominator);
		num = numerator / g;
		den = denominator / g;
		if (den < 0) {
			den = -den;
			num = -num;
		}
	}

	public long numerator() {
		return num;
	}

	public long denominator() {
		return den;
	}

	public double toDouble() {
		return (double) num / den;
	}

	public String toString() {
		if (den == 1)
			return num + "";
		else
			return num + "/" + den;
	}

	public int compareTo(Rational b) {
		Rational a = this;
		long lhs = a.num * b.den;
		long rhs = a.den * b.num;
		if (lhs < rhs)
			return -1;
		if (lhs > rhs)
			return +1;
		return 0;
	}
	
	
	
/*	 public Rational(int numerator, int denominator) {

	        // deal with x/0
	        //if (denominator == 0) {
	        //   throw new RuntimeException("Denominator is zero");
	        //}

	        // reduce fraction
	        int g = gcd(numerator, denominator);
	        num = numerator   / g;
	        den = denominator / g;

	        // only needed for negative numbers
	        if (den < 0) { den = -den; num = -num; }
	    }

	    // return the numerator and denominator of (this)
	    public int numerator()   { return num; }
	    public int denominator() { return den; }

	    // return double precision representation of (this)
	    public double toDouble() {
	        return (double) num / den;
	    }

	    // return string representation of (this)
	    public String toString() { 
	        if (den == 1) return num + "";
	        else          return num + "/" + den;
	    }

	    // return { -1, 0, +1 } if a < b, a = b, or a > b
	    public int compareTo(Rational b) {
	        Rational a = this;
	        int lhs = a.num * b.den;
	        int rhs = a.den * b.num;
	        if (lhs < rhs) return -1;
	        if (lhs > rhs) return +1;
	        return 0;
	    }

	    // is this Rational object equal to y?
	    public boolean equals(Object y) {
	        if (y == null) return false;
	        if (y.getClass() != this.getClass()) return false;
	        Rational b = (Rational) y;
	        return compareTo(b) == 0;
	    }

	    // hashCode consistent with equals() and compareTo()
	    public int hashCode() {
	        return this.toString().hashCode();
	    }*/

	public boolean equals(Object y) {
		if (y == null)
			return false;
		if (y.getClass() != this.getClass())
			return false;
		Rational b = (Rational) y;
		return compareTo(b) == 0;
	}

	public int hashCode() {
		return this.toString().hashCode();
	}

	public static Rational mediant(Rational r, Rational s) {
		return new Rational(r.num + s.num, r.den + s.den);
	}

	private static long gcd(long numerator, long denominator) {
		if (numerator < 0)
			numerator = -numerator;
		if (denominator < 0)
			denominator = -denominator;
		if (0 == denominator)
			return numerator;
		else
			return gcd(denominator, numerator % denominator);
	}

	private static long lcm(long den2, long den3) {
		if (den2 < 0)
			den2 = -den2;
		if (den3 < 0)
			den3 = -den3;
		return den2 * (den3 / gcd(den2, den3));
	}

	public Rational times(Rational b) {
		Rational a = this;
		Rational c = new Rational(a.num, b.den);
		Rational d = new Rational(b.num, a.den);
		return new Rational(c.num * d.num, c.den * d.den);
	}

	public Rational plus(Rational b) {
		Rational a = this;
		if (a.compareTo(zero) == 0)
			return b;
		if (b.compareTo(zero) == 0)
			return a;
		long f = gcd(a.num, b.num);
		long g = gcd(a.den, b.den);
		Rational s = new Rational((a.num / f) * (b.den / g) + (b.num / f) * (a.den / g), lcm(a.den, b.den));
		s.num *= f;
		return s;
	}

	public Rational negate() {
		return new Rational(-num, den);
	}

	public Rational minus(Rational b) {
		Rational a = this;
		return a.plus(b.negate());
	}

	public Rational reciprocal() {
		return new Rational(den, num);
	}

	public Rational divides(Rational b) {
		Rational a = this;
		return a.times(b.reciprocal());
	}
}

public class ProblemD {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int cases = Integer.parseInt(br.readLine());

		for (int index = 1; index <= cases; index++) {
			String inp[] = br.readLine().split(" ");
			int n = Integer.parseInt(inp[0]);
			int m = Integer.parseInt(inp[1]);
			List<Point2D> coOrdinates = new ArrayList<>();
			
			
			
			for (int i = 0; i < n; i++) {
				String[] cord = br.readLine().split(" ");
				coOrdinates.add(new Point2D.Double
						(Integer.parseInt(cord[0]),
								Integer.parseInt(cord[1])));
			}
			
			
			int length = coOrdinates.size();

			Double polygonArea = polygoinArea(coOrdinates, length);
			Long inputArea = polygonArea.longValue();
			if (inputArea < 0) {
				inputArea = (-1) * inputArea;
			}
			Double sysNr = 0.0;
			Double areaTriangle;
			for (int i = 0; i < n; i++) {
				Point2D point1, point2, point3;
				if (i == 0) {
					point1 = new Point2D.Double(coOrdinates.get(i).getX(), coOrdinates.get(i).getY());
					point2 = new Point2D.Double(coOrdinates.get(i + 1).getX(), coOrdinates.get(i + 1).getY());
					point3 = new Point2D.Double(coOrdinates.get(n - 1).getX(), coOrdinates.get(n - 1).getY());
				} else if (i == n - 1) {
					point1 = new Point2D.Double(coOrdinates.get(i).getX(), coOrdinates.get(i).getY());
					point2 = new Point2D.Double(coOrdinates.get(0).getX(), coOrdinates.get(0).getY());
					point3 = new Point2D.Double(coOrdinates.get(i - 1).getX(), coOrdinates.get(i - 1).getY());
				} else {
					point1 = new Point2D.Double(coOrdinates.get(i).getX(), coOrdinates.get(i).getY());
					point2 = new Point2D.Double(coOrdinates.get(i + 1).getX(), coOrdinates.get(i + 1).getY());
					point3 = new Point2D.Double(coOrdinates.get(i - 1).getX(), coOrdinates.get(i - 1).getY());
				}
				List<Point2D> crds = new ArrayList<>();
				crds.add(point1);
				crds.add(point2);
				crds.add(point3);
				areaTriangle = polygoinArea(crds, 3);
				if (areaTriangle < 0) {
					areaTriangle = (-1) * areaTriangle;
				}
				sysNr = sysNr + areaTriangle;
			}
			Rational rational = new Rational(sysNr.intValue(), m * m * inputArea);
			System.out.println("Case #" + index + ": " + rational.numerator() + "/" + rational.denominator());
			br.readLine();
			

		}
	}

	private static Double polygoinArea(List<Point2D> cords, int len) {
		Double area = 0.0D;
		for (int i = 0; i < len; i++) {
			Point2D curr = cords.get(i);
			Point2D next = cords.get((i + 1) % len);
			area += curr.getX() * next.getY() - curr.getY() * next.getX();
		}
		return area;
	}
}
