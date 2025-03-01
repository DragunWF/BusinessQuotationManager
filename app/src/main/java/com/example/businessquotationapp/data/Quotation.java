package com.example.businessquotationapp.data;

public class Quotation extends Model {
    private String customerName, phone, item, status, date;
    private double price;
    private int quotationNumber, quantity;

    public Quotation(String customerName, String phone, String item, String status, String date, double price, int quotationNumber, int quantity) {
        this.customerName = customerName;
        this.phone = phone;
        this.item = item;
        this.status = status;
        this.date = date;
        this.price = price;
        this.quotationNumber = quotationNumber;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Quotation{" +
                "customerName='" + customerName + '\'' +
                ", phone='" + phone + '\'' +
                ", item='" + item + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", id=" + id +
                '}';
    }

    // Dynamic Getters
    public double getTotalAmount() {
        return price * quantity;
    }

    // Setters and Getters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuotationNumber() {
        return quotationNumber;
    }

    public void setQuotationNumber(int quotationNumber) {
        this.quotationNumber = quotationNumber;
    }
}
