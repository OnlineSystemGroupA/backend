package com.stcos.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stcos.server.exception.ServerErrorException;
import lombok.experimental.UtilityClass;

/**
 * description
 *
 * @author Kekwy
 * @version 1.0
 * @since 2023/5/17 14:02
 */

@UtilityClass
public class JSONUtil {

    private final ObjectMapper MAPPER = new ObjectMapper();

    public <T> T parseObject(String jsonString, Class<T> type) {
        try {
            return MAPPER.readValue(jsonString, type);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(e);
        }
    }

    public String toJSONString(Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(e);
        }
    }

}
