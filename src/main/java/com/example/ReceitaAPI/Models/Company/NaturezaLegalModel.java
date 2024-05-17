package com.example.ReceitaAPI.Models.Company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString

@Table(name = "tb_Natureza_Legal")
@NoArgsConstructor
public class NaturezaLegalModel {


    @Id
    private Integer id;

    public String description;

    public NaturezaLegalModel(Integer id, String description) {
        this.id = id;
        this.description = description;    }

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
