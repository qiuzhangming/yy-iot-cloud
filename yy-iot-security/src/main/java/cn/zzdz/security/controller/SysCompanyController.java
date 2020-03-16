package cn.zzdz.security.controller;

import cn.zzdz.common.entity.result.Result;
import cn.zzdz.common.entity.result.ResultCode;
import cn.zzdz.common.entity.security.SysCompany;
import cn.zzdz.common.entity.security.SysPermission;
import cn.zzdz.common.entity.security.SysRole;
import cn.zzdz.common.entity.security.SysUser;
import cn.zzdz.common.utils.IdWorker;
import cn.zzdz.security.dao.SysCompanyDao;
import cn.zzdz.security.dao.SysPermissionDao;
import cn.zzdz.security.dao.SysRoleDao;
import cn.zzdz.security.dao.SysUserDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author joe
 * @email qiuzhangming@gmail.com
 * @date 2020-03-02 17:13:03
 *
 * @ApiResponses({
 *         @ApiResponse(code=400,message="请求参数没填好"),
 *         @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
 * })
 *
 *  @ApiImplicitParams({
 *             @ApiImplicitParam(name="id",value="公司id",required=true,paramType="form"),
 *             @ApiImplicitParam(name="id",value="公司id",required=true,paramType="form")
 *     })
 */
@Api(tags="企业管理")
@RestController
@RequestMapping("/sys")
@Transactional
public class SysCompanyController extends BaseController {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SysCompanyDao sysCompanyDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysPermissionDao sysPermissionDao;



    /**
     * 查询所有
     */
    @ApiOperation(value="查询所有公司",notes="需要系统管理员权限-role:sys:admin")
    @RequiresRoles({"role:sys:admin"})
    @GetMapping("/company")
    public Result findAll(Pageable pageable) {
        Page<SysCompany> page = sysCompanyDao.findAll(pageable);
        return new Result(ResultCode.SUCCESS, page);
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value="根据id查询",notes="需要系统管理员权限-role:sys:admin")
    @RequiresRoles({"role:sys:admin"})
    @GetMapping("/company/{id}")
    public Result findById(@PathVariable("id") String id) {
        SysCompany sysCompany = sysCompanyDao.findById(id).get();
        return new Result(ResultCode.SUCCESS, sysCompany);
    }

    /**
     * 保存
     */
    @ApiOperation(value="新增公司",notes="需要系统管理员权限-role:sys:admin")
    @RequiresRoles({"role:sys:admin"})
    @PostMapping("/company")
    public Result save(@RequestBody SysCompany sysCompany) {
        sysCompany.setId(idWorker.nextId()+"");
        sysCompany.setCreateTime(new Date());
        sysCompanyDao.save(sysCompany);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 修改
     */
    @ApiOperation(value="修改公司信息",notes="需要系统管理员权限-role:sys:admin。只能修改名字和备注信息。")
    @RequiresRoles({"role:sys:admin"})
    @PutMapping("/company/{id}")
    public Result update(@PathVariable("id") String id, @RequestBody SysCompany sysCompany){
        SysCompany entity = sysCompanyDao.findById(id).get();

        entity.setName(sysCompany.getName());
        entity.setRemark(sysCompany.getRemark());
        sysCompanyDao.save(entity);

        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 删除
     */
    @ApiOperation(value="删除公司",notes="需要系统管理员权限-role:sys:admin")
    @RequiresRoles({"role:sys:admin"})
    @DeleteMapping("/company/{id}")
    public Result delete(@PathVariable("id") String id) {
        sysCompanyDao.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 给企业添加管理员用户
     */
    @PostMapping("/company/addAdminUser")
    @ApiOperation(value="给企业添加管理员用户",notes="需要系统管理员权限-role:sys:admin")
    @RequiresRoles({"role:sys:admin"})
    public Result addAdminUser(@RequestParam("companyId") String companyId, @RequestBody SysUser sysUser) {
        // 构建用户信息
        sysUser.setId(idWorker.nextId()+"");
        sysUser.setCompanyId(companyId);
        sysUser.setCreateTime(new Date());
        String password = new Md5Hash(sysUser.getPassword(),sysUser.getName(),3).toString();
        sysUser.setPassword(password);


        // 创建管理员角色
        SysRole sysRole = new SysRole();
        sysRole.setId(idWorker.nextId()+"");
        sysRole.setCompanyId(companyId);
        sysRole.setName("企业管理员");
        sysRole.setDescription("企业管理员");
        sysRole.setCode("role:company:admin");
        sysRole.setCreateTime(new Date());

        // 给管理员用户分配角色
        Set<SysRole> roles = new HashSet<>();
        roles.add(sysRole);
        sysUser.setRoles(roles);
        sysUserDao.save(sysUser);

        //给管理员角色分配权限
        List<SysPermission> sysPermission = sysPermissionDao.findAll();
        sysRole.setPermissions(new HashSet<>(sysPermission));
        sysRoleDao.save(sysRole);

        return new Result(ResultCode.SUCCESS);
    }


    /**
     * 根据企业id查询该公司所有用户
     */
    @ApiOperation(value="查看企业有用户",notes="需要系统管理员权限-role:sys:admin")
    @RequiresRoles({"role:sys:admin"})
    @GetMapping("/company/users")
    public Result findAllUsersByCompanyId(@RequestParam("companyId") String companyId, Pageable pageable) {
        Page<SysUser> page = sysUserDao.findAllByCompanyId(companyId, pageable);
        return new Result(ResultCode.SUCCESS, page);
    }

    /**
     * 查看企业下所有角色
     */
    @GetMapping("/company/roles")
    @ApiOperation(value="查看企业有角色",notes="需要系统管理员权限-role:sys:admin")
    @RequiresRoles({"role:sys:admin"})
    public Result findAllRolesByCompanyId(@RequestParam("companyId") String companyId, Pageable pageable){
        Page<SysRole> page = sysRoleDao.findAllByCompanyId(companyId, pageable);
        return new Result(ResultCode.SUCCESS, page);
    }


    @PutMapping("/company/assignAdminPermissions")
    @ApiOperation(value="给管理员角色分配权限",notes="需要系统管理员权限-role:sys:admin，会分配已知的所有权限。")
    @RequiresRoles({"role:sys:admin"})
    public Result assignAdminPermissions(@RequestParam("roleId") String roleId) {
        // 获取所有权限
        List<SysPermission> sysPermission = sysPermissionDao.findAll();

        // 设置权限
        SysRole sysRole = sysRoleDao.findById(roleId).get();
        sysRole.setPermissions(new HashSet<>(sysPermission));
        sysRoleDao.save(sysRole);

        return new Result(ResultCode.SUCCESS);
    }

}
