package atm.client;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import atm.common.ErrorsCode;
import atm.common.Request;
import atm.common.Response;

import java.util.HashMap;
import java.util.Map;

@Log
@Service
public class ATMService {

    public BalanceInfo getClientBalance(String cardNum, String pinCode) {

        final String balanceUrl = "http://127.0.0.1:8080/hosts/1/clients/balance";

        Map<ErrorsCode, String> errMessages = new HashMap<ErrorsCode, String>() {{
            put(ErrorsCode.CARD_NOT_FOUND_OR_PIN_INCORRECT, "Карточка не найдена или не корректный пинкод");
            put(ErrorsCode.DATE_EXPIRED, "Истек срок действия карты");
            put(ErrorsCode.INCORRECT_CARD_NUMBER, "Не корректно введен номер карты: номер должен состоять из 16 цифр");
            put(ErrorsCode.INCORRECT_PIN_CODE, "Не корректно введен пинкод: пинкод должен состоять из 4 цифр");
        }};


        Request request = new Request(cardNum, pinCode);
        log.info("request.toString()" + request.toString());

        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.postForObject(
                balanceUrl, request, Response.class);
        log.info("response.toString" + response.toString());

        if (response.getErrorsCode() != ErrorsCode.NOERROR) {
            throw new ATMInternalErrorException(errMessages.get(response.getErrorsCode()));
        }

        return new BalanceInfo(response.getBalance(), response.getIsoCode() );
    }
}
