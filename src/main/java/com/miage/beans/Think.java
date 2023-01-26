package com.miage.beans;

import lombok.Data;

@Data
public abstract class Think {
    private String type;
    private Position position;
}
