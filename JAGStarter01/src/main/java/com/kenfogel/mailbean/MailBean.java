/**
 * 
 */
package com.kenfogel.mailbean;

import java.util.ArrayList;

/**
 * Here is a basic email data bean. It will have to have more fields for the
 * project
 * 
 * @author Ken Fogel
 *
 */
public class MailBean {

	// The address or addresses that this email is being sent to
	private ArrayList<String> toField;

	// The sender of the email
	private String fromField;

	// The subject line of the email
	private String subjectField;

	// Plain text part of the email
	private String textMessageField;

	// Name of the folder
	private String folder;

	// Status 0 = New Email for Sending
	// Status 1 = Received Email
	private int mailStatus;

	/**
	 * Default constructor for a new mail message waiting to be sent
	 */
	public MailBean() {
		super();
		this.toField = new ArrayList<>();
		this.fromField = "";
		this.subjectField = "";
		this.textMessageField = "";
		this.folder = "";
		this.mailStatus = 0;
	}

	/**
	 * Constructor for creating messages from either a form or a database record
	 * 
	 * @param toField
	 * @param fromField
	 * @param subjectField
	 * @param textMessageField
	 * @param folder
	 * @param mailStatus
	 */
	public MailBean(final ArrayList<String> toField, final String fromField, final String subjectField,
			final String textMessageField, final String folder, final int mailStatus) {
		super();
		this.toField = toField;
		this.fromField = fromField;
		this.subjectField = subjectField;
		this.textMessageField = textMessageField;
		this.folder = folder;
		this.mailStatus = mailStatus;
	}

	/**
	 * @return the fromField
	 */
	public final String getFromField() {
		return fromField;
	}

	/**
	 * When passing a reference to a setter it is best practice to declare it
	 * final so that the setter cannot change the reference
	 * 
	 * @param fromField
	 *            the fromField to set
	 */
	public final void setFromField(final String fromField) {
		this.fromField = fromField;
	}

	/**
	 * @return the subjectField
	 */
	public final String getSubjectField() {
		return subjectField;
	}

	/**
	 * @param subjectField
	 *            the subjectField to set
	 */
	public final void setSubjectField(final String subjectField) {
		this.subjectField = subjectField;
	}

	/**
	 * @return the textMessageField
	 */
	public final String getTextMessageField() {
		return textMessageField;
	}

	/**
	 * @param textMessageField
	 *            the textMessageField to set
	 */
	public final void setTextMessageField(final String textMessageField) {
		this.textMessageField = textMessageField;
	}

	/**
	 * @return the folder
	 */
	public final String getFolder() {
		return folder;
	}

	/**
	 * @param folder
	 *            the folder to set
	 */
	public final void setFolder(final String folder) {
		this.folder = folder;
	}

	/**
	 * @return the mailStatus
	 */
	public final int getMailStatus() {
		return mailStatus;
	}

	/**
	 * @param mailStatus
	 *            the mailStatus to set
	 */
	public final void setMailStatus(final int mailStatus) {
		this.mailStatus = mailStatus;
	}

	/**
	 * There is no set when working with collections. When you get the ArrayList
	 * you can add elements to it. A set method implies changing the current
	 * ArrayList for another ArrayList and this is something we rarely do with
	 * collections.
	 * 
	 * @return the toField
	 */
	public final ArrayList<String> getToField() {
		return toField;
	}

}
