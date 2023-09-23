package api.payload;

public class Assignment_Submit_Payload {
	private int assignmentId;
	private int grade;
	private String gradedBy;
	Assignment_Submit_Graded_DateTime GradedDateTime;
	private String subComments;
	//Assignment_Submit_DateTime SubDateTime;
	private String SubDateTime;
	private String subDesc;
	private String subPathAttach1;
	private String subPathAttach2;
	private String subPathAttach3;
	private String subPathAttach4;
	private String subPathAttach5;
	private int submissionId;
	private String userId;
	public int getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getGradedBy() {
		return gradedBy;
	}
	public void setGradedBy(String gradedBy) {
		this.gradedBy = gradedBy;
	}
	public Assignment_Submit_Graded_DateTime getGradedDateTime() {
		return GradedDateTime;
	}
	public void setGradedDateTime(Assignment_Submit_Graded_DateTime gradedDateTime) {
		GradedDateTime = gradedDateTime;
	}
	public String getSubComments() {
		return subComments;
	}
	public void setSubComments(String subComments) {
		this.subComments = subComments;
	}
	public String getSubDateTime() {
		return SubDateTime;
	}
	public void setSubDateTime(String subDateTime) {
		SubDateTime = subDateTime;
	}
	public String getSubDesc() {
		return subDesc;
	}
	public void setSubDesc(String subDesc) {
		this.subDesc = subDesc;
	}
	public String getSubPathAttach1() {
		return subPathAttach1;
	}
	public void setSubPathAttach1(String subPathAttach1) {
		this.subPathAttach1 = subPathAttach1;
	}
	public String getSubPathAttach2() {
		return subPathAttach2;
	}
	public void setSubPathAttach2(String subPathAttach2) {
		this.subPathAttach2 = subPathAttach2;
	}
	public String getSubPathAttach3() {
		return subPathAttach3;
	}
	public void setSubPathAttach3(String subPathAttach3) {
		this.subPathAttach3 = subPathAttach3;
	}
	public String getSubPathAttach4() {
		return subPathAttach4;
	}
	public void setSubPathAttach4(String subPathAttach4) {
		this.subPathAttach4 = subPathAttach4;
	}
	public String getSubPathAttach5() {
		return subPathAttach5;
	}
	public void setSubPathAttach5(String subPathAttach5) {
		this.subPathAttach5 = subPathAttach5;
	}
	public int getSubmissionId() {
		return submissionId;
	}
	public void setSubmissionId(int submissionId) {
		this.submissionId = submissionId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Assignment_Submit_Payload [assignmentId=" + assignmentId + ", grade=" + grade + ", gradedBy=" + gradedBy
				+ ", GradedDateTime=" + GradedDateTime + ", subComments=" + subComments + ", SubDateTime=" + SubDateTime
				+ ", subDesc=" + subDesc + ", subPathAttach1=" + subPathAttach1 + ", subPathAttach2=" + subPathAttach2
				+ ", subPathAttach3=" + subPathAttach3 + ", subPathAttach4=" + subPathAttach4 + ", subPathAttach5="
				+ subPathAttach5 + ", submissionId=" + submissionId + ", userId=" + userId + "]";
	}
	

	
}
