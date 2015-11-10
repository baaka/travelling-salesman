package edu.tsu.ge.salesman.core.input;

import edu.tsu.ge.salesman.core.input.xml.Cities;
import edu.tsu.ge.salesman.core.model.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlInputParserTest {

    private Cities cities;

    @Before
    public void init() {
        try {
            JAXBContext context = JAXBContext.newInstance(Cities.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            cities = (Cities) unmarshaller.unmarshal(new File("./src/test/resources/cities.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCities() throws Exception {
        List<City> result = new ArrayList<>();
        if (cities != null) {
            for (edu.tsu.ge.salesman.core.input.xml.City city : cities.getCity()) {
                edu.tsu.ge.salesman.core.model.City c = new edu.tsu.ge.salesman.core.model.City(city.getX(), city.getY(), city.getName());
                result.add(c);
            }
        }

        Assert.assertNotEquals(null, cities);
        Assert.assertEquals(5, cities.getCity().size());

        System.out.println(cities.getCity());
    }
}