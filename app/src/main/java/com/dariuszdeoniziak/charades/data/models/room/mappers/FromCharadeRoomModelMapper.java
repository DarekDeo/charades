package com.dariuszdeoniziak.charades.data.models.room.mappers;

import com.dariuszdeoniziak.charades.data.models.Charade;
import com.dariuszdeoniziak.charades.data.models.room.CharadeRoomModel;
import com.dariuszdeoniziak.charades.utils.Mapper;


public class FromCharadeRoomModelMapper implements Mapper<CharadeRoomModel, Charade> {

    @Override
    public Charade map(CharadeRoomModel charadeRoomModel) {
        Charade charade = new Charade();
        charade.id = charadeRoomModel.id;
        charade.name = charadeRoomModel.name;
        charade.categoryId = charadeRoomModel.categoryId;
        return charade;
    }
}
