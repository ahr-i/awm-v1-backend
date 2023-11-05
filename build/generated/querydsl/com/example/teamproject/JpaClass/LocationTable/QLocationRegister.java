package com.example.teamproject.JpaClass.LocationTable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocationRegister is a Querydsl query type for LocationRegister
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocationRegister extends EntityPathBase<LocationRegister> {

    private static final long serialVersionUID = 94005307L;

    public static final QLocationRegister locationRegister = new QLocationRegister("locationRegister");

    public final StringPath categoty = createString("categoty");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> locationId = createNumber("locationId", Integer.class);

    public final NumberPath<Integer> registerId = createNumber("registerId", Integer.class);

    public final StringPath userId = createString("userId");

    public QLocationRegister(String variable) {
        super(LocationRegister.class, forVariable(variable));
    }

    public QLocationRegister(Path<? extends LocationRegister> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocationRegister(PathMetadata metadata) {
        super(LocationRegister.class, metadata);
    }

}

