package com.example.login_demo.api.admin;

import com.example.login_demo.api.admin.param.AdminLoginParam;
import com.example.login_demo.common.Constants;
import com.example.login_demo.service.AdminUserService;
import com.example.login_demo.utils.Result;
import com.example.login_demo.utils.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(value = "v1", tags = "后台管理系统管理员模块接口")
@RequestMapping("/manage-api/v1")
public class NewBeeAdminManageUserAPI {

    @Resource
    private AdminUserService adminUserService;

    private static final Logger logger = LoggerFactory.getLogger(NewBeeAdminManageUserAPI.class);

    @ApiOperation(value="登录验证", notes="登录验证")
    @ApiImplicitParam(name = "adminLoginParam", value = "登录参数", required = true, dataType = "AdminLoginParam")
    @RequestMapping(value = "/adminUser/login", method = RequestMethod.POST)
    public Result<String> login(@RequestBody AdminLoginParam adminLoginParam) {
        if (adminLoginParam == null || StringUtils.isEmpty(adminLoginParam.getUserName()) || StringUtils.isEmpty(adminLoginParam.getPasswordMd5())) {
            return ResultGenerator.genFailResult("用户名或密码不能为空");
        }
        String loginResult = adminUserService.login(adminLoginParam.getUserName(), adminLoginParam.getPasswordMd5());
        logger.info("manage login api,adminName={},loginResult={}", adminLoginParam.getUserName(), loginResult);

        //登录成功
        if (!StringUtils.isEmpty(loginResult) && loginResult.length() == Constants.TOKEN_LENGTH) {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }
        //登录失败
        return ResultGenerator.genFailResult(loginResult);
    }
}
