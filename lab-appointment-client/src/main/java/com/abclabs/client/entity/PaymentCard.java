package com.abclabs.client.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PaymentCard {

	@NotNull(message = "Card holder's name required!")
	private String cardName;
	
	@NotNull(message = "Required")
	@Pattern(regexp = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$", 
	message = "Invalid card number. Only VISA are cards are accepted!")
	private String cardNumber;
	
	@NotNull(message = "Required")
	@Pattern(regexp = "(0[1-9]|1[0-2])\\/[0-9]{2}", message = "Invalid card expiry date!")
	private String cardExpireDate;
	
	@NotNull(message = "Required")
	@Pattern(regexp = "^[0-9]{3}$", message = "Invalid security digits found!")
	private String securityCode;
	
	@NotNull(message = "Required")
	@Pattern(regexp = "^[0-9]{5}$", message = "Invalid zip /postal code found!")
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
