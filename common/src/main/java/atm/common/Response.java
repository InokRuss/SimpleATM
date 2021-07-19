package atm.common;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response {
    private ErrorsCode errorsCode;
    private String account;
    private String isoCode;
    private int balance;

    public Response(final ErrorsCode errorsCode) {
        this.errorsCode = errorsCode;
        this.balance = 0;
        this.account = "";
        this.isoCode = "";
    }
}
