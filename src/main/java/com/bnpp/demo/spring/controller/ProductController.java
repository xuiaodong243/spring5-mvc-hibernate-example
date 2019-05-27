package com.bnpp.demo.spring.controller;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import com.bnpp.demo.spring.model.Product1;
import com.bnpp.demo.spring.model.Product1History;
import com.bnpp.demo.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String productForm(Locale locale, Model model) {
		model.addAttribute("products", productService.list(0));
		model.addAttribute("pageIndex",0);
		return "addProducts";
	}

	@GetMapping("/listProducts")
	public String getProductByPage(@ModelAttribute("page")Integer pageIndex, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "redirect:/";
		}
		if(pageIndex == null){pageIndex = 0;}
		model.addAttribute("products", productService.list(pageIndex));
		model.addAttribute("pageIndex",pageIndex);
		return "addProducts";
	}
	
	@ModelAttribute("product")
    public Product1 formBackingObject() {
        return new Product1();
    }

	@PostMapping("/addProduct")
	public String saveProduct(@ModelAttribute("product") @Valid Product1 product, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("products", productService.list(0));
			return "addProducts";
		}

		productService.save(product);
		return "redirect:/";
	}

	@GetMapping("/getProduct")
	public String getProduct(@ModelAttribute("id") @Valid Long id, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "redirect:/";
		}
		Product1 product = productService.getById(id);
		List<Product1History> pList = productService.getHistory(id);
		model.addAttribute("product",product);
		model.addAttribute("pList",pList);

		pList.stream().forEach(p -> {
			System.out.println("Test : "+p.getUpdateDt());
		});

		return "editProducts";
	}

	@PostMapping("/editProduct")
	public String editProduct(@ModelAttribute("product") @Valid Product1 product, BindingResult result, Model model) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "redirect:/getProduct?id=" + product.getId();
		}
		product.setUpdateDt(new Date());
		productService.update(product);
		return "redirect:/getProduct?id=" + product.getId();
	}

	@GetMapping("/preTestData")
	public String preTestData(Locale locale, Model model) {
		//preTestData();
		model.addAttribute("products", productService.list(0));
		return "addProducts";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}




}
