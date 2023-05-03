package com.wiremock.testmocks.gettransaction;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetTransactionModel {
    public Integer pageIndex;
    public Integer pageSize;
    public String hasNextPage;
    public String hasPreviousPage;
    public Integer totalCount;
    public Integer totalPages;
}
