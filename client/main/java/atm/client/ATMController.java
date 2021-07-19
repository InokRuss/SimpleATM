package atm.client;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log
public class ATMController {
    private ATMService atmService;

    @GetMapping("/ATMs")
    public String getATMsStatus() {
        return "1 ATM готов";
    }

    @GetMapping("/ATMs/{ATMId}/clients/balance/{cardNum}/{pinCode}")
    public BalanceInfo getClientBalance(
            @PathVariable("ATMId") Long ATMId,
            @PathVariable("cardNum") String cardNum,
            @PathVariable("pinCode") String pinCode) {

        log.info("ATMId=" + ATMId+ " cardNum=" + cardNum + " pinCode=" + pinCode);

        if (ATMId != 1) {
            throw new ATMInternalErrorException("Внутренняя ошибка ATM");
        }

        return atmService.getClientBalance(cardNum, pinCode);
    }
}