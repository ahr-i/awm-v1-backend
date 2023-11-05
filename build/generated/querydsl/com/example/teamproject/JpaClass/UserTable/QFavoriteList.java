package com.example.teamproject.JpaClass.UserTable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFavoriteList is a Querydsl query type for FavoriteList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFavoriteList extends EntityPathBase<FavoriteList> {

    private static final long serialVersionUID = 381480499L;

    public static final QFavoriteList favoriteList = new QFavoriteList("favoriteList");

    public final StringPath list = createString("list");

    public final StringPath userId = createString("userId");

    public QFavoriteList(String variable) {
        super(FavoriteList.class, forVariable(variable));
    }

    public QFavoriteList(Path<? extends FavoriteList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFavoriteList(PathMetadata metadata) {
        super(FavoriteList.class, metadata);
    }

}

