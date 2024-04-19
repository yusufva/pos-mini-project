package com.sigmaka.miniprojectpos.helper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GlobalHttpResponse<T> {
    private int statusCode;
    private String message;
    private T data;
}

