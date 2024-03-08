package fr.iut.sj.pkdxapi.models;

public class PkmnRegion {
    private String regionName;
    private int regionNumber;

    public PkmnRegion(String regionName, int regionNumber) {
        this.regionName = regionName;
        this.regionNumber = regionNumber;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getRegionNumber() {
        return regionNumber;
    }

    public void setRegionNumber(int regionNumber) {
        this.regionNumber = regionNumber;
    }
}
