package com.stcos.server.entity.process;

import java.util.List;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/19 16:45
 */
public interface TaskConfig {

    String getEmailSubject();

    String getEmailText();

    List<String> getReadableForms();

    List<String> getWritableForms();

    List<String> getWillDisReadableForms();

    List<String> getWillDisWritableForms();

}
