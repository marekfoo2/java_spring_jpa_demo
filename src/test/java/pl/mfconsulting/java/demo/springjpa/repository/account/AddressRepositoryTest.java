package pl.mfconsulting.java.demo.springjpa.repository.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Account;
import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Address;

@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    AccountRepository userRepo;

    @Autowired
    AddressRepository addressRepo;

    @Test
    public void whenAddAddressToUserThenOK() {
        // given
        Account accountDB = createUser("aladyn");

        Address address = new Address("Domowy", "Wilk", "12-342", "Wro");
        address.setUser(userRepo.getReferenceById(accountDB.getId()));

        // when
        Address addressDB = addressRepo.saveAndFlush(address);

        // then
        assertEquals(addressDB.getAddressType(), address.getAddressType());
    }

    @Test
    public void whenUpdateOnlyStreetThenUpdatedOnlyIt() {
        // given
        Account account = createUser("alladyn");

        Address address = new Address("Domowy", "Wilk", "12-342", "Wro");
        address.setUser(userRepo.getReferenceById(account.getId()));

        Address addressDB = addressRepo.saveAndFlush(address);
        addressDB.setStreet("Wilk1");

        // when
        Address addressDBAfterUpdate = addressRepo.saveAndFlush(address);

        assertEquals(addressDBAfterUpdate.getAddressType(), "Domowy");
        // and
        assertEquals(addressDBAfterUpdate.getStreet(), "Wilk1");
    }

    @Test
    public void whenAddListOfAddAddressToUserThenOK() {
        // given
        Account account = createUser("aladyn1");

        List<Address> addresses = addAddressesToUser(account, 10);

        // when
        List<Address> addressesDB = addressRepo.saveAllAndFlush(addresses);

        assertEquals(addressesDB.size(), 10);
    }

    @Test
    public void whenUpdateListOfAddAddressForUserThenOK() {
        // given
        Account accountDB = createUser("alladyn2");
        List<Address> addresses = addAddressesToUser(accountDB, 10);
        List<Address> addressesDB = addressRepo.saveAllAndFlush(addresses);

        // when
        addressesDB.forEach(addr -> addr.setAddressType("praca"));
        List<Address> addressesDBAfterUpdate = addressRepo.saveAllAndFlush(addresses);

        // then
        addressesDBAfterUpdate.forEach(address -> assertEquals(address.getAddressType(), "praca"));
    }

    @Test
    public void whenUserExistsThenFindAllAddresses() {
        // given
        Account account = createUser("alladyn3");
        List<Address> addresses = addAddressesToUser(account, 5);
        addressRepo.saveAllAndFlush(addresses);

        // when
        Set<Address> addressesAfterFind = addressRepo.findAllAddressesForAccount(account);

        // then
        assertEquals(addresses.size(), addressesAfterFind.size());
    }

    @Test
    public void whenUserExistsThenFetchHimAndAllAddresses() {
        // given
        Account account = createUser("alladyn3");
        List<Address> addresses = addAddressesToUser(account, 5);
        addressRepo.saveAllAndFlush(addresses);

        // when
        Set<Address> addressesAfterFind = addressRepo.findAllAddressesWithAccount(account);

        // then
        String loginAfterSeach = addressesAfterFind.stream().findFirst().get().getUser().getLogin();
        assertEquals(account.getLogin(), loginAfterSeach);
    }

    private List<Address> addAddressesToUser(Account accountDB, int quantOfAddresses) {
        List<Address> addresses = new ArrayList<>();
        for (int i = 0; i < quantOfAddresses; i++) {
            Address address = new Address("Domowy" + i, "Wilk" + 1, "12-342", "Wro");
            address.setUser(userRepo.getReferenceById(accountDB.getId()));
            addresses.add(address);
        }

        return addresses;
    }

    private Account createUser(String login) {
        Account account = new Account(login);
        account.setEmail("safsf");
        return userRepo.saveAndFlush(account);
    }

}
