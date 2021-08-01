/**
 * 
 */
package org.com.fxdeals.dto;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class FXDeal {
	
	private String dealUniqueId;
	private String fromCurrency;
	private String toCurrency;
	private Date dealTime;
	private String dealAmount;
	
	
	/**
	 * @return the fromCurrency
	 */
	public String getFromCurrency() {
		return fromCurrency;
	}
	/**
	 * @param fromCurrency the fromCurrency to set
	 */
	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}
	/**
	 * @return the toCurrency
	 */
	public String getToCurrency() {
		return toCurrency;
	}
	/**
	 * @param toCurrency the toCurrency to set
	 */
	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}
	/**
	 * @return the dealAmount
	 */
	public String getDealAmount() {
		return dealAmount;
	}
	/**
	 * @param dealAmount the dealAmount to set
	 */
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	
	/**
	 * @return the dealTime
	 */
	public Date getDealTime() {
		return dealTime;
	}
	/**
	 * @param dealTime the dealTime to set
	 */
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	/**
	 * @return the dealUniqueId
	 */
	public String getDealUniqueId() {
		return dealUniqueId;
	}
	/**
	 * @param dealUniqueId the dealUniqueId to set
	 */
	public void setDealUniqueId(String dealUniqueId) {
		this.dealUniqueId = dealUniqueId;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("From currency: " + fromCurrency)
		.append(" To Currency: " + toCurrency)
		.append(" Deal time: " + dealTime)
		.append(" Deal amount: " + dealAmount);
		
		return sb.toString();
	}

}
