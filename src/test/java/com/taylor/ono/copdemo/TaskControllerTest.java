package com.taylor.ono.copdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taylor.ono.copdemo.entities.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void tasks_returns200_andTaskResponse() throws Exception {
        mockMvc.perform(post("/tasks")
                .content("{\"title\": \"New Task\", \"dueDate\": \"2021-01-01\", \"priority\": 1 }")
                .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("title").value("New Task"))
                    .andExpect(jsonPath("dueDate").value("2021-01-01T00:00:00.000+0000"))
                    .andExpect(jsonPath("priority").value(1));
    }

    @Test
    public void task_returns200_andTaskResponse() throws Exception {
        mockMvc.perform(post("/tasks")
                .content("{\"title\": \"New Task\", \"dueDate\": \"2021-01-01\", \"priority\": 1 }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)));
    }

    @Test
    public void task_withId_returns200_andTaskResponse() throws Exception {
        String result = mockMvc.perform(post("/tasks")
                .content("{\"title\": \"New Task\", \"dueDate\": \"2021-01-01\", \"priority\": 1 }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Task task = objectMapper.readValue(result, Task.class);

        mockMvc.perform(get("/tasks/{id}", task.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(task.getId()));
    }

    @Test
    public void task_withBadId_returns404() throws Exception {
        mockMvc.perform(get("/tasks/bad-id"))
                .andExpect(status().isNotFound());
    }
}