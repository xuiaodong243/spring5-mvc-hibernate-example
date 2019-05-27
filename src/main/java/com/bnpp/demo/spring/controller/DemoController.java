package com.bnpp.demo.spring.controller;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

import javax.validation.Valid;

import com.bnpp.demo.spring.model.demo.Product;
import com.bnpp.demo.spring.service.DemoService;
import com.bnpp.demo.spring.service.ProductService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("demo")
public class DemoController {

	@Autowired
	private DemoService demoService;

	@GetMapping("/")
	public String menu(Locale locale, Model model) {
		return "demo_menu";
	}

	@GetMapping("listDemoProducts/{page}")
	public String productFormWithPageIndex(@PathVariable("page")Integer pageIndex,  Model model) {
		model.addAttribute("products", demoService.listProducts(pageIndex));
		model.addAttribute("pageIndex",pageIndex);
		conSuppliersAndCatesToMap(model);
		return "demo_products";
	}

	@GetMapping("listDemoProducts")
	public String productForm() {
		return "redirect:listDemoProducts/0";
	}

	private void conSuppliersAndCatesToMap(Model model){

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

	@PostMapping("addDemoProduct")
	public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("products", demoService.listProducts(0));
			return "redirect:listDemoProducts";
		}
		product.setLastupdDt(new Date());
		product.setCreateDt(new Date());
		product.setCreateBy("admin");
		product.setLastupdBy("admin");
		demoService.saveProduct(product);
		return "redirect:listDemoProducts";
	}

	@GetMapping("getDemoProduct")
	public String getProduct(@ModelAttribute("id") @Valid Long id, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "redirect:listDemoProducts";
		}

		Product product = demoService.getProductById(id);
		model.addAttribute("product",product);
		model.addAttribute("auditLogs",demoService.listAuditLog(product.getId(),"Product"));
		model.addAttribute("hList",demoService.getProductHistory(id));
		conSuppliersAndCatesToMap(model);
		return "demo_update_product";
	}

	@PostMapping("updateDemoProduct")
	public String updateProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "redirect:getDemoProduct?id=" + product.getId();
		}
		product.setLastupdDt(new Date());
		demoService.updateProduct(product);
		return "redirect:getDemoProduct?id=" + product.getId();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@ModelAttribute("product")
	public Product formBackingObject() {
		return new Product();
	}


}
