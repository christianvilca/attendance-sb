package org.parish.attendancesb.repositories;

import org.junit.jupiter.api.Test;
import org.parish.attendancesb.models.Catequesis;
import org.parish.attendancesb.models.ReceiverPerson;
import org.parish.attendancesb.models.datetime.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CatequesisRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CatequesisRepository repository;

    @Test
    public void should_find_no_catequesis_if_repository_is_empty() {
        Iterable catequesis = repository.findAll();

        assertThat(catequesis).isEmpty();
    }

    @Test
    public void should_store_a_catequesis() {
        Catequesis catequesis = repository.save(new Catequesis("Confirmacion 2022", "Lunes", new Time("03:30 PM"), new Time(18, 0), 10));

        assertThat(catequesis).hasFieldOrPropertyWithValue("name", "Confirmacion 2022");
        assertThat(catequesis).hasFieldOrPropertyWithValue("timeStart", new Time("03:30 PM"));
    }

    @Test
    public void should_find_all_catequeses() {
        Catequesis cat1 = new Catequesis("Confirmacion 2022 I", "Domingo", new Time("04:00 PM"), new Time("06:00 PM"), 10);
        entityManager.persist(cat1);
        Catequesis cat2 = new Catequesis("1ra Comunion 2022", "Sabado", new Time("03:30 PM"), new Time("06:00 PM"), 10);
        entityManager.persist(cat2);
        Catequesis cat3 = new Catequesis("Confirmacion 2022 II", "Viernes", new Time("05:30 PM"), new Time("07:30 PM"), 10);
        entityManager.persist(cat3);

        Iterable catequeses = repository.findAll();
        assertThat(catequeses).hasSize(3).contains(cat1, cat2, cat3);
    }

    @Test
    public void should_find_catequesis_by_id() {
        Catequesis cat1 = new Catequesis("Confirmacion 2022 I", "Domingo", new Time("04:00 PM"), new Time("06:00 PM"), 10);
        entityManager.persist(cat1);
        Catequesis cat2 = new Catequesis("1ra Comunion 2022", "Sabado", new Time("03:30 PM"), new Time("06:00 PM"), 10);
        entityManager.persist(cat2);

        Catequesis foundCatequesis = repository.findById(cat2.getId()).get();
        assertThat(foundCatequesis).isEqualTo(cat2);
    }

    @Test
    public void should_find_catequeses_by_name_containing_string() {
        Catequesis cat1 = new Catequesis("Confirmacion 2022 I", "Domingo", new Time("04:00 PM"), new Time("06:00 PM"), 10);
        entityManager.persist(cat1);
        Catequesis cat2 = new Catequesis("1ra Comunion 2022", "Sabado", new Time("03:30 PM"), new Time("06:00 PM"), 10);
        entityManager.persist(cat2);
        Catequesis cat3 = new Catequesis("Confirmacion 2022 II", "Viernes", new Time("05:30 PM"), new Time("07:30 PM"), 10);
        entityManager.persist(cat3);

        Iterable catequeses = repository.findByNameContaining("cion");
        assertThat(catequeses).hasSize(2).contains(cat1, cat3);
    }

    @Test
    public void should_update_catequesis_by_id() {
        Catequesis cat1 = new Catequesis("Confirmacion 2022 I", "Domingo", new Time("04:00 PM"), new Time("06:00 PM"), 10);
        entityManager.persist(cat1);
        Catequesis cat2 = new Catequesis("1ra Comunion 2022", "Sabado", new Time("03:30 PM"), new Time("06:00 PM"), 10);
        entityManager.persist(cat2);

        Catequesis updatedCat = new Catequesis("updated 2022 I", "Domingo", new Time("04:30 PM"), new Time("06:30 PM"), 10);
        Catequesis cat = repository.findById(cat2.getId()).get();
        cat.setName(updatedCat.getName());
        cat.setTimeStart(updatedCat.getTimeStart());
        cat.setTimeEnd(updatedCat.getTimeEnd());
        repository.save(cat);
        Catequesis checkCat = repository.findById(cat2.getId()).get();

        assertThat(checkCat.getId()).isEqualTo(cat2.getId());
        assertThat(checkCat.getName()).isEqualTo(updatedCat.getName());
        assertThat(checkCat.getTimeStart()).isEqualTo(updatedCat.getTimeStart());
        assertThat(checkCat.getTimeEnd()).isEqualTo(updatedCat.getTimeEnd());
    }

    @Test
    public void should_delete_catequesis_by_id() {
        Catequesis cat1 = new Catequesis("Confirmacion 2022 I", "Domingo", new Time("04:00 PM"), new Time("06:00 PM"), 10);
        entityManager.persist(cat1);
        Catequesis cat2 = new Catequesis("1ra Comunion 2022", "Sabado", new Time("03:30 PM"), new Time("06:00 PM"), 10);
        entityManager.persist(cat2);
        Catequesis cat3 = new Catequesis("Confirmacion 2022 II", "Viernes", new Time("05:30 PM"), new Time("07:30 PM"), 10);
        entityManager.persist(cat3);

        repository.deleteById(cat2.getId());
        Iterable catequeses = repository.findAll();
        assertThat(catequeses).hasSize(2).contains(cat1, cat3);
    }

    @Test
    public void should_delete_all_catequeses() {
        entityManager.persist(new Catequesis("Confirmacion 2022 I", "Domingo", new Time("04:00 PM"), new Time("06:00 PM"), 10));
        entityManager.persist(new Catequesis("1ra Comunion 2022", "Sabado", new Time("03:30 PM"), new Time("06:00 PM"), 10));
        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

}