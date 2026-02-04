package com.bankTransaction.services;

import com.bankTransaction.data.model.Bvn;
import com.bankTransaction.data.model.Nin;
import com.bankTransaction.dto.request.NinRegistrationRequest;
import com.bankTransaction.dto.response.NinRegistrationResponse;

public interface NinService {
    NinRegistrationResponse registerNin(NinRegistrationRequest request);
    Nin getNinData(String nin);
    boolean verifyNinMatchesBvn(String nin, Bvn bvn);
}
