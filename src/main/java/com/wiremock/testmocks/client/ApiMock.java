/*
package com.wiremock.testmocks.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.wiremock.basemock.service.MockServices;
import com.wiremock.testmocks.gettransaction.GetTransactionModel;
import lombok.SneakyThrows;
import org.testng.annotations.Test;


public class ApiMock {

    @SneakyThrows
    public void GetTransaction() {

        getTransactionModel transactionMock = getTransactionModel.builder()
                .pageIndex(1)
                .pageSize(10)
                .hasNextPage("false")
                .hasPreviousPage("false")
                .totalCount(0)
                .totalPages(0)
                .build();

        String json = MockServices.buildJson(transactionMock);
        MockServices.createForGetMock("/some/thing", json);

    }
    @SneakyThrows
    @Test
    public void test() {

        getTransaction();

    }
}
*/
