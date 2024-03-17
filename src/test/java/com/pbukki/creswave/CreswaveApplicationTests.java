package com.pbukki.creswave;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@SpringBootTest

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthControllerTest.class,
        PostControllerTest.class,
        CommentControllerTest.class
})
class CreswaveApplicationTests {

    @Test
    void contextLoads() {
    }

}
