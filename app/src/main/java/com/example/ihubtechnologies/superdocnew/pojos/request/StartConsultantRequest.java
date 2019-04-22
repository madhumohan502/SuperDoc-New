package com.example.ihubtechnologies.superdocnew.pojos.request;

public class StartConsultantRequest {

    /**
     * appId : 120
     * startConsultant : 1
     */

    private int appId;

    public StartConsultantRequest(int appId) {
        this.appId = appId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "StartConsultantRequest{" +
                "appId=" + appId +
                '}';
    }
}
