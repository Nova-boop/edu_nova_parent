package com.nova.cmsService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nova.cmsService.entity.CrmBanner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author nova
 * @since 2020-12-06
 */
public interface CrmBannerService extends IService<CrmBanner> {

    // 查询首页显示的banner
    List<CrmBanner> SelectBannerList();
}
