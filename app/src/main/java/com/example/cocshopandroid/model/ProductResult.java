package com.example.cocshopandroid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductResult implements Serializable {

    @SerializedName("totalRecords")
    private int totalRecords;
    @SerializedName("pageIndex")
    private int pageIndex;
    @SerializedName("pageSize")
    private int pageSize;
    @SerializedName("results")
    private List<Product> results;

    public ProductResult() {
    }

    public ProductResult(int totalRecords, int pageIndex, int pageSize, List<Product> results) {
        this.totalRecords = totalRecords;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.results = results;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Product> getResults() {
        return results;
    }

    public void setResults(List<Product> results) {
        this.results = results;
    }
}
