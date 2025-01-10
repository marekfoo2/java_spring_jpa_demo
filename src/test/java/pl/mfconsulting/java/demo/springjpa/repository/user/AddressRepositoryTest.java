package pl.mfconsulting.java.demo.springjpa.repository.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pl.mfconsulting.java.demo.springjpa.repository.user.entity.Address;
import pl.mfconsulting.java.demo.springjpa.repository.user.entity.User;

@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    UserRepository userRepo;

    @Autowired
    AddressRepository addressRepo;

    @Test
    public void whenAddAddressToUserThenOK() {
        // given
        User userDB = createUser("aladyn");

        Address address = new Address("Domowy", "Wilk", "12-342", "Wro");
        address.setUser(userRepo.getReferenceById(userDB.getId()));

        // when
        Address addressDB = addressRepo.saveAndFlush(address);

        // then
        assertEquals(addressDB.getAddressType(), address.getAddressType());
    }

    @Test
    public void whenUpdateOnlyStreetThenUpdatedOnlyIt() {
        // given
        User user = createUser("alladyn");

        Address address = new Address("Domowy", "Wilk", "12-342", "Wro");
        address.setUser(userRepo.getReferenceById(user.getId()));

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
        User user = createUser("aladyn1");

        List<Address> addresses = addAddressesToUser(user, 10);

        // when
        List<Address> addressesDB = addressRepo.saveAllAndFlush(addresses);

        assertEquals(addressesDB.size(), 10);
    }

    @Test
    public void whenUpdateListOfAddAddressForUserThenOK() {
        // given
        User userDB = createUser("alladyn2");
        List<Address> addresses = addAddressesToUser(userDB, 10);
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
        User user = createUser("alladyn3");
        List<Address> addresses = addAddressesToUser(user, 5);
        addressRepo.saveAllAndFlush(addresses);

        // when
        Set<Address> addressesAfterFind = addressRepo.findAllAddressesForUser(user);

        // then
        assertEquals(addresses.size(), addressesAfterFind.size());
    }

    @Test
    public void whenUserExistsThenFetchHimAndAllAddresses() {
        // given
        User user = createUser("alladyn3");
        List<Address> addresses = addAddressesToUser(user, 5);
        addressRepo.saveAllAndFlush(addresses);

        // when
        Set<Address> addressesAfterFind = addressRepo.findAllAddressesWithUser(user);

        // then
        String loginAfterSeach = addressesAfterFind.stream().findFirst().get().getUser().getLogin();
        assertEquals(user.getLogin(), loginAfterSeach);
    }

    private List<Address> addAddressesToUser(User userDB, int quantOfAddresses) {
        List<Address> addresses = new ArrayList<>();
        for (int i = 0; i < quantOfAddresses; i++) {
            Address address = new Address("Domowy" + i, "Wilk" + 1, "12-342", "Wro");
            address.setUser(userRepo.getReferenceById(userDB.getId()));
            addresses.add(address);
        }

        return addresses;
    }

    private User createUser(String login) {
        User user = new User(login);
        user.setEmail("safsf");
        return userRepo.saveAndFlush(user);
    }

}
