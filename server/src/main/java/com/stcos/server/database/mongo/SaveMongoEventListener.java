package com.stcos.server.database.mongo;

import com.stcos.server.model.form.AutoId;
import com.stcos.server.snowflake.SnowflakeIdWorker;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import java.util.Objects;

/**
 * 对mongodb保存过程进行监听，实现主键的递增替换
 */
@Component
public class SaveMongoEventListener extends AbstractMongoEventListener<Object> {
    private final MongoTemplate mongoTemplate;
    private final SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    public SaveMongoEventListener(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        snowflakeIdWorker = new SnowflakeIdWorker(0,0);
    }

    /**
     * 在保存的时候进行监听，通过反射对ID进行变更
     * 此时MongoDB data已经将要操作的实体类转为Document{@link Document}对象，
     * 所以这时候应该对document对象进行换ID
     * @param event
     */
    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event) {
        //得到操作的实体类对象
        Object source = event.getSource();
        //spring-mongo-data与MongoDB交互的document对象
        Document document = event.getDocument();
        if (Objects.nonNull(source)) {
            //利用反射进行相关操作
            ReflectionUtils.doWithFields(source.getClass(), field -> {
                //使操作的成员可访问
                ReflectionUtils.makeAccessible(field);
                //该字段是否使用此注解且是Number类型
                if (field.isAnnotationPresent(AutoId.class) && field.get(source) instanceof Number) {
                    String collectionName = source.getClass().getSimpleName().substring(0, 1).toLowerCase()
                            + source.getClass().getSimpleName().substring(1);
                    //判断document不能为空
                    Assert.notNull(document,"event.document must not be null");
                    //获取自增主键
                    Long newId = snowflakeIdWorker.nextId();
                    //对ID进行替换
                    if (document.get("_id").equals(-1L)){
                        document.put("_id", newId);
                        field.set(source,newId);
                    }
                }
            });
        }
        super.onBeforeSave(event);
    }
}