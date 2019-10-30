package com.ktb.leadandsales.trello.api.model;

import lombok.Data;

import java.util.List;

@Data
public class RequestSprintDashboard {
    private String taskGroupId;
    private String sprintName;
    private List<String> labelNames;
}
