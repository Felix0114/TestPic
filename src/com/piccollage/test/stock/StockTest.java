package com.piccollage.test.stock;

import java.util.ArrayList;


public class StockTest {
	
	
	
	

	
	public static void main(String[] args) {
		int  stock_prices_yesterday[]={5, 6, 4, 7, 9, 8, 8};
		
		ArrayList<StockItem> stockItems=new ArrayList<StockItem>();
		
		
		
		for(int i=0;i<stock_prices_yesterday.length;i++){
			int tvalue=stock_prices_yesterday[i];
			
			if(i==0){//first
				stockItems.add(new StockItem(tvalue,i));
				
			}else if(i==stock_prices_yesterday.length-1){//last
				//check candidate 
				int ltvalue=stock_prices_yesterday[i-1];
				if(tvalue>ltvalue   ){//peak
					
					for(int j=0;j<stockItems.size();j++){
						if(stockItems.get(j).getSell()<tvalue){
							stockItems.get(j).setSell(tvalue,i);				
						}
					}
				}else if(tvalue<ltvalue  ){ //valley
					stockItems.add(new StockItem(tvalue,i));
				}
			}else{
				//check candidate 
				int ltvalue=stock_prices_yesterday[i-1];
				int rtvalue=stock_prices_yesterday[i+1];
				if(tvalue>=ltvalue && tvalue>=rtvalue  ){//peak
					
					for(int j=0;j<stockItems.size();j++){
						if(stockItems.get(j).getSell()<tvalue){
							stockItems.get(j).setSell(tvalue,i);				
						}
					}
				}else if(tvalue<=ltvalue && tvalue<=rtvalue ){ //valley
					stockItems.add(new StockItem(tvalue,i));
				}else{
					//do nothing
				}
			}			
		}//end 
		int maxProfit=stockItems.get(0).getProfit();
		int maxProfitIndex=0;
		
		
		for(int k=1;k<stockItems.size();k++){
			if(stockItems.get(k).getProfit()>maxProfit){
				maxProfit=stockItems.get(k).getProfit();
				maxProfitIndex=k;			
			}			
		}
		System.out.println("buy at "+stockItems.get(maxProfitIndex).getBuy());
		System.out.println("sell at "+stockItems.get(maxProfitIndex).getSell());
		System.out.println("maxProfit = "+stockItems.get(maxProfitIndex).getProfit());
		

	}

}
