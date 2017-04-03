package com.example.services;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class MathService {

  public String calculate(String operation, String x, String y) {
    int total;
    int xInt = Integer.parseInt(x);
    int yInt = Integer.parseInt(y);
    String operator;

    switch (operation) {
      case "add": 			operator = " + ";
                        total = xInt+yInt;
                        break;
      case "subtract": 	operator = " - ";
                        total = xInt-yInt;
                        break;
      case "multiply": 	operator = " * ";
                        total = xInt*yInt;
                        break;
      case "divide":		operator = " / ";
                        total = xInt/yInt;
                        break;
      default: 					operator = " + ";
                        total = xInt+yInt;
                        break;
    }

    return x + operator + y + " = " + Integer.toString(total);
  }

  public String sum(MultiValueMap<String, String> query) {
    String equation = "";
    int sum = 0;

    for (String key : query.keySet()) {
      for (String value : query.get(key)) {
        int index = query.get(key).indexOf(value);

        if (index == 0) {
          equation = value;
        } else {
          equation += " + " + value;
        }

        sum = sum + Integer.parseInt(value);
      }
    }

    return equation + " = " + Integer.toString(sum);
  }

  public String rectangleInfo(int length, int width, int height) {
    int volume = length*width*height;
    String lengthSt = Integer.toString(length);
    String widthSt = Integer.toString(width);
    String heightSt = Integer.toString(height);
    String dimensions = lengthSt + "x" + widthSt + "x" + heightSt;

    return "The volume of a " + dimensions + " rectangle is " + volume;
  }
}