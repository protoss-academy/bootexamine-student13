package com.protosstechnology.train.bootexamine;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"page", "item_per_page", "doc"})
public class PagingResponse {
    @JsonProperty("doc")
    private List<DocumentResponse> docResponseList;

    private int page;

    @JsonProperty("item_per_page")
    private int itemPerPage;

    public PagingResponse(int page, int itemPerPage) {
        this.page = page;
        this.itemPerPage = itemPerPage;
    }

    public void setdocResponse(List<DocumentResponse> docResponseList) {
        this.docResponseList = docResponseList;
    }

    public List<DocumentResponse> getdocResponseList() {
        return docResponseList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }
}
