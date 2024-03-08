package fr.iut.sj.pkdxapi.models;

import java.util.List;

public class Pkmn {
    private String name;
    private String description;
    private String imgURL;
    private List<PkmnRegion> regions;
    private List<PkmnTypes> types;

    public Pkmn(String name, String description, String imgURL, List<PkmnRegion> regions, List<PkmnTypes> types) {
        this.name = name;
        this.description = description;
        this.imgURL = imgURL;
        this.regions = regions;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public List<PkmnRegion> getRegions() {
        return regions;
    }

    public void setRegions(List<PkmnRegion> regions) {
        this.regions = regions;
    }

    public List<PkmnTypes> getTypes() {
        return types;
    }

    public void setTypes(List<PkmnTypes> types) {
        this.types = types;
    }
}
