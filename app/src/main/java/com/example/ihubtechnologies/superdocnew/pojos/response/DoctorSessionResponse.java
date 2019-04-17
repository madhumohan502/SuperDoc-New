package com.example.ihubtechnologies.superdocnew.pojos.response;

public class DoctorSessionResponse {

    /**
     * doctorSessionId : 1
     * todayAppointmentsCount : 0
     * organizationName : Shubham Clinics
     */

    private int doctorSessionId;
    private int todayAppointmentsCount;
    private String organizationName;

    public int getDoctorSessionId() {
        return doctorSessionId;
    }

    public void setDoctorSessionId(int doctorSessionId) {
        this.doctorSessionId = doctorSessionId;
    }

    public int getTodayAppointmentsCount() {
        return todayAppointmentsCount;
    }

    public void setTodayAppointmentsCount(int todayAppointmentsCount) {
        this.todayAppointmentsCount = todayAppointmentsCount;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Override
    public String toString() {
        return "DoctorSessionResponse{" +
                "doctorSessionId=" + doctorSessionId +
                ", todayAppointmentsCount=" + todayAppointmentsCount +
                ", organizationName='" + organizationName + '\'' +
                '}';
    }
}
