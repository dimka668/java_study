package com.klyshov.ioc.autowired;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.ContextLoaderListener;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by 16688641 on 31.10.2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader=AnnotationConfigContextLoader.class,
        classes=ApplicationContextTestAutowiredName.class)
public class FieldAutowiredIntegrationTest {

    @Autowired
    private ArbitraryDependency arbitraryDependencyy;

    @Test
    public void givenAutowired_WhenSetOnField_ThenDependencyResolved() {
        assertNotNull(arbitraryDependencyy);
        assertEquals("Arbitrary Dependency", arbitraryDependencyy.toString());
    }
}
