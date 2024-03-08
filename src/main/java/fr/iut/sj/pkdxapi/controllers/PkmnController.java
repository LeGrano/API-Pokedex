package fr.iut.sj.pkdxapi.controllers;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.iut.sj.pkdxapi.models.PkmnData;
import fr.iut.sj.pkdxapi.models.PkmnRegion;
import fr.iut.sj.pkdxapi.services.PkmnService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



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

    @PostMapping("/pkmn")
    public ResponseEntity<PkmnData> newPkmn(@RequestBody PkmnData pkmn) {
        PkmnData createdPkmn = pkmnService.newPkmn(pkmn);
        return ResponseEntity.ok(createdPkmn);
    }

    @DeleteMapping("/pkmn")
    public ResponseEntity<String> deletePkmn(@RequestParam ObjectId id) {
        pkmnService.deletePkmn(id);
        return ResponseEntity.ok("Pokemouille deleted");
    }

    @PutMapping("pkmn")
    public ResponseEntity<PkmnData> updatePkmn(
            @RequestParam ObjectId id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String imgURL,
            @RequestParam(required = false) String typeOne,
            @RequestParam(required = false) String typeTwo) {

        PkmnData updatedPkmn = pkmnService.updatePkmn(id, name, description, imgURL, typeOne, typeTwo);
        return ResponseEntity.ok(updatedPkmn);
    }

    @PostMapping("/pkmn/region")
    public ResponseEntity<PkmnData> addRegion(@RequestParam String name, @RequestParam String regionName, @RequestParam int regionNumber) {
        PkmnRegion pkmnRegion = new PkmnRegion(regionName, regionNumber);
        PkmnData updatedPkmn = pkmnService.addRegion(name, pkmnRegion);
        return ResponseEntity.ok(updatedPkmn);
    }

    @DeleteMapping("/pkmn/region")
    public ResponseEntity<PkmnData> removeRegion(@RequestParam ObjectId id, @RequestParam String regionName) {
        PkmnData updatedPkmn = pkmnService.removeRegion(id, regionName);
        return ResponseEntity.ok(updatedPkmn);
    }

    @GetMapping("/pkmn/search")
    public ResponseEntity<List<PkmnData>> getPkmnByPartialName(@RequestParam String partialName) {
        List<PkmnData> pkmnList = pkmnService.getPkmnByPartialName(partialName);
        return ResponseEntity.ok(pkmnList);
    }

    @GetMapping("/pkmn")
    public ResponseEntity<PkmnData> getPkmnByName(@RequestParam String name) {
        PkmnData pkmn = pkmnService.getPkmnByName(name);
        return ResponseEntity.ok(pkmn);
    }
}
