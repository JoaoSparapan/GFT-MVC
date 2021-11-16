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

import com.gft.campeonato.entities.Campeonato;
import com.gft.campeonato.services.AcademiaService;
import com.gft.campeonato.services.CampeonatoService;
import com.gft.campeonato.services.EsporteService;

@Controller
@RequestMapping("campeonato")
public class CampeonatoController {

	@Autowired
	private CampeonatoService cs;
	
	@Autowired
	private AcademiaService as;
	
	@Autowired
	private EsporteService es;
	
	@RequestMapping(path = "editar")
	public ModelAndView editarCampeonato(@RequestParam(required = false) Long id)
	{
		ModelAndView mv = new ModelAndView("campeonato/form.html");
		Campeonato camp;
		
		if(id==null)
		{
			camp = new Campeonato();
		}else {
			try {
				camp = cs.obterCampeonato(id);
			}catch(Exception e) {
				camp = new Campeonato();
				mv.addObject("mensagem", "Erro ao editar o campeonato!");
			}
		}
		mv.addObject("campeonato", camp);
		mv.addObject("listaEsportes", es.listarEsportes());
		mv.addObject("listaAcademias", as.listarAcademias());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST ,path="editar")
	public ModelAndView salvarCampeonato(@Valid Campeonato camp, BindingResult bindResult)
	{
		ModelAndView mv = new ModelAndView("campeonato/form.html");
		
		boolean novo = true;
		
		if(camp.getId()!=null)
		{
			novo=false;
		}
		
		if(bindResult.hasErrors()) {
			mv.addObject("campeonato", camp);
			return mv;
		}

		cs.salvarCampeonato(camp);
		
		if(novo)
		{
			mv.addObject("projeto", new Campeonato());
		}else {
			mv.addObject("projeto", camp);
		}

		
		mv.addObject("mensagem", "Campeonato salvo com sucesso!");
		mv.addObject("listaEsportes", es.listarEsportes());
		mv.addObject("listaAcademias", as.listarAcademias());
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarCampeonatos(String nome, String sigla)
	{
		ModelAndView mv = new ModelAndView("campeonato/listar.html");
		
		
		mv.addObject("lista", cs.listarCampeonatos(nome, sigla));
		mv.addObject("nome", nome);
		mv.addObject("sigla", sigla);
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirCampeonato(@RequestParam Long id, RedirectAttributes redirectAttributes)
	{
		ModelAndView mv = new ModelAndView("redirect:/campeonato");
		
		try {
			cs.excluirCampeonato(id);
			redirectAttributes.addFlashAttribute("mensagem", "Campeonato excluído com sucesso!");
		}catch(Exception e)
		{
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir o campeonato!"+e.getMessage());
		}
		
		return mv;
	}
}
