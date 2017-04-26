package com.rmznk.xmltask.entity;


public class Flower {
    private String name;
    private SoilType soil;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private MultiplyingType multiplying;
    private String id;
    private Integer price;

    public Flower() {
        visualParameters = new VisualParameters();
        growingTips = new GrowingTips();
        price = null;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public SoilType getSoil() {
        return soil;
    }

    public void setSoil(SoilType value) {
        this.soil = value;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String value) {
        this.origin = value;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters value) {
        this.visualParameters = value;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips value) {
        this.growingTips = value;
    }

    public MultiplyingType getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(MultiplyingType value) {
        this.multiplying = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", soil=" + soil +
                ", origin='" + origin + '\'' +
                ", visualParameters=" + visualParameters +
                ", growingTips=" + growingTips +
                ", multiplying=" + multiplying +
                ", id='" + id + '\'' +
                '}';
    }

}
