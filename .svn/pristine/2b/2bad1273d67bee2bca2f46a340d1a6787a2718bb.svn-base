package com.siping.intranet.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siping.intranet.common.repository.CommonBillsNumberRepository;

@Service
public class CommonBillsNumberService {
    @Autowired
    private CommonBillsNumberRepository commonBillsNumberRepository;
    public String getGenerateBillsNumber(String tableName, String primaryKeyName){
        return commonBillsNumberRepository.getGenerateBillsNumber(tableName, primaryKeyName);
    }
}
