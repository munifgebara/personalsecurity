package br.com.munif.personalsecurity.aplicacao.autorizacao;

import br.com.munif.personalsecurity.entidades.Usuario;
import java.util.HashMap;
import java.util.Map;

public class TokenAdapter {

    public static Map getLoginData(Usuario usuario) {
        Map toReturn;

        toReturn = new HashMap<>();
        toReturn.put("token", usuario.getId());
        toReturn.put("login", usuario.getEmail());
        toReturn.put("name", usuario.getNome());
        toReturn.put("idUser", usuario.getId());
        toReturn.put("picture", null);
        toReturn.put("organization", usuario.getNome());
        toReturn.put("organizationLogo", null);
        toReturn.put("organizationHierarchyCode", usuario.getPid());
        toReturn.put("securityManager", false);
        toReturn.put("softwareHouse", false);
        toReturn.put("timeOfCreation", ""+System.currentTimeMillis());
        toReturn.put("timeOfExpiration", ""+(System.currentTimeMillis()+1000000));
        return toReturn;
    }
    
    public static Map getAutorizationData(Usuario usuario,String operation){
        Map toReturn;

        toReturn = new HashMap<>();
        toReturn.put("organizarion", usuario.getNome());
        toReturn.put("reason", "ok for "+usuario.getNome());
        toReturn.put("organizationCode", usuario.getPid());
        toReturn.put("response", "allow");
        toReturn.put("organizarionId", usuario.getId());
        toReturn.put("login", usuario.getEmail());
        toReturn.put("key", operation);
        return toReturn;
       
    }


}
