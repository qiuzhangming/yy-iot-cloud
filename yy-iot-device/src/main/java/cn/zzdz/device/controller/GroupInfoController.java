package cn.zzdz.device.controller;


import cn.zzdz.common.dto.device.GroupInfoDto;
import cn.zzdz.common.entity.device.GroupInfo;
import cn.zzdz.common.entity.result.Result;
import cn.zzdz.common.entity.result.ResultCode;
import cn.zzdz.common.validated.CreatMethod;
import cn.zzdz.common.validated.SearchMethord;
import cn.zzdz.common.validated.UpdateMethod;
import cn.zzdz.device.service.GroupInfoService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

import static cn.zzdz.common.constData.ConstData.ID_SIZE;

@Api(tags="分组操作")
@Slf4j
@Validated
@RequestMapping("/group")
@RestController
public class GroupInfoController {

    @Autowired
    private GroupInfoService groupInfoService;

    @ApiOperation(value = "添加分组")
    @PostMapping("/")
    public Result save(@Validated({CreatMethod.class}) @RequestBody GroupInfoDto groupInfoDto) {
        log.info("添加分组：{}", JSON.toJSONString(groupInfoDto));
        GroupInfo groupInfo = new GroupInfo();
        BeanUtils.copyProperties(groupInfoDto, groupInfo);
        int count = groupInfoService.save(groupInfo);
        return getResult(count);
    }

    @ApiOperation(value = "根据id查找")
    @GetMapping("/{id}")
    public Result findById(@Size(min = ID_SIZE) @PathVariable("id") String id) {
        GroupInfo groupInfo = groupInfoService.findById(id);
        return new Result(ResultCode.SUCCESS, groupInfo);
    }

    @ApiOperation(value = "根据id删除")
    @PutMapping("/{id}")
    public Result updateById(@Size(min = ID_SIZE) @PathVariable("id") String id,@Validated({UpdateMethod.class}) @RequestBody GroupInfoDto groupInfoDto) {
        GroupInfo groupInfo = new GroupInfo();
        BeanUtils.copyProperties(groupInfoDto, groupInfo);
        int count = groupInfoService.updateById(id, groupInfo);
        return getResult(count);
    }

    @ApiOperation(value = "根据id更新")
    @DeleteMapping("/{id}")
    public Result deleyeById(@Size(min = ID_SIZE) @PathVariable("id") String id) {
        int count = groupInfoService.deleyeById(id);
        return getResult(count);
    }

    @ApiOperation(value = "根据 companyId，groupModel，groupType查找")
    @PostMapping("/findByCompanyIdAndModelAndType")
    public Result findByProjectAndModelAndType(@Validated({SearchMethord.class}) @RequestBody GroupInfoDto groupInfoDto,
                                               @RequestParam("companyId") String companyId,
                                               @RequestParam("model") int model,
                                               @RequestParam("type") int type,
                                               @RequestParam("pageNum") int pageNum,
                                               @RequestParam("pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<GroupInfo> groupInfos = groupInfoService.findByCompanyIdAndModelAndType(companyId,model,type);
        PageInfo<GroupInfo> pageInfo = new PageInfo<>(groupInfos);

        return new Result(ResultCode.SUCCESS, pageInfo);
    }

    /**
     * 根据影响行数返回结果
     * @param count
     * @return
     */
    private Result getResult(int count) {
        if (count > 0 ) {
            return new Result(ResultCode.SUCCESS,count);
        }
        return new Result(ResultCode.FAIL,count);
    }
}
