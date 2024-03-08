package fr.iut.sj.pkdxapi.services;

import fr.iut.sj.pkdxapi.models.PkmnTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PkmnService {

    public Map<String, Object> getAllPkmnTypes(){
        List<String> types = new ArrayList<>();
        for (PkmnTypes.Types type : PkmnTypes.Types.values()) {
            types.add(type.toString());
        }
        int TypeCount = types.size();
        
        //Création du json
        Map<String, Object> response = new HashMap<>();
        response.put("data", types);
        response.put("count", TypeCount);

        return response;
    }

}
