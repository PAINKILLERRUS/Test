package org.example.address.model;

import java.util.List;

public class WorkingSchedule {
    private Boolean openNow;
    private String timeZoneId;
    private List<WorkingHour> workingHours;
    private String workingHoursNotes;

    public Boolean getOpenNow() {
        return openNow;
    }

    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public List<WorkingHour> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<WorkingHour> workingHours) {
        this.workingHours = workingHours;
    }

    public String getWorkingHoursNotes() {
        return workingHoursNotes;
    }

    public void setWorkingHoursNotes(String workingHoursNotes) {
        this.workingHoursNotes = workingHoursNotes;
    }
}
