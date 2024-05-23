package com.example.ReceitaAPI.Services.Estabelecimento;

import com.example.ReceitaAPI.Dtos.Estabelecimento.CnaePrincipalDto;
import com.example.ReceitaAPI.Models.Estabelecimento.CnaeFiscalPrincipalModel;
import com.example.ReceitaAPI.Repositories.Estabelecimento.CnaeFiscalPrincipalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CnaeService {

    @Autowired
    CnaeFiscalPrincipalRepository cnaeFiscalPrincipalRepository;

    public void addCnae(CnaePrincipalDto cnaePrincipalDto){

        CnaeFiscalPrincipalModel cnaeFiscalPrincipalModel = new CnaeFiscalPrincipalModel();
        BeanUtils.copyProperties(cnaePrincipalDto,cnaeFiscalPrincipalModel);

        Optional<CnaeFiscalPrincipalModel> obj = cnaeFiscalPrincipalRepository.findById(cnaeFiscalPrincipalModel.getId());
        if(obj.isEmpty()){
            cnaeFiscalPrincipalRepository.save(cnaeFiscalPrincipalModel);



        }else{
            System.out.println("ja cadastrado");
        }

    }
    public void addAllCnae(List<CnaeFiscalPrincipalModel> cnaeFiscalPrincipalModels){
        cnaeFiscalPrincipalRepository.saveAll(cnaeFiscalPrincipalModels);
    }


    public List<CnaeFiscalPrincipalModel> trasformData(List<String[]> list){

        List<CnaeFiscalPrincipalModel> response = new ArrayList<>();
        list.forEach((line)->{
            var data = new CnaeFiscalPrincipalModel(Integer.parseInt(line[0]),line[1]);
            response.add(data);
        });
        return response;
    }

}
