package com.ambrosia.athenea;

import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

import com.ambrosia.athenea.controllers.EnrollmentController;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Receiver {

    @Autowired
    EnrollmentController enrollmentController;

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println(message);
        String enrollment = this.getEnrollmentFromMessage(message);
        String[] enrollmentFields = this.getFieldsFromEnrollment(enrollment);
        String studentCode = enrollmentFields[0];
        String academicHistoryCode = enrollmentFields[1];
        String[] courseGroupsCodes = enrollmentFields[2].split(",");

        enrollmentController.createEnrollment(studentCode, academicHistoryCode, courseGroupsCodes);

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    private String getEnrollmentFromMessage(String message) {
        message = message.substring(message.indexOf("{") + 1);
        message = message.substring(0, message.indexOf("}"));
        return message;
    }

    private String[] getFieldsFromEnrollment(String enrollment) {
        String[] fields = enrollment.split("#");
        return fields;
    }

}
