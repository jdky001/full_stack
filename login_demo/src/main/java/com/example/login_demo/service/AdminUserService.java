package com.example.login_demo.service;

public interface AdminUserService {

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    String login(String userName, String password);

    /**
     * 登出接口
     * @param adminUserId
     * @return
     */
    Boolean logout(Long adminUserId);   //


}
