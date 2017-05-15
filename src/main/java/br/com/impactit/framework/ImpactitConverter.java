package br.com.impactit.framework;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ImpactitConverter implements Converter {

    private ImpactitService service;

    public ImpactitConverter(ImpactitService service) {
        this.service = service;
        System.out.println("---->" + this.getClass().getSimpleName());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if ("".equals(value)) {
            return null;
        }
        return service.view(value);

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if (value==null){
//            return "";
//        }
//        ImpactitEntity impactitEntity = (ImpactitEntity) value;
//        return impactitEntity.getId();

        if (value != null) {
            return String.valueOf(value);
        } else {
            return null;
        }
    }

}
