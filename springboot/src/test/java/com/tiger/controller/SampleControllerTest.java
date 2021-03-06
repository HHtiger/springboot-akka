package com.tiger.controller;


import com.tiger.com.tiger.PicServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ComponentScan(
        basePackages = "com.tiger",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.tiger.model")}
)
public class SampleControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PicServer picServer;

    @InjectMocks
    private SampleController sampleController;

    @Before
    public void before() {
        this.mockMvc = standaloneSetup(sampleController).build();
    }

    @Test
    public void testGetPic() throws Exception {

        String testRes = "noBig-noSmall";

        when(picServer.getPic()).thenReturn(testRes);

        mockMvc.perform(get("/getPic").accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(testRes));
    }

}