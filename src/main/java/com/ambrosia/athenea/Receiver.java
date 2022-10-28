package com.ambrosia.athenea;

import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        // Enrollment enrollment =
        String enrollment = this.getEnrollmentFromMessage(message);
        String[] enrollmentFields = this.getFieldsFromEnrollment(enrollment);
        String courseCode = enrollmentFields[0];
        String academicHistoryCode = enrollmentFields[1];
        String[] courseGroupsCodes = enrollmentFields[2].split(",");

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
