package com.desafio.codenation.services;

import com.desafio.codenation.domain.logs.Log;
import com.desafio.codenation.repositories.LogRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogRepositorie logRepositorie;

    public Log getLog(Long id) {
        return logRepositorie.findById(id).orElse(null);
    }

    public Page<Log> getLogs(Pageable pageable) {
        return logRepositorie.findAll(pageable);
    }

    public Log insert(Log Log) {
        return logRepositorie.save(Log);
    }
}
