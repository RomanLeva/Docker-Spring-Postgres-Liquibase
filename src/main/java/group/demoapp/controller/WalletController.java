package group.demoapp.controller;

import group.demoapp.controller.mapper.MapperDtoToView;
import group.demoapp.controller.view.WalletViewModel;
import group.demoapp.service.WalletService;
import group.demoapp.service.dto.WalletChangeDto;
import group.demoapp.service.dto.WalletDto;
import group.demoapp.service.dto.WalletRegisterDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    @Autowired
    private final MapperDtoToView viewMapper;

    @AllArgsConstructor
    @Getter
    public static class ControllerResponse {
        private String responseMessage;
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    public ResponseEntity<WalletViewModel> getWallet(@PathVariable Long WALLET_UUID) {
        WalletDto walletDto = walletService.getWalletById(WALLET_UUID);

        return ResponseEntity.ok(viewMapper.mapDtoToView(WalletViewModel.class, walletDto));
    }

    @PostMapping("/register")
    public ResponseEntity<ControllerResponse> registerWallet(@RequestBody WalletRegisterDto walletRegisterDto) {
        WalletDto walletDto = walletService.registerWallet(walletRegisterDto);

        return ResponseEntity.ok(new ControllerResponse("New wallet saved with UUID " + walletDto.getUuid()));
    }

    @PostMapping("/wallet")
    public ResponseEntity<ControllerResponse> changeWallet(@Valid @RequestBody WalletChangeDto walletChangeDto) {
        walletService.changeWallet(walletChangeDto);

        return ResponseEntity.ok(new ControllerResponse("Wallet changed."));
    }
}
