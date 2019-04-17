package com.example.ihubtechnologies.superdocnew.pojos.request;

public class OtpVerificationRequest {

    /**
     * mobileNumber : 8309136351
     * otpNumber : 1233
     */

    private String mobileNumber;
    private String otpNumber;

    public OtpVerificationRequest(String mobileNumber, String otpNumber) {
        this.mobileNumber = mobileNumber;
        this.otpNumber = otpNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtpNumber() {
        return otpNumber;
    }

    public void setOtpNumber(String otpNumber) {
        this.otpNumber = otpNumber;
    }
}
