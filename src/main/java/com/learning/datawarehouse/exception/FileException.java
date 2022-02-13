package com.learning.datawarehouse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class FileException extends RuntimeException implements Serializable {
    String message;
    String errorCode;
}
