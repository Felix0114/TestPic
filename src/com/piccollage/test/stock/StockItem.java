package com.piccollage.test.stock;

public class StockItem{
	
	
	private int buy;
	private int buyIndex;
	private int sell;
	private int sellIndex;
	private int profit;
	
	public StockItem(int buy, int buyIndex){
		this.buy=buy;
		this.buyIndex=buyIndex;
		sell=buy;
		sellIndex=buyIndex;
		profit=0;
	}
	public int getBuy() {
		return buy;
	}
	
	public int getBuyIndex() {
		return buyIndex;
	}
	
	public int getSell() {
		return sell;
	}
	public void setSell(int sell, int sellIndex) {
		this.sell = sell;
		this.sellIndex = sellIndex;
		this.profit=sell-buy;
	}
	public int getSellIndex() {
		return sellIndex;
	}
	
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}
	
}