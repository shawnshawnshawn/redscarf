package com.baiye.redscarf.siege.service.reference.service;

import com.baiye.redscarf.siege.common.dto.UserAccountDto;

/**
 * @author baiye
 * @date 2021/3/4 6:14 下午
 **/
public interface FeignUserService {

    UserAccountDto getUserAccountInfo(Long id);

}
