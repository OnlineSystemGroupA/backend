package com.stcos.server.listener;

import com.stcos.server.entity.process.TaskName;
import org.springframework.stereotype.Component;

/*
    __  ___          ___ ____      ______            __                  __  ____             ______  __    _      __
   /  |/  /___  ____/ (_) __/_  __/ ____/___  ____  / /__________ ______/ /_/ __ \_________ _/ __/ /_/ /   (_)____/ /____  ____  ___  _____
  / /|_/ / __ \/ __  / / /_/ / / / /   / __ \/ __ \/ __/ ___/ __ `/ ___/ __/ / / / ___/ __ `/ /_/ __/ /   / / ___/ __/ _ \/ __ \/ _ \/ ___/
 / /  / / /_/ / /_/ / / __/ /_/ / /___/ /_/ / / / / /_/ /  / /_/ / /__/ /_/ /_/ / /  / /_/ / __/ /_/ /___/ (__  ) /_/  __/ / / /  __/ /
/_/  /_/\____/\__,_/_/_/  \__, /\____/\____/_/ /_/\__/_/   \__,_/\___/\__/_____/_/   \__,_/_/  \__/_____/_/____/\__/\___/_/ /_/\___/_/
                         /____/
 */

/**
 * "市场部修改合同草稿"任务监听器
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/9 16:18
 */
@Component
public class ModifyContractDraftListener extends OperatorTaskListener {

    public ModifyContractDraftListener() {
        super(TaskName.NAME_TASK_13);
    }
}
