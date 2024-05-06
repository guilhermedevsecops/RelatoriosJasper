package com.guilhermedevsecops.start.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.guilhermedevsecops.start.repository.EnderecoRepository;
import com.guilhermedevsecops.start.repository.NivelRepository;
import com.guilhermedevsecops.start.service.JasperService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

@Controller
public class JasperController {

    @Autowired
    private JasperService service;

    @Autowired
    private NivelRepository nivelRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/reports")
    public String abrir() {
        return "reports";
    }

    @GetMapping("/relatorio/pdf/jr1")
    public void exibirOuBaixarRelatorio(@RequestParam("code") String code,
                                         @RequestParam("acao") String acao,
                                         HttpServletResponse response) throws IOException, JRException {
        byte[] bytes = service.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        if ("v".equals(acao)) {
            response.setHeader("Content-Disposition", "inline; filename=relatorio-" + code + ".pdf");
        } else if ("d".equals(acao)) {
            response.setHeader("Content-Disposition", "attachment; filename=relatorio-" + code + ".pdf");
        }
        response.getOutputStream().write(bytes);
    }

    @GetMapping("/relatorio/pdf/jr9/{code}")
    public void exibirOuBaixarRelatorio09(@PathVariable("code") String code,
                                           @PathVariable(name="nivel", required = false) String nivel,
                                           @PathVariable(name="uf", required = false) String uf,
                                           HttpServletResponse response) throws IOException, JRException {
        service.addParams("NIVEL_DESC", nivel);
        service.addParams("UF_DESC", uf);

        byte[] bytes = service.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-Disposition", "inline; filename=relatorio-" + code + ".pdf");
        response.getOutputStream().write(bytes);
    }

    @ModelAttribute("niveis")
    public List<String> getNiveis(){
        return nivelRepository.findNiveis();
    }

    @ModelAttribute("ufs")
    public List<String> getUfs(){
        return enderecoRepository.findUfs();
    }
}
