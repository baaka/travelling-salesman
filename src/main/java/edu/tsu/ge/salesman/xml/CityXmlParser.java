package edu.tsu.ge.salesman.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;

public class CityXmlParser {
    private Cities cities;

    public CityXmlParser() {
        try {
            JAXBContext context = JAXBContext.newInstance(Cities.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            cities = (Cities) unmarshaller.unmarshal(getClass().getClassLoader().getResourceAsStream(getClass().getPackage().getName().replace('.', '/') + "/cities.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // transform and return cities from xml to cities from model
    public List<edu.tsu.ge.salesman.model.City> getCities() {
        List<edu.tsu.ge.salesman.model.City> result = new ArrayList<>();
        if (cities != null) {
            for (City city : cities.getCity()) {
                edu.tsu.ge.salesman.model.City c = new edu.tsu.ge.salesman.model.City(city.getX(), city.getY(), city.getName());
                result.add(c);
            }
        }
        return result;
    }
}
