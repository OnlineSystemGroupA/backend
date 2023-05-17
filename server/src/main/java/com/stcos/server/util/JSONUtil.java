package com.stcos.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private ObjectMapper mapper;
//
//    public <T> T parseObject(String jsonString, Class<T> type) {
//        try {
//            mapper.readValue(jsonString, type);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        ret
//    }

}
