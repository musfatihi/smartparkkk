package com.smartpark.application.service.intrfaces;


import java.util.List;

public interface IService<Req,Resp,Id> {

    List<Resp> findAll();

    Resp get(Id id);

    void update(Id id, Req req);

    void delete(Id id);

}
