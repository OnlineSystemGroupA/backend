package com.stcos.server.service.impl;

import com.stcos.server.service.SettingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SettingServiceImpTest {

    @Autowired
    private SettingService settingService;

    @Test
    void getMarketingManager() {
        String managerId = settingService.getMarketingManager();
        assertEquals("op-2", managerId);
    }

    @Test
    void getTestingManager() {
        String managerId = settingService.getTestingManager();
        assertEquals("op-1", managerId);
    }

    @Test
    void getQualityManager() {
        String managerId = settingService.getQualityManager();
        assertEquals("op-3", managerId);
    }

    @Test
    void getSignatory() {
        String managerId = settingService.getSignatory();
        assertEquals("op-4", managerId);
    }
}
