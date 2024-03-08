package fr.iut.sj.pkdxapi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.iut.sj.pkdxapi.services.PkmnService;

@RestController
public class PkmnController {


    private PkmnService pkmnService;

    public PkmnController(PkmnService pkmnService){
        this.pkmnService = pkmnService;
    }

    @GetMapping("/pkmn/types")
    public Map<String, Object> getTypes() {
        Map<String, Object> types = pkmnService.getAllPkmnTypes();
        return types;
    }
}
