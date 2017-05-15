package br.com.impactit.framework;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ImpactitEntityConverter implements Converter {

    public ImpactitEntityConverter() {
        System.out.println("---->" + this.getClass().getSimpleName());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if ("".equals(value)) {
            return null;
        }
        ImpactitEntity impactitEntity = new ImpactitEntity();
        impactitEntity.setId(value);
        return impactitEntity;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if (value == null) {
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
