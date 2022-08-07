package org.parish.attendancesb.repositories;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.Group;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.models.datetime.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GroupRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    GroupRepository repository;

    @Test
    public void should_find_no_group_if_repository_is_empty() {
        Iterable groups = repository.findAll();

        assertThat(groups).isEmpty();
    }

    @Test
    public void should_store_a_group() {
        Group group = repository.save(new Group("San Mateo", new Catequesis(1)));

        assertThat(group).hasFieldOrPropertyWithValue("catequesis.id", 1);
        assertThat(group).hasFieldOrPropertyWithValue("name", "San Mateo");
    }

    @Test
    public void should_find_all_group() {
        Group gr1 = new Group("San Mateo", new Catequesis(1));
        entityManager.persist(gr1);
        Group gr2 = new Group("San Marcos", new Catequesis(1));
        entityManager.persist(gr2);
        Group gr3 = new Group("San Lucas", new Catequesis(1));
        entityManager.persist(gr3);

        Iterable group = repository.findAll();
        assertThat(group).hasSize(3).contains(gr1, gr2, gr3);
    }

    @Test
    public void should_find_group_by_id() {
        Group gr1 = new Group("San Mateo", new Catequesis(1));
        entityManager.persist(gr1);
        Group gr2 = new Group("San Marcos", new Catequesis(1));
        entityManager.persist(gr2);

        Group foundGroup = repository.findById(gr2.getId()).get();
        assertThat(foundGroup).isEqualTo(gr2);
    }

    @Test
    public void should_find_group_by_name_containing_string() {
        Group gr1 = new Group("San Mateo", new Catequesis(1));
        entityManager.persist(gr1);
        Group gr2 = new Group("San Marcos", new Catequesis(1));
        entityManager.persist(gr2);
        Group gr3 = new Group("San Lucas", new Catequesis(1));
        entityManager.persist(gr3);

        Iterable catequeses = repository.findAllByNameContaining("Ma");
        assertThat(catequeses).hasSize(2).contains(gr1, gr2);
    }

    @Test
    public void should_find_group_by_catequesis() {
        Group gr1 = new Group("San Mateo", new Catequesis(1));
        entityManager.persist(gr1);
        Group gr2 = new Group("San Marcos", new Catequesis(2));
        entityManager.persist(gr2);
        Group gr3 = new Group("San Lucas", new Catequesis(1));
        entityManager.persist(gr3);

        Iterable receiverPeople = repository.findAllByCatequesis(new Catequesis(1));
        assertThat(receiverPeople).hasSize(2).contains(gr1, gr3);
    }

    @Test
    public void should_update_group_by_id() {
        Group gr1 = new Group("San Mateo", new Catequesis(1));
        entityManager.persist(gr1);
        Group gr2 = new Group("San Marcos", new Catequesis(1));
        entityManager.persist(gr2);

        Group updatedGr = new Group("updated 2022 I", new Catequesis(1));
        Group gr = repository.findById(gr2.getId()).get();
        gr.setName(updatedGr.getName());
        repository.save(gr);
        Group checkCat = repository.findById(gr2.getId()).get();

        assertThat(checkCat.getId()).isEqualTo(gr2.getId());
        assertThat(checkCat.getName()).isEqualTo(updatedGr.getName());
    }

    @Test
    public void should_delete_group_by_id() {
        Group gr1 = new Group("San Mateo", new Catequesis(1));
        entityManager.persist(gr1);
        Group gr2 = new Group("San Marcos", new Catequesis(1));
        entityManager.persist(gr2);
        Group gr3 = new Group("San Lucas", new Catequesis(1));
        entityManager.persist(gr3);

        repository.deleteById(gr2.getId());
        Iterable groups = repository.findAll();
        assertThat(groups).hasSize(2).contains(gr1, gr3);
    }

    @Test
    public void should_delete_all_groups() {
        entityManager.persist(new Group("San Lucas", new Catequesis(1)));
        entityManager.persist(new Group("San Marcos", new Catequesis(1)));
        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

}