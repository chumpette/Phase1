package com.kenfogel.test.mailaction;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kenfogel.mailaction.BasicSendAndReceive;
import com.kenfogel.mailbean.MailBean;
import com.kenfogel.properties.mailbean.MailConfigBean;
import com.kenfogel.test.MethodLogger;

/**
 * A basic test method that determines if a simple message sent is the same
 * message received
 * 
 * @author Ken Fogel
 *
 */
public class MailActionTest {

	// A Rule is implemented as a class with methods that are associated
	// with the lifecycle of a unit test. These methods run when required.
	// Avoids the need to cut and paste code into every test method.
	@Rule
	public MethodLogger methodLogger = new MethodLogger();

	// Real programmers use logging, not System.out.println
	private final Logger log = LoggerFactory.getLogger(getClass().getName());

	/**
	 * Test method for
	 * {@link com.kenfogel.mailaction.BasicSendAndReceive#sendEmail(com.kenfogel.mailbean.MailBean, com.kenfogel.properties.mailbean.MailConfigBean)}
	 * .
	 *
	 * In this test a message is created, sent, received and compared.
	 */
	@Test
	public void testSendEmail() {
		MailConfigBean sendConfigBean = new MailConfigBean("smtp.gmail.com", "cst.send@gmail.com", "frog$$peace");
		MailConfigBean receiveConfigBean = new MailConfigBean("imap.gmail.com", "cst.receive@gmail.com", "frog$$peace");
		BasicSendAndReceive basicSendAndReceive = new BasicSendAndReceive();

		MailBean mailBeanSend = new MailBean();
		mailBeanSend.getToField().add("cst.receive@gmail.com");
		mailBeanSend.setFromField(sendConfigBean.getUserEmailAddress());
		mailBeanSend.setSubjectField("A Test Message - 03");
		mailBeanSend.setTextMessageField("This is the text of the message - 03.");
		String messageId = basicSendAndReceive.sendEmail(mailBeanSend, sendConfigBean);
		log.info("MessageId is " + messageId);

		ArrayList<MailBean> mailBeansReceive = basicSendAndReceive.receiveEmail(receiveConfigBean);

		boolean test = false;

		// An optional block of log statements so that you can see the message
		log.info("     To: " + mailBeanSend.getToField().get(0) + " : " + mailBeansReceive.get(0).getToField().get(0));
		log.info("   From: " + mailBeanSend.getFromField() + " : " + mailBeansReceive.get(0).getFromField());
		log.info("Subject: " + mailBeanSend.getSubjectField() + " : " + mailBeansReceive.get(0).getSubjectField());
		log.info("   Text: " + mailBeanSend.getTextMessageField() + "=" + mailBeanSend.getTextMessageField().length()
				+ " : " + mailBeansReceive.get(0).getTextMessageField() + "="
				+ mailBeansReceive.get(0).getTextMessageField().length());

		// A better test approach is to have a custom equals and hashCode method
		// in the MailBean. This way you could just write:
		// assertEquals(mailSendbean, mailReceiveBean);
		// I leave this for you to implement. Here is the crude implementation.
		if (mailBeanSend.getToField().get(0).trim().equals(mailBeansReceive.get(0).getToField().get(0).trim())) {
			if (mailBeanSend.getFromField().trim().equals(mailBeansReceive.get(0).getFromField().trim())) {
				if (mailBeanSend.getSubjectField().trim().equals(mailBeansReceive.get(0).getSubjectField().trim())) {
					if (mailBeanSend.getTextMessageField().trim()
							.equals(mailBeansReceive.get(0).getTextMessageField().trim())) {
						test = true;
					}
				}
			}
		}

		// It would be better if we were informed which fields did not match.
		// Another feature left to you.
		assertTrue("Messages are not the same", test);
	}
}
