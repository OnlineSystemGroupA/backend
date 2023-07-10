/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.5.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.stcos.server.controller.api;

import com.stcos.server.entity.dto.*;
import com.stcos.server.entity.process.ProcessDetails;
import com.stcos.server.util.ApiUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-21T21:36:55.308582200+08:00[Asia/Shanghai]")
@Validated
@Tag(name = "workflow", description = "the workflow API")
public interface WorkflowApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /workflow/processes/{processId}/complete_task : 完成任务
     * 完成现阶段任务使流程进入下一阶段
     *
     * @param processId 流程实例 Id (required)
     * @param passable 是否通过，流程遇到网关用于决定运行方向 (optional)
     * @return 成功完成指定任务 (status code 200)
     *         or 指定流程对该用户不可见或当前用户无完成任务权限 (status code 403)
     *         or 指定流程不存在 (status code 404)
     *         or 未满足完成条件 (status code 460)
     */
    @Operation(
            operationId = "completeTask",
            summary = "完成任务",
            description = "完成现阶段任务使流程进入下一阶段",
            tags = { "workflow" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "成功完成指定任务"),
                    @ApiResponse(responseCode = "403", description = "指定流程对该用户不可见或当前用户无完成任务权限"),
                    @ApiResponse(responseCode = "404", description = "指定流程不存在"),
                    @ApiResponse(responseCode = "460", description = "未满足完成条件")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/workflow/processes/{processId}/complete_task"
    )
    default ResponseEntity<Void> completeTask(
            @Parameter(name = "processId", description = "流程实例 Id", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId,
            @Parameter(name = "passable", description = "是否通过，流程遇到网关用于决定运行方向", in = ParameterIn.QUERY) @Valid @RequestParam(value = "passable", required = false) Boolean passable
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /workflow/processes/{processId}/files/sample : 所有样品下载
     * 获取指定任务中的指定资源
     *
     * @param processId 指定流程实例 id (required)
     * @return 成功获取指定资源 (status code 200)
     *         or 指定任务或资源对该用户不可见 (status code 403)
     *         or 指定任务或资源不存在 (status code 404)
     */
    @Operation(
            operationId = "downloadFileSample",
            summary = "所有样品下载",
            description = "获取指定任务中的指定资源",
            tags = { "workflow" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "成功获取指定资源", content = {
                            @Content(mediaType = "application/octet-stream", schema = @Schema(implementation = org.springframework.core.io.Resource.class))
                    }),
                    @ApiResponse(responseCode = "403", description = "指定任务或资源对该用户不可见"),
                    @ApiResponse(responseCode = "404", description = "指定任务或资源不存在")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/workflow/processes/{processId}/files/sample",
            produces = { "application/octet-stream" }
    )
    default ResponseEntity<org.springframework.core.io.Resource> downloadFileSample(
            @Parameter(name = "processId", description = "指定流程实例 id", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /workflow/processes/{processId}/forms/{formName} : 获取表单
     * 获取表单
     *
     * @param processId 指定流程实例 id (required)
     * @param formName 期望获取的表单名称 (required)
     * @return 成功获取指定资源 (status code 200)
     *         or 指定流程或表单对该用户不可见 (status code 403)
     *         or 指定流程或表单不存在 (status code 404)
     */
    @Operation(
        operationId = "getForm",
        summary = "获取表单",
        description = "获取表单",
        tags = { "workflow" },
        responses = {
            @ApiResponse(responseCode = "200", description = "成功获取指定资源", content = {
                @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "403", description = "指定流程或表单对该用户不可见"),
            @ApiResponse(responseCode = "404", description = "指定流程或表单不存在")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/workflow/processes/{processId}/forms/{formName}",
        produces = { "text/plain" }
    )
    default ResponseEntity<String> getForm(
        @Parameter(name = "processId", description = "指定流程实例 id", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId,
        @Parameter(name = "formName", description = "期望获取的表单名称", required = true, in = ParameterIn.PATH) @PathVariable("formName") String formName
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /workflow/processes/{processId}/forms : 获取表单
     * 获取表单
     *
     * @param processId 指定流程实例 id (required)
     * @return 成功获取可见表单列表 (status code 200)
     *         or 指定流程不存在 (status code 404)
     */
    @Operation(
            operationId = "getFormMetadata",
            summary = "获取表单",
            description = "获取表单",
            tags = { "workflow" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "成功获取可见表单列表", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FormMetadataDto.class)))
                    }),
                    @ApiResponse(responseCode = "404", description = "指定流程不存在")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/workflow/processes/{processId}/forms",
            produces = { "application/json" }
    )
    default ResponseEntity<List<FormMetadataDto>> getFormMetadata(
            @Parameter(name = "processId", description = "指定流程实例 id", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"formName\" : \"formName\", \"formMetadataId\" : 0 }, { \"formName\" : \"formName\", \"formMetadataId\" : 0 } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /workflow/processes/{processId}/details : 获取流程详情
     * 获取流程详情
     *
     * @param processId 流程实例 Id (required)
     * @return 成功获取指定流程的详细信息 (status code 200)
     * or 指定流程对该用户不可见 (status code 403)
     * or 指定流程不存在 (status code 404)
     */
    @Operation(
        operationId = "getProcessDetails",
        summary = "获取流程详情",
        description = "获取流程详情",
        tags = { "workflow" },
        responses = {
            @ApiResponse(responseCode = "200", description = "成功获取指定流程的详细信息"),
            @ApiResponse(responseCode = "403", description = "指定流程对该用户不可见"),
            @ApiResponse(responseCode = "404", description = "指定流程不存在")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/workflow/processes/{processId}/details"
    )
    default ResponseEntity<ProcessDetails> getProcessDetails(
        @Parameter(name = "processId", description = "流程实例 Id", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /workflow/processes : 获取流程实例
     /**
     * GET /workflow/processes : 获取流程实例
     * 获取与当前用户相关的流程实例
     *
     * @param pageIndex 需要查询的页号 (required)
     * @param numPerPage 每页的项目条目数 (required)
     * @param orderBy 用于排序的字段 (required)
     * @param assigned 其值为 true 表示获取当前有任务被分配给当前用户的项目 (optional)
     * @return 成功获取流程实例列表 (status code 200)
     */
    @Operation(
            operationId = "getProcesses",
            summary = "获取流程实例",
            description = "获取与当前用户相关的流程实例",
            tags = { "workflow" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "成功获取流程实例列表", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProcessDto.class)))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/workflow/processes",
            produces = { "application/json" }
    )
    default ResponseEntity<List<ProcessDto>> getProcesses(
            @NotNull @Parameter(name = "pageIndex", description = "需要查询的页号", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
            @NotNull @Parameter(name = "numPerPage", description = "每页的项目条目数", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "numPerPage", required = true) Integer numPerPage,
            @NotNull @Parameter(name = "orderBy", description = "用于排序的字段", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "orderBy", required = true) String orderBy,
            @Parameter(name = "assigned", description = "其值为 true 表示获取当前有任务被分配给当前用户的项目", in = ParameterIn.QUERY) @Valid @RequestParam(value = "assigned", required = false) Boolean assigned
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"processId\" : \"processId\", \"taskName\" : \"taskName\", \"assignee\" : \"assignee\", \"startUser\" : \"startUser\", \"title\" : \"title\", \"projectId\" : \"projectId\", \"taskId\" : \"taskId\", \"startDate\" : \"startDate\" }, { \"processId\" : \"processId\", \"taskName\" : \"taskName\", \"assignee\" : \"assignee\", \"startUser\" : \"startUser\", \"title\" : \"title\", \"projectId\" : \"projectId\", \"taskId\" : \"taskId\", \"startDate\" : \"startDate\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /workflow/processes/{processId}/forms/{formName} : 更新或创建表单
     * 更新或创建表单
     *
     * @param processId 指定流程实例 id (required)
     * @param formName 期望操作的表单名称 (required)
     * @param body  (optional)
     * @return 成功更新或创建表单 (status code 200)
     *         or 该流程实例对当前用户不可见或当前用户无修改权限 (status code 403)
     *         or 指定流程实例不存在 (status code 404)
     */
    @Operation(
            operationId = "putForm",
            summary = "更新或创建表单",
            description = "更新或创建表单",
            tags = { "workflow" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "成功更新或创建表单"),
                    @ApiResponse(responseCode = "403", description = "该流程实例对当前用户不可见或当前用户无修改权限"),
                    @ApiResponse(responseCode = "404", description = "指定流程实例不存在")
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/workflow/processes/{processId}/forms/{formName}",
            consumes = { "text/plain" }
    )
    default ResponseEntity<Void> putForm(
            @Parameter(name = "processId", description = "指定流程实例 id", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId,
            @Parameter(name = "formName", description = "期望操作的表单名称", required = true, in = ParameterIn.PATH) @PathVariable("formName") String formName,
            @Parameter(name = "formData", description = "表单数据") @Valid @RequestBody(required = true) String formData
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /workflow/start : 启动流程
     * 开启一个新的委托流程
     *
     * @return 启动成功 (status code 200)
     */
    @Operation(
        operationId = "startProcess",
        summary = "启动流程",
        description = "开启一个新的委托流程",
        tags = { "workflow" },
        responses = {
            @ApiResponse(responseCode = "200", description = "启动成功", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessIdDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/workflow/start",
        produces = { "application/json" }
    )
    default ResponseEntity<ProcessIdDto> startProcess(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"processId\" : \"processId\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /workflow/processes/{processId}/files/sample : 上传样品
     * 上传与对应流程相关的样品文件
     *
     * @param processId 指定流程实例 id (required)
     * @param file  (optional)
     * @return 成功上传 (status code 201)
     *         or 没有上传文件 (status code 400)
     *         or 该任务对当前用户不可见或当前用户无修改权限，或文件校验不通过 (status code 403)
     *         or 指定任务不存在 (status code 404)
     *         or 上传文件失败 (status code 500)
     *         or 存储空间不足 (status code 507)
     */
    @Operation(
            operationId = "uploadFileSample",
            summary = "上传样品",
            description = "上传与对应流程相关的样品文件",
            tags = { "workflow" },
            responses = {
                    @ApiResponse(responseCode = "201", description = "成功上传", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = FileMetadataDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "没有上传文件"),
                    @ApiResponse(responseCode = "403", description = "该任务对当前用户不可见或当前用户无修改权限，或文件校验不通过"),
                    @ApiResponse(responseCode = "404", description = "指定任务不存在"),
                    @ApiResponse(responseCode = "500", description = "上传文件失败"),
                    @ApiResponse(responseCode = "507", description = "存储空间不足")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/workflow/processes/{processId}/files/sample",
            produces = { "application/json" },
            consumes = { "multipart/form-data" }
    )
    default ResponseEntity<FileMetadataDto> uploadFileSample(
            @Parameter(name = "processId", description = "指定流程实例 id", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId,
            @Parameter(name = "file", description = "") @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"fileName\" : \"fileName\", \"fileMetadataId\" : 0, \"fileType\" : \"fileType\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /workflow/processes/count : 流程实例总数
     * 获取与当前用户相关的流程实例总数
     *
     * @return ok (status code 200)
     */
    @Operation(
            operationId = "getProcessCount",
            summary = "流程实例总数",
            description = "获取与当前用户相关的流程实例总数",
            tags = { "workflow" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "ok", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/workflow/processes/count",
            produces = { "application/json" }
    )
    default ResponseEntity<Integer> getProcessCount(

    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /workflow/processes/{processId}/participants : 分配人员
     * 为指定流程设置对应的流程参与者
     *
     * @param processId 指定流程实例 ID (required)
     * @param userIdDto 目标员工的 ID (optional)
     * @return ok (status code 200)
     *         or 指定流程对当前登录用户不可见 (status code 403)
     *         or 指定流程或用户不存在 (status code 404)
     *         or 指定用户所在部门不符合要求 (status code 409)
     */
    @Operation(
            operationId = "setParticipant",
            summary = "分配人员",
            description = "为指定流程设置对应的流程参与者",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ok"),
                    @ApiResponse(responseCode = "403", description = "指定流程对当前登录用户不可见"),
                    @ApiResponse(responseCode = "404", description = "指定流程或用户不存在"),
                    @ApiResponse(responseCode = "409", description = "指定用户所在部门不符合要求")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/workflow/processes/{processId}/participants",
            consumes = { "application/json" }
    )
    default ResponseEntity<Void> setParticipant(
            @Parameter(name = "processId", description = "指定流程实例 ID", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId,
            @Parameter(name = "UserIdDto", description = "目标员工的 ID") @Valid @RequestBody(required = false) UserIdDto userIdDto
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /workflow/processes/{processId}/files/forms/{formName} : 下载表单
     * 下载表单 PDF 文件
     *
     * @param processId 目标流程实例的 ID (required)
     * @param formName 目标表单的名称 (required)
     * @return ok (status code 200)
     */
    @Operation(
            operationId = "downloadFileForm",
            summary = "下载表单",
            description = "下载表单 PDF 文件",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ok", content = {
                            @Content(mediaType = "application/pdf", schema = @Schema(implementation = org.springframework.core.io.Resource.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/workflow/processes/{processId}/files/forms/{formName}",
            produces = { "application/pdf" }
    )
    default ResponseEntity<org.springframework.core.io.Resource> downloadFileForm(
            @Parameter(name = "processId", description = "目标流程实例的 ID", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId,
            @Parameter(name = "formName", description = "目标表单的名称", required = true, in = ParameterIn.PATH) @PathVariable("formName") String formName
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /workflow/processes/{processId}/files/forms/{formName}
     *
     * @param processId 目标流程实例的 ID (required)
     * @param formName 目标表单的名称 (required)
     * @param file 需要上传的表单 PDF 文件 (required)
     * @return ok (status code 200)
     */
    @Operation(
            operationId = "uploadFileForm",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ok")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/workflow/processes/{processId}/files/forms/{formName}",
            consumes = { "multipart/form-data" }
    )
    default ResponseEntity<Void> uploadFileForm(
            @Parameter(name = "processId", description = "目标流程实例的 ID", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId,
            @Parameter(name = "formName", description = "目标表单的名称", required = true, in = ParameterIn.PATH) @PathVariable("formName") String formName,
            @Parameter(name = "file", description = "需要上传的表单 PDF 文件", required = true) @RequestPart(value = "file", required = true) MultipartFile file
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /workflow/processes/{processId}/details : 删除流程
     * 管理员删除流程
     *
     * @param processId 流程实例 Id (required)
     * @return 成功删除指定流程 (status code 200)
     *         or 指定流程对该用户不可见 (status code 403)
     *         or 指定流程不存在 (status code 404)
     */
    @Operation(
            operationId = "deleteProcess",
            summary = "删除流程",
            description = "管理员删除流程",
            tags = { "admin" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "成功删除指定流程"),
                    @ApiResponse(responseCode = "403", description = "指定流程对该用户不可见"),
                    @ApiResponse(responseCode = "404", description = "指定流程不存在")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/workflow/processes/{processId}/details"
    )
    default ResponseEntity<Void> deleteProcess(
            @Parameter(name = "processId", description = "流程实例 Id", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
