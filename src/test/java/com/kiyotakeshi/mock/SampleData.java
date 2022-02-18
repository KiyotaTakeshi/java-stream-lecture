package com.kiyotakeshi.mock;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.kiyotakeshi.beans.Car;
import com.kiyotakeshi.beans.Person;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SampleData {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Person> getPersons() throws IOException {
        InputStream inputStream = Resources.getResource("persons.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        return objectMapper.readValue(json, new TypeReference<ArrayList<Person>>(){});
    }

    public static List<Car> getCars() throws IOException {
        InputStream inputStream = Resources.getResource("cars.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        return objectMapper.readValue(json, new TypeReference<ArrayList<Car>>(){});
    }
}
