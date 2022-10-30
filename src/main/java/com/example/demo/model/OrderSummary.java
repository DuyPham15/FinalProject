package com.example.demo.model;

public class OrderSummary {

	private long orderReceivedCount;
	private long orderDeliveriedCount;
	private long orderCanceledCount;
	private long orderFinishedCount;
	
	public long getOrderReceivedCount() {
		return orderReceivedCount;
	}
	public void setOrderReceivedCount(long orderReceivedCount) {
		this.orderReceivedCount = orderReceivedCount;
	}
	public long getOrderDeliveriedCount() {
		return orderDeliveriedCount;
	}
	public void setOrderDeliveriedCount(long orderDeliveriedCount) {
		this.orderDeliveriedCount = orderDeliveriedCount;
	}
	public long getOrderCanceledCount() {
		return orderCanceledCount;
	}
	public void setOrderCanceledCount(long orderCanceledCount) {
		this.orderCanceledCount = orderCanceledCount;
	}
	public long getOrderFinishedCount() {
		return orderFinishedCount;
	}
	public void setOrderFinishedCount(long orderFinishedCount) {
		this.orderFinishedCount = orderFinishedCount;
	}
	
		
}
