package com.example.login_demo.service.impl;

import com.example.login_demo.common.ServiceResultEnum;
import com.example.login_demo.dao.AdminUserMapper;
import com.example.login_demo.dao.NewBeeAdminUserTokenMapper;
import com.example.login_demo.entity.AdminUser;
import com.example.login_demo.entity.AdminUserToken;
import com.example.login_demo.service.AdminUserService;
import com.example.login_demo.utils.NumberUtil;
import com.example.login_demo.utils.SystemUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
    private NewBeeAdminUserTokenMapper newBeeAdminUserTokenMapper;

    @Override
    public String login(String userName, String password) {
        AdminUser loginAdminUser = adminUserMapper.login(userName, password);
        if (loginAdminUser != null) {
            //登录后即执行修改token的操作
            String token = getNewToken(System.currentTimeMillis() + "", loginAdminUser.getAdminUserId());
            AdminUserToken adminUserToken = newBeeAdminUserTokenMapper.selectByPrimaryKey(loginAdminUser.getAdminUserId());
            //当前时间
            Date now = new Date();
            //过期时间
            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);//过期时间 48 小时
            if (adminUserToken == null) {
                adminUserToken = new AdminUserToken();
                adminUserToken.setAdminUserId(loginAdminUser.getAdminUserId());
                adminUserToken.setToken(token);
                adminUserToken.setUpdateTime(now);
                adminUserToken.setExpireTime(expireTime);
                //新增一条token数据
                if (newBeeAdminUserTokenMapper.insertSelective(adminUserToken) > 0) {
                    //新增成功后返回
                    return token;
                }
            } else {
                adminUserToken.setToken(token);
                adminUserToken.setUpdateTime(now);
                adminUserToken.setExpireTime(expireTime);
                //更新
                if (newBeeAdminUserTokenMapper.updateByPrimaryKeySelective(adminUserToken) > 0) {
                    //修改成功后返回
                    return token;
                }
            }

        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }


    /**
     * 获取token值
     *
     * @param timeStr
     * @param userId
     * @return
     */
    private String getNewToken(String timeStr, Long userId) {
        String src = timeStr + userId + NumberUtil.genRandomNum(6);
        return SystemUtil.genToken(src);
    }

    @Override
    public Boolean logout(Long adminUserId) {
        return newBeeAdminUserTokenMapper.deleteByPrimaryKey(adminUserId) > 0;
    }
}