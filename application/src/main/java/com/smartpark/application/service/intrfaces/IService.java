package com.smartpark.application.service.intrfaces;


import org.apache.coyote.BadRequestException;

import java.util.List;

public interface IService<Req,Resp,Id> {

    List<Resp> findAll();

    Resp save(Req req);

    Resp get(Id id);

    Resp update(Req req);

    void delete(Id id);

}
