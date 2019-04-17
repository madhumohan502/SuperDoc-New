package com.example.ihubtechnologies.superdocnew.pojos.response;

import java.util.List;

public class OtpVerificationResponse {

    /**
     * doctorId : EMP002
     * doctorName : Dr. Venkateswara Reddy  R
     * doctorExperience : 6
     * doctorFee : null
     * designation : Internal Medicine
     * organization : Shubham Clinics
     * doctorSpeciality : ["General Physician"]
     * doctorDepartment : ["General Medicine"]
     * doctorstudies : MBBS., MD(Internal Medicine)
     * doctorProfileImg : null
     */

    private String doctorId;
    private String doctorName;
    private String doctorExperience;
    private Object doctorFee;
    private String designation;
    private String organization;
    private String doctorstudies;
    private Object doctorProfileImg;
    private List<String> doctorSpeciality;
    private List<String> doctorDepartment;

    public OtpVerificationResponse(String doctorId, String doctorName, String doctorExperience, Object doctorFee, String designation, String organization, String doctorstudies, Object doctorProfileImg, List<String> doctorSpeciality, List<String> doctorDepartment) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorExperience = doctorExperience;
        this.doctorFee = doctorFee;
        this.designation = designation;
        this.organization = organization;
        this.doctorstudies = doctorstudies;
        this.doctorProfileImg = doctorProfileImg;
        this.doctorSpeciality = doctorSpeciality;
        this.doctorDepartment = doctorDepartment;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorExperience() {
        return doctorExperience;
    }

    public void setDoctorExperience(String doctorExperience) {
        this.doctorExperience = doctorExperience;
    }

    public Object getDoctorFee() {
        return doctorFee;
    }

    public void setDoctorFee(Object doctorFee) {
        this.doctorFee = doctorFee;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDoctorstudies() {
        return doctorstudies;
    }

    public void setDoctorstudies(String doctorstudies) {
        this.doctorstudies = doctorstudies;
    }

    public Object getDoctorProfileImg() {
        return doctorProfileImg;
    }

    public void setDoctorProfileImg(Object doctorProfileImg) {
        this.doctorProfileImg = doctorProfileImg;
    }

    public List<String> getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(List<String> doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }

    public List<String> getDoctorDepartment() {
        return doctorDepartment;
    }

    public void setDoctorDepartment(List<String> doctorDepartment) {
        this.doctorDepartment = doctorDepartment;
    }
}
