package test.pavelyurtaev.compensation.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendEmail(final Long employeeId, final String text) {
        // send email to Employee
        logger.info("Sent email " + text + " to employee id " + employeeId);
    }

}
