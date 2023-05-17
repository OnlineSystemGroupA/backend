/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.5.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.stcos.server.controller.api;

import com.stcos.server.entity.dto.ProcessIdDto;
import com.stcos.server.entity.dto.SamplePathDto;
import com.stcos.server.entity.dto.TaskDto;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-09T00:09:21.323897900+08:00[Asia/Shanghai]")
@Validated
@Tag(name = "workflow", description = "the workflow API")
public interface WorkflowApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /workflow/tasks/{taskId}/complete : 完成任务
     * 将指定任务设置为已完成，并跳转至下一阶段
     *
     * @param taskId 待完成的任务 id (required)
     * @return 成功完成指定任务 (status code 200)
     *         or 指定任务对该用户不可见或当前用户无完成任务权限 (status code 403)
     *         or 指定任务不存在 (status code 404)
     */
    @Operation(
        operationId = "completeTask",
        summary = "完成任务",
        description = "将指定任务设置为已完成，并跳转至下一阶段",
        tags = { "workflow" },
        responses = {
            @ApiResponse(responseCode = "200", description = "成功完成指定任务"),
            @ApiResponse(responseCode = "403", description = "指定任务对该用户不可见或当前用户无完成任务权限"),
            @ApiResponse(responseCode = "404", description = "指定任务不存在")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/workflow/tasks/{taskId}/complete"
    )
    default ResponseEntity<Void> completeTask(
        @Parameter(name = "taskId", description = "待完成的任务 id", required = true, in = ParameterIn.PATH) @PathVariable("taskId") String taskId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /workflow/processes/{processId}/samples : 所有样品下载
     * 获取指定任务中的指定资源
     *
     * @param processId 指定流程实例 id (required)
     * @return 成功获取指定资源 (status code 200)
     *         or 指定任务或资源对该用户不可见 (status code 403)
     *         or 指定任务或资源不存在 (status code 404)
     */
    @Operation(
        operationId = "downloadSamples",
        summary = "所有样品下载",
        description = "获取指定任务中的指定资源",
        tags = { "workflow" },
        responses = {
            @ApiResponse(responseCode = "200", description = "成功获取指定资源", content = {
                @Content(mediaType = "multipart/form-data", array = @ArraySchema(schema = @Schema(implementation = MultipartFile.class)))
            }),
            @ApiResponse(responseCode = "403", description = "指定任务或资源对该用户不可见"),
            @ApiResponse(responseCode = "404", description = "指定任务或资源不存在")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/workflow/processes/{processId}/samples",
        produces = { "multipart/form-data" }
    )
    default ResponseEntity<List<MultipartFile>> downloadSamples(
        @Parameter(name = "processId", description = "指定流程实例 id", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("multipart/form-data"))) {
                    String exampleString = "Custom MIME type example not yet supported: multipart/form-data";
                    ApiUtil.setExampleResponse(request, "multipart/form-data", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /workflow/tasks/{taskId} : 获取单个任务
     * 通过 id 查询某一个任务，前提是该任务必须对当前登录用户可见
     *
     * @param taskId 待查询的任务 id (required)
     * @return 成功获取指定任务 (status code 200)
     *         or 指定任务对该用户不可见 (status code 403)
     *         or 指定任务不存在 (status code 404)
     */
    @Operation(
        operationId = "getTaskById",
        summary = "获取单个任务",
        description = "通过 id 查询某一个任务，前提是该任务必须对当前登录用户可见",
        tags = { "workflow" },
        responses = {
            @ApiResponse(responseCode = "200", description = "成功获取指定任务", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class))
            }),
            @ApiResponse(responseCode = "403", description = "指定任务对该用户不可见"),
            @ApiResponse(responseCode = "404", description = "指定任务不存在")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/workflow/tasks/{taskId}",
        produces = { "application/json" }
    )
    default ResponseEntity<TaskDto> getTaskById(
        @Parameter(name = "taskId", description = "待查询的任务 id", required = true, in = ParameterIn.PATH) @PathVariable("taskId") String taskId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"startUserId\" : \"startUserId\", \"processId\" : \"processId\", \"description\" : \"description\", \"taskName\" : \"taskName\", \"taskId\" : \"taskId\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /workflow/processes/{processId}/items/{itemName} : 获取流程资源
     * 获取指定任务中的指定资源
     *
     * @param processId 指定流程实例 id (required)
     * @param itemName 指定资源名 (required)
     * @return 成功获取指定资源 (status code 200)
     *         or 指定流程或资源对该用户不可见 (status code 403)
     *         or 指定流程或资源不存在 (status code 404)
     */
    @Operation(
        operationId = "getTaskItem",
        summary = "获取流程资源",
        description = "获取指定任务中的指定资源",
        tags = { "workflow" },
        responses = {
            @ApiResponse(responseCode = "200", description = "成功获取指定资源", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))
            }),
            @ApiResponse(responseCode = "403", description = "指定流程或资源对该用户不可见"),
            @ApiResponse(responseCode = "404", description = "指定流程或资源不存在")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/workflow/processes/{processId}/items/{itemName}",
        produces = { "application/json" }
    )
    default ResponseEntity<Object> getTaskItem(
        @Parameter(name = "processId", description = "指定流程实例 id", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId,
        @Parameter(name = "itemName", description = "指定资源名", required = true, in = ParameterIn.PATH) @PathVariable("itemName") String itemName
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /workflow/tasks : 获取所有任务
     * 获取与当前用户可见的任务列表
     *
     * @return 成功获取任务列表 (status code 200)
     */
    @Operation(
        operationId = "getTasks",
        summary = "获取所有任务",
        description = "获取与当前用户可见的任务列表",
        tags = { "workflow" },
        responses = {
            @ApiResponse(responseCode = "200", description = "成功获取任务列表", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TaskDto.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/workflow/tasks",
        produces = { "application/json" }
    )
    default ResponseEntity<List<TaskDto>> getTasks(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"startUserId\" : \"startUserId\", \"processId\" : \"processId\", \"description\" : \"description\", \"taskName\" : \"taskName\", \"taskId\" : \"taskId\" }, { \"startUserId\" : \"startUserId\", \"processId\" : \"processId\", \"description\" : \"description\", \"taskName\" : \"taskName\", \"taskId\" : \"taskId\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /workflow/processes/{processId}/samples : 上传样品
     * 上传与对应流程相关的样品文件
     *
     * @param processId 指定流程实例 id (required)
     * @param files  (optional)
     * @return 成功上传 (status code 201)
     *         or 没有上传文件 (status code 400)
     *         or 该任务对当前用户不可见或当前用户无修改权限，或文件校验不通过 (status code 403)
     *         or 指定任务不存在 (status code 404)
     *         or 上传文件失败 (status code 500)
     *         or 存储空间不足 (status code 507)
     */
    @Operation(
        operationId = "samplesUpload",
        summary = "上传样品",
        description = "上传与对应流程相关的样品文件",
        tags = { "workflow" },
        responses = {
            @ApiResponse(responseCode = "201", description = "成功上传", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SamplePathDto.class)))
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
        value = "/workflow/processes/{processId}/samples",
        produces = { "application/json" },
        consumes = { "multipart/form-data" }
    )
    default ResponseEntity<List<SamplePathDto>> samplesUpload(
        @Parameter(name = "processId", description = "指定流程实例 id", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId,
        @Parameter(name = "files", description = "") @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"path\" : \"path\", \"startUserId\" : \"startUserId\", \"description\" : \"description\", \"taskName\" : \"taskName\", \"taskId\" : \"taskId\" }, { \"path\" : \"path\", \"startUserId\" : \"startUserId\", \"description\" : \"description\", \"taskName\" : \"taskName\", \"taskId\" : \"taskId\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
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
        tags = { "process" },
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
     * PUT /workflow/processes/{processId}/items/{itemName} : 更新流程资源
     * 修改指定流程中的指定资源
     *
     * @param processId 指定流程实例 id (required)
     * @param itemName 指定资源名 (required)
     * @return 成功更新指定资源 (status code 200)
     *         or 成功创建指定资源 (status code 201)
     *         or 该流程实例对当前用户不可见或当前用户无修改权限 (status code 403)
     *         or 指定流程实例不存在 (status code 404)
     */
    @Operation(
        operationId = "updateTaskItem",
        summary = "更新流程资源",
        description = "修改指定流程中的指定资源",
        tags = { "workflow" },
        responses = {
            @ApiResponse(responseCode = "200", description = "成功更新指定资源"),
            @ApiResponse(responseCode = "201", description = "成功创建指定资源"),
            @ApiResponse(responseCode = "403", description = "该流程实例对当前用户不可见或当前用户无修改权限"),
            @ApiResponse(responseCode = "404", description = "指定流程实例不存在")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/workflow/processes/{processId}/items/{itemName}"
    )
    default ResponseEntity<Void> updateTaskItem(
        @Parameter(name = "processId", description = "指定流程实例 id", required = true, in = ParameterIn.PATH) @PathVariable("processId") String processId,
        @Parameter(name = "itemName", description = "指定资源名", required = true, in = ParameterIn.PATH) @PathVariable("itemName") String itemName
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
