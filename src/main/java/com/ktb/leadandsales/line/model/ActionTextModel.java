package com.ktb.leadandsales.line.model;

import lombok.Data;

import java.util.List;

@Data
public class ActionTextModel {
    private String action;
    private String member;
    private String labelSprint;
    private List<String> labelBacklogItemType;
}
