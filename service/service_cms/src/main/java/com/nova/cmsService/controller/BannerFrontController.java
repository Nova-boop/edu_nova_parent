package com.nova.cmsService.controller;


import com.nova.cmsService.entity.CrmBanner;
import com.nova.cmsService.service.CrmBannerService;
import com.nova.commonutils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author nova
 * @since 2020-12-06
 */
@RestController
@RequestMapping("/cmsService/bannerFront")
@Api(value = "Banner管理-Front")
public class BannerFrontController {

    // 注入service
    private final CrmBannerService bannerService;

    public BannerFrontController(CrmBannerService bannerService) {
        this.bannerService = bannerService;
    }

    // 查询首页显示的banner
    @GetMapping("getAllBanner")
    public Result getAllBanner(){
        List<CrmBanner> bannerList=bannerService.SelectBannerList();

        return Result.ok().data("bannerList",bannerList);
    }

}

