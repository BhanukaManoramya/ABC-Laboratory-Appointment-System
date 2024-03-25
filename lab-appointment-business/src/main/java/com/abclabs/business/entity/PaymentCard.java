package com.abclabs.business.entity;


public class PaymentCard {

	private String cardName;
	
	private String cardNumber;
	
	private String cardExpireDate;
	
	private String securityCode;
	
	private String zipCode;
	
	private String holderEmailAddress;
	
	public PaymentCard() {
	}

	public String getCardName() {
		return cardName;
	}


	public void setCardName(String cardName) {
		this.cardName = cardName;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getCardExpireDate() {
		return cardExpireDate;
	}


	public void setCardExpireDate(String cardExpireDate) {
		this.cardExpireDate = cardExpireDate;
	}


	public String getSecurityCode() {
		return securityCode;
	}


	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getHolderEmailAddress() {
		return holderEmailAddress;
	}

	public void setHolderEmailAddress(String holderEmailAddress) {
		this.holderEmailAddress = holderEmailAddress;
	}

	@Override
	public String toString() {
		return "PaymentCard [cardName=" + cardName + ", cardNumber=" + cardNumber + ", cardExpireDate=" + cardExpireDate
				+ ", securityCode=" + securityCode + ", zipCode=" + zipCode + ", holderEmailAddress="
				+ holderEmailAddress + "]";
	}

}
