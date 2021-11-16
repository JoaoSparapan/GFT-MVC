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

import com.gft.campeonato.entities.Academia;
import com.gft.campeonato.services.AcademiaService;
import com.gft.campeonato.services.EsporteService;

@Controller
@RequestMapping("academia")
public class AcademiaController {

	@Autowired
	private AcademiaService as;
	
	@Autowired
	private EsporteService es;
	
	@RequestMapping(path = "editar")
	public ModelAndView editarAcademia(@RequestParam(required = false) Long id)
	{
		ModelAndView mv = new ModelAndView("academia/form.html");
		Academia acad;
		
		if(id==null)
		{
			acad = new Academia();
		}else {
			try {
				acad = as.obterAcademia(id);
			}catch(Exception e) {
				acad = new Academia();
				mv.addObject("mensagem", "Erro ao editar o cadastro da academia!");
			}
		}
		mv.addObject("academia", acad);
		mv.addObject("listaEsportes", es.listarEsportes());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST ,path="editar")
	public ModelAndView salvarAcademia(@Valid Academia acad, BindingResult bindResult)
	{
		ModelAndView mv = new ModelAndView("academia/form.html");
		
		boolean novo = true;
		
		if(acad.getId()!=null)
		{
			novo=false;
		}
		
		if(bindResult.hasErrors()) {
			mv.addObject("academia", acad);
			return mv;
		}

		as.salvarAcademia(acad);
		
		if(novo)
		{
			mv.addObject("academia", new Academia());
		}else {
			mv.addObject("academia", acad);
		}

		mv.addObject("mensagem", "Academia salva com sucesso!");
		mv.addObject("listaEsportes", es.listarEsportes());
		return mv;
	}
	@RequestMapping
	public ModelAndView listarAcademias()
	{
		ModelAndView mv = new ModelAndView("academia/listar.html");
		
		
		mv.addObject("lista", as.listarAcademias());
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirAcademia(@RequestParam Long id, RedirectAttributes redirectAttributes)
	{
		ModelAndView mv = new ModelAndView("redirect:/academia");
		
		try {
			as.excluirAcademia(id);
			redirectAttributes.addFlashAttribute("mensagem", "Academia excluída com sucesso!");
		}catch(Exception e)
		{
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir a academia!"+e.getMessage());
		}
		
		return mv;
	}
}
