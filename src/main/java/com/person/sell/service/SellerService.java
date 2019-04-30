package com.person.sell.service;

import com.person.sell.dataobject.SellerInfo;

public interface SellerService {

    SellerInfo findSellerInfoByUsername(String username);

}
