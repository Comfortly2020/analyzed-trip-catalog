package com.comfortly.analyzedtripcatalog.lib;

import java.time.Instant;

public class SummaryAnalyzedTripData {
    private Integer id;
    private String userId;
    private String startLocationName;
    private String endLocationName;
    private Instant startTime;
    private Instant endTime;
    private Double distance;
    private Double comfortLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartLocationName() {
        return startLocationName;
    }

    public void setStartLocationName(String startLocationName) {
        this.startLocationName = startLocationName;
    }

    public String getEndLocationName() {
        return endLocationName;
    }

    public void setEndLocationName(String endLocationName) {
        this.endLocationName = endLocationName;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getComfortLevel() {
        return comfortLevel;
    }

    public void setComfortLevel(Double comfortLevel) {
        this.comfortLevel = comfortLevel;
    }
}
