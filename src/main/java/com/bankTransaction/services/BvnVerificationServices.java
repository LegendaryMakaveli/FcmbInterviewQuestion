package com.bankTransaction.services;

import com.bankTransaction.data.model.BvnData;
import com.bankTransaction.dto.request.VerifyNinRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface BvnVerificationServices {
    BvnData verifyAndFetchBvnData(String bvn);
    boolean verifyNinMatchesBvn(VerifyNinRequest request);
}
