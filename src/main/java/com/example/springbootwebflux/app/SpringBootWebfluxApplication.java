package com.example.springbootwebflux.app;

import models.dao.ProductoDao;
import models.documents.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner {

    //DAO o Repository
    @Autowired
    private ProductoDao dao;

    private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebfluxApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Flux.just(new Producto("Paquete grande detodito",12.500),
                new Producto("Gaseosa Mega 3L",6.200),
                new Producto("Sony Camara HD Digital",177.500),
                new Producto("Iphone 12",1.500000),
                new Producto("Hewlet Packard Multifuncional",322.500)
                )
                .flatMap(producto -> dao.save(producto))
                .subscribe(producto -> log.info("Insert:" + producto.getId() + "" + producto.getNombre()));
    }

}
