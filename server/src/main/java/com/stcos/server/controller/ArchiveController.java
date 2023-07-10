package com.stcos.server.controller;

import com.stcos.server.controller.api.ArchiveApi;
import com.stcos.server.entity.dto.ProcessDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * description
 *
 * @author kura
 * @version 1.0
 * @since 2023/7/10 18:22
 */
public class ArchiveController implements ArchiveApi {
    @Override
    public ResponseEntity<List<ProcessDto>> getArchiveProcesses(Integer pageIndex, Integer numPerPage, String orderBy, Boolean assigned) {
        return ArchiveApi.super.getArchiveProcesses(pageIndex, numPerPage, orderBy, assigned);
    }

    @Override
    public ResponseEntity<Void> deleteArchiveProcess(String processId) {
        return ArchiveApi.super.deleteArchiveProcess(processId);
    }
}
