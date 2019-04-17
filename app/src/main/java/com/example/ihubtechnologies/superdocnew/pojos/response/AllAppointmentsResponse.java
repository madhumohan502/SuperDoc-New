package com.example.ihubtechnologies.superdocnew.pojos.response;

public class AllAppointmentsResponse {

    /**
     * apptID : 120
     * patientId : 1_1@UMR19000338
     * patientName : ANAND  RAO
     * bookeddate : 08/04/2019
     * apptTime : 09:00
     * apptStatus : Confirmed
     */

    private int apptID;
    private String patientId;
    private String patientName;
    private String bookeddate;
    private String apptTime;
    private String apptStatus;

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

    @Override
    public String toString() {
        return "AllAppointmentsResponse{" +
                "apptID=" + apptID +
                ", patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", bookeddate='" + bookeddate + '\'' +
                ", apptTime='" + apptTime + '\'' +
                ", apptStatus='" + apptStatus + '\'' +
                '}';
    }
}
