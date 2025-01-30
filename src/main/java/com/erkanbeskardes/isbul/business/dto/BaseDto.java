package com.erkanbeskardes.isbul.business.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter


public abstract class BaseDto implements Serializable {


    protected Long id;


    protected Date systemCreatedDate;

    protected String systemCreatedBy;
} //end BaseDto