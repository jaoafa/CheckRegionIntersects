package com.jaoafa.CheckRegionIntersects;

import java.awt.Polygon;
import java.awt.geom.Area;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("warning: There are not enough arguments.");
			System.out.println("CheckRegionIntersects.exe <Region1Json> <Region2Json>");
			System.exit(-1);
			return;
		}
		if (!isJSON(args[0])) {
			System.out.println("warning: Region1 is not JSON.");
			System.out.println("CheckRegionIntersects.exe <Region1Json> <Region2Json>");
			System.exit(-1);
			return;
		}
		if (!isJSON(args[1])) {
			System.out.println("warning: Region2 is not JSON.");
			System.out.println("CheckRegionIntersects.exe <Region1Json> <Region2Json>");
			System.exit(-1);
			return;
		}

		JSONArray json1 = new JSONArray(args[0]);
		Polygon region1 = getPolygonFromJSON(json1);

		JSONArray json2 = new JSONArray(args[1]);
		if (json2.length() == 1) {
			JSONObject obj = json2.getJSONObject(0);
			if (!obj.has("x")) {
				System.out.println("warning: \"x\" not found in part of RegionJson.");
				System.out.println("CheckRegionIntersects.exe <Region1Json> <Region2Json>");
				System.exit(-1);
			}
			if (!obj.has("z")) {
				System.out.println("warning: \"z\" not found in part of RegionJson.");
				System.out.println("CheckRegionIntersects.exe <Region1Json> <Region2Json>");
				System.exit(-1);
			}

			int x = obj.optInt("x", Integer.MIN_VALUE);
			int z = obj.optInt("z", Integer.MIN_VALUE);

			if (x == Integer.MIN_VALUE) {
				System.out.println("warning: \"x\" is not integer.");
				System.out.println("CheckRegionIntersects.exe <Region1Json> <Region2Json>");
				System.exit(-1);
				return;
			}
			if (z == Integer.MIN_VALUE) {
				System.out.println("warning: \"z\" is not integer.");
				System.out.println("CheckRegionIntersects.exe <Region1Json> <Region2Json>");
				System.exit(-1);
				return;
			}

			if (region1.contains(x, z)) {
				System.out.println("region1 contains point2(region2).");
				System.exit(1);
				return;
			}

			System.out.println("region1 not contains point2(region2).");
			System.exit(0);
			return;
		}
		Polygon region2 = getPolygonFromJSON(json2);

		boolean ret = false;
		/*
		if (region1.intersects(region2.getBounds2D())) {
			System.out.println("region1 intersects region2.");
			ret = true;
		}
		
		if (region1.contains(region2.getBounds2D())) {
			System.out.println("region1 contains region2.");
			ret = true;
		}
		
		if (region2.intersects(region1.getBounds2D())) {
			System.out.println("region2 intersects region1.");
			ret = true;
		}
		
		if (region2.contains(region1.getBounds2D())) {
			System.out.println("region2 contains region1.");
			ret = true;
		}
		*/

		Area area1 = new Area(region1);
		Area area2 = new Area(region2);

		area1.intersect(area2);
		if (!area1.isEmpty()) {
			System.out.println("area1 intersects area2");
			ret = true;
		}

		if (ret) {
			System.exit(1);
			return;
		}

		System.out.println("region1 not intersects and not contains region2.");
		System.exit(0);
		return;
	}

	static boolean isJSON(String json) {
		try {
			new JSONArray(json);
			return true;
		} catch (JSONException e) {
			return false;
		}
	}

	static Polygon getPolygonFromJSON(JSONArray region) {
		Polygon polygon = new Polygon();
		for (int i = 0; i < region.length(); i++) {
			JSONObject obj = region.getJSONObject(i);
			if (!obj.has("x")) {
				System.out.println("warning: \"x\" not found in part of RegionJson.");
				System.out.println("CheckRegionIntersects.exe <Region1Json> <Region2Json>");
				System.exit(-1);
				return null;
			}
			if (!obj.has("z")) {
				System.out.println("warning: \"z\" not found in part of RegionJson.");
				System.out.println("CheckRegionIntersects.exe <Region1Json> <Region2Json>");
				System.exit(-1);
				return null;
			}

			int x = obj.optInt("x", Integer.MIN_VALUE);
			int z = obj.optInt("z", Integer.MIN_VALUE);

			if (x == Integer.MIN_VALUE) {
				System.out.println("warning: \"x\" is not integer.");
				System.out.println("CheckRegionIntersects.exe <Region1Json> <Region2Json>");
				System.exit(-1);
				return null;
			}
			if (z == Integer.MIN_VALUE) {
				System.out.println("warning: \"z\" is not integer.");
				System.out.println("CheckRegionIntersects.exe <Region1Json> <Region2Json>");
				System.exit(-1);
				return null;
			}

			polygon.addPoint(x, z);
		}
		return polygon;
	}
}
