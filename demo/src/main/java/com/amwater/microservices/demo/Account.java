package com.amwater.microservices.demo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

import com.amwater.microservices.demo.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "response")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	private AccountId accountId;
	private String customerNumber;
	private String customerType;
	private String premiseNumber;
	private String companyCode;
	private String firstName;
	private String middleName;
	private String lastName;
	private String organizationName;
	private String careOfName;
	private String phoneNumber;
	private String phoneNumberExt;
	private String cellPhoneNumber;
	private String workPhoneNumber;
	private String workPhoneNumberExt;
	private String accountStatus;
	private String accountType;
	private String accountClassDescription;
	private Calendar accountStartDate;
	private Calendar accountClosedDate;
	private Calendar lastBilledDate;
	private boolean budgetBilling;
	private BigDecimal budgetBillAmount;
	private boolean cutInLandlord;
	private boolean cashOnly;
	private boolean paperlessBillingEnrolled;
	private boolean paperlessBillingEmailAddressInValid;
	private String paperlessBillingEmailAddress;
	private boolean redirectToContractBillingVendorSite;
	private String employerIdNumber;
	private boolean parentAccount;
	private boolean childAccount;
	private String parentAccountNumber;

	public AccountId getAccountId() {
		return accountId;
	}

	public void setAccountId(AccountId accountId) {
		this.accountId = accountId;
	}

	public String getCustomerNumber() {
		return (customerNumber == null) ? "" : customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getPremiseNumber() {
		return (premiseNumber == null) ? "" : premiseNumber;
	}

	public void setPremiseNumber(String premiseNumber) {
		this.premiseNumber = premiseNumber;
	}

	public String getCompanyCode() {
		return (companyCode == null) ? "" : companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getFirstName() {
		return (firstName == null) ? "" : firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return (middleName == null) ? "" : middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return (lastName == null) ? "" : lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getCareOfName() {
		return (careOfName == null) ? "" : careOfName;
	}

	public void setCareOfName(String careOfName) {
		this.careOfName = careOfName;
	}

	public String getAccountStatus() {
		return (accountStatus == null) ? "" : accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAccountType() {
		return (accountType == null) ? "" : accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountClassDescription() {
		return (accountClassDescription == null) ? "" : accountClassDescription;
	}

	public void setAccountClassDescription(String accountClassDescription) {
		this.accountClassDescription = accountClassDescription;
	}

	public Calendar getAccountStartDate() {
		return accountStartDate;
	}

	public void setAccountStartDate(Calendar accountStartDate) {
		this.accountStartDate = accountStartDate;
	}

	public Calendar getAccountClosedDate() {
		return accountClosedDate;
	}

	public void setAccountClosedDate(Calendar accountClosedDate) {
		this.accountClosedDate = accountClosedDate;
	}

	public Calendar getLastBilledDate() {
		return lastBilledDate;
	}

	public void setLastBilledDate(Calendar lastBilledDate) {
		this.lastBilledDate = lastBilledDate;
	}

	public boolean isBudgetBilling() {
		return budgetBilling;
	}

	public void setBudgetBilling(boolean budgetBilling) {
		this.budgetBilling = budgetBilling;
	}

	public BigDecimal getBudgetBillAmount() {
		return budgetBillAmount;
	}

	public void setBudgetBillAmount(BigDecimal budgetBillAmount) {
		this.budgetBillAmount = budgetBillAmount;
	}

	public boolean isCutInLandlord() {
		return cutInLandlord;
	}

	public void setCutInLandlord(boolean cutInLandlord) {
		this.cutInLandlord = cutInLandlord;
	}

	public boolean isCashOnly() {
		return cashOnly;
	}

	public void setCashOnly(boolean cashOnly) {
		this.cashOnly = cashOnly;
	}

	public boolean isPaperlessBillingEmailAddressInValid() {
		return paperlessBillingEmailAddressInValid;
	}

	public void setPaperlessBillingEmailAddressInValid(boolean paperlessBillingEmailAddressInValid) {
		this.paperlessBillingEmailAddressInValid = paperlessBillingEmailAddressInValid;
	}

	public String getPaperlessBillingEmailAddress() {
		return (paperlessBillingEmailAddress == null) ? "" : paperlessBillingEmailAddress;
	}

	public void setPaperlessBillingEmailAddress(String paperlessBillingEmailAddress) {
		this.paperlessBillingEmailAddress = paperlessBillingEmailAddress;
	}

	public boolean isPaperlessBillingEnrolled() {
		return paperlessBillingEnrolled;
	}

	public void setPaperlessBillingEnrolled(boolean paperlessBillingEnrolled) {
		this.paperlessBillingEnrolled = paperlessBillingEnrolled;
	}

	public String getFormattedFullName() {
		if (customerType == null) {
			return null;
		}
		if (customerType.equalsIgnoreCase("P")) {
			return ((StringUtils.trimToEmpty(getFirstName()) + " " + StringUtils.trimToEmpty(getMiddleName())).trim() + " " + StringUtils.trimToEmpty(getLastName())).trim();
		} else {
			return StringUtils.trimToEmpty(getOrganizationName());
		}
	}

	public boolean isRedirectToContractBillingVendorSite() {
		return redirectToContractBillingVendorSite;
	}

	public void setRedirectToContractBillingVendorSite(boolean redirectToContractBillingVendorSite) {
		this.redirectToContractBillingVendorSite = redirectToContractBillingVendorSite;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumberExt() {
		return phoneNumberExt;
	}

	public void setPhoneNumberExt(String phoneNumberExt) {
		this.phoneNumberExt = phoneNumberExt;
	}

	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	public String getWorkPhoneNumber() {
		return workPhoneNumber;
	}

	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}

	public String getWorkPhoneNumberExt() {
		return workPhoneNumberExt;
	}

	public void setWorkPhoneNumberExt(String workPhoneNumberExt) {
		this.workPhoneNumberExt = workPhoneNumberExt;
	}

	public String getEmployerIdNumber() {
		return employerIdNumber;
	}

	public void setEmployerIdNumber(String employerIdNumber) {
		this.employerIdNumber = employerIdNumber;
	}

	// public String getContactFirstName() {
	// return contactFirstName;
	// }
	// public void setContactFirstName(String contactFirstName) {
	// this.contactFirstName = contactFirstName;
	// }
	// public String getContactLastName() {
	// return contactLastName;
	// }
	// public void setContactLastName(String contactLastName) {
	// this.contactLastName = contactLastName;
	// }
	// public String getFormattedContactFullName() {
	// return FormatUtils.formatName(contactFirstName, contactLastName);
	// }
	// public Long getContactPhoneNumber() {
	// return (contactPhoneNumber == null) ? new Long(0) : contactPhoneNumber;
	// }
	// public void setContactPhoneNumber(Long contactPhoneNumber) {
	// this.contactPhoneNumber = contactPhoneNumber;
	// }
	// public String getContactPhoneNumberExt() {
	// return (contactPhoneNumberExt == null) ? "" : contactPhoneNumberExt;
	// }
	// public void setContactPhoneNumberExt(String contactPhoneNumberExt) {
	// this.contactPhoneNumberExt = contactPhoneNumberExt;
	// }
	// public Long getContactCellPhoneNumber() {
	// return (contactCellPhoneNumber == null) ? new Long(0) : contactCellPhoneNumber;
	// }
	// public void setContactCellPhoneNumber(Long contactCellPhoneNumber) {
	// this.contactCellPhoneNumber = contactCellPhoneNumber;
	// }
	// public Long getContactWorkPhoneNumber() {
	// return (contactWorkPhoneNumber == null) ? new Long(0) : contactWorkPhoneNumber;
	// }
	// public void setContactWorkPhoneNumber(Long contactWorkPhoneNumber) {
	// this.contactWorkPhoneNumber = contactWorkPhoneNumber;
	// }
	// public String getContactWorkPhoneNumberExt() {
	// return (contactWorkPhoneNumberExt == null) ? "" : contactWorkPhoneNumberExt;
	// }
	// public void setContactWorkPhoneNumberExt(String contactWorkPhoneNumberExt) {
	// this.contactWorkPhoneNumberExt = contactWorkPhoneNumberExt;
	// }

	public boolean isParentAccount() {
		return parentAccount;
	}

	public void setParentAccount(boolean parentAccount) {
		this.parentAccount = parentAccount;
	}

	public boolean isChildAccount() {
		return childAccount;
	}

	public void setChildAccount(boolean childAccount) {
		this.childAccount = childAccount;
	}

	public String getParentAccountNumber() {
		return parentAccountNumber;
	}

	public void setParentAccountNumber(String parentAccountNumber) {
		this.parentAccountNumber = parentAccountNumber;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", customerNumber=" + customerNumber + ", customerType="
				+ customerType + ", premiseNumber=" + premiseNumber + ", companyCode=" + companyCode + ", firstName="
				+ firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", organizationName="
				+ organizationName + ", careOfName=" + careOfName + ", phoneNumber=" + phoneNumber + ", phoneNumberExt="
				+ phoneNumberExt + ", cellPhoneNumber=" + cellPhoneNumber + ", workPhoneNumber=" + workPhoneNumber
				+ ", workPhoneNumberExt=" + workPhoneNumberExt + ", accountStatus=" + accountStatus + ", accountType="
				+ accountType + ", accountClassDescription=" + accountClassDescription + ", accountStartDate="
				+ accountStartDate + ", accountClosedDate=" + accountClosedDate + ", lastBilledDate=" + lastBilledDate
				+ ", budgetBilling=" + budgetBilling + ", budgetBillAmount=" + budgetBillAmount + ", cutInLandlord="
				+ cutInLandlord + ", cashOnly=" + cashOnly + ", paperlessBillingEnrolled=" + paperlessBillingEnrolled
				+ ", paperlessBillingEmailAddressInValid=" + paperlessBillingEmailAddressInValid
				+ ", paperlessBillingEmailAddress=" + paperlessBillingEmailAddress
				+ ", redirectToContractBillingVendorSite=" + redirectToContractBillingVendorSite + ", employerIdNumber="
				+ employerIdNumber + ", parentAccount=" + parentAccount + ", childAccount=" + childAccount
				+ ", parentAccountNumber=" + parentAccountNumber + "]";
	}
}
