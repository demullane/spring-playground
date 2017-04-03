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

import static java.util.Arrays.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class MathServiceTest {

  MathService mathService = new MathService();

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
}