package atm.common;

import lombok.Value;

@Value
public class Request {
    String cardNum;
    String pinCode;
}
