package com.stcos.server.service.impl;

import com.stcos.server.database.mysql.SettingMapper;
import com.stcos.server.entity.Setting;
import com.stcos.server.exception.ServerErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SettingServiceImpTestMock{

    @InjectMocks
    private SettingServiceImp settingService;

    @Mock
    private SettingMapper settingMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getMarketingManager() {
        Setting marketingManagerSetting = new Setting();
        marketingManagerSetting.setSettingKey("marketingManager");
        marketingManagerSetting.setSettingVal("op-2");
        when(settingMapper.selectById("marketingManager")).thenReturn(marketingManagerSetting);

        String managerId = settingService.getMarketingManager();
        assertEquals("op-2", managerId);
        verify(settingMapper, times(1)).selectById("marketingManager");
    }

    @Test
    void getTestingManager() {
        Setting testingManagerSetting = new Setting();
        testingManagerSetting.setSettingKey("testingManager");
        testingManagerSetting.setSettingVal("op-1");
        when(settingMapper.selectById("testingManager")).thenReturn(testingManagerSetting);

        String managerId = settingService.getTestingManager();
        assertEquals("op-1", managerId);
        verify(settingMapper, times(1)).selectById("testingManager");
    }

    @Test
    void getQualityManager() {
        Setting qualityManagerSetting = new Setting();
        qualityManagerSetting.setSettingKey("qualityManager");
        qualityManagerSetting.setSettingVal("op-3");
        when(settingMapper.selectById("qualityManager")).thenReturn(qualityManagerSetting);

        String managerId = settingService.getQualityManager();
        assertEquals("op-3", managerId);
        verify(settingMapper, times(1)).selectById("qualityManager");
    }

    @Test
    void getSignatory() {
        Setting signatorySetting = new Setting();
        signatorySetting.setSettingKey("signatory");
        signatorySetting.setSettingVal("op-4");
        when(settingMapper.selectById("signatory")).thenReturn(signatorySetting);

        String managerId = settingService.getSignatory();
        assertEquals("op-4", managerId);
        verify(settingMapper, times(1)).selectById("signatory");
    }
}
