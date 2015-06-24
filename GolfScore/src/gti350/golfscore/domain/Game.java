package gti350.golfscore.domain;

import gti350.golfscore.data.CourseData;
import gti350.golfscore.utils.BetterDateFormat;

import java.util.Date;

/**
 * A golf game.
 * Contains the course it is played on, the players that
 * played and the date it started.
 * 
 * @author Simon RG
 */
public class Game {

	private int id;
	private Date date;
	private int courseId;
	private int currentPage;
	
	/**
	 * Fully dependency-injected constructor.
	 */
	public Game(int id, Date date, int courseId, int currentPage) {
		this.id = id;
		this.date = date;
		this.courseId = courseId;
		this.currentPage = currentPage;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	@Override
	public String toString() {
		BetterDateFormat bdf = new BetterDateFormat(date);
		return CourseData.get(courseId).toString() + "\n" + bdf.format();
	}
	
}
