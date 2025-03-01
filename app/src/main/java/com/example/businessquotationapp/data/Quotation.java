package com.example.businessquotationapp.data;

public class Quotation extends Model {
    private String customerName, phone, item, status, date;
    private double price, totalAmount;
    private int quantity;

    public Quotation(String customerName, String phone, String item, String status, String date, double price, double totalAmount, int quantity) {
        this.customerName = customerName;
        this.phone = phone;
        this.item = item;
        this.status = status;
        this.date = date;
        this.price = price;
        this.totalAmount = totalAmount;
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
                ", totalAmount=" + totalAmount +
                ", quantity=" + quantity +
                ", id=" + id +
                '}';
    }

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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
