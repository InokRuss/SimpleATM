package atm.server;

import lombok.Value;
import java.util.List;
import java.util.Date;

@Value
public class CardInfo {
    private final String pinCode;
    private final String cardNum;
    private final Date expireDate;
    private final int cvcCode;
}
