package com.example.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class MathServiceTest {

  MathService mathService = new MathService();

  @Test
  public void testPi() throws Exception {
    String pi = mathService.pi();
    assertEquals(Double.toString(Math.PI), pi);
  }

  @Test
  public void testCalculation() throws Exception {
    String calculation = mathService.calculate("and", "3", "4");
    assertEquals("3 + 4 = 7", calculation);
  }

  @Test
  public void testSum() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    params.add("n", "4");
    params.add("n", "5");
    params.add("n", "6");

    String calculation = mathService.sum(params);
    assertEquals("4 + 5 + 6 = 15", calculation);
  }

  @Test
  public void testRectangleInfo() throws Exception {
    String response = mathService.rectangleInfo(4, 5, 6);
    assertEquals("The volume of a 4x5x6 rectangle is 120", response);
  }

  @Test
  public void testAreawithCirlce() throws Exception {
    Map<String, String> hm = new HashMap<String, String>();
    hm.put("type", "circle");
    hm.put("radius", "4.5");

    String response = mathService.area(hm);
    assertEquals("Area of a circle with a radius of 4.5 is 63.61725123519331", response);
  }

  @Test
  public void testAreawithRectangle() throws Exception {
    Map<String, String> hm = new HashMap<String, String>();
    hm.put("type", "rectangle");
    hm.put("width", "5.5");
    hm.put("height", "6.5");

    String response = mathService.area(hm);
    assertEquals("Area of a 5.5x6.5 rectangle is 35.75", response);
  }

  @Test
  public void testAreawithInvalidCirlce() throws Exception {
    Map<String, String> hm = new HashMap<String, String>();
    hm.put("type", "circle");
    hm.put("width", "4.5");

    String response = mathService.area(hm);
    assertEquals("Invalid", response);
  }

  @Test
  public void testAreawithInvalidRectangle() throws Exception {
    Map<String, String> hm = new HashMap<String, String>();
    hm.put("type", "rectangle");
    hm.put("radius", "4.5");

    String response = mathService.area(hm);
    assertEquals("Invalid", response);
  }
}