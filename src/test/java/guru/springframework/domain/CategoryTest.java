package guru.springframework.domain;

//import org.junit.Before;
//import org.junit.Test;

//import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    Category category;

    @BeforeAll
    public void setUp(){
        category = new Category();
    }
    @Test
    public void getId() {
        Long idValue = 4L;

        category.setId(idValue);

        assertEquals(idValue, category.getId(), 0);

    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}