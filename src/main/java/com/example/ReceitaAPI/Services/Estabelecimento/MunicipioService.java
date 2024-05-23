package com.example.ReceitaAPI.Services.Estabelecimento;

import com.example.ReceitaAPI.Models.Estabelecimento.MunicipioModel;
import com.example.ReceitaAPI.Repositories.Estabelecimento.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MunicipioService {

    @Autowired
    MunicipioRepository municipioRepository;
    public List<MunicipioModel> addMunicipio(List<MunicipioModel> municipioModel){

        return municipioRepository.saveAll(municipioModel);


    }

    public List<MunicipioModel> transformInModel(List<String[]> data){
        List<MunicipioModel> list = new ArrayList<>();
        data.forEach((line)->{
            MunicipioModel municipioModel = new MunicipioModel(Integer.parseInt(line[0]),line[1]);
            list.add(municipioModel);
        });
        return list;
    }




}
