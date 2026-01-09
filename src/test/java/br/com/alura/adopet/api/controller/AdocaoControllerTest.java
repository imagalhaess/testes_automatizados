package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class AdocaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private AdocaoService service;

    @Test
    void deveriaDevolverCodigo400ParaSolicitacaoDeAdocaoComErros() throws Exception {
        // ARRANGE
        String json = "{}";

        // ACT
        var response = mvc.perform(post("/adocoes")
                                           .content(json)
                                           .contentType(MediaType.APPLICATION_JSON)
                                           .with(csrf()))
                .andReturn().getResponse();

        // ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo200ParaSolicitacaoDeAdocaoSemErros() throws Exception {
        // ARRANGE
        String json = """
                 {
                    "idPet": 1,
                    "idTutor": 1,
                    "motivo": "Motivo qualquer"
                }""";

        // ACT
        var response = mvc.perform(post("/adocoes")
                                           .content(json)
                                           .contentType(MediaType.APPLICATION_JSON)
                                           .with(csrf()))
                .andReturn().getResponse();

        // ASSERT
        Assertions.assertEquals(200, response.getStatus());
    }

}