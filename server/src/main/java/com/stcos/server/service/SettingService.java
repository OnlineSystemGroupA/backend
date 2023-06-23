package com.stcos.server.service;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/23 20:23
 */
public interface SettingService {

    /**
     * 查找当前平台中市场部主管的用户 ID
     *
     * @return 市场部主管 ID
     */
    String getMarketingManager();

    /**
     * 查找当前平台中测试部主管的用户 ID
     *
     * @return 测试部主管 ID
     */
    String getTestingManager();

    /**
     * 查找当前平台中质量部主管的用户 ID
     *
     * @return 质量部主管 ID
     */
    String getQualityManager();


    /**
     * 查找当前平台中授权签字人的用户 ID
     *
     * @return 授权签字人 ID
     */
    String getSignatory();



}
