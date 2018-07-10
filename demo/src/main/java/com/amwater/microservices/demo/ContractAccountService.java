package com.amwater.microservices.demo;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amwater.microservices.demo.exception.AMWEnterpriseException;
import com.amwater.microservices.demo.utils.ConverterUtils;
import com.amwater.microservices.demo.utils.StringUtils;
import com.amwater.rtc._000922.pi.businesstechnicalmasterdata.v5.AccountDetail;
import com.amwater.rtc._000922.pi.businesstechnicalmasterdata.v5.AccountDetail.AccountDetailRequest;
import com.amwater.rtc._000922.pi.businesstechnicalmasterdata.v5.AccountDetailInfo;
import com.amwater.rtc._000922.sapisu.buisnesstechnicalmasterdata.v5.AccountDetailQueryIBSYC;

@Service
public class ContractAccountService {
	private static final Logger logger = Logger.getLogger(ContractAccountService.class);

	public String STATUS_CODE_SUCCESS = "002";
	public String ERRORCODE_FAILED = "FAILED";

	@Autowired
	private AccountDetailQueryIBSYC accountDetailQueryService; 
	
	public ContractAccountService() {}

	public Account getAccount(String accountNumber) {
		List<Account> accounts = null;
		Account account = null;

		if (accountNumber == null)
			AMWEnterpriseException.throwAMWException(this.getClass().getSimpleName() + ".getAccount failed", ERRORCODE_FAILED, null);

		accounts = getAccounts(accountNumber);
		if (accounts != null && accounts.size() == 1)
			account = accounts.get(0);

		return account;
	}

	public List<Account> getAccounts(String accountNumber) {
		List<Account> accounts = null;
		Account account = null;

		try {
			AccountDetail accountDetail = new AccountDetail();
			AccountDetail.AccountDetailRequest accountDetailRequest = new AccountDetailRequest();
			List<AccountDetailInfo.Status> statuses = null;

			accountDetailRequest.setBPNo(null);
			accountDetailRequest.setLoginID(null);
			accountDetailRequest.getCANo().add(accountNumber);
			accountDetail.setAccountDetailRequest(accountDetailRequest);

			AccountDetailInfo accountDetailInfo = null;

			try {
				accountDetailInfo = accountDetailQueryService.accountDetailQueryIBSYC(accountDetail);
			}
			catch (WebServiceException e) {
				Throwable t = ExceptionUtils.getRootCause(e);
				if (t instanceof SocketException) { // Retry SocketException
					logger.info("Retrying web service - WebServiceException");
					accountDetailInfo = accountDetailQueryService.accountDetailQueryIBSYC(accountDetail);
					logger.info("Retry successful");
				} else {
					logger.info("Rethrowing exception");
					throw e;
				}
			}

			if (accountDetailInfo == null)
				AMWEnterpriseException.throwAMWException(this.getClass().getSimpleName() + ".getAccount()" + " accountNumber=" + accountNumber + " ) failed; SAP returned null", ERRORCODE_FAILED, null);

			statuses = accountDetailInfo.getStatus();
			
			List<AccountDetailInfo.AccountDetailResponse> accountDetailResponses = accountDetailInfo.getAccountDetailResponse();
			if (accountDetailResponses == null)
				return null;
			
			Iterator<AccountDetailInfo.Status> statusItr = statuses.iterator();
			for (AccountDetailInfo.AccountDetailResponse accDetRsp : accountDetailResponses) {
				if (!statusItr.hasNext())
					break;

				AccountDetailInfo.Status status = statusItr.next();
				if (status.getReturnStatusCode().equals(STATUS_CODE_SUCCESS)) {
					account = new Account();
					account.setAccountClosedDate(ConverterUtils.toCalendar(accDetRsp.getAccountCloseDate()));
					account.setAccountId(new AccountId(accDetRsp.getCANo()));
					account.setAccountStartDate(ConverterUtils.toCalendar(accDetRsp.getAccountStartDate()));
					account.setAccountStatus(accDetRsp.getAccountStatus());
					account.setAccountType(StringUtils.trimToEmpty(accDetRsp.getAccountClass()).toUpperCase());
					account.setBudgetBillAmount(ConverterUtils.toBigDecimal(accDetRsp.getBudgetBillAmount()));
					account.setBudgetBilling(ConverterUtils.toBoolean(accDetRsp.getBudgetBillIndicator()));
					account.setCareOfName(accDetRsp.getCareOfName());
					account.setCashOnly(ConverterUtils.toBoolean(accDetRsp.getCashOnlyFlag()));
					account.setRedirectToContractBillingVendorSite(ConverterUtils.redirectBololean(accDetRsp.getRedirectStatus()));
					account.setCustomerNumber(accDetRsp.getBPNo());
					account.setCutInLandlord(ConverterUtils.toBoolean(accDetRsp.getCutinlandlordFlag()));
					account.setPaperlessBillingEmailAddress(accDetRsp.getEBillingEmailAddress());
					account.setPaperlessBillingEmailAddressInValid(ConverterUtils.toBoolean(accDetRsp.getEBillingEmailAddressInvalid()));
					account.setPaperlessBillingEnrolled(ConverterUtils.toBoolean(accDetRsp.getEBillingEnrolledFlag()));
					account.setFirstName(accDetRsp.getFirstName());
					account.setLastBilledDate(ConverterUtils.toCalendar(accDetRsp.getLastBillDate()));
					account.setLastName(accDetRsp.getLastName());
					account.setMiddleName(accDetRsp.getMiddleName());
					account.setPremiseNumber(accDetRsp.getPremiseNo());
					account.setCustomerType(accDetRsp.getPartnerCategory());
					account.setOrganizationName(accDetRsp.getOrganisationName());
					account.setCareOfName(accDetRsp.getCareOfName());
					account.setEmployerIdNumber(accDetRsp.getEmployerId());
					account.setPhoneNumber(ConverterUtils.toStringPhone(accDetRsp.getPhoneNo()));
					account.setPhoneNumberExt(accDetRsp.getPhoneExten());
					account.setWorkPhoneNumber(ConverterUtils.toStringPhone(accDetRsp.getPhoneNo2()));
					account.setWorkPhoneNumberExt(accDetRsp.getPhoneExten2());
					account.setCellPhoneNumber(ConverterUtils.toStringPhone(accDetRsp.getMobileNo()));
					account.setCompanyCode(accDetRsp.getCompanyCode());
					
					String accountCategory = StringUtils.trimToEmpty(accDetRsp.getAccountCategory());
					if (accountCategory.equals("02")) 
						account.setParentAccount(true);
					else
						account.setParentAccount(false);
					
					account.setParentAccountNumber(StringUtils.trimToEmpty(accDetRsp.getCollectiveAccountNumber()));
					
					if (StringUtils.isNotBlank(accDetRsp.getCollectiveAccountNumber())) 
						account.setChildAccount(true);
					else 
						account.setChildAccount(false);
					
					account.setAccountClassDescription(StringUtils.trimToEmpty(accDetRsp.getAccountClassDescription()).replaceAll("[^A-Za-z0-9()\\s]", "-"));

					if (accounts == null)
						accounts = new ArrayList<Account>();
					accounts.add(account);
				} else {
					AMWEnterpriseException.throwAMWException(this.getClass().getSimpleName() + ".getAccount()" + " accountNumber=" + accountNumber + " ) failed; SAP returned null", ERRORCODE_FAILED, null);
				}
			}
		}
		catch (Exception e) {
			AMWEnterpriseException.throwAMWException(this.getClass().getSimpleName() + ".getAccount()" + " accountNumber=" + accountNumber + " ) failed", ERRORCODE_FAILED, e);
		}
		
		return accounts;
	}
}
