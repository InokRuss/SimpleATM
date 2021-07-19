package atm.server;

import lombok.Value;
import java.util.List;

@Value
public class ClientInfo {
    String firstName;
    String lastName;
    List<AccountInfo> clientAccountList;
}
