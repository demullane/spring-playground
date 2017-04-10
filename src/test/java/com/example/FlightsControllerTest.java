package com.example;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightsController.class)
public class FlightsControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void testFlightEndpoint() throws Exception {
    this.mvc.perform(get("/flights/flight")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        // why is it adding 6 hours to the original hour that I gave it??
        .andExpect(jsonPath("$.departs", is("2017-05-21 20:34")))
        .andExpect(jsonPath("$.tickets[0].passenger.firstName", is("Danielle")))
        .andExpect(jsonPath("$.tickets[0].passenger.lastName", is("Mullane")))
        .andExpect(jsonPath("$.tickets[0].price", is(200)));
  }


  @Test
  public void testFlightsEndpoint() throws Exception {
    this.mvc.perform(get("/flights")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        // why is it adding 6 hours to the original hour that I gave it??
        .andExpect(jsonPath("$[0].departs", is("2017-05-21 20:34")))
        .andExpect(jsonPath("$[0].tickets[0].passenger.firstName", is("Danielle")))
        .andExpect(jsonPath("$[0].tickets[0].passenger.lastName", is("Mullane")))
        .andExpect(jsonPath("$[0].tickets[0].price", is(200)))
        // why is it adding 6 hours to the original hour that I gave it??
        .andExpect(jsonPath("$[1].departs", is("2017-05-21 20:34")))
        .andExpect(jsonPath("$[1].tickets[0].passenger.firstName", is("Taylor")))
        .andExpect(jsonPath("$[1].tickets[0].passenger.lastName").value(IsNull.nullValue()))
        .andExpect(jsonPath("$[1].tickets[0].price", is(400)));

  }
}