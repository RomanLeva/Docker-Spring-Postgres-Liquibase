package group.demoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import group.demoapp.controller.WalletController;
import group.demoapp.controller.view.WalletViewModel;
import group.demoapp.service.dto.WalletChangeDto;
import group.demoapp.service.dto.WalletRegisterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testWalletRegisterIsSuccessful() throws Exception {
        WalletRegisterDto walletRegisterDto = new WalletRegisterDto(100);
        mockMvc.perform(post("/api/v1/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(walletRegisterDto)))
                        .andExpect(status().isOk());
    }

    @Test
    void testGetWalletIsSuccessful() throws Exception {
        WalletRegisterDto walletRegisterDto = new WalletRegisterDto(100);
        mockMvc.perform(post("/api/v1/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(walletRegisterDto)))
                        .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/wallets/1")).andExpect(status().isOk());
    }

    @Test
    void testWalletChangeIsSuccessful() throws Exception {
        WalletChangeDto walletChangeDto = new WalletChangeDto(1L, 100, WalletChangeDto.OPERATION_TYPE.DEPOSIT);
        WalletRegisterDto walletRegisterDto = new WalletRegisterDto(100);

        mockMvc.perform(post("/api/v1/register")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(walletRegisterDto)))
                        .andExpect(status().isOk());
        mockMvc.perform(post("/api/v1/wallet")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(walletChangeDto)))
                        .andExpect(status().isOk());
    }

}

