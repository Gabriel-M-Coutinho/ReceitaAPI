package com.example.ReceitaAPI.Services.Socio;

import com.example.ReceitaAPI.Models.Estabelecimento.EstabelecimentoModel;
import com.example.ReceitaAPI.Models.Socios.SocioModel;
import com.example.ReceitaAPI.Repositories.Socio.SocioRepository;
import com.example.ReceitaAPI.Services.UtillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class SocioService {
    @Autowired
    SocioRepository socioRepository;
    @Autowired
    UtillsService utillsService;

    public void saveAll(List<SocioModel> socioModels){
        socioRepository.saveAll(socioModels);
    }

    public SocioModel parseLine(String[] data, SimpleDateFormat format) {

        if (data == null || data.length == 0) {
            return null; // Ignora linhas vazias ou nulas
        }
        Date dataEntradaSociedade = utillsService.parseDate(data[6], format);

        SocioModel socioModel = new SocioModel();
        socioModel.setCnpjBase(utillsService.parseLong(data[0]));
        socioModel.setIdentificador(utillsService.parseInt(data[1]));
        socioModel.setNome_razao(utillsService.truncate(data[2], 255));
        socioModel.setCpf_cnpj_socio(utillsService.truncate(data[3], 255));
        socioModel.setQualificacao(utillsService.parseLong(data[4]));
        socioModel.setData_entrada_socidedade(dataEntradaSociedade);
        socioModel.setPais(utillsService.parseLong(data[5]));
        socioModel.setRepresentante_legal(utillsService.truncate(data[6], 255));
        socioModel.setNome_representante(utillsService.truncate(data[7], 255));
        socioModel.setQualificacao_representante(utillsService.parseLong(data[8]));
        socioModel.setFaixa_etaria(utillsService.parseInt(data[9]));

        return socioModel;


    }

}
