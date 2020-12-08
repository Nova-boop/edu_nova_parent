package com.nova.cmsService.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nova.cmsService.entity.CrmBanner;
import com.nova.cmsService.service.CrmBannerService;
import com.nova.commonutils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后台管理的banner 接口
 * </p>
 *
 * @author nova
 * @since 2020-12-06
 */
@RestController
@RequestMapping("/cmsService/bannerAdmin")
@Api(description = "Banner管理-admin")
public class BannerAdminController {

    // 注入service
    @Autowired
    private CrmBannerService bannerService;


    // 增加banner
    @ApiOperation(value = "增加banner")
    @PostMapping("addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return Result.ok();
    }

    // 删除banner
    @ApiOperation(value = "根据ID删除banner")
    @DeleteMapping("remove/{bannerId}")
    public Result removeBannerById(@PathVariable String bannerId) {
        bannerService.removeById(bannerId);
        return Result.ok();
    }

    // 修改banner
    @ApiOperation(value = "修改banner")
    @PostMapping("updateBanner")
    public Result update(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return Result.ok();
    }

    // 根据ID 查询banner
    @ApiOperation(value = "根据ID查询banner")
    @GetMapping("getBanner/{bannerId}")
    public Result getBannerById(@PathVariable String bannerId) {
        CrmBanner banner = bannerService.getById(bannerId);
        return Result.ok().data("banner", banner);
    }

    // 分页查询
    @ApiOperation(value = "分页查询banner")
    @GetMapping("pageQueryBanner/{current}/{limit}")
    public Result pageQueryBanner(@PathVariable Long current,
                                  @PathVariable Long limit) {
        Page<CrmBanner> page = new Page<>(current, limit);
        bannerService.page(page, null);

        return Result.ok().data("items", page.getRecords()).data("total", page.getTotal());
    }


}

