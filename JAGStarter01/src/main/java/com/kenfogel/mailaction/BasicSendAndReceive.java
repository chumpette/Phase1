package com.kenfogel.mailaction;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Flags;

import com.kenfogel.mailbean.MailBean;
import com.kenfogel.properties.MailConfigBean;

import jodd.mail.Email;
import jodd.mail.EmailFilter;
import jodd.mail.EmailMessage;
import jodd.mail.ImapServer;
import jodd.mail.ImapSslServer;
import jodd.mail.MailAddress;
import jodd.mail.ReceiveMailSession;
import jodd.mail.ReceivedEmail;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;
import jodd.mail.SmtpSslServer;

/**
 * Here is a starter class for phase 1. It sends and receives basic email that
 * consists of a to, from, subject and text.
 * 
 * @author Ken Fogel
 *
 */
public class BasicSendAndReceive {

	/**
	 * Basic send routine for this sample. It does not handle CC, BCC,
	 * attachments or other information.
	 */
	public final String sendEmail(final MailBean mailBean, final MailConfigBean sendConfigBean) {

		// Create am SMTP server object
		SmtpServer<?> smtpServer = SmtpSslServer.create(sendConfigBean.getHost())
				.authenticateWith(sendConfigBean.getUserEmailAddress(), sendConfigBean.getPassword());

		// Do not use the fluent type because ArrayLists need to be processed
		// from the bean
		Email email = Email.create();

		email.from(sendConfigBean.getUserEmailAddress());

		for (String emailAddress : mailBean.getToField()) {
			email.to(emailAddress);
		}

		email.subject(mailBean.getSubjectField());
		email.addText(mailBean.getTextMessageField());

		// A session is the object responsible for communicating with the server
		SendMailSession session = smtpServer.createSession();

		// Like a file we open the session, send the message and close the
		// session
		session.open();
		String messageId = session.sendMail(email);
		session.close();

		return messageId;

	}

	/**
	 * Basic receive that only takes the values that match the basic mail bean.
	 * Returns an array list because there could be more than one message. This
	 * could be a problem during testing so use addresses that do not receive
	 * any other messages and you can assume that subscript 0 in the array is
	 * the message you just sent.
	 */
	public final ArrayList<MailBean> receiveEmail(final MailConfigBean receiveConfigBean) {

		ArrayList<MailBean> mailBeans = null;

		// Create an IMAP server that does not display debug info
		ImapServer imapServer = new ImapSslServer(receiveConfigBean.getHost(), receiveConfigBean.getUserEmailAddress(),
				receiveConfigBean.getPassword());

		// A session is the object responsible for communicating with the server
		ReceiveMailSession session = imapServer.createSession();
		session.open();

		// We only want messages that have not been read yet.
		// Messages that are delivered are then marked as read on the server
		ReceivedEmail[] emails = session.receiveEmailAndMarkSeen(EmailFilter.filter().flag(Flags.Flag.SEEN, false));

		// If there is any email then loop through them adding their contents to
		// a new MailBean that is then added to the array list.
		if (emails != null) {

			// Instantiate the array list of messages
			mailBeans = new ArrayList<MailBean>();

			for (ReceivedEmail email : emails) {

				MailBean mailBean = new MailBean();

				mailBean.setFromField(email.getFrom().getEmail());
				mailBean.setSubjectField(email.getSubject());
				for (MailAddress mailAddress : email.getTo()) {
					mailBean.getToField().add(mailAddress.getEmail());
				}
				// Messages may be multi-part so they are stored in an array
				// In this demo we only want the first part
				List<EmailMessage> messages = email.getAllMessages();
				mailBean.setTextMessageField(messages.get(0).getContent());

				// Add the mailBean to the array list
				mailBeans.add(mailBean);
			}
		}
		session.close();

		return mailBeans;
	}
}
