package com.dpktech.accounts;


import java.text.Normalizer;

public class FrenchStringComapre {
    public static void main(String[] args) {
        String str1 = "</getTransactionV08/transactionQueryDefinition/transactionCriteria/newCriteria/searchCriteria/0/paymentSearch/requestedExecutionDate/0/dateSearch/equalDate>contient des éléments qui ne sont pas autorisés <*>";
        String str2 = "</getTransactionV08/transactionQueryDefinition/transactionCriteria/newCriteria/searchCriteria/0/paymentSearch/requestedExecutionDate/0/dateSearch/equalDate>contient des éléments qui ne sont pas autorisés <*>";

        String normalizedStr1 = Normalizer.normalize(str1, Normalizer.Form.NFD);
        String normalizedStr2 = Normalizer.normalize(str2, Normalizer.Form.NFD);

        if (normalizedStr1.equals(normalizedStr2)) {
            System.out.println("The strings are equal.");
        } else {
            System.out.println("The strings are not equal.");
        }
    }
}