package com.kenfogel.properties;

/**
 * Class to contain the information for an email account. This is sufficient for
 * this project but will need more fields if you wish the program to work with
 * mail systems other than GMail. This should be stored in properties file. If
 * you are feeling adventurous you can look into how you might encrypt the
 * password as it will be in a simple text file.
 * 
 * @author Ken Fogel
 * 
 */
public class MailConfigBean {

	private String host;
	private String userEmailAddress;
	private String password;

	/**
	 * Default Constructor
	 */
	public MailConfigBean() {
		super();
		this.host = "";
		this.userEmailAddress = "";
		this.password = "";
	}

	/**
	 * @param host
	 * @param userName
	 * @param password
	 */
	public MailConfigBean(final String host, final String userEmailAddress, final String password) {
		super();
		this.host = host;
		this.userEmailAddress = userEmailAddress;
		this.password = password;
	}

	/**
	 * @return the host
	 */
	public final String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public final void setHost(final String host) {
		this.host = host;
	}

	/**
	 * @return the userName
	 */
	public final String getUserEmailAddress() {
		return userEmailAddress;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public final void setUserEmailAddress(final String userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public final void setPassword(final String password) {
		this.password = password;
	}
}