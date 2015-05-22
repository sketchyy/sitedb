package tests;

import com.sitedb.Application;
import com.sitedb.entities.Site;
import com.sitedb.entities.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by sketchyy on 21.05.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EntitiesTest extends TestCase {
    private final String FRONT_URI = "http://localhost:8082";
    private final String DB_URI = "http://localhost:8080";

    @Test
    public void testSiteHrefToFrontCreation() throws Exception {
        final int id = 1;
        final String EXPECTED = FRONT_URI + "/site?id=" + id;

        Site s = new Site();
        s.setIdByLink(DB_URI + "/sites/" + id);

        assertEquals(EXPECTED, s.getHrefToFront());
    }

    @Test
    public void testUserHrefToFrontCreation() throws Exception {
        final int id = 1;
        final String EXPECTED = FRONT_URI + "/user?id=" + id;

        User u = new User();
        u.setHrefToFront(DB_URI + "/users/" + id);

        assertEquals(EXPECTED, u.getHrefToFront());
    }
}
