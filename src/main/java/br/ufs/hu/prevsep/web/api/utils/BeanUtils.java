package br.ufs.hu.prevsep.web.api.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

public class BeanUtils {

    /**
     * Returns all Null properties of a given Bean.
     * @param source Bean that will be analysed
     * @return A list of the names of the null fields.
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
     * Copies all fields values from a Bean to another
     *
     * @param src The source Bean, from where the info will come
     * @param target The target Bean, to where the values will be copy
     * @param ignoreNulls If true, fields with null values will be ignored during the copie process
     * @param <T> Bean type
     */
    public static <T> void copyPropertiesIgnoreNulls(T src, T target, boolean ignoreNulls) {
        if (ignoreNulls)
            org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
        else
            org.springframework.beans.BeanUtils.copyProperties(src, target);
    }
}
