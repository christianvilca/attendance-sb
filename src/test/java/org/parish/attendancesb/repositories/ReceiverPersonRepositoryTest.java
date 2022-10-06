package org.parish.attendancesb.repositories;

import org.junit.jupiter.api.Test;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.models.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReceiverPersonRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ReceiverPersonRepository repository;

    @Test
    public void should_find_no_receiver_person_if_repository_is_empty() {
        Iterable receiverPeople = repository.findAll();

        assertThat(receiverPeople).isEmpty();
    }

    @Test
    public void should_store_a_receiver_person() {
        ReceiverPerson receiverPerson = repository.save(new ReceiverPerson("Code 1", "first nombre", "second nombre", new Group(1)));

        assertThat(receiverPerson).hasFieldOrPropertyWithValue("code", "Code 1");
        assertThat(receiverPerson).hasFieldOrPropertyWithValue("firstName", "first nombre");
        assertThat(receiverPerson).hasFieldOrPropertyWithValue("lastName", "second nombre");
        assertThat(receiverPerson).hasFieldOrPropertyWithValue("group.id", 1);
    }

    @Test
    public void should_find_all_receiver_person() {
        ReceiverPerson rp1 = new ReceiverPerson("Code 1", "first nombre 1", "second nombre 1", new Group(1));
        entityManager.persist(rp1);
        ReceiverPerson rp2 = new ReceiverPerson("Code 2", "first nombre 2", "second nombre 2", new Group(1));
        entityManager.persist(rp2);
        ReceiverPerson rp3 = new ReceiverPerson("Code 3", "first nombre 3", "second nombre 3", new Group(1));
        entityManager.persist(rp3);

        Iterable receiverPeople = repository.findAll();
        assertThat(receiverPeople).hasSize(3).contains(rp1, rp2, rp3);
    }

    @Test
    public void should_find_receiver_person_by_id() {
        ReceiverPerson rp1 = new ReceiverPerson("Code 1", "first nombre 1", "second nombre 1", new Group(1));
        entityManager.persist(rp1);
        ReceiverPerson rp2 = new ReceiverPerson("Code 2", "first nombre 2", "second nombre 2", new Group(1));
        entityManager.persist(rp2);

        ReceiverPerson foundReceiverPerson = repository.findById(rp2.getId()).get();
        assertThat(foundReceiverPerson).isEqualTo(rp2);
    }

    @Test
    public void should_find_receiverPeople_by_name_containing_string() {
        ReceiverPerson rp1 = new ReceiverPerson("Code 1", "first nombre 1", "second nombre 1", new Group(1));
        entityManager.persist(rp1);
        ReceiverPerson rp2 = new ReceiverPerson("Code 2", "nombre 2", "second nombre 2", new Group(1));
        entityManager.persist(rp2);
        ReceiverPerson rp3 = new ReceiverPerson("Code 3", "first-nombre 3", "second nombre 3", new Group(1));
        entityManager.persist(rp3);

        Iterable receiverPeople = repository.findByNameContaining("rst");
        assertThat(receiverPeople).hasSize(2).contains(rp1, rp3);
    }

    @Test
    public void should_find_receiverPeople_by_group() {
        ReceiverPerson rp1 = new ReceiverPerson("Code 1", "first nombre 1", "second nombre 1", new Group(1));
        entityManager.persist(rp1);
        ReceiverPerson rp2 = new ReceiverPerson("Code 2", "nombre 2", "second nombre 2", new Group(2));
        entityManager.persist(rp2);
        ReceiverPerson rp3 = new ReceiverPerson("Code 3", "first-nombre 3", "second nombre 3", new Group(1));
        entityManager.persist(rp3);

        Iterable receiverPeople = repository.findByGroup(new Group(1));
        assertThat(receiverPeople).hasSize(2).contains(rp1, rp3);
    }

    @Test
    public void should_update_receiverPeople_by_id() {
        ReceiverPerson rp1 = new ReceiverPerson("Code 1", "first nombre 1", "second nombre 1", new Group(1));
        entityManager.persist(rp1);
        ReceiverPerson rp2 = new ReceiverPerson("Code 2", "first nombre 2", "second nombre 2", new Group(1));
        entityManager.persist(rp2);

        ReceiverPerson updatedCat = new ReceiverPerson("Code other", "other name", "other name", new Group(1));
        ReceiverPerson cat = repository.findById(rp2.getId()).get();
        cat.setCode(updatedCat.getCode());
        cat.setFirstName(updatedCat.getFirstName());
        cat.setLastName(updatedCat.getLastName());
        repository.save(cat);
        ReceiverPerson checkCat = repository.findById(rp2.getId()).get();

        assertThat(checkCat.getId()).isEqualTo(rp2.getId());
        assertThat(checkCat.getCode()).isEqualTo(updatedCat.getCode());
        assertThat(checkCat.getFirstName()).isEqualTo(updatedCat.getFirstName());
        assertThat(checkCat.getLastName()).isEqualTo(updatedCat.getLastName());
    }

    @Test
    public void should_delete_catequesis_by_id() {
        ReceiverPerson rp1 = new ReceiverPerson("Code 1", "first nombre 1", "second nombre 1", new Group(1));
        entityManager.persist(rp1);
        ReceiverPerson rp2 = new ReceiverPerson("Code 2", "first nombre 2", "second nombre 2", new Group(1));
        entityManager.persist(rp2);
        ReceiverPerson rp3 = new ReceiverPerson("Code 3", "first nombre 3", "second nombre 3", new Group(1));
        entityManager.persist(rp3);

        repository.deleteById(rp2.getId());
        Iterable catequeses = repository.findAll();
        assertThat(catequeses).hasSize(2).contains(rp1, rp3);
    }

    @Test
    public void should_delete_all_catequeses() {
        entityManager.persist(new ReceiverPerson("Code 1", "first nombre 1", "second nombre 1", new Group(1)));
        entityManager.persist(new ReceiverPerson("Code 1", "first nombre 1", "second nombre 1", new Group(1)));
        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    public void contains_person() {
        ReceiverPerson rp1 = new ReceiverPerson("Code 1", "first nombre 1", "second nombre 1", new Group(1));
        entityManager.persist(rp1);
        ReceiverPerson rp2 = new ReceiverPerson("Code 1", "first nombre 1", "second nombre 1", new Group(1));
        //entityManager.persist(rp2);
        ReceiverPerson rp3 = new ReceiverPerson("Code 3", "first nombre 3", "second nombre 3", new Group(1));
        //entityManager.persist(rp3);

        boolean exists1 = repository.contains(rp2);
        assertThat(exists1).isTrue();

        boolean exists2 = repository.contains(rp3);
        assertThat(exists2).isFalse();
    }

}