package com.bnpp.demo.spring.controller;

import com.bnpp.demo.spring.model.demo.Product;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("separator")
public class SeparateController extends BaseController{

	@GetMapping("/")
	public String menu(Locale locale, Model model) {
		return "demo_menu";
	}


	@GetMapping("getSeparatorProduct")
	public String getProduct(@ModelAttribute("id") @Valid Long id, BindingResult result, Model model) {
		if (result.hasErrors()) { return "redirect:/"; }

		Product product = separateTableService.getObjectById(id,Product.class);
		model.addAttribute("product",product);
		model.addAttribute("auditLogs",demoService.listAuditLog(product.getId(),"Product"));
		model.addAttribute("hList",demoService.getProductHistory(id));
		conSuppliersAndCatesToMap(model);
		return "demo_update_product";
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
