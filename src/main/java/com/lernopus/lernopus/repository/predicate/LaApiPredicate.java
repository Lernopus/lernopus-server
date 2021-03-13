package com.lernopus.lernopus.repository.predicate;

import java.util.Arrays;
import java.util.List;

import com.lernopus.lernopus.model.AuthProvider;
import com.lernopus.lernopus.model.LaLearnUser;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

public class LaApiPredicate {

    private LaApiSearchCriteria criteria;
    
    public LaApiPredicate(LaApiSearchCriteria criteria) {
        this.criteria = criteria;
    }
    
    public LaApiSearchCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(LaApiSearchCriteria criteria) {
        this.criteria = criteria;
    }

	public BooleanExpression getPredicate(String tableName, List<String> numberTypeColumnList) {
        PathBuilder<LaLearnUser> entityPath = new PathBuilder<>(LaLearnUser.class, tableName);
        List<String> authProviderList = Arrays.asList(String.valueOf(AuthProvider.facebook), String.valueOf(AuthProvider.github), String.valueOf(AuthProvider.google), String.valueOf(AuthProvider.local));
        
        if(numberTypeColumnList.contains(criteria.getKey())) {
        	if (isNumeric(criteria.getValue().toString())) {
                NumberPath<Long> path = entityPath.getNumber(criteria.getKey(), Long.class);
                Long value = Long.parseLong(criteria.getValue().toString());
                switch (criteria.getOperation()) {
                    case ":":
                        return path.eq(value);
                    case ">":
                        return path.goe(value);
                    case "<":
                        return path.loe(value);
                }
            } 
        } else {
        	if (isNumeric(criteria.getValue().toString())) {
                NumberPath<Long> path = entityPath.getNumber(criteria.getKey(), Long.class);
                Long value = Long.parseLong(criteria.getValue().toString());
                switch (criteria.getOperation()) {
                    case ":":
                        return path.eq(value);
                    case ">":
                        return path.goe(value);
                    case "<":
                        return path.loe(value);
                }
            } else if(criteria.getValue() instanceof Boolean) {
                BooleanPath path = entityPath.getBoolean(criteria.getKey());
                if (criteria.getOperation().equalsIgnoreCase(":")) {
                    return path.eq(Boolean.valueOf(String.valueOf(criteria.getValue())));
                }
            }  else if(authProviderList.contains(String.valueOf(criteria.getValue())) && AuthProvider.valueOf(String.valueOf(criteria.getValue())) instanceof AuthProvider) {
                EnumPath<AuthProvider> path = entityPath.getEnum(criteria.getKey(), AuthProvider.class);
                if (criteria.getOperation().equalsIgnoreCase(":")) {
                    return path.eq(AuthProvider.valueOf(String.valueOf(criteria.getValue())));
                }
            } else {
                StringPath path = entityPath.getString(criteria.getKey());
                if (criteria.getOperation().equalsIgnoreCase(":")) {
                    return path.containsIgnoreCase(criteria.getValue().toString());
                }
            }	
        }
        return null;
    }
    
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
