package com.shsxt.crm.task;

import com.shsxt.crm.service.CustomerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JobService {

    @Resource
    private CustomerService customerService;

    // @Scheduled(cron = "0 0 18 20 * ? ")
    public void job() {
        customerService.updateCustomerState();
    }
}
