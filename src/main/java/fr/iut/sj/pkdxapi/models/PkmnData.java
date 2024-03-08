package fr.iut.sj.pkdxapi.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class PkmnData extends Pkmn {
    @Id
    private ObjectId id;

    public PkmnData(String name, String description, String imgURL, List<PkmnRegion> regions, List<PkmnTypes> types) {
        super(name, description, imgURL, regions, types);
        id = ObjectId.get();
    }
}
