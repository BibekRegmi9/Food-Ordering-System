package com.bibek.Food.Ordering.response;

import com.bibek.Food.Ordering.enums.ResponseStatus;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalApiResponse implements Serializable {
    private String message;
    private Object data;
    private ResponseStatus status;
}

