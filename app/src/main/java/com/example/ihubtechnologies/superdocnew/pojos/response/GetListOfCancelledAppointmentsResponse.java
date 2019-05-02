package com.example.ihubtechnologies.superdocnew.pojos.response;

public class GetListOfCancelledAppointmentsResponse {

    /**
     * apptID : 2
     * patientId : 2_2@JH1602850
     * patientName : P.MANI
     * bookeddate : 27/04/2019
     * apptTime : 14:00
     * apptStatus : Cancelled
     * startConsultation : true
     * checkedIn : false
     * closeConsultation : false
     */

    private int apptID;
    private String patientId;
    private String patientName;
    private String bookeddate;
    private String apptTime;
    private String apptStatus;
    private boolean startConsultation;
    private boolean checkedIn;
    private boolean closeConsultation;

    public int getApptID() {
        return apptID;
    }

    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getBookeddate() {
        return bookeddate;
    }

    public void setBookeddate(String bookeddate) {
        this.bookeddate = bookeddate;
    }

    public String getApptTime() {
        return apptTime;
    }

    public void setApptTime(String apptTime) {
        this.apptTime = apptTime;
    }

    public String getApptStatus() {
        return apptStatus;
    }

    public void setApptStatus(String apptStatus) {
        this.apptStatus = apptStatus;
    }

    public boolean isStartConsultation() {
        return startConsultation;
    }

    public void setStartConsultation(boolean startConsultation) {
        this.startConsultation = startConsultation;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public boolean isCloseConsultation() {
        return closeConsultation;
    }

    public void setCloseConsultation(boolean closeConsultation) {
        this.closeConsultation = closeConsultation;
    }

    @Override
    public String toString() {
        return "GetListOfCancelledAppointmentsResponse{" +
                "apptID=" + apptID +
                ", patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", bookeddate='" + bookeddate + '\'' +
                ", apptTime='" + apptTime + '\'' +
                ", apptStatus='" + apptStatus + '\'' +
                ", startConsultation=" + startConsultation +
                ", checkedIn=" + checkedIn +
                ", closeConsultation=" + closeConsultation +
                '}';
    }
}
