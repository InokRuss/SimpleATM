package atm.server;

import atm.server.entity.AccountEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import atm.server.entity.ClientEntity;
import atm.server.entity.CardEntity;
import atm.server.exception.AccountNotFoundException;
import atm.server.exception.CardNotFoundException;
import atm.server.exception.ClientNotFoundException;
import atm.server.repository.AccountRepository;
import atm.server.repository.CardRepository;
import atm.server.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ClientService {
    private final CardRepository cardRepository;
    private final ClientRepository clientRepository;
//    private final AccountRepository accountRepository;

    public ClientInfo getClient(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        Set<AccountEntity> accountEntitySet = clientEntity.getAccountList();
        List<AccountInfo> accountInfoSet = new ArrayList<>();

        for (AccountEntity account : accountEntitySet) {

            Set<CardEntity> cardEntitySet = account.getCardList();
            List<CardInfo> cardInfoSet = new ArrayList<>();
            for (CardInfo card : cardInfoSet) {
                cardInfoSet.add(
                        new CardInfo(card.getPinCode(), card.getCardNum(), card.getExpireDate(), card.getCvcCode())
                );
            }

            accountInfoSet.add(
                    new AccountInfo(account.getId().intValue(), account.getAccountNum(), account.getIsoCode(),
                            account.getBalance(), cardInfoSet)
            );
        }

        return new ClientInfo(clientEntity.getFirstName(), clientEntity.getLastName(), accountInfoSet);
    }

    public List<ClientInfo> getAllClients() {
        Iterable<ClientEntity> clientIterable = clientRepository.findAll();
        List<ClientInfo> clients = new ArrayList<>();

        clientIterable.forEach(
                client -> {
                    Set<AccountEntity> accountEntitySet = client.getAccountList();
                    List<AccountInfo> accountInfoSet = new ArrayList<>();

                    for (AccountEntity account : accountEntitySet) {

                        Set<CardEntity> cardEntitySet = account.getCardList();
                        List<CardInfo> cardInfoSet = new ArrayList<>();
                        for (CardEntity card : cardEntitySet) {
                            cardInfoSet.add(
                                    new CardInfo(card.getPinCode(), card.getCardNum(), card.getExpireDate(), card.getCvcCode())
                            );
                        }

                        accountInfoSet.add(
                                new AccountInfo(account.getId().intValue(), account.getAccountNum(), account.getIsoCode(),
                                     account.getBalance(), cardInfoSet)
                        );
                    }

                    clients.add(
                            new ClientInfo(client.getFirstName(), client.getLastName(), accountInfoSet));
                }
        );
        return clients;
    }

    public ClientInfo getClientByCardNum(String cardNum) {
        CardEntity card = cardRepository.findByCardNum(cardNum).orElseThrow(CardNotFoundException::new);
        AccountEntity account = card.getAccount_id();
        ClientEntity client = account.getClient_id();

        List<CardInfo> cardInfoSet = new ArrayList<>();
        cardInfoSet.add(
                new CardInfo(card.getPinCode(), card.getCardNum(), card.getExpireDate(), card.getCvcCode())
        );

        List<AccountInfo> accountInfoSet = new ArrayList<>();
        accountInfoSet.add(
                new AccountInfo(account.getId().intValue(), account.getAccountNum(), account.getIsoCode(),
                     account.getBalance(), cardInfoSet)
        );

        return new ClientInfo(client.getFirstName(), client.getLastName(), accountInfoSet);
    }
}