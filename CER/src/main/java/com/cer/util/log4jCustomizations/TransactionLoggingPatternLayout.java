package com.cer.util.log4jCustomizations;

import org.apache.log4j.PatternLayout;

public class TransactionLoggingPatternLayout extends PatternLayout{

	private String header;  
	  
    public void setHeader(String header){  
        this.header = header;  
    }
  
    public String getHeader(){          
        return this.header+System.getProperty("line.separator");  
    }
}
