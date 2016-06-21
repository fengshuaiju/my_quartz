package com.feng.util;

import org.springframework.stereotype.Service;

@Service  
public class Job {  
      
    private String SmileName;
    private String goBus;
    
    
	public Job() {
	}
	public Job(String smileName, String goBus) {
		SmileName = smileName;
		this.goBus = goBus;
	}
	public String getSmileName() {
		return SmileName;
	}
	public void setSmileName(String smileName) {
		SmileName = smileName;
	}
	public String getGoBus() {
		return goBus;
	}
	public void setGoBus(String goBus) {
		this.goBus = goBus;
	}
    
}  
