package com.mycustody.Model.TripSchedule;

public class TripSchedule {
    private String idTrip;
    private String jobTrip;
    private String timeTrip;
    private String progressTrip;
    private String logoTrip;

    public TripSchedule(String idTrip, String jobTrip, String timeTrip, String progressTrip, String logoTrip) {
        this.idTrip = idTrip;
        this.jobTrip = jobTrip;
        this.timeTrip = timeTrip;
        this.progressTrip = progressTrip;
        this.logoTrip = logoTrip;
    }

    public String getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(String idTrip) {
        this.idTrip = idTrip;
    }

    public String getJobTrip() {
        return jobTrip;
    }

    public void setJobTrip(String jobTrip) {
        this.jobTrip = jobTrip;
    }

    public String getTimeTrip() {
        return timeTrip;
    }

    public void setTimeTrip(String timeTrip) {
        this.timeTrip = timeTrip;
    }

    public String getProgressTrip() {
        return progressTrip;
    }

    public void setProgressTrip(String progressTrip) {
        this.progressTrip = progressTrip;
    }

    public String getLogoTrip() {
        return logoTrip;
    }

    public void setLogoTrip(String logoTrip) {
        this.logoTrip = logoTrip;
    }
}
