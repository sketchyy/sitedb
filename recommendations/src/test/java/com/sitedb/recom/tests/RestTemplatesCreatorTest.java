package com.sitedb.recom.tests;

import com.sitedb.recom.Application;
import com.sitedb.recom.utils.RestTemplateCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by sketchyy on 21.05.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RestTemplatesCreatorTest {

    @Test
    public void testRestTemplateCreator () {
        final List<MediaType> halJson = MediaType.parseMediaTypes("application/hal+json");

        RestTemplate restTemplate = RestTemplateCreator.create();
        HttpMessageConverter converter = restTemplate.getMessageConverters().get(0);

        assert converter instanceof MappingJackson2HttpMessageConverter;
        assert ((MappingJackson2HttpMessageConverter) converter).getSupportedMediaTypes().containsAll(halJson);
    }
}
