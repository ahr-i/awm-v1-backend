package com.example.teamproject.JpaClass.UserTable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlackList is a Querydsl query type for BlackList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlackList extends EntityPathBase<BlackList> {

    private static final long serialVersionUID = -670177788L;

    public static final QBlackList blackList = new QBlackList("blackList");

    public final StringPath list = createString("list");

    public final StringPath userId = createString("userId");

    public QBlackList(String variable) {
        super(BlackList.class, forVariable(variable));
    }

    public QBlackList(Path<? extends BlackList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlackList(PathMetadata metadata) {
        super(BlackList.class, metadata);
    }

}

