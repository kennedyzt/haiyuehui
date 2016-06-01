package com.siping.service.businesspartner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;

import com.siping.service.businesspartner.repository.BankAccountRestRepository;
import com.siping.smartone.businesspartner.request.BankAccountRequest;
import com.siping.smartone.businesspartner.request.DeleteBankAccountRequest;
import com.siping.smartone.businesspartner.response.BankAccountResponse;

@Service
public class BankAccountRestService extends DBSwitch {
    @Autowired
    private BankAccountRestRepository bankAccountRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(BankAccountRequest request) {
        return bankAccountRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(BankAccountRequest request) {
        return bankAccountRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public BankAccountResponse get(Integer id) {
        return bankAccountRepository.get(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeleteBankAccountRequest request) {
        return bankAccountRepository.delete(request);
    }

    public List<BankAccountResponse> getList(Integer id) {
        return bankAccountRepository.getList(id);
    }

}
