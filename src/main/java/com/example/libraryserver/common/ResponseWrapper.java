package com.example.libraryserver.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapper<T> {
    private int code = 200;
    private String message = "Request Successful";
    private T data = null;
}
