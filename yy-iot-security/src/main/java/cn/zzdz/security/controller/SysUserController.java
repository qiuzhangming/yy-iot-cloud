package cn.zzdz.security.controller;


import cn.zzdz.common.entity.result.Result;
import cn.zzdz.common.entity.result.ResultCode;
import cn.zzdz.common.entity.security.SysRole;
import cn.zzdz.common.entity.security.SysUser;
import cn.zzdz.common.utils.IdWorker;
import cn.zzdz.security.dao.SysRoleDao;
import cn.zzdz.security.dao.SysUserDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 
 *
 * @author joe
 * @email qiuzhangming@gmail.com
 * @date 2020-03-02 17:13:03
 */
@Api(tags="用户管理")
@RestController
@RequestMapping("/sys")
public class SysUserController extends BaseController {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @ApiOperation(value="给用户分配角色",notes="需要权限-sys:user:addRoles")
    @RequiresPermissions(value = "sys:user:addRoles")
    @PutMapping("/user/assignRoles")
    public Result assignRoles(@RequestBody Map<String,Object> map) {
        //1.获取被分配的用户id
        String userId = (String) map.get("id");
        //2.获取到角色的id列表
        List<String> roleIds = (List<String>) map.get("roleIds");
        Set<SysRole> roles = new HashSet<>();
        for (String roleId : roleIds) {
            SysRole sysRole = sysRoleDao.findById(roleId).get();
            roles.add(sysRole);
        }

        SysUser sysUser = sysUserDao.findById(userId).get();
        sysUser.setRoles(roles);
        sysUserDao.save(sysUser);

        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询所有
     */
    @ApiOperation(value="查询所有用户",notes="需要权限-sys:user:list")
    @RequiresPermissions(value = {"sys:user:list", "or"}, logical = Logical.OR)
    @GetMapping("/user")
    public Result findAll(Pageable pageable) {
        Page<SysUser> page = sysUserDao.findAllByCompanyId(companyId, pageable);
        return new Result(ResultCode.SUCCESS, page);
    }

    /**
     * 根据id查询
     */
    @ApiOperation(value="根据用户id查询",notes="需要权限-sys:user:info")
    @RequiresPermissions("sys:user:info")
    @GetMapping("/user/{id}")
    public Result findById(@PathVariable("id") String id) {
		SysUser sysUser = sysUserDao.findById(id).get();
        return new Result(ResultCode.SUCCESS, sysUser);
    }

    /**
     * 添加用户
     */
    @PostMapping("/user")
    @ApiOperation(value="添加用户",notes="需要权限-sys:user:save")
    @RequiresPermissions("sys:user:save")
    public Result save(@RequestBody SysUser sysUser) {
        sysUser.setId(idWorker.nextId()+"");
        sysUser.setCompanyId(companyId);
        sysUser.setCreateTime(new Date());
        String password = new Md5Hash(sysUser.getPassword(),sysUser.getName(),3).toString();
        sysUser.setPassword(password);

        sysUserDao.save(sysUser);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 修改
     */
    @PutMapping("/user/{id}")
    @ApiOperation(value="修改用户信息",notes="需要权限-sys:user:update，只能修改用户状态和部门。")
    @RequiresPermissions("sys:user:update")
    public Result update(@PathVariable("id") String id, @RequestBody SysUser sysUser){
        SysUser entity = sysUserDao.findById(id).get();

        entity.setStatus(sysUser.getStatus());
        entity.setDepartmentId(sysUser.getDepartmentId());
        sysUserDao.save(entity);

        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 删除
     */
    @DeleteMapping("/user/{id}")
    @ApiOperation(value="删除用户",notes="需要权限-sys:user:delete")
    @RequiresPermissions("sys:user:delete")
    public Result delete(@PathVariable("id") String id) {
        sysUserDao.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

}
