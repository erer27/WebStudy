package com.sist.vo;
/*
 * RNO     NOT NULL NUMBER       
ID               VARCHAR2(20) 
FNO              NUMBER       
DAY              VARCHAR2(20) 
TIME             VARCHAR2(20) 
INWON            VARCHAR2(20) 
REGDATE          DATE         
ISOK             CHAR(1)      

 */
import java.util.*;

import lombok.Data;
@Data
public class TimeVO {
	
	
	private int tno;
	private String time;
}
