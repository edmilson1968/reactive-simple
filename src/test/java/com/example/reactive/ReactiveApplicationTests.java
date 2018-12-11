package com.example.reactive;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class ReactiveApplicationTests {

	@Autowired 
	private MockMvc mockMvc;

	@Test
	public void testaFetchDocs() throws Exception {
		mockMvc.perform(get("/documents"))
				.andExpect(status().isOk())
				;							
				
	}

	@Test
	public void testaFetchStreaming() throws Exception {
		mockMvc.perform(get("/streams"))
				.andExpect(status().isOk())
				;							
				
	}

}
