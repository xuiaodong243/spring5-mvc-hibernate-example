package com.bnpp.demo.spring.controller;

import com.bnpp.demo.spring.service.DemoService;
import com.bnpp.demo.spring.service.SeparateTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    @Autowired
    protected DemoService demoService;

    @Autowired
    protected SeparateTableService separateTableService;


    protected void conSuppliersAndCatesToMap(Model model){

        Map<String, String> suppliers = new HashMap<String, String>();
        demoService.listSuppliers().stream().forEach(d -> {
            suppliers.put(d.getId().toString(),d.getName());
        });
        Map<String, String> categories = new HashMap<String, String>();
        demoService.listCategories().stream().forEach(d -> {
            categories.put(d.getId().toString(),d.getName());
        });

        model.addAttribute("suppliers",suppliers);
        model.addAttribute("categories",categories);
    }
}
