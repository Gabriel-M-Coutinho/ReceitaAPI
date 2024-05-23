package com.example.ReceitaAPI.Services.Estabelecimento;


import com.example.ReceitaAPI.Models.Estabelecimento.MotivoSituacaoCadastralModel;
import com.example.ReceitaAPI.Models.Estabelecimento.MunicipioModel;
import com.example.ReceitaAPI.Repositories.Estabelecimento.MotivoSituacaoCadastralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MotivoSituacaoCadastralService {

    @Autowired
    MotivoSituacaoCadastralRepository motivoSituacaoCadastralRepository;
    public List<MotivoSituacaoCadastralModel> addSituaCaocadastral(List<MotivoSituacaoCadastralModel> motivoSituacaoCadastralModels){

        return motivoSituacaoCadastralRepository.saveAll(motivoSituacaoCadastralModels);


    }

    public List<MotivoSituacaoCadastralModel> transformInModel(List<String[]> data){
        List<MotivoSituacaoCadastralModel> list = new ArrayList<>();
        data.forEach((line)->{
            MotivoSituacaoCadastralModel municipioModel = new MotivoSituacaoCadastralModel(Integer.parseInt(line[0]),line[1]);
            list.add(municipioModel);
        });
        return list;
    }

}
