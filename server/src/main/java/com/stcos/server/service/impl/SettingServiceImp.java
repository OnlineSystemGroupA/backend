package com.stcos.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stcos.server.database.mysql.SettingMapper;
import com.stcos.server.entity.Setting;
import com.stcos.server.exception.ServerErrorException;
import com.stcos.server.service.SettingService;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/6/23 17:22
 */

@Service
public class SettingServiceImp extends ServiceImpl<SettingMapper, Setting> implements SettingService {

    @Override
    public String getMarketingManager() {
        try {
            return baseMapper.selectByKey("marketingManager").getVal();
        } catch (NullPointerException e) {
            // 一般情况下，该返回值不能为 null，
            // 若为 null 则说明发生错误
            throw new ServerErrorException(e);
        }
    }

    @Override
    public String getTestingManager() {
        try {
            return baseMapper.selectByKey("testingManager").getVal();
        } catch (NullPointerException e) {
            throw new ServerErrorException(e);
        }
    }

    @Override
    public String getQualityManager() {
        try {
            return baseMapper.selectByKey("qualityManager").getVal();
        } catch (NullPointerException e) {
            throw new ServerErrorException(e);
        }
    }

    @Override
    public String getSignatory() {
        try {
            return baseMapper.selectByKey("signatory").getVal();
        } catch (NullPointerException e) {
            throw new ServerErrorException(e);
        }
    }


}
