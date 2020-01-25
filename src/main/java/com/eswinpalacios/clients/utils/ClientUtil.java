package com.eswinpalacios.clients.utils;

import com.eswinpalacios.clients.model.Client;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public abstract class ClientUtil {

    private static ArrayList<Integer> getAgesArray(List<Client> clients){
        return clients.stream().map(Client::getAge)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static double ageAverage(ArrayList<Integer> ages){
        Optional<Integer> totalAges = ages.stream().reduce(Integer::sum);

        return totalAges.map(integer -> integer / ages.size()).orElse(0);
    }

    public static double ageAverage(List<Client> clients){
        ArrayList<Integer> arrAges = getAgesArray(clients);

        return ClientUtil.ageAverage(arrAges);
    }

    public static double standardDeviation(List<Client> clients){
        ArrayList<Integer> arrAges = getAgesArray(clients);

        int countAges = arrAges.size();
        double average = ageAverage(arrAges);

        Optional<Double> totalDistance  = arrAges.stream()
                .map(age -> Math.pow(Math.abs(age - average), 2))
                .reduce(Double::sum);

        return totalDistance.map(total -> Math.sqrt((total / countAges))).orElse(0.0);
    }

    public static Date dateOfDie(Date date)
    {
        if(date == null) return null;

        // http://m.inei.gob.pe/prensa/noticias/esperanza-de-vida-de-poblacion-peruana-aumento-en-15-anos-en-las-ultimas-cuatro-decadas-8723/

        Date dateNow = new Date();

        long diffTime = dateNow.getTime() - date.getTime();
        int ageDaysNow = (int) TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
        int ageDaysEnd = 74 * 365;
        int ageDaysLeft = ageDaysEnd - ageDaysNow;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow);
        calendar.add(Calendar.DAY_OF_YEAR, ageDaysLeft);

        return calendar.getTime();
    }

    public static void completeAgeFromDateOfBirth(Client client){
        Date dateNow = new Date();
        long diffTime = dateNow.getTime() - client.getDateOfBirth().getTime();
        int ageDays = (int) TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS) / 365;
        client.setAge(ageDays);
    }

}
