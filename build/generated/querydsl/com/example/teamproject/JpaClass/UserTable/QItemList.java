package com.example.teamproject.JpaClass.UserTable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemList is a Querydsl query type for ItemList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemList extends EntityPathBase<ItemList> {

    private static final long serialVersionUID = 142860458L;

    public static final QItemList itemList = new QItemList("itemList");

    public final StringPath list = createString("list");

    public final StringPath userId = createString("userId");

    public QItemList(String variable) {
        super(ItemList.class, forVariable(variable));
    }

    public QItemList(Path<? extends ItemList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemList(PathMetadata metadata) {
        super(ItemList.class, metadata);
    }

}

