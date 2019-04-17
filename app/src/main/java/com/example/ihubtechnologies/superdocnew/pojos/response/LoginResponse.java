package com.example.ihubtechnologies.superdocnew.pojos.response;

public class LoginResponse {

    /**
     * Msg : Otp Send Successfully
     */

    private String Msg;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "Msg='" + Msg + '\'' +
                '}';
    }
}
