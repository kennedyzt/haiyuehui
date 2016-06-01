package com.siping.intranet.crm.businesspartner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siping.intranet.crm.businesspartner.repository.BankAccountRepository;
import com.siping.smartone.businesspartner.request.BankAccountRequest;
import com.siping.smartone.businesspartner.response.BankAccountResponse;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public Boolean add(BankAccountRequest request) {
        return bankAccountRepository.add(request);
    }

    public Boolean edit(BankAccountRequest request) {
        return bankAccountRepository.edit(request);
    }

    public BankAccountResponse get(Integer id) {
        return bankAccountRepository.get(id);
    }

    public Boolean delete(List<Integer> ids, Integer updateBy) {
        return bankAccountRepository.delete(ids, updateBy);
    }

    public List<BankAccountResponse> getList(Integer id) {
        return bankAccountRepository.getList(id);
    }
}
