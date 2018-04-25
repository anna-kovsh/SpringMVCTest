package com.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
public class SampleController {

  @GetMapping(value = "/sample")
  // the same as @RequestMapping(value = "/sample", method = RequestMethod.GET)
  public String sample(ModelMap model) {
    model.addAttribute("message", UUID.randomUUID());
    return "sample";
  }

  // works as well
//  @RequestMapping(value = "/sample")
//  public ModelAndView sample() {
//    ModelAndView model = new ModelAndView("sample");
//    model.addObject("message", UUID.randomUUID());
//    return model;
//  }

}
