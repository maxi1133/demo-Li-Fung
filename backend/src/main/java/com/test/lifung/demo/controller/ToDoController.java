package com.test.lifung.demo.controller;

import com.test.lifung.demo.common.response.Response;
import com.test.lifung.demo.common.utils.CommonUtils;
import com.test.lifung.demo.dto.ToDoAddRequestDto;
import com.test.lifung.demo.entity.ToDo;
import com.test.lifung.demo.service.ToDoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * To-Do controller.
 *
 * @author BMK
 * @since 20.08.2023
 */
@RestController("/todo")
@Slf4j
public class ToDoController {
    /**
     * To-do service
     */
    @Autowired
    private ToDoService toDoService;

    /**
     * Request
     */
    @Autowired
    private HttpServletRequest request;

    /*
     * Get all to-do list of user.
     *
     * @return to-do list.
     */
    @Operation(summary = "Get todo list of user.")
    @ApiResponse(description = "Get data successfully.", responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))})
    @ApiResponse(description = "Error.", responseCode = "500", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))})
    @GetMapping
    public Response getToDoListByUserId() {
        final List<ToDo> data = this.toDoService.getToDoList(CommonUtils.getUserId(this.request));
        return Response.ok("Get successfully.", data);
    }

    /**
     * Create or update todo item
     *
     * @param data data from client.
     * @return todo item.
     */
    @Operation(summary = "Create of update todo item.")
    @ApiResponse(description = "Action success.", responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))})
    @ApiResponse(description = "Error.", responseCode = "500", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))})
    @PostMapping
    public Response createOrUpdateTodoList(@Validated ToDoAddRequestDto data) {
        data.setUserId(CommonUtils.getUserId(this.request));

        final ToDo todoItem = this.toDoService.insertOrUpdateTodoItem(data);
        if (Objects.isNull(todoItem)) {
            return Response.error("Action failed.");
        }
        return Response.ok("Action successfully.", todoItem);
    }

    /**
     * Delete todo item.
     *
     * @param todoId todo key
     * @return success/ error message.
     */
    @Operation(summary = "Delete to do item.")
    @ApiResponse(description = "Delete successfully.", responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))})
    @ApiResponse(description = "Error.", responseCode = "500", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))})
    @DeleteMapping("deleteById/{todoId}")
    private Response deleteTodoItemById(@PathVariable("todoId") @NotBlank final String todoId) {
        this.toDoService.deleteTodoItem(CommonUtils.getUserId(this.request), todoId);
        return Response.ok("Deleted successfully.");
    }
}
