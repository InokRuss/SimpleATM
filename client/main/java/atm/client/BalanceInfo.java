package atm.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BalanceInfo{
    private final int balance;
    private final String isoCode;
}
