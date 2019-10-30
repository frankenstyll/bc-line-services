package com.ktb.leadandsales.trello.api.model;

import lombok.Data;

@Data
public class SprintDashboardDto {
    private String taskListId;
    private String taskListName;
    private Integer taskListPosition;
    private Integer taskListSummary;
}
