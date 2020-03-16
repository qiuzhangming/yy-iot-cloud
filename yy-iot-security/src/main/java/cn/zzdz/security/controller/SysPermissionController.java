package com.zzdz.security.controller;

import cn.zzdz.common.entity.result.Result;
import cn.zzdz.common.entity.result.ResultCode;
import cn.zzdz.common.entity.security.SysPermission;
import cn.zzdz.common.utils.IdWorker;
import cn.zzdz.security.controller.BaseController;
import cn.zzdz.security.dao.SysPermissionDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * 权限信息，只允许管理员编辑
 *
 * @author joe
 * @email qiuzhangming@gmail.com
 * @date 2020-03-02 17:13:03
 */
@Api(tags="权限管理")
@RestController
@RequestMapping("/sys")
public class SysPermissionController extends BaseController {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SysPermissionDao sysPermissionDao;

    /**
     * 列表
     */
    @GetMapping("/permission")
    @ApiOperation(value="查询所有权限信息",notes="需要权限-sys:permission:list")
    @RequiresPermissions("sys:permission:list")
    public Result findAllByCompanyId(Pageable pageable) {
        Page<SysPermission> page = sysPermissionDao.findAll(pageable);

        return new Result(ResultCode.SUCCESS, page);
    }


    /**
     * 信息
     */
    @GetMapping("/permission/{id}")
    @ApiOperation(value="根据id查询权限信息",notes="需要权限-sys:permission:info")
    @RequiresPermissions("sys:permission:info")
    public Result findById(@PathVariable("id") String id) {
		SysPermission sysPermission = sysPermissionDao.findById(id).get();

        return new Result(ResultCode.SUCCESS, sysPermission);
    }

    /**
     * 保存
     */
    @PostMapping("/permission")
    @ApiOperation(value="新增权限",notes="需要系统管理员权限-role:sys:admin")
    @RequiresRoles({"role:sys:admin"})
    //@RequiresPermissions("sys:permission:save")
    public Result save(@RequestBody SysPermission sysPermission) {
        sysPermission.setId(idWorker.nextId()+"");
        sysPermission.setCreateTime(new Date());
		sysPermissionDao.save(sysPermission);

        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 修改
     */
    @PutMapping("/permission/{id}")
    @ApiOperation(value="修改权限信息",notes="需要系统管理员权限-role:sys:admin，只允许修改名字和描述")
    @RequiresRoles({"role:sys:admin"})
    //@RequiresPermissions("sys:permission:update")
    public Result update(@PathVariable("id") String id, @RequestBody SysPermission sysPermission){
        SysPermission entity = sysPermissionDao.findById(id).get();

        entity.setName(sysPermission.getName());
        entity.setDescription(sysPermission.getDescription());
        sysPermissionDao.save(entity);

        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 删除
     */
    @DeleteMapping("/permission/{id}")
    @ApiOperation(value="删除权限",notes="需要系统管理员权限-role:sys:admin")
    @RequiresRoles({"role:sys:admin"})
    //@RequiresPermissions("sys:permission:delete")
    public Result delete(@PathVariable("id") String id) {
        sysPermissionDao.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

}
