package atm.server;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import atm.server.exception.CardNotFoundException;
import atm.common.ErrorsCode;
import atm.common.Response;
import java.util.Date;

@Log
@AllArgsConstructor
public class ATMService {

    private final ClientService clientService;

    public ErrorsCode validate(String cardNo, String pinCode){
        String cardRegex = "\\d{16}";

        if (! cardNo.matches(cardRegex)){
            return ErrorsCode.INCORRECT_CARD_NUMBER;
        }

        if (pinCode.length() != 4){
            return ErrorsCode.INCORRECT_PIN_CODE;
        }

        return ErrorsCode.NO_ERROR;
    }

    public Response getCardBalance(String cardNo, String pinCode){
        ClientInfo client;
        AccountInfo account;
        CardInfo card;

        ErrorsCode errorsCode = validate(cardNo, pinCode);
        if (!errorsCode.equals(ErrorsCode.NO_ERROR)) {
            return new Response(ErrorsCode.INCORRECT_CARD_NUMBER);
        }

        try {
          client = clientService.getClientByCardNum(cardNo);
        } catch (CardNotFoundException e) {
            log.info("Не найдена карта с указанным cardNo =  " + cardNo);
            return new Response(ErrorsCode.CARD_NOT_FOUND_OR_PIN_INCORRECT);
        }

        account = client.getClientAccountList().get(0);
        card = account.getCardList().get(0);

        if (!card.getPinCode().equals(pinCode)) {
            log.info("Не найден баланс для карты cardNo = " + cardNo + ", pinCode = " + pinCode);
            return new Response(ErrorsCode.CARD_NOT_FOUND_OR_PIN_INCORRECT);
        }

        if (card.getExpireDate().before(new Date())) {
            log.info("Неверный срок действия карты cardNo = " + cardNo + ", date = " + card.getExpireDate());
            return new Response(ErrorsCode.DATE_EXPIRED);
        }

        return new Response(ErrorsCode.NO_ERROR, account.getAccountNum(), account.getIsoCode(), account.getBalance());
    }
}