/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adit.oep.model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Win_It
 */
public class Assignment {

    public static final String SPLIT_REGEX = " ";
    public String aName;
    private final Calendar aDate;
    private Calendar sDate;
    private boolean aStatus;
    private Subject aSubject;

    public Assignment(String aName, Subject aSubject, String aDate) {
        this.aName = aName;
        this.aStatus = false;
        this.aSubject = aSubject;

        this.aDate = Calendar.getInstance();

        String[] date = aDate.trim().split(Assignment.SPLIT_REGEX);
        if (date.length == 3) 
            this.aDate.set(Integer.valueOf(date[2]), Integer.valueOf(date[1]), Integer.valueOf(date[0]));
    }
    
    public Assignment(String aName, Subject aSubject, String aDate, String sDate) {
        this.aName = aName;
        this.aStatus = false;
        this.aSubject = aSubject;

        this.aDate = Calendar.getInstance();

        String[] date = aDate.trim().split(Assignment.SPLIT_REGEX);
        if (date.length == 3) 
            this.aDate.set(Integer.valueOf(date[2]), Integer.valueOf(date[1]), Integer.valueOf(date[0]));
        
        this.sDate = Calendar.getInstance();
        
        date = sDate.trim().split(Assignment.SPLIT_REGEX);
        if (date.length == 3) 
            this.sDate.set(Integer.valueOf(date[2]), Integer.valueOf(date[1]), Integer.valueOf(date[0]));
        
    }

    public long getDays() {
        Date adate = this.aDate.getTime();
        Date sdate = this.sDate.getTime();
        
        long diff = sdate.getTime() - adate.getTime();
        
        return diff / 1000 / 60 / 60 / 24;
    }

    public void assignmentDone() {
        this.aStatus = true;
    }
    
    public boolean isComplete() {
        return aStatus;
    }
}
