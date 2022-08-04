package com.mailorderpharmacy.drugservice.entity;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse
{
    String messge;
    LocalDateTime timestamp;
    HttpStatus status;
}