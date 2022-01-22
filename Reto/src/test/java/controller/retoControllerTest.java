package controller;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.usuario;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import service.usuarioService;

import java.io.IOException;
import java.nio.channels.UnsupportedAddressTypeException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class retoControllerTest {

    @Autowired
    public retoController retoController;

    @MockBean
    public usuarioService usuarioService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(retoController).build();
    }

    public String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    public <T> T mapFromJson(String json, Class<T> clase)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clase);
    }

    @Test
    void testGetMappingisOk() throws Exception {

        String urlGet = "/usuarios";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(urlGet).
                accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        usuario[] usuarioList = mapFromJson(content, usuario[].class);
        assertTrue(usuarioList.length>0);

    }

    @Test
    void testPostMappingisOk() throws Exception{
        String urlPost = "/usuarios";
        usuario usuario = new usuario();
        usuario.setId(8);
        usuario.setFirst_name("Miguel");
        usuario.setLast_name("Lara");
        usuario.setEmail("yahoo");
        usuario.setAvatar("AvatarMiguel.html");

        String inputJson = mapToJson(usuario);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(urlPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Product is created successfully");


    }





























}