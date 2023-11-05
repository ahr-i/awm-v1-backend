package com.example.teamproject.JpaClass.LocationTable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QContributor is a Querydsl query type for Contributor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContributor extends EntityPathBase<Contributor> {

    private static final long serialVersionUID = -367478728L;

    public static final QContributor contributor = new QContributor("contributor");

    public final NumberPath<Integer> contributorId = createNumber("contributorId", Integer.class);

    public final NumberPath<Integer> locationId = createNumber("locationId", Integer.class);

    public final NumberPath<Integer> rate = createNumber("rate", Integer.class);

    public final NumberPath<Integer> registerId = createNumber("registerId", Integer.class);

    public final StringPath userId = createString("userId");

    public QContributor(String variable) {
        super(Contributor.class, forVariable(variable));
    }

    public QContributor(Path<? extends Contributor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContributor(PathMetadata metadata) {
        super(Contributor.class, metadata);
    }

}

