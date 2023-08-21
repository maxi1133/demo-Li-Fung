package com.test.lifung.demo.service.impl;

import com.test.lifung.demo.common.utils.CommonUtils;
import com.test.lifung.demo.constant.CommonConstants;
import com.test.lifung.demo.dto.ToDoAddRequestDto;
import com.test.lifung.demo.entity.ToDo;
import com.test.lifung.demo.service.ToDoService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Todo service implementation.
 *
 * @author BMK
 * @since 20.08.2023
 */
@Service
@Slf4j
public class ToDoServiceImpl implements ToDoService {

    /**
     * Base todo list.
     */
    private final static ArrayList<ToDo> currentToDoList = new ArrayList<>();

    /**
     * Init data.
     */
    @PostConstruct
    private void init() {
        currentToDoList.add(
                ToDo.builder().userId(CommonConstants.FIRST_USER_ID).id(CommonUtils.genRandomKey())
                        .title("Go somewhere").description("Go to place at address xxx, xxx")
                        .startTime(LocalDateTime.now().withDayOfMonth(1)).build()
        );
        currentToDoList.add(
                ToDo.builder().userId(CommonConstants.FIRST_USER_ID).id(CommonUtils.genRandomKey())
                        .title("Buy something").description("Go to market and buy xxx")
                        .startTime(LocalDateTime.now().withDayOfMonth(2)).build()
        );
        currentToDoList.add(
                ToDo.builder().userId(CommonConstants.SECOND_USER_ID).id(CommonUtils.genRandomKey())
                        .title("Go to mall").description("Go to mall and meet xxx")
                        .startTime(LocalDateTime.now().withDayOfMonth(1)).build()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ToDo> getToDoList(final String userid) {
        return currentToDoList.stream().filter(todo -> todo.getUserId().equals(userid)).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ToDo insertOrUpdateTodoItem(final ToDoAddRequestDto dataForAdd) {
        Assert.notNull(dataForAdd, "Adding or updating data must not be null.");
        // Insert
        if (Objects.isNull(dataForAdd.getId())) {
            final ToDo newToDoItem = ToDo.builder()
                    .id(CommonUtils.genRandomKey())
                    .title(dataForAdd.getTitle())
                    .description(dataForAdd.getDescription())
                    .startTime(dataForAdd.getStartTime())
                    .build();
            currentToDoList.add(newToDoItem);
            return newToDoItem;
        }
        // Update
        final Optional<ToDo> existedToDoOptional = currentToDoList.stream()
                .filter(todo -> todo.getId().equals(dataForAdd.getId()))
                .findFirst();
        if (existedToDoOptional.isEmpty()) {
            return null;
        }


        final ToDo existedToDoObject = existedToDoOptional.get();
        final ToDo newItem = ToDo.builder()
                .id(existedToDoObject.getId())
                .userId(existedToDoObject.getUserId())
                .title(dataForAdd.getTitle())
                .description(dataForAdd.getDescription())
                .startTime(dataForAdd.getStartTime())
                .build();

        if (Collections.replaceAll(currentToDoList, existedToDoObject, newItem)) {
            return newItem;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTodoItem(final String userId, final String todoKey) {
        Assert.isTrue(Objects.nonNull(userId), "User id must not be null.");
        Assert.isTrue(Objects.nonNull(todoKey), "Todo key must not be null.");

        final var existedTodoItemOption = currentToDoList.stream()
                .filter(todo -> todo.getUserId().equals(userId) && todo.getId().equals(todoKey))
                .findFirst();
        Assert.isTrue(existedTodoItemOption.isPresent(), "Not existed data in list.");

        final ToDo existedTodoItem = existedTodoItemOption.get();
        currentToDoList.remove(existedTodoItem);
    }
}
