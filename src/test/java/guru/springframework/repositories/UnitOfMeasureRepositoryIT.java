package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {
    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeAll
    public void setUp() throws Exception {
    }

    @AfterAll
    public void tearDown() throws Exception {
    }

    @Test
    @DirtiesContext
    public void findByDescription() {
        String value = "Tablespoon";
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription(value);

        assertEquals(value, unitOfMeasureOptional.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() {
        String value = "Cup";
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription(value);

        assertEquals(value, unitOfMeasureOptional.get().getDescription());
    }
}