package edu.tsu.ge.salesman.core.input;

import edu.tsu.ge.salesman.core.model.City;

import java.io.InputStream;
import java.util.List;

public interface InputParser {
    void init(InputStream inputStream);

    List<City> getCities();
}
