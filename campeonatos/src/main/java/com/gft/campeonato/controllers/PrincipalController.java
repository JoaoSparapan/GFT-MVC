package com.gft.campeonato.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class PrincipalController {
	@RequestMapping
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("index.html");
		
		return mv;
	}
}
