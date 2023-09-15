/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Laptop
 */
public class order {
    private String orderId;
    private String orderDate;
    private String cusName;
    private int orddeId;
    private String flowerId;
    private int quantity;
    private float flowerCost;

    public order() {
    }

    public order(String orderId, String orderDate, String cusName, int orddeId, String flowerId, int quantity, float flowerCost) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cusName = cusName;
        this.orddeId = orddeId;
        this.flowerId = flowerId;
        this.quantity = quantity;
        this.flowerCost = flowerCost;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCusName() {
        String[] arr = cusName.split(" ");
	//dùng vòng lặp duyệt các từ và thay thế từ đầu tiên!
	String stringfromclient1 = "";
	for (String x : arr) {
		stringfromclient1 = stringfromclient1 + (x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase());
		stringfromclient1 = stringfromclient1 + " ";
	}
        cusName = stringfromclient1.trim();
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public int getOrddeId() {
        return orddeId;
    }

    public void setOrddeId(int orddeId) {
        this.orddeId = orddeId;
    }

    public String getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(String flowerId) {
        this.flowerId = flowerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getFlowerCost() {
        return flowerCost;
    }

    public void setFlowerCost(float flowerCost) {
        this.flowerCost = flowerCost;
    }

    @Override
    public String toString() {
        return this.getOrderId() + ", " + this.getOrderDate() + ", " + this.getCusName() + ", " +
                this.getOrddeId() + ", " + this.getFlowerId() + ", " + this.getQuantity() + ", " + this.getFlowerCost();
    }
    
    
}
