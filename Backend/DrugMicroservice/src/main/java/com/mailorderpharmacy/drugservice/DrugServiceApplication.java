package com.mailorderpharmacy.drugservice;

import org.springframework.boot.SpringApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class DrugServiceApplication
{
    public static void main(final String[] args) {
        SpringApplication.run(DrugServiceApplication.class, args);
    }
}