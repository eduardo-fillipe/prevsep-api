package br.ufs.hu.prevsep.web.api.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

public class BeanUtils {

    /**
     * Retorna todas as propriedades nulas de um dado Bean.
     * @param source Bean que será analizado
     * @return Uma lista de nomes dos campos nulos.
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * Copia todos os valores dos campos de um Bean para outro
     *
     * @param src O Bean fonte, de onde a informação virá
     * @param target O Bean, para onde os valores serão copiados
     * @param ignoreNulls Se verdadeiro, campos com valores nulos serão ignorados durante a cópia
     * @param <T> Tipo do Bean
     */
    public static <T> void copyPropertiesIgnoreNulls(T src, T target, boolean ignoreNulls) {
        if (ignoreNulls)
            org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
        else
            org.springframework.beans.BeanUtils.copyProperties(src, target);
    }
}
