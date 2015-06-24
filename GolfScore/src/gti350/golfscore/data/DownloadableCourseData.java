package gti350.golfscore.data;

import gti350.golfscore.domain.Course;

import java.util.ArrayList;

public class DownloadableCourseData {

	/**
	 * Courses that show up in the Course List Activity.
	 */
	private static ArrayList<Course> courses = new ArrayList<Course>();
	private static int nextId;
	
	public static Course get(int id) {
		for (Course c: courses) {
			if (c.getId() == id) {
				return c;
			}
		}
		
		return null;
	}
	
	public static ArrayList<Course> getAll() {
		return new ArrayList<Course>(courses);
	}
	
	public static Course add(String name, int drawableId) {
		Course course = new Course(nextId, name, new int[] {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}, drawableId);
		courses.add(course);
		nextId++;
		return course;
	}
	
	public static void delete(int id) {
		int indexToDelete = -1;
		
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getId() == id) {
				indexToDelete = i;
				break;
			}
		}
		
		if (indexToDelete != -1) {
			courses.remove(indexToDelete);
		}
	}
	
	public static void update(Course course) {
		if (courses.size() == 0) return;
		
		Course real = null;
		
		for (Course c: courses) {
			if (c.getId() == course.getId()) {
				real = c;
				break;
			}
		}
		
		if (real != null) {
			real.setName(course.getName());
			real.setPar(1, course.getPar(1));
			real.setPar(2, course.getPar(2));
			real.setPar(3, course.getPar(3));
			real.setPar(4, course.getPar(4));
			real.setPar(5, course.getPar(5));
			real.setPar(6, course.getPar(6));
			real.setPar(7, course.getPar(7));
			real.setPar(8, course.getPar(8));
			real.setPar(9, course.getPar(9));
			real.setPar(10, course.getPar(10));
			real.setPar(11, course.getPar(11));
			real.setPar(12, course.getPar(12));
			real.setPar(13, course.getPar(13));
			real.setPar(14, course.getPar(14));
			real.setPar(15, course.getPar(15));
			real.setPar(16, course.getPar(16));
			real.setPar(17, course.getPar(17));
			real.setPar(18, course.getPar(18));
			real.setDrawableId(course.getDrawableId());
		}
	}
	
}
