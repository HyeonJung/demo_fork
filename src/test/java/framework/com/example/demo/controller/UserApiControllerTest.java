package framework.com.example.demo.controller;




import com.fasterxml.jackson.databind.ObjectMapper;
import framework.com.example.demo.model.entity.User;
import framework.com.example.demo.model.network.Header;
import framework.com.example.demo.model.network.request.UserApiRequest;
import framework.com.example.demo.model.network.response.UserApiResponse;
import framework.com.example.demo.repository.UserRepository;
import framework.com.example.demo.service.UserApiLogicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import  static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserApiController.class)
class UserApiControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserApiLogicService userApiLogicService;

    @MockBean
    private UserApiResponse userApiResponse;

    @MockBean
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
    }

    @Test
    public void create() throws Exception {

        UserApiRequest userApiRequest = new UserApiRequest();
        userApiRequest.setAccount("Test");
        userApiRequest.setPassword("Test");
        userApiRequest.setPhoneNumber("010-1234-5678");
        Header<UserApiRequest> req = new Header<UserApiRequest>();
        req.setData(userApiRequest);
        String content = objectMapper.writeValueAsString(req);

        mvc.perform(post("/api/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void read() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}