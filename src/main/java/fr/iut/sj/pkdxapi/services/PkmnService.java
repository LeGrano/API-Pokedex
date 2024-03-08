package fr.iut.sj.pkdxapi.services;

import fr.iut.sj.pkdxapi.models.PkmnData;
import fr.iut.sj.pkdxapi.models.PkmnRegion;
import fr.iut.sj.pkdxapi.models.PkmnTypes;
import fr.iut.sj.pkdxapi.models.UserData;
import fr.iut.sj.pkdxapi.repositories.PkmnRepository;
import fr.iut.sj.pkdxapi.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class PkmnService {

    private final PkmnRepository pkmnRepository;

    public PkmnService(PkmnRepository pkmnRepository) {
        this.pkmnRepository = pkmnRepository;
    }

    public Map<String, Object> getAllPkmnTypes() {
        List<String> types = new ArrayList<>();
        for (PkmnTypes type : PkmnTypes.values()) {
            types.add(type.toString());
        }
        int TypeCount = types.size();

        // Création du json
        Map<String, Object> response = new HashMap<>();
        response.put("data", types);
        response.put("count", TypeCount);

        return response;
    }

    public PkmnData newPkmn(PkmnData pkmnData) {
        String PkmnName = pkmnData.getName();
        boolean PkmnExists = findByName(PkmnName);
        if (PkmnExists) {
            throw new UnsupportedOperationException("Pkmn already exists");
        }
        String PkmnDescription = pkmnData.getDescription();
        String PkmnImgURL = pkmnData.getImgURL();
        List<PkmnTypes> Type = pkmnData.getTypes();
        List<PkmnRegion> Region = pkmnData.getRegions();
        PkmnData pkmn = new PkmnData(PkmnName, PkmnDescription, PkmnImgURL, Region, Type);
        pkmnRepository.insert(pkmn);
        return pkmn;
    }

    public void deletePkmn(ObjectId id) {
        PkmnData pkmn = pkmnRepository.findById(id).orElseThrow();
        pkmnRepository.delete(pkmn);
    }

    public PkmnData updatePkmn(ObjectId id, String name, String description, String imgURL, String typeOne, String typeTwo) {
        PkmnData pkmn = pkmnRepository.findById(id).orElseThrow();

        if (name != null) {
            pkmn.setName(name);
        }
        if (description != null) {
            pkmn.setDescription(description);
        }
        if (imgURL != null) {
            pkmn.setImgURL(imgURL);
        }

        if (typeOne != null ) {
            PkmnTypes type1 = PkmnTypes.valueOf(typeOne);
            if (pkmn.getTypes().isEmpty()) {
                pkmn.getTypes().add(type1);
            } else {
                pkmn.getTypes().set(0, type1);
            }
        }
        if (typeTwo != null) {
            PkmnTypes type2 = PkmnTypes.valueOf(typeTwo);
            if (pkmn.getTypes().size() < 2) {
                pkmn.getTypes().add(type2);
            } else {
                pkmn.getTypes().set(1, type2);
            }
        }

        pkmnRepository.save(pkmn);
        return pkmn;
    }

    public PkmnData addRegion(String pkmnName, PkmnRegion region) {
        List<PkmnData> pkmnList = pkmnRepository.findByName(pkmnName);

        if (pkmnList.isEmpty()) {
            throw new IllegalArgumentException("Pokemon not found");
        }

        PkmnData pkmn = pkmnList.get(0);

        List<PkmnRegion> regions = pkmn.getRegions();
        regions.add(region);
        pkmn.setRegions(regions);
        pkmnRepository.save(pkmn);

        return pkmn;
    }

    public List<PkmnData> getPkmnByPartialName(String partialName) {
        return pkmnRepository.findByPartialName(partialName);
    }

    public PkmnData getPkmnByName(String name) {
        List<PkmnData> pkmnList = pkmnRepository.findByName(name);
        if (pkmnList.isEmpty()) {
            throw new IllegalArgumentException("Pokemon not found");
        }
        return pkmnList.get(0);
    }

    public boolean findByName(String name) {
        List<PkmnData> checkPkmn = pkmnRepository.findByName(name);

        if (checkPkmn.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
