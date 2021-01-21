package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PrimaryDTO;
import com.example.demo.dto.SecondaryDTO;
import com.example.demo.repository.primary.PrimaryRepository;
import com.example.demo.repository.secondary.SecondaryRepository;

@RestController
@RequestMapping("api/product")
public class ProductController {

	@Autowired
	private PrimaryRepository primaryRepository;
	@Autowired
	private SecondaryRepository secondaryRepository;

	@GetMapping
	public Map<String, Object> getData(){

		Map<String, Object> response = new HashMap<>();

		List<PrimaryDTO> sampleList = primaryRepository.selectAll();
		List<SecondaryDTO> secondaryList = secondaryRepository.selectAll2();

		response.put("sampleList", sampleList);
		response.put("secondaryList", secondaryList);

		return response;
	}
}
