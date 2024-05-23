package com.example.ReceitaAPI.Services.Estabelecimento;

import com.example.ReceitaAPI.Dtos.Estabelecimento.CnaePrincipalDto;
import com.example.ReceitaAPI.Dtos.Estabelecimento.IdentificadorMatrizFilialDto;
import com.example.ReceitaAPI.Dtos.Estabelecimento.SituacaoCadastralDto;
import com.example.ReceitaAPI.Models.Estabelecimento.CnaeFiscalPrincipalModel;
import com.example.ReceitaAPI.Models.Estabelecimento.IdentificadorMatrizFilialModel;
import com.example.ReceitaAPI.Models.Estabelecimento.MunicipioModel;
import com.example.ReceitaAPI.Models.Estabelecimento.SitutacaoCadastralModel;
import com.example.ReceitaAPI.Repositories.Estabelecimento.CnaeFiscalPrincipalRepository;
import com.example.ReceitaAPI.Repositories.Estabelecimento.IdentificadorMatrizFilialRepository;
import com.example.ReceitaAPI.Repositories.Estabelecimento.MunicipioRepository;
import com.example.ReceitaAPI.Repositories.Estabelecimento.SituacaoCadastralRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoStatusService {
    @Autowired
    IdentificadorMatrizFilialRepository identificadorMatrizFilialRepository;
    @Autowired
    MunicipioRepository municipioRepository;
    @Autowired
    SituacaoCadastralRepository situacaoCadastralRepository;

    @Autowired
    CnaeFiscalPrincipalRepository cnaeFiscalPrincipalRepository;

    public List<IdentificadorMatrizFilialModel> addIdentificadorMatriz(IdentificadorMatrizFilialModel identificadorMatrizFilialModel) {
        Optional<IdentificadorMatrizFilialModel> obj = identificadorMatrizFilialRepository.findById(identificadorMatrizFilialModel.getId());
        if (obj.isEmpty()) {
            identificadorMatrizFilialRepository.save(identificadorMatrizFilialModel);
            return  identificadorMatrizFilialRepository.findAll();
        }
        throw new Error("o Status Identificador Matriz Filial ja foi cadastrado");
    }



    public List<SitutacaoCadastralModel> addSituacaoCadastral(SituacaoCadastralDto situacaoCadastralDto){

        Optional<SitutacaoCadastralModel> obj = situacaoCadastralRepository.findById(situacaoCadastralDto.id());
        if(obj.isEmpty()){
            SitutacaoCadastralModel situtacaoCadastralModel = new SitutacaoCadastralModel();
            BeanUtils.copyProperties(situacaoCadastralDto,situtacaoCadastralModel);
            situacaoCadastralRepository.save(situtacaoCadastralModel);
            return situacaoCadastralRepository.findAll();
        }
        throw new Error("O Status Situacao Cadastral ja foi cadastrado");
    }

    /*Matriz Filial Status*/
    public IdentificadorMatrizFilialModel addFilial(IdentificadorMatrizFilialDto identificadorMatrizFilialDto){
        IdentificadorMatrizFilialModel identificadorMatrizFilialModel = new IdentificadorMatrizFilialModel(identificadorMatrizFilialDto.id(),identificadorMatrizFilialDto.description());
        return identificadorMatrizFilialRepository.save(identificadorMatrizFilialModel);
    }




}
