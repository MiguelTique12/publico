package com.simps.simps.Entity.Parametrizacion;

import java.time.LocalTime;

import com.simps.simps.Entity.BaseModel.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "schendules")
public class Schendules extends BaseModel{

	public enum day{MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY}
	
	@Column(name= "start_time", nullable = false, length = 50)
	private LocalTime startTime;
	
	@Column(name= "end_time", nullable = false, length = 50)
	private LocalTime endTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name= "day", nullable = false, length = 50)
	private day day;

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public day getDay() {
		return day;
	}

	public void setDay(day day) {
		this.day = day;
	}



}
