package com.sitedb.recom.tests;

import com.sitedb.recom.Application;
import com.sitedb.recom.controllers.SitesController;
import com.sitedb.recom.entities.Site;
import com.sitedb.recom.entities.Tag;
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
public class SitesControllerTest extends TestCase {

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

        SitesController sc = new SitesController();
        List<Site> result = sc.extractSitesFromResponse(response);

        assertEquals(result.size(), response.size());
        assertEquals(result.get(0).getName(), "site1");
        assertEquals(result.get(1).getName(), "site2");
        assertEquals(result.get(2).getName(), "site3");
    }

    public void tagsExtractorTest() {
        final List<Resource<Tag>> response = new ArrayList<>();
        Tag t1 = new Tag();
        t1.setName("tag1");
        Tag t2 = new Tag();
        t2.setName("tag2");
        Tag t3 = new Tag();
        t3.setName("tag3");

        response.add(new Resource<>(t1));
        response.add(new Resource<>(t2));
        response.add(new Resource<>(t3));

        SitesController sc = new SitesController();
        List<Tag> result = sc.extractTagsFromResponse(response);

        assertEquals(result.size(), response.size());
        assertEquals(result.get(0).getName(), "tag1");
        assertEquals(result.get(1).getName(), "tag2");
        assertEquals(result.get(2).getName(), "tag3");
    }
 }
