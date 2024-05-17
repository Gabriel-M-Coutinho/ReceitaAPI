package com.example.ReceitaAPI.Services;

import com.example.ReceitaAPI.Models.Estabelecimento.IdentificadorMatrizFilialModel;
import com.example.ReceitaAPI.Repositories.Estabelecimento.IdentificadorMatrizFilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyStatusService {
    @Autowired
    IdentificadorMatrizFilialRepository identificadorMatrizFilialRepository;
    public List<IdentificadorMatrizFilialModel> addIdentificadorMatrizFilial(IdentificadorMatrizFilialModel identificadorMatrizFilialModel){
        identificadorMatrizFilialRepository.save(identificadorMatrizFilialModel);
        return identificadorMatrizFilialRepository.findAll();
    }

}
