package com.example.businessquotationapp.data;

public abstract class Model {
    protected int id;

    public Model() {
        id = -1;
    }

    public Model(int id) {
        this.id = id;
    }

    @Override
    public abstract String toString();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
