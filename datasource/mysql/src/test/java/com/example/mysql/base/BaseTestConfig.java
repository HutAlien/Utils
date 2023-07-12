package com.example.mysql.base;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/7/12 9:38
 * @version:
 */
public interface BaseTestConfig {

    static void getResult(MockMvc mockMvc, MockHttpServletRequestBuilder builder) {
        String content = null;
        try {
            content = mockMvc.perform(builder)
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(content);
    }
}
