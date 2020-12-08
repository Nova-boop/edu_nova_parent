package com.nova.cmsService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nova.cmsService.entity.CrmBanner;
import com.nova.cmsService.mapper.CrmBannerMapper;
import com.nova.cmsService.service.CrmBannerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author nova
 * @since 2020-12-06
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    // 查询首页显示的banner
    @Override
    @Cacheable(key = "'SelectBannerList'", value = "banner")
    public List<CrmBanner> SelectBannerList() {
        // 查询Banner

        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 4");
        List<CrmBanner> bannerList = baseMapper.selectList(null);
        return bannerList;
    }
}
