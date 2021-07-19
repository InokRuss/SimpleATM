package atm.server;

import lombok.Getter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Getter
@Value
public class AccountInfo {
    int accountId;
    String accountNum;
    String isoCode;
    int balance;
    List<CardInfo> cardList;
}
