package com.example.ReceitaAPI.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Natureza_Legal")
public class LegalNatureModel {
    @Id
    private Integer id;

    @OneToMany(mappedBy = "legalNatureModel")
    private List<CompanyModel> companies = new ArrayList<>();

    public String description;

    public LegalNatureModel(Integer id, String description) {
        this.id = id;
        this.description = description;    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
