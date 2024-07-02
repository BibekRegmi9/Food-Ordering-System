package com.bibek.Food.Ordering.enums;

import com.bibek.Food.Ordering.config.CustomMessageSource;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ResponseStatus {
    FAILURE(0),
    SUCCESS(1);

    private final int index;
    private final CustomMessageSource customMessageSource = new CustomMessageSource();

    ResponseStatus(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        if (this.equals(ResponseStatus.SUCCESS)) {
            return dataRefactoring(customMessageSource.get("success"));
        } else {
            return dataRefactoring(customMessageSource.get("failure"));
        }
    }

    private String dataRefactoring(String value) {
        if (!"np".equalsIgnoreCase(customMessageSource.getCurrentLocale().getDisplayLanguage())) {
            return value.toUpperCase();
        }
        return value;
    }
}

