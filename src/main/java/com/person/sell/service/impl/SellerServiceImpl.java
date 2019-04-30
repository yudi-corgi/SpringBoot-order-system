package com.person.sell.service.impl;

import com.person.sell.dataobject.SellerInfo;
import com.person.sell.repository.SellerInfoRepository;
import com.person.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByUsername(String username) {
        return sellerInfoRepository.findByUsername(username);
    }
}
