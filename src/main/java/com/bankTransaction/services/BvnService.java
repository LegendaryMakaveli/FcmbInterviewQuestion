package com.bankTransaction.services;

import com.bankTransaction.data.model.Bvn;
import com.bankTransaction.dto.request.BvnRegistrationRequest;
import com.bankTransaction.dto.response.BvnRegistrationResponse;

public interface BvnService {
    BvnRegistrationResponse registerBvn(BvnRegistrationRequest request);
    Bvn getBvnData(String bvn);
}
