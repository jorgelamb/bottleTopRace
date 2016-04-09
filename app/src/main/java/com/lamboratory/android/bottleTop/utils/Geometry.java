package com.lamboratory.android.bottleTop.utils;

public class Geometry {

	public static final double THRESHOLD = 0.0000001;
	
	public static boolean isIntersect(float ax, float ay, float bx, float by, float cx, float cy, float dx, float dy) {
		double A1 = by - ay;
		double B1 = ax - bx;
		double C1 = A1 * ax + B1 * ay;

		double A2 = dy - cy;
		double B2 = cx - dx;
		double C2 = A2 * cx + B2 * cy;

		double det = (A1 * B2) - (A2 * B1);

		if (Math.abs(det) < THRESHOLD) {
			if ((A1 * cx) + (B1 * cy) == C1) {
				if ((Math.min(ax, bx) < cx) && (Math.max(ax, bx) > cx))
					return true;
				if ((Math.min(ax, bx) < cx) && (Math.max(ax, bx) > cx))
					return true;
				return false;
			}
			return false;
		} else {
			double x = (B2 * C1 - B1 * C2) / det;
			double y = (A1 * C2 - A2 * C1) / det;
			if ((x >= Math.min(ax, bx) && x <= Math.max(ax, bx)) && (y >= Math.min(ay, by) && y <= Math.max(ay, by))) {
				if ((x >= Math.min(cx, dx) && x <= Math.max(cx, dx)) && (y >= Math.min(cy, dy) && y <= Math.max(cy, dy))) {
					return true;
				}
			}
			return false;
		}
	}
}
