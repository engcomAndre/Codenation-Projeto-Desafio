package com.desafio.codenation.services.utils;

import com.desafio.codenation.domain.events.Events;
import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.domain.origin.Origins;
import com.desafio.codenation.domain.user.User;

import java.util.Objects;

public class updtUtils {

    public static void updtOrigins(Origins actOrigin, Origins newOrigin) {
        if (!Objects.equals(actOrigin.getName(), newOrigin.getName())) {
            actOrigin.setName(newOrigin.getName());
        }
        if (!Objects.equals(actOrigin.getDescription(), newOrigin.getDescription())) {
            actOrigin.setDescription(newOrigin.getDescription());
        }
        if (!Objects.equals(actOrigin.getOriginKey(), newOrigin.getOriginKey())) {
            actOrigin.setOriginKey(newOrigin.getOriginKey());
        }

    }

    public static void updtLog(Log actLog, Log newLog) {
        if (!Objects.equals(actLog.getDescription(), newLog.getDescription())) {
            actLog.setDescription(newLog.getDescription());
        }
    }

    public static void updtEvento(Events actEvents, Events newtEvents) {
        if (actEvents.getOrigins() != newtEvents.getOrigins()) {
            actEvents.setOrigins(newtEvents.getOrigins());
        }
        if (!Objects.equals(actEvents.getDescription(), newtEvents.getDescription())) {
            actEvents.setDescription(newtEvents.getDescription());
        }
        if (actEvents.getLevel() != newtEvents.getLevel()) {
            actEvents.setLevel(newtEvents.getLevel());
        }
        if (!Objects.equals(actEvents.getQuantity(), newtEvents.getQuantity())) {
            actEvents.setOrigins(newtEvents.getOrigins());
        }
        updtLog(actEvents.getLog(), newtEvents.getLog());
    }

    public static void updtUser(User actUser, User newUser) {
        if (!Objects.equals(actUser.getEmail(), newUser.getEmail())) {
            actUser.setEmail(newUser.getEmail());
        }
        if (!Objects.equals(actUser.getPassword(), newUser.getPassword())) {
            actUser.setPassword(newUser.getPassword());
        }
        actUser.setGrants(newUser.getGrants());
    }
}
