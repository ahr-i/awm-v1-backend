package com.example.teamproject.JpaClass.LocationTable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocationInfo is a Querydsl query type for LocationInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocationInfo extends EntityPathBase<LocationInfo> {

    private static final long serialVersionUID = 58468934L;

    public static final QLocationInfo locationInfo = new QLocationInfo("locationInfo");

    public final StringPath address = createString("address");

    public final NumberPath<Integer> infoId = createNumber("infoId", Integer.class);

    public final StringPath information = createString("information");

    public final NumberPath<Integer> locationId = createNumber("locationId", Integer.class);

    public final StringPath title = createString("title");

    public final StringPath userId = createString("userId");

    public QLocationInfo(String variable) {
        super(LocationInfo.class, forVariable(variable));
    }

    public QLocationInfo(Path<? extends LocationInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocationInfo(PathMetadata metadata) {
        super(LocationInfo.class, metadata);
    }

}

