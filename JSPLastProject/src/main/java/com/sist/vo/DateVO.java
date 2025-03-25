package com.sist.vo;

import java.util.Date;

import lombok.Data;

/*
 * RNO  NOT NULL NUMBER        
	RDAY          NUMBER        
	TIME NOT NULL VARCHAR2(200) 

 */
@Data
public class DateVO {
	
	
	private int rno,fno;
	private String id,day,time,inwon,isok;
	private Date regdate;
}
