package tests;

import com.sitedb.Application;
import com.sitedb.controllers.UsersController;
import com.sitedb.entities.Site;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.hateoas.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sketchyy on 21.05.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ControllersTest extends TestCase {

    @Test
    public void sitesExtractorTest() {
        final List<Resource<Site>> response = new ArrayList<>();
        Site s1 = new Site();
        s1.setName("site1");
        Site s2 = new Site();
        s2.setName("site2");
        Site s3 = new Site();
        s3.setName("site3");

        response.add(new Resource<>(s1));
        response.add(new Resource<>(s2));
        response.add(new Resource<>(s3));

        UsersController sc = new UsersController();
        List<Site> result = sc.extractSites(response);

        assertEquals(result.size(), response.size());
        assertEquals(result.get(0).getName(), "site1");
        assertEquals(result.get(1).getName(), "site2");
        assertEquals(result.get(2).getName(), "site3");
    }

 }
