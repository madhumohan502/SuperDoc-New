package com.example.ihubtechnologies.superdocnew.pojos.request;

public class StartConsultantRequest {

    /**
     * appId : 120
     * startConsultant : 1
     */

    private int appId;
    private int startConsultant;

    public StartConsultantRequest(int appId, int startConsultant) {
        this.appId = appId;
        this.startConsultant = startConsultant;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getStartConsultant() {
        return startConsultant;
    }

    public void setStartConsultant(int startConsultant) {
        this.startConsultant = startConsultant;
    }
}
