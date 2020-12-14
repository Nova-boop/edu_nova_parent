package com.nova.eduService.client;

import com.nova.commonutils.vo.UcenterMemberVo;
import org.springframework.stereotype.Component;

@Component
public class UcenterFileDegradeFeignClient implements UcenterClient {
    @Override
    public UcenterMemberVo getUserInfo(String userId) {
        return null;
    }
}
