package cn.zzdz.security.controller;

import cn.zzdz.common.entity.security.ProfileResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {

    public static void main(String[] args) {
        String password = new Md5Hash("123456","admin",3).toString();
        System.out.println(password);
    }

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected String companyId;

    /**
     * 使用shiro获取
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setResAnReq(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        //获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //1.subject获取所有的安全数据集合
        PrincipalCollection principals = subject.getPrincipals();
        if(principals != null && !principals.isEmpty()){
            //2.获取安全数据
            ProfileResult profileResult = (ProfileResult) principals.getPrimaryPrincipal();
            this.companyId = profileResult.getCompanyId();
            System.out.println("companyId:" + companyId);
        }
    }
}
