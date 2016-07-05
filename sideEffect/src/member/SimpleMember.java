// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleMember.java

package member;

import account.Account;
import java.io.PrintStream;
import java.util.Date;

// Referenced classes of package member:
//            Member, MemberPrivilege

public class SimpleMember implements Member {

	public SimpleMember() {
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Date getFirstCreated() {
		return firstCreated;
	}

	public void setFirstCreated(Date firstCreated) {
		this.firstCreated = firstCreated;
	}

	public long getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(long privilegeId) {
		this.privilegeId = privilegeId;
	}

	public MemberPrivilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(MemberPrivilege privilege) {
		this.privilege = privilege;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getWholeEmail() {
		return wholeEmail;
	}

	public void setWholeEmail(String wholeEmail) {
		this.wholeEmail = wholeEmail;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public String getEmailUserName() {
		return emailUserName;
	}

	public void setEmailUserName(String emailUserName) {
		this.emailUserName = emailUserName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getPropertyValue() {
		return null;
	}

	public void setPropertyValue(String s) {
	}

	public boolean validate() {

		try {

			boolean hasAccountName = getAccountName() != null && !"".equals(getAccountName()) ? true : false;
			/*boolean hasEmailUserName = getEmailUserName()!=null&&!"".equals(getEmailUserName())?true:false;
			boolean hasEmailDomain = getEmailDomain()!=null&&!"".equals(getEmailDomain())?true:false;
			boolean hasOrg = getOrganisation()!=null&&!"".equals(getOrganisation())?true:false;*/
			boolean hasMemberPassword = getMemberPassword() != null && !"".equals(getMemberPassword()) ? true : false;

			if (hasAccountName
					/*&& hasEmailUserName
					&& hasEmailDomain
					&& hasOrg*/
					&& hasMemberPassword) {

			} else {

				System.out.println("hasAccountName:" + hasAccountName);
				/*System.out.println("hasEmailUserName:" +hasEmailUserName );
				System.out.println("hasEmailDomain:" + hasEmailDomain);
				System.out.println("hasOrg:" + hasOrg);*/
				System.out.println("hasMemberPassword:" + hasMemberPassword);

				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	private long id;
	private String accountName;
	private String name;
	private Account account;
	private String organisation;
	private String manager;
	private String wholeEmail;
	private String emailDomain;
	private String emailUserName;
	private String phoneNumber;
	private MemberPrivilege privilege;
	private long privilegeId;
	private Date lastModified;
	private Date firstCreated;
	private String memberPassword;
}
