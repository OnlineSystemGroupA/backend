package com.stcos.server.service.impl;

import com.stcos.server.service.EmailService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/4/24 0:24
 */
public class EmailServiceImp implements EmailService, JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("用户委托完成！");
    }
}
