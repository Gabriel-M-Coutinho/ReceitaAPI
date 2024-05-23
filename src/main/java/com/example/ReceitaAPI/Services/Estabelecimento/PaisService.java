package com.example.ReceitaAPI.Services.Estabelecimento;

import com.example.ReceitaAPI.Models.Estabelecimento.CnaeFiscalPrincipalModel;
import com.example.ReceitaAPI.Models.Estabelecimento.PaisModel;
import com.example.ReceitaAPI.Repositories.Estabelecimento.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaisService {
    @Autowired
    PaisRepository paisRepository;
    public void addAllContry(List<PaisModel> paisModelList){
        paisRepository.saveAll(paisModelList);
    }


    public List<PaisModel> trasformData(List<String[]> list){

        List<PaisModel> response = new ArrayList<>();
        list.forEach((line)->{
            var data = new PaisModel(Integer.parseInt(line[0]),line[1]);
            response.add(data);
        });
        return response;
    }
}
