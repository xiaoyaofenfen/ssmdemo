package com.liangfen.model.excel;

/**
 * 卡号-用户名对
 * 卡号作为唯一标识符
 * @author liangfen
 *
 */
public class CardNumUserNamePair {
	
	/**
	 * 卡号
	 */
	private String cardNum;
	
	/**
	 * 用户名	
	 */
	private String userName;
	
	public CardNumUserNamePair()
	{}
	
	public CardNumUserNamePair(String theCardNum, String theUserName)
	{
		cardNum = theCardNum;
		userName = theUserName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		if(cardNum == null) return super.hashCode();
		return cardNum.hashCode();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new CardNumUserNamePair(cardNum, userName);
	}
	

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == null || !(obj instanceof CardNumUserNamePair)) return false;
		
		String t = ((CardNumUserNamePair)obj).getCardNum();
		if(cardNum != null && t != null)
		{
			return cardNum.equals(t);
		}
		else if (cardNum == null && t == null) {
			return true;
		}
			
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("CardNumUserNamePair[ cardNum = %s, userName = %s]", cardNum, userName);
	}
	
	

}
