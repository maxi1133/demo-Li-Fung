package com.test.lifung.demo.service;

import com.test.lifung.demo.dto.ToDoAddRequestDto;
import com.test.lifung.demo.entity.ToDo;

import java.util.List;

/**
 * To do service.
 *
 * @author BMK
 * @since 20.08.2023
 */
public interface ToDoService {
    /**
     * Get to do list of user.
     *
     * @param userid user id.
     * @return todo list
     */
    List<ToDo> getToDoList(String userid);

    /**
     * Add or update to-do item.
     *
     * @param requestData client's data for add or update.
     * @return to-do item.
     */
    ToDo insertOrUpdateTodoItem(ToDoAddRequestDto requestData);

    /**
     * Delete todo item belong to user.
     *
     * @param userId user id.
     * @param todoKey to-do key.
     */
    void deleteTodoItem(String userId, String todoKey);
}
