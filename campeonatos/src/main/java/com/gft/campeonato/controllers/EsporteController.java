package com.gft.campeonato.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.campeonato.entities.Esporte;
import com.gft.campeonato.services.EsporteService;


@Controller
@RequestMapping("esporte")
public class EsporteController {
	@Autowired
	private EsporteService es;
	
	@RequestMapping(path = "editar")
	public ModelAndView editarEsporte(@RequestParam(required = false) Long id)
	{
		ModelAndView mv = new ModelAndView("esporte/form.html");
		Esporte esporte;
		
		if(id==null)
		{
			esporte = new Esporte();
		}else {
			try {
				esporte = es.obterEsporte(id);
			}catch(Exception e) {
				esporte = new Esporte();
				mv.addObject("mensagem", "Erro ao editar o cadastro do esporte!");
			}
		}
		mv.addObject("esporte", esporte);
		mv.addObject("listaEsportes", es.listarEsportes());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST ,path="editar")
	public ModelAndView salvarEsporte(@Valid Esporte esporte, BindingResult bindResult)
	{
		ModelAndView mv = new ModelAndView("esporte/form.html");
		
		boolean novo = true;
		
		if(esporte.getId()!=null)
		{
			novo=false;
		}
		
		if(bindResult.hasErrors()) {
			mv.addObject("esporte", esporte);
			return mv;
		}

		es.salvarEsporte(esporte);
		
		if(novo)
		{
			mv.addObject("esporte", new Esporte());
		}else {
			mv.addObject("esporte", esporte);
		}

		mv.addObject("mensagem", "Esporte salvo com sucesso!");
		mv.addObject("listaEsportes", es.listarEsportes());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarEsportes()
	{
		ModelAndView mv = new ModelAndView("esporte/listar.html");
		mv.addObject("lista", es.listarEsportes());
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirEsporte(@RequestParam Long id, RedirectAttributes redirectAttributes)
	{
		ModelAndView mv = new ModelAndView("redirect:/esporte");
		
		try {
			es.excluirEsporte(id);
			redirectAttributes.addFlashAttribute("mensagem", "Esporte excluído com sucesso!");
		}catch(Exception e)
		{
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir esporte!"+e.getMessage());
		}
		
		return mv;
	}
}
