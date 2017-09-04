package com.tiger.controller;


import com.tiger.com.tiger.PicServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest2SpringBoot {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PicServer picServer;

    @Test
    public void testGetPic() throws Exception {

        String testRes = "noBig-noSmall";

        when(picServer.getPic()).thenReturn(testRes);

        mockMvc.perform(get("/getPic"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(testRes));

    }

}