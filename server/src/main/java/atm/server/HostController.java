package atm.server;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import atm.common.Request;
import atm.common.Response;
import atm.server.ClientInfo;
import atm.server.entity.ClientEntity;
import atm.server.exception.CardNotFoundException;
import atm.server.exception.HostNotFoundException;
import atm.server.ATMService;
import atm.server.ClientService;
import java.util.List;

@RestController
@AllArgsConstructor
@Log
public class HostController {

    private ClientService clientService;

    @GetMapping("/hosts")
    public String getHostsInfo() {
        return "{data: \"1 host available\"}";
    }

    @GetMapping("/hosts/{hostId}")
    public String getHostInfo(@PathVariable Long hostId) {
        if (hostId == 1) {
            return "{data: \"Host " + hostId + " ready\"}";
        }else{
            return "{data: \"Host " + hostId + " not ready\"}";
        }
    }

    @GetMapping("/hosts/{hostId}/clients")
    public List<ClientInfo> getClientsInfo(@PathVariable Long hostId) {
        if (hostId != 1) {
            throw new HostNotFoundException();
        }

        return clientService.getAllClients();
    }

    @PostMapping("/hosts/{hostId}/clients/balance")
    public Response getBalance(@PathVariable("hostId") Long hostId,
                               @RequestBody Request request) {
        if (hostId != 1) {
            throw new RuntimeException("Host " + hostId + " is not ready!");
        }

        log.info(request.toString());

        ATMService atmService = new ATMService(clientService);

        Response response = atmService.getCardBalance(request.getCardNum(), request.getPinCode());
        log.info(response.toString());
        return response;
    }
}