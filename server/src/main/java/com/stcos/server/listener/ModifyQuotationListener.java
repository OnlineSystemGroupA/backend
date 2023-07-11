package com.stcos.server.listener;

import com.stcos.server.entity.process.TaskName;
import org.springframework.stereotype.Component;

/*
    __  ___          ___ ____      ____              __        __  _             __    _      __
   /  |/  /___  ____/ (_) __/_  __/ __ \__  ______  / /_____ _/ /_(_)___  ____  / /   (_)____/ /____  ____  ___  _____
  / /|_/ / __ \/ __  / / /_/ / / / / / / / / / __ \/ __/ __ `/ __/ / __ \/ __ \/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
 / /  / / /_/ / /_/ / / __/ /_/ / /_/ / /_/ / /_/ / /_/ /_/ / /_/ / /_/ / / / / /___/ (__  ) /_/  __/ / / /  __/ /
/_/  /_/\____/\__,_/_/_/  \__, /\___\_\__,_/\____/\__/\__,_/\__/_/\____/_/ /_/_____/_/____/\__/\___/_/ /_/\___/_/
                         /____/
 */

/**
 * "市场部修改报价"任务监听器
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:19
 */
@Component
public class ModifyQuotationListener extends OperatorTaskListener {
    public ModifyQuotationListener() {
        super(TaskName.NAME_TASK_10);
    }
}
