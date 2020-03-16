package cn.zzdz.security.controller;


import cn.zzdz.common.entity.result.Result;
import cn.zzdz.common.entity.result.ResultCode;
import cn.zzdz.common.entity.security.SysPermission;
import cn.zzdz.common.entity.security.SysRole;
import cn.zzdz.common.entity.utils.IdWorker;
import cn.zzdz.security.dao.SysPermissionDao;
import cn.zzdz.security.dao.SysRoleDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 角色
 *
 * @author joe
 * @email qiuzhangming@gmail.com
 * @date 2020-03-02 17:13:03
 */
@Api(tags="角色管理")
@RestController
@RequestMapping("/sys")
public class SysRoleController extends BaseController {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysPermissionDao sysPermissionDao;


    @PutMapping("/role/assignPermissions")
    @ApiOperation(value="给角色分配权限",notes="需要权限-sys:role:addPermissions")
    @RequiresPermissions(value = "sys:role:addPermissions")
    public Result assignPermissions(@RequestBody Map<String,Object> map) {
        //1.获取被分配的角色id
        String roleId = (String) map.get("id");
        //2.获取到角色的id列表
        List<String> permissionIds = (List<String>) map.get("permissionIds");
        Set<SysPermission> permissions = new HashSet<>();
        for (String permissionId : permissionIds) {
            SysPermission sysPermission = sysPermissionDao.findById(permissionId).get();
            permissions.add(sysPermission);
        }

        SysRole sysRole = sysRoleDao.findById(roleId).get();
        sysRole.setPermissions(permissions);
        sysRoleDao.save(sysRole);

        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询所有
     */
    @GetMapping("/role")
    @ApiOperation(value="查看所有角色",notes="需要权限-sys:role:list")
    @RequiresPermissions("sys:role:list")
    public Result findAll(Pageable pageable){
        Page<SysRole> page = sysRoleDao.findAllByCompanyId(companyId, pageable);
        return new Result(ResultCode.SUCCESS, page);
    }


    /**
     * 信息
     */
    @GetMapping("/role/{id}")
    @ApiOperation(value="根据角色id查询",notes="需要权限-sys:role:info")
    @RequiresPermissions("sys:role:info")
    public Result findById(@PathVariable("id") String id){
		SysRole sysRole = sysRoleDao.findById(id).get();
        return new Result(ResultCode.SUCCESS, sysRole);
    }

    /**
     * 保存
     */
    @PostMapping("/role")
    @ApiOperation(value="添加角色",notes="需要权限-sys:role:save")
    @RequiresPermissions("sys:role:save")
    public Result save(@RequestBody SysRole sysRole){
        sysRole.setId(idWorker.nextId()+"");
        sysRole.setCompanyId(companyId);
        sysRole.setCode("role:company:user");
        sysRole.setCreateTime(new Date());
        sysRoleDao.save(sysRole);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 修改
     */
    @PutMapping("/role/{id}")
    @ApiOperation(value="修改角色",notes="需要权限-sys:role:update，只能修改名字和描述")
    @RequiresPermissions("sys:role:update")
    public Result update(@PathVariable("id") String id, @RequestBody SysRole sysRole){
        SysRole entity = sysRoleDao.findById(id).get();

        entity.setName(sysRole.getName());
        entity.setDescription(sysRole.getDescription());
        sysRoleDao.save(entity);

        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="删除角色",notes="需要权限-sys:role:delete")
    @RequiresPermissions("sys:role:delete")
    public Result delete(@PathVariable("id") String id) {
        sysRoleDao.deleteById(id);

        return new Result(ResultCode.SUCCESS);
    }

}
