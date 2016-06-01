package com.siping.service.businesspartner.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.businesspartner.service.BankAccountRestService;
import com.siping.smartone.businesspartner.request.BankAccountRequest;
import com.siping.smartone.businesspartner.request.DeleteBankAccountRequest;
import com.siping.smartone.businesspartner.response.BankAccountResponse;

@Controller
public class BankAccountRestController extends StromaWebserviceController {
    @Autowired
    private BankAccountRestService bankAccountService;

    @RequestMapping(value = "/bankaccount/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody BankAccountRequest request) {
        return bankAccountService.add(request);
    }

    @RequestMapping(value = "/bankaccount/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody BankAccountRequest request) {
        return bankAccountService.edit(request);
    }

    @RequestMapping(value = "/bankaccount/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BankAccountResponse get(@PathVariable Integer id) {
        return bankAccountService.get(id);
    }

    @RequestMapping(value = "/bankaccount/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeleteBankAccountRequest request) {
        return bankAccountService.delete(request);
    }
    
    @RequestMapping(value = "/bankaccount/getlist/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<BankAccountResponse> getList(@PathVariable Integer id) {
        return bankAccountService.getList(id);
    }
}
