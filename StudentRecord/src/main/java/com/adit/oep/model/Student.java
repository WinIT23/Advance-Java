/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adit.oep.model;

import java.util.List;

/**
 *
 * @author Win_It
 */
public class Student {

    private String sName;
    private long sEnNumber;
    private int sBranchCode;
    private int sEntryYear;

    private List<Assignment> assignmentDone;
    private List<Assignment> assignmentRemain;

    public Student(String sName, long sEnNumber) {
        this.sName = sName;
        this.sEnNumber = sEnNumber;

        processEnrollmentNumber();
    }

    private void processEnrollmentNumber() {
        long temp = this.sEnNumber;
        temp /= 1000;
        this.sBranchCode = (int) (temp % 100);
        temp /= 10000000;

        this.sEntryYear = 2000 + (int) temp;
        
        
    }
}
