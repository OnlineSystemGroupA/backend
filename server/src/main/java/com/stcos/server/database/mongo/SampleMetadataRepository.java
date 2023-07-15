package com.stcos.server.database.mongo;

import com.stcos.server.model.file.SampleMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 样品元数据仓库，用于在 MongoDB 中进行样品元数据的 CRUD 操作
 * 注意：该仓库已经废弃
 */
@Deprecated
public interface SampleMetadataRepository extends MongoRepository<SampleMetadata, Long> {

    /**
     * 将样品元数据保存到数据库
     *
     * @param sampleMetadata 要保存的样品元数据对象
     */
    default public void saveSampleMetadata(SampleMetadata sampleMetadata) {
        insert(sampleMetadata);
    }

    /**
     * 更新数据库中的样品元数据
     *
     * @param sampleMetadata 更新后的样品元数据对象
     */
    default public void updateSampleMetadata(SampleMetadata sampleMetadata) {
        deleteById(sampleMetadata.getSampleMetadataId());
        insert(sampleMetadata);
    }

    /**
     * 根据样品元数据 ID 从数据库中删除样品元数据
     *
     * @param sampleMetadataId 要删除的样品元数据的 ID
     */
    default public void deleteBySampleMetadataId(Long sampleMetadataId) {
        deleteById(sampleMetadataId);
    }

    /**
     * 根据样品元数据 ID 从数据库中检索样品元数据
     *
     * @param sampleMetadataId 样品元数据的 ID
     * @return 指定 ID 的样品元数据对象，如果未找到则返回 null
     */
    default public SampleMetadata selectSampleMetadataById(Long sampleMetadataId) {
        return findById(sampleMetadataId).orElse(null);
    }
}
