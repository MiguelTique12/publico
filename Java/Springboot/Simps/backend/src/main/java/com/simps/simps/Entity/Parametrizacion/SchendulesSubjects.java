package com.simps.simps.Entity.Parametrizacion;



import com.simps.simps.Entity.BaseModel.BaseModel;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "schendules_subjects")
public class SchendulesSubjects extends BaseModel{

	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subjects subjectId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "schendule_id", nullable = false)
    private Schendules schenduleId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "curse_id", nullable = false)
    private Curses curseId;


	/**
	 * @return the subjectId
	 */
	public Subjects getSubjectId() {
		return subjectId;
	}


	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(Subjects subjectId) {
		this.subjectId = subjectId;
	}


	/**
	 * @return the schenduleId
	 */
	public Schendules getSchenduleId() {
		return schenduleId;
	}


	/**
	 * @param schenduleId the schenduleId to set
	 */
	public void setSchenduleId(Schendules schenduleId) {
		this.schenduleId = schenduleId;
	}


	/**
	 * @return the curseId
	 */
	public Curses getCurseId() {
		return curseId;
	}


	/**
	 * @param curseId the curseId to set
	 */
	public void setCurseId(Curses curseId) {
		this.curseId = curseId;
	}
	
	
}
